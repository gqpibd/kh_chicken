package client.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import client.singleton.Singleton;
import dto.MenuShowDto;

public class AddMenuView extends JFrame implements ActionListener {
	private JTextField filePathField;
	private JTextField nameField;
	private JTextField priceField;
	JLabel imgLabel;
	JButton searchBtn;
	JButton submitBtn;
	JButton cancelBtn;
	String path = "";

	public AddMenuView() {
		setTitle("메뉴 추가");
		setBounds(300, 150, 285, 436);
		getContentPane().setLayout(null);

		filePathField = new JTextField();
		filePathField.setBounds(91, 160, 83, 21);
		getContentPane().add(filePathField);
		filePathField.setColumns(10);
		filePathField.setEditable(false);

		JLabel imgFileLabel = new JLabel("이미지 파일");
		imgFileLabel.setBounds(12, 163, 83, 15);
		getContentPane().add(imgFileLabel);

		imgLabel = new JLabel();
		imgLabel.setBounds(44, 198, 188, 138);
		getContentPane().add(imgLabel);

		ButtonGroup btnGroup = new ButtonGroup();

		JRadioButton beverageRadBtn = new JRadioButton("음료");
		beverageRadBtn.setHorizontalAlignment(SwingConstants.CENTER);
		beverageRadBtn.setBounds(151, 59, 83, 23);
		btnGroup.add(beverageRadBtn);
		getContentPane().add(beverageRadBtn);
		beverageRadBtn.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (beverageRadBtn.isSelected()) {
					searchBtn.setVisible(false);
					filePathField.setVisible(false);
					imgLabel.setVisible(false);
					imgFileLabel.setVisible(false);
				} else {
					searchBtn.setVisible(true);
					filePathField.setVisible(true);
					imgLabel.setVisible(true);
					imgFileLabel.setVisible(true);
				}

			}
		});

		JRadioButton chickenRadBtn = new JRadioButton("치킨");
		chickenRadBtn.setHorizontalAlignment(SwingConstants.CENTER);
		chickenRadBtn.setBounds(34, 59, 83, 23);
		chickenRadBtn.setSelected(true);
		btnGroup.add(chickenRadBtn);
		getContentPane().add(chickenRadBtn);

		JLabel titleLabel = new JLabel("메뉴 추가");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("돋움체", Font.BOLD, 20));
		titleLabel.setBounds(0, 10, 269, 30);
		getContentPane().add(titleLabel);

		searchBtn = new JButton("검색");
		searchBtn.addActionListener(this);
		searchBtn.setBounds(186, 159, 65, 23);
		getContentPane().add(searchBtn);

		nameField = new JTextField();
		nameField.setBounds(91, 100, 103, 21);
		getContentPane().add(nameField);
		nameField.setColumns(10);

		JLabel label = new JLabel("메뉴 이름");
		label.setBounds(12, 103, 57, 15);
		getContentPane().add(label);

		JLabel priceLabel = new JLabel("가격");
		priceLabel.setBounds(12, 134, 57, 15);
		getContentPane().add(priceLabel);

		priceField = new JTextField();
		priceField.setBounds(91, 131, 103, 21);
		getContentPane().add(priceField);
		priceField.setColumns(10);

		cancelBtn = new JButton("취소");
		cancelBtn.setBounds(161, 346, 83, 34);
		getContentPane().add(cancelBtn);
		cancelBtn.addActionListener(this);

		submitBtn = new JButton("완료");
		submitBtn.setBounds(33, 346, 83, 34);
		getContentPane().add(submitBtn);
		submitBtn.addActionListener(this);

		JLabel wonLabel = new JLabel("원");
		wonLabel.setBounds(206, 134, 57, 15);
		getContentPane().add(wonLabel);

		setVisible(true);
	}

	public String jFileChooserUtil() {

		String folderPath = "";

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
		chooser.setCurrentDirectory(new File("d:/images")); // 현재 사용 디렉토리를 지정
		chooser.setAcceptAllFileFilterUsed(true); // Fileter 모든 파일 적용
		chooser.setDialogTitle("파일 위치 검색"); // 창의 제목
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드

		FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지파일(.jpg)", "jpg"); // filter 확장자 추가
		chooser.setFileFilter(filter); // 파일 필터를 추가

		int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈

		if (returnVal == JFileChooser.APPROVE_OPTION) { // 열기를 클릭
			folderPath = chooser.getSelectedFile().toString();
		} else if (returnVal == JFileChooser.CANCEL_OPTION) { // 취소를 클릭
			System.out.println("cancel");
			folderPath = "";
		}

		ImageIcon icon = new ImageIcon(folderPath);
		JLabel imgLabel = new JLabel(icon);
		getContentPane().add(imgLabel);

		return folderPath;

	}

	public void setImage(String path) {
		try {
			BufferedImage m_numberImage = ImageIO.read(new File(path));
			ImageIcon icon = new ImageIcon(m_numberImage);

			// ImageIcon에서 Image를 추출
			Image originImg = icon.getImage();

			// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
			Image changedImg = originImg.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(),
					Image.SCALE_SMOOTH);

			// 새로운 Image로 ImageIcon객체를 생성
			ImageIcon resizedIcon = new ImageIcon(changedImg);

			imgLabel.setIcon(resizedIcon);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn) { // 이미지 검색 수행
			path = jFileChooserUtil();
			filePathField.setText(path.substring(path.lastIndexOf("\\") + 1)); // 전체 경로에서 파일 이름과 확장자명만 가져온다.
			setImage(path);
		} else if (e.getSource() == submitBtn) { // 새 메뉴를 추가한다
			MenuShowDto dto = new MenuShowDto(nameField.getText(), Integer.parseInt(priceField.getText()));
			System.out.println(dto.toString());
			Singleton s = Singleton.getInstance();
			s.getMenuCtrl().insert(dto, path);
			dispose();
		} else if (e.getSource() == cancelBtn) {
			dispose();
		}

	}

}
