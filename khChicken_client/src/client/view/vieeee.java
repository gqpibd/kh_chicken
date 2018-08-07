package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class vieeee extends JFrame {

	private JPanel contentPane;
	private JTextField price;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vieeee frame = new vieeee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vieeee() {
		
		JButton btn_Login;
		JButton btn_Register;
		JButton btn_Order;
		JButton btn_Manage;
		JButton btn_good;
		JLabel label_goodCount;
		JPanel pn_menu;
		
		//버튼설정 
				btn_Login = new JButton("로그인");
				//btn_Login.setBounds();
				
				btn_Register = new JButton("회원가입");
				//btn_Register.setBounds();
				
				btn_Order = new JButton("주문하기");
				//btn_Order.setBounds();
				
				btn_Manage = new JButton("관리");
				//btn_Order.setBounds();
				btn_Manage.setVisible(false);	//상필 관리자 로그인에서 true 받아야함
				
				
				//바탕에 저장
				getContentPane().add(btn_Login);
				getContentPane().add(btn_Register);
				getContentPane().add(btn_Order);
				getContentPane().add(btn_Manage);
				
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
			//panel_top
		JPanel panel_Top = new JPanel();
		panel_Top.setBounds(0, 0, 584, 78);
		panel_Top.setLayout(null);
		
		
		//panel _ middle
		JPanel panel_Middle = new JPanel();
		panel_Middle.setBounds(0, 77, 584, 586);
		panel_Middle.setLayout(null);
		
		
		//middle 이미지 
		JLabel label_img1 = new JLabel("image1");
		label_img1.setBounds(42, 30, 230, 170); 
		label_img1.setBackground(Color.BLUE);
		panel_Middle.add(label_img1);

		price = new JTextField();
		price.setText("img1가격");
		price.setBounds(104, 220, 116, 21);
		panel_Middle.add(price);
		price.setColumns(10);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setBounds(75, 218, 21, 23);
		panel_Middle.add(checkBox);
	
		JLabel label_img2 = new JLabel("image2");
		label_img2.setBounds(312, 30, 230, 170);
		panel_Middle.add(label_img2);
		
		
		JCheckBox checkBox_img2 = new JCheckBox("");
		checkBox_img2.setBounds(347, 218, 21, 23);
		panel_Middle.add(checkBox_img2);
		
		JLabel label_img3 = new JLabel("image3");
		label_img3.setBounds(42, 298, 230, 170);
		panel_Middle.add(label_img3);
		
		textField = new JTextField("img2가격");
		textField.setBounds(376, 220, 116, 21);
		panel_Middle.add(textField);
		textField.setColumns(10);
		
		JCheckBox checkBox_img3 = new JCheckBox("");
		checkBox_img3.setBounds(75, 481, 21, 23);
		panel_Middle.add(checkBox_img3);
		
		textField_1 = new JTextField("img3가격");
		textField_1.setBounds(104, 483, 116, 21);
		panel_Middle.add(textField_1);
		textField_1.setColumns(10);
		
		JCheckBox checkBox_img4 = new JCheckBox("");
		checkBox_img4.setBounds(347, 481, 21, 23);
		panel_Middle.add(checkBox_img4);
		
		textField_2 = new JTextField("img4가격");
		textField_2.setBounds(376, 483, 116, 21);
		panel_Middle.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_img4 = new JLabel("image4");
		label_img4.setBounds(312, 298, 230, 170);
		panel_Middle.add(label_img4);
		contentPane.add(panel_Top);
		
		JButton btnNewButton_1 = new JButton("로그인");
		btnNewButton_1.setBounds(370, 45, 97, 23);
		panel_Top.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("회원가입");
		btnNewButton.setBounds(479, 45, 97, 23);
		panel_Top.add(btnNewButton);
		
		JPanel panel_Down = new JPanel();
		panel_Down.setBounds(0, 662, 584, 89);
		panel_Down.setLayout(null);
		
		
		
	
		
		contentPane.add(panel_Down);
		
		JButton btnNewButton_2 = new JButton("주문하기");
		btnNewButton_2.setBounds(475, 24, 97, 55);
		panel_Down.add(btnNewButton_2);
		
		JButton button = new JButton("관리");
		button.setBounds(394, 45, 66, 34);
		panel_Down.add(button);
		contentPane.add(panel_Middle);
		

		
	}
}
