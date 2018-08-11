package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.singleton.Singleton;
import dto.MemberDto;

public class Window_Login extends JFrame implements ActionListener {
	JTextField JTextF_ID;
	JTextField JTextF_PW;

	JButton Jbut_login;
	JButton Jbut_Account;
	JButton Jbut_Exit;

	public Window_Login() {
		super("로그인");
		setLayout(null);
		JPanel Jpanl_JTextF = new JPanel();
		Jpanl_JTextF.setBounds(99, 56, 132, 66);

		JTextF_ID = new JTextField(10);
		Jpanl_JTextF.add(JTextF_ID);
		JTextF_PW = new JTextField(10);
		Jpanl_JTextF.add(JTextF_PW);
		add(Jpanl_JTextF);

		JLabel JLabel_ID = new JLabel("아이디");
		JLabel_ID.setBounds(37, 56, 42, 27);
		add(JLabel_ID);

		JLabel JLabel_PW = new JLabel("비밀번호");
		JLabel_PW.setBounds(37, 95, 62, 18);
		add(JLabel_PW);

		Jbut_Account = new JButton("회원가입");
		Jbut_Account.addActionListener(this);
		Jbut_Account.setBounds(74, 134, 89, 27);
		add(Jbut_Account);

		Jbut_login = new JButton("로그인");
		Jbut_login.addActionListener(this);
		Jbut_login.setBounds(233, 56, 75, 66);
		add(Jbut_login);

		Jbut_Exit = new JButton("취소");
		Jbut_Exit.addActionListener(this);
		Jbut_Exit.setBounds(190, 134, 89, 27);
		add(Jbut_Exit);

		setBounds(100, 100, 372, 239);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		Singleton s = Singleton.getInstance();
		String id = JTextF_ID.getText();
		String pw = JTextF_PW.getText();
		MemberDto dto = new MemberDto();
		if (obj == Jbut_login) {

			if (id.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
			} else if (pw.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
			} else if (!id.equals("") && !pw.equals("")) {
				dto.setId(id);
				dto.setPw(pw);
				boolean login = s.getMemCtrl().select_login(dto);
				if (login == true) {
					s.getMemCtrl().backToMain(this);
				} else if (login == false) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀리셨습니다.");
				}
			}
		} else if (obj == Jbut_Account) {
			s.getMemCtrl().AccountView(this);
		} else if (obj == Jbut_Exit) {
			s.getMemCtrl().backToMain(this);
		}
	}

}
