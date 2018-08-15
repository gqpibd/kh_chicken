package client.view.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import client.singleton.Singleton;
import client.view.MainView;
import dto.MenuShowDto;
import utils.images.ImageUtils;

public class AddMenuView extends JFrame implements ActionListener {
	private JTextField filePathField;
	private JTextField nameField;
	private JTextField priceField;
	private JTextArea description;
	private JLabel imgLabel;
	private JButton searchBtn;
	private JButton submitBtn;
	private JButton cancelBtn;
	private ButtonGroup btnGroup;
	private String path = "";
	
	private static final String PATH = "images/manageView/";

	public AddMenuView() {
		setTitle("메뉴 추가");
		
		setContentPane(new JLabel(new ImageIcon(PATH + "addMenuView.jpg")));
		setResizable(false);
		
		setBounds(300, 150, 482, 439);
		getContentPane().setLayout(null);

		priceField = new JTextField();
		priceField.setBounds(328, 100, 86, 21);
		getContentPane().add(priceField);
		priceField.setColumns(10);

		filePathField = new JTextField();
		filePathField.setBounds(85, 132, 102, 21);
		getContentPane().add(filePathField);
		filePathField.setColumns(10);
		filePathField.setEditable(false);

		JLabel imgFileLabel = new JLabel("이미지");
		imgFileLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		imgFileLabel.setBounds(12, 135, 83, 15);
		getContentPane().add(imgFileLabel);

		imgLabel = new JLabel();
		imgLabel.setBounds(12, 170, 244, 167);
		getContentPane().add(imgLabel);

		btnGroup = new ButtonGroup();

		JRadioButton mainRadBtn = new JRadioButton("메인");
		mainRadBtn.setBackground(Color.WHITE);
		mainRadBtn.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		mainRadBtn.setHorizontalAlignment(SwingConstants.CENTER);
		mainRadBtn.setBounds(54, 59, 83, 23);
		mainRadBtn.setSelected(true);
		btnGroup.add(mainRadBtn);
		getContentPane().add(mainRadBtn);

		JRadioButton sideRadBtn = new JRadioButton("사이드");
		sideRadBtn.setBackground(Color.WHITE);
		sideRadBtn.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		sideRadBtn.setHorizontalAlignment(SwingConstants.CENTER);
		sideRadBtn.setBounds(191, 59, 83, 23);
		btnGroup.add(sideRadBtn);
		getContentPane().add(sideRadBtn);

		JRadioButton beverageRadBtn = new JRadioButton("음료");
		beverageRadBtn.setBackground(Color.WHITE);
		beverageRadBtn.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		beverageRadBtn.setHorizontalAlignment(SwingConstants.CENTER);
		beverageRadBtn.setBounds(328, 59, 83, 23);
		btnGroup.add(beverageRadBtn);
		getContentPane().add(beverageRadBtn);

		/*JLabel titleLabel = new JLabel("메뉴 추가");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("돋움체", Font.BOLD, 20));
		titleLabel.setBounds(0, 10, 466, 30);
		getContentPane().add(titleLabel);
*/
		searchBtn = new JButton(new ImageIcon(PATH + "MenuSearchBtn.jpg"));
		searchBtn.addActionListener(this);
		searchBtn.setBounds(199, 131, 75, 23);
		getContentPane().add(searchBtn);

		nameField = new JTextField();
		nameField.setBounds(85, 100, 103, 21);
		getContentPane().add(nameField);
		nameField.setColumns(10);

		JLabel label = new JLabel("메뉴이름");
		label.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		label.setBounds(14, 103, 57, 15);
		getContentPane().add(label);

		JLabel priceLabel = new JLabel("가격");
		priceLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		priceLabel.setBounds(288, 103, 46, 15);
		getContentPane().add(priceLabel);

		cancelBtn = new JButton(new ImageIcon(PATH + "addCancelBtn.jpg"));
		cancelBtn.setBounds(278, 354, 83, 34);
		getContentPane().add(cancelBtn);
		cancelBtn.addActionListener(this);
		
		submitBtn = new JButton(new ImageIcon(PATH + "completeBtn.jpg"));
		submitBtn.setBounds(136, 354, 83, 34);
		getContentPane().add(submitBtn);
		submitBtn.addActionListener(this);

		JLabel wonLabel = new JLabel("원");
		wonLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		wonLabel.setBounds(428, 103, 38, 15);
		getContentPane().add(wonLabel);

		JLabel label_1 = new JLabel("제품 설명");
		label_1.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		label_1.setBounds(290, 131, 57, 15);
		getContentPane().add(label_1);

		description = new JTextArea();
		description.setLineWrap(true);

		JScrollPane scrollPane = new JScrollPane(description);
		scrollPane.setBounds(288, 164, 150, 173);
		getContentPane().add(scrollPane);

		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		if (e.getSource() == searchBtn) { // 이미지 검색 수행
			path = ImageUtils.jFileChooserUtil();
			if (path.length() != 0) {
				filePathField.setText(path.substring(path.lastIndexOf("\\") + 1)); // 전체 경로에서 파일 이름과 확장자명만 가져온다.
				ImageUtils.setResizedImage(imgLabel, path);
			}
		} else if (e.getSource() == submitBtn) { // 새 메뉴를 추가한다
			String type = getSelectedRadioBtn(btnGroup).getText();
			MenuShowDto dto = new MenuShowDto(nameField.getText(), Integer.parseInt(priceField.getText()), type,
					description.getText(), 10);
			System.out.println(dto.toString());
			s.getMenuCtrl().insert(dto, path);
			s.getMenuCtrl().menuManageView(this);
			s.resetMainView(); // 추가된 메뉴가 메인에서 보이도록 해준다.
		} else if (e.getSource() == cancelBtn) {
			s.getMenuCtrl().menuManageView(this);
		}

	}

	private JRadioButton getSelectedRadioBtn(ButtonGroup bGroup) {
		Enumeration<AbstractButton> enums = bGroup.getElements(); // 요소의 수를 저장한다.
		JRadioButton jb = null;
		while (enums.hasMoreElements()) { // hasMoreElements() 다음 요소이 있는지 조사 (boolean)
			AbstractButton ab = enums.nextElement(); // 다음 요소를 받아온다.
			jb = (JRadioButton) ab; // 그 요소를 변환해주고
			if (jb.isSelected()) { // 선택되었는지 확인한다.
				break;
			}
		}
		return jb;
	}
}
