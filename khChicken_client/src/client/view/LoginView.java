package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.singleton.Singleton;
import dto.MemberDto;

public class LoginView extends JFrame implements ActionListener {
	JTextField JTextF_ID;
	JPasswordField JTextF_PW;

	JButton Jbut_login;
	JButton Jbut_Account;
	JButton Jbut_Exit;
	private static final String PATH = "images/loginView/";


	public LoginView() {
		super("로그인");
		setContentPane(new JLabel(new ImageIcon(PATH + "loginView.jpg")));
		setResizable(false);
		getContentPane().setLayout(null);
		setResizable(false);
		
		JPanel Jpanl_JTextF = new JPanel();
		Jpanl_JTextF.setBackground(Color.WHITE);
		Jpanl_JTextF.setBounds(105, 56, 132, 66);

		JTextF_ID = new JTextField(10);
		Jpanl_JTextF.add(JTextF_ID);
		
		JTextF_PW = new JPasswordField(10);
		JTextF_PW.addActionListener(this);
		Jpanl_JTextF.add(JTextF_PW);
		
		getContentPane().add(Jpanl_JTextF);

		JLabel JLabel_ID = new JLabel("아이디");
		JLabel_ID.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		JLabel_ID.setBounds(36, 56, 42, 27);
		getContentPane().add(JLabel_ID);

		JLabel JLabel_PW = new JLabel("비밀번호");
		JLabel_PW.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		JLabel_PW.setBounds(36, 88, 62, 18);
		getContentPane().add(JLabel_PW);

		Jbut_Account = new JButton(new ImageIcon(PATH + "signInBtn.jpg"));
		Jbut_Account.addActionListener(this);
		Jbut_Account.setBounds(80, 134, 89, 27);
		getContentPane().add(Jbut_Account);

		Jbut_login = new JButton(new ImageIcon(PATH + "logInBtn.jpg"));
		Jbut_login.addActionListener(this);
		Jbut_login.setBounds(239, 56, 75, 66);
		getContentPane().add(Jbut_login);

		Jbut_Exit = new JButton(new ImageIcon(PATH + "returnLoginBtn.jpg"));
		Jbut_Exit.addActionListener(this);
		Jbut_Exit.setBounds(196, 134, 89, 27);
		getContentPane().add(Jbut_Exit);
		
		setBackground(Color.WHITE);
		setBounds(100, 100, 372, 239);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		Singleton s = Singleton.getInstance();
		String id = JTextF_ID.getText();
		String pw = new String(JTextF_PW.getPassword());
		MemberDto dto = new MemberDto();
		if (obj == Jbut_login || obj == JTextF_PW) {

			if (id.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
			} else if (pw.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
			} else if (!id.equals("") && !pw.equals("")) {
				dto.setId(id);
				dto.setPw(pw);
				boolean login = s.getMemCtrl().select_login(dto);
				if (login == true) {
					s.backToMain(this);
					s.getMainView().login();
				} else if (login == false) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀리셨습니다.");
				}
			}
		} else if (obj == Jbut_Account) {
			s.getMemCtrl().accountView(this);
		} else if (obj == Jbut_Exit) {
			s.backToMain(this);
		}
	}

}
