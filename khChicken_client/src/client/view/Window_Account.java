package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import dto.MemberDto;
import client.singleton.Singleton;

public class Window_Account extends JFrame implements ActionListener {

	JTextField JtextF_Id;
	JTextField JtextF_pwd;
	JTextField JtextF_pwd_Check;
	JTextField JtextF_name;
	JTextField JtextF_address;
	JTextField JtextF_phone;

	JButton Jbut_Check;
	JButton Jbut_Join;
	JButton Jbut_Back;

	String Str_ID_Check = "";

	public Window_Account() {

		setTitle("회원가입창");
		setLayout(null);

		JtextF_Id = new JTextField(10);
		JtextF_Id.setText("아이디"); // DB쪽 벨류값때문에 아이디 너무길게 입력하면 값이 안들어감.
		JtextF_Id.setBounds(65, 53, 116, 21);

		JtextF_pwd = new JTextField(10);
		JtextF_pwd.setText("비밀번호");
		JtextF_pwd.setBounds(64, 84, 116, 21);

		JtextF_pwd_Check = new JTextField(10); // 아직 연동안되서 안되니깐 무시하고 아무거나 입력
		JtextF_pwd_Check.setText("비밀번호확인");
		JtextF_pwd_Check.setBounds(64, 115, 116, 21);

		JtextF_name = new JTextField(10);
		JtextF_name.setText("닉네임");
		JtextF_name.setBounds(64, 146, 116, 21);

		JtextF_address = new JTextField(10);
		JtextF_address.setText("주소");
		JtextF_address.setBounds(65, 177, 116, 21);

		JtextF_phone = new JTextField(10);
		JtextF_phone.setText("핸드폰번호");
		JtextF_phone.setBounds(65, 208, 116, 21);

		Jbut_Join = new JButton("중복확인");
		Jbut_Join.addActionListener(this);
		Jbut_Join.setBounds(193, 52, 41, 23);

		Jbut_Check = new JButton("회원가입");
		Jbut_Check.addActionListener(this);
		Jbut_Check.setBounds(85, 239, 84, 32);

		Jbut_Back = new JButton("이전으로");
		Jbut_Back.addActionListener(this);
		Jbut_Back.setBounds(12, 10, 58, 23);

		add(JtextF_Id);
		add(JtextF_pwd);
		add(JtextF_pwd_Check);
		add(JtextF_name);
		add(JtextF_address);
		add(JtextF_phone);
		add(Jbut_Join);
		add(Jbut_Check);
		add(Jbut_Back);

		setBounds(100, 100, 267, 362);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton single = Singleton.getInstance();

		// 보기쉽게 텍스트에있는거 전부다 스트린으로 옮김
		String id = JtextF_Id.getText();
		String pw = JtextF_pwd.getText();
		String name = JtextF_name.getText();
		String address = JtextF_address.getText();
		String phone = JtextF_phone.getText();
		System.out.println(id + "뷰");

		if (obj == Jbut_Join) {
			if (id.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입려해주세요.");
			} else {
				MemberDto dto = new MemberDto();
				dto.setId(id);
				boolean existingId = single.getMemCtrl().select(dto);
				if (existingId == false) {
					Str_ID_Check = id;
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
				} else if (existingId == true) {
					JOptionPane.showMessageDialog(null, "이미 있는 아이디입니다.");
				}
			}
			// 밑에껀 무시 아직 준비중
		} else if (obj == Jbut_Check) {

			if (!Str_ID_Check.equals(id) && Str_ID_Check.equals("")) {
				JOptionPane.showMessageDialog(null, "중복확인을 눌러주세요.");
			} else if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "닉네임을 입력해주세요");
			} else if (address.equals("")) {
				JOptionPane.showMessageDialog(null, "주소를 입력해주세요");
			} else if (phone.equals("")) {
				JOptionPane.showMessageDialog(null, "핸드폰 번호를 입력해주세요.");
			} else {
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				JOptionPane.showMessageDialog(null, "로그인 해주세요.");
				MemberDto dto = new MemberDto(id, pw, name, 0, 0, address, phone);
				single.getMemCtrl().insert(dto);
				single.backToMain(this);
				// single.Win_Loging = new Window_Login();
			}

		} else if (obj == Jbut_Back) {
			single.getMemCtrl().loginView(this);
			// single.Win_Loging = new Window_Login();
		}
	}
}