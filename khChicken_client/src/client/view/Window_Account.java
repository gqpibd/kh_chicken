package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import dto.MemberDto;
import client.singleton.Singleton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Window_Account extends JFrame implements ActionListener {

	JTextField JtextF_Id;
	JPasswordField JtextF_pwd;
	JPasswordField JtextF_pwd_Check;
	JTextField JtextF_name;
	JTextField JtextF_address;
	JTextField JtextF_phone;

	JButton Jbut_Check;
	JButton Jbut_Join;
	JButton Jbut_Back;

	String Str_ID_Check = "";
	private JLabel JLabel_id;
	private JLabel JLabel_pw;
	private JLabel JLabel_pw2;
	private JLabel nameLabel;
	private JLabel JLabel_add;
	private JLabel JLabel_phone;

	public Window_Account() {

		setTitle("회원가입창");
		getContentPane().setLayout(null);

		JtextF_Id = new JTextField(10);
		JtextF_Id.setBounds(113, 53, 116, 21);

		JtextF_pwd = new JPasswordField(10);
		JtextF_pwd.setBounds(112, 84, 116, 21);

		JtextF_pwd_Check = new JPasswordField(10);
		JtextF_pwd_Check.setBounds(112, 115, 116, 21);

		JtextF_name = new JTextField(10);
		JtextF_name.setBounds(112, 146, 116, 21);

		JtextF_address = new JTextField(10);
		JtextF_address.setBounds(113, 177, 212, 21);

		JtextF_phone = new JTextField(10);
		JtextF_phone.setBounds(113, 208, 146, 21);

		Jbut_Join = new JButton("중복확인");
		Jbut_Join.addActionListener(this);
		Jbut_Join.setBounds(241, 53, 96, 23);

		Jbut_Check = new JButton("회원가입");
		Jbut_Check.addActionListener(this);
		Jbut_Check.setBounds(241, 253, 101, 32);

		Jbut_Back = new JButton("메인으로");
		Jbut_Back.addActionListener(this);
		Jbut_Back.setBounds(12, 10, 101, 23);

		getContentPane().add(JtextF_Id);
		getContentPane().add(JtextF_pwd);
		getContentPane().add(JtextF_pwd_Check);
		getContentPane().add(JtextF_name);
		getContentPane().add(JtextF_address);
		getContentPane().add(JtextF_phone);
		getContentPane().add(Jbut_Join);
		getContentPane().add(Jbut_Check);
		getContentPane().add(Jbut_Back);

		JLabel_id = new JLabel("아이디");
		JLabel_id.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_id.setBounds(12, 57, 89, 15);
		getContentPane().add(JLabel_id);

		JLabel_pw = new JLabel("비밀번호");
		JLabel_pw.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_pw.setBounds(12, 88, 89, 15);
		getContentPane().add(JLabel_pw);

		JLabel_pw2 = new JLabel("비밀번호 확인");
		JLabel_pw2.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_pw2.setBounds(12, 122, 89, 15);
		getContentPane().add(JLabel_pw2);

		nameLabel = new JLabel("이름");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(12, 150, 89, 15);
		getContentPane().add(nameLabel);

		JLabel_add = new JLabel("주소");
		JLabel_add.setLabelFor(JLabel_add);
		JLabel_add.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_add.setBounds(12, 181, 89, 15);
		getContentPane().add(JLabel_add);

		JLabel_phone = new JLabel("핸드폰번호");
		JLabel_phone.setLabelFor(JtextF_phone);
		JLabel_phone.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_phone.setBounds(12, 212, 89, 15);
		getContentPane().add(JLabel_phone);

		setBounds(100, 100, 365, 339);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton single = Singleton.getInstance();

		// 보기쉽게 텍스트에있는거 전부다 스트린으로 옮김
		String id = JtextF_Id.getText();
		String pw = new String(JtextF_pwd.getPassword());
		String pw_Check = new String(JtextF_pwd_Check.getPassword());
		String name = JtextF_name.getText();
		String address = JtextF_address.getText();
		String phone = JtextF_phone.getText();
		System.out.println(id + "뷰");

		if (obj == Jbut_Join) {
			if (id.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
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
			System.out.println(pw);
			System.out.println(pw_Check);
			
			
			if((!Str_ID_Check.equals(id)) || (id.equals(""))) {
				JOptionPane.showMessageDialog(null,"중복확인을 눌러주세요.");
			}else if(pw.equals("")){
				JOptionPane.showMessageDialog(null,"패스워드를 입력해주세요.");
			}else if(pw_Check.equals("")) {
				JOptionPane.showMessageDialog(null,"패스워드 확인을  입력해주세요.");
			}else if(name.equals("")) {
				JOptionPane.showMessageDialog(null,"닉네임을 입력해주세요");
			}else if(address.equals("")) {
				JOptionPane.showMessageDialog(null,"주소를 입력해주세요");
			}else if(phone.equals("")) {
				JOptionPane.showMessageDialog(null,"핸드폰 번호를 입력해주세요.");
			}else if(!pw.equals(pw_Check)) {	
				JOptionPane.showMessageDialog(null,"비밀번호와 비밀번호 확인이 맞지 않습니다.");
			}else {
				JOptionPane.showMessageDialog(null,"회원가입이 완료되었습니다.");
				JOptionPane.showMessageDialog(null,"로그인 해주세요.");
				MemberDto dto = new MemberDto(id, pw, name, 0, 0, address, phone);
				single.getMemCtrl().insert(dto);
				dispose();
<<<<<<< HEAD
				single.getMemCtrl().loginView(this);
			}

=======
				single.getMemCtrl().loginView(this,3);
			}

		
>>>>>>> branch 'yun_2' of https://github.com/gqpibd/kh_semi
		} else if (obj == Jbut_Back) {
			Singleton.getInstance().backToMain(this);
		}
	}
}