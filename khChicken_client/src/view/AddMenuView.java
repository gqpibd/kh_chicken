package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;

public class AddMenuView extends JFrame {
	private JTextField filePathTField;
	private JTextField nameField;
	private JTextField textField;

	public AddMenuView() {
		setTitle("메뉴 추가");
		setBounds(0,0,346,400);
		getContentPane().setLayout(null);
		
		filePathTField = new JTextField();
		filePathTField.setBounds(91, 215, 148, 21);
		getContentPane().add(filePathTField);
		filePathTField.setColumns(10);
		
		JLabel imgFileLabel = new JLabel("이미지 파일");
		imgFileLabel.setBounds(12, 218, 83, 15);
		getContentPane().add(imgFileLabel);
		
		JRadioButton BeverageRadBtn = new JRadioButton("음료");
		BeverageRadBtn.setBounds(12, 59, 83, 23);
		getContentPane().add(BeverageRadBtn);
		
		JRadioButton ChickenRadBtn = new JRadioButton("치킨");
		ChickenRadBtn.setBounds(118, 59, 121, 23);
		getContentPane().add(ChickenRadBtn);
		
		JLabel titleLabel = new JLabel("메뉴 추가");
		titleLabel.setFont(new Font("돋움체", Font.PLAIN, 16));
		titleLabel.setBounds(12, 24, 222, 15);
		getContentPane().add(titleLabel);
		
		
		
		//
		JLabel imgLabel = new JLabel();

		imgLabel.setBounds(12, 246, 180, 105);
		imgLabel.setVisible(true);
		imgLabel.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		getContentPane().add(imgLabel);
		JButton btnNewButton = new JButton("검색");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = jFileChooserUtil();
				filePathTField.setText(path);

				try {
					BufferedImage m_numberImage = ImageIO.read(new File(path));
					
					//path.replaceAll("\\", "/");
					System.out.println(path);
					ImageIcon icon = new ImageIcon(m_numberImage);
					
					
					//ImageIcon에서 Image를 추출

					Image originImg = icon.getImage(); 

					//추출된 Image의 크기를 조절하여 새로운 Image객체 생성

					Image changedImg= originImg.getScaledInstance(180, 100, Image.SCALE_SMOOTH );

					//새로운 Image로 ImageIcon객체를 생성

					ImageIcon Icon = new ImageIcon(changedImg);					
					
					imgLabel.setIcon(Icon);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
				
				
				
			}
		});
		btnNewButton.setBounds(253, 214, 65, 23);
		getContentPane().add(btnNewButton);
		
		nameField = new JTextField();
		nameField.setBounds(91, 100, 148, 21);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel label = new JLabel("메뉴 이름");
		label.setBounds(12, 103, 57, 15);
		getContentPane().add(label);
		
		JLabel priceLabel = new JLabel("가격");
		priceLabel.setBounds(12, 157, 57, 15);
		getContentPane().add(priceLabel);
		
		textField = new JTextField();
		textField.setBounds(91, 154, 148, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.setBounds(221, 317, 97, 34);
		getContentPane().add(cancelBtn);
		
		JButton submitBtn = new JButton("완료");
		submitBtn.setBounds(221, 264, 97, 34);
		getContentPane().add(submitBtn);
		
		
	
		setVisible(true);
	}
	
	public String jFileChooserUtil() {

		String folderPath = "";

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
		chooser.setCurrentDirectory(new File("d:/images")); // 현재 사용 디렉토리를 지정
		chooser.setAcceptAllFileFilterUsed(true); // Fileter 모든 파일 적용
		chooser.setDialogTitle("파일 위치 검색"); // 창의 제목
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드

		//FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary File", "cd11"); // filter 확장자 추가
		//chooser.setFileFilter(filter); // 파일 필터를 추가

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
}
