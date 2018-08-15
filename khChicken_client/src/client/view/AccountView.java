package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.singleton.Singleton;
import dto.MemberDto;

public class AccountView extends JFrame implements ActionListener {

	private JTextField JtextF_Id; // 아이디
	private JPasswordField JtextF_pwd; // 비밀번호
	private JPasswordField JtextF_pwd_Check; // 비밀번호 확인
	private JTextField JtextF_name; // 이름
	private JTextField JtextF_address; // 주소
	private JTextField JtextF_address2; // 상세 주소
	private JTextField JtextF_phone; // 전화번호

	private JButton Jbut_Check; // 중복확인
	private JButton Jbut_Join; // 가입 
	private JButton Jbut_Back; // 뒤로가기
	private JButton JBut_Search; // 검색(주소)

	private String Str_ID_Check = "";
	private static final String PATH = "images/mainView/";
	public AccountView() {
		setInitView();
	}

	public void setInitView() {
		JLabel JLabel_id;
		JLabel JLabel_pw;
		JLabel JLabel_pw2;
		JLabel nameLabel;
		JLabel JLabel_add;
		JLabel JLabel_phone;

		setTitle("회원가입창");
		getContentPane().setLayout(null);

		JtextF_Id = new JTextField(10);
		JtextF_Id.setBounds(113, 53, 134, 21);

		JtextF_pwd = new JPasswordField(10);
		JtextF_pwd.setBounds(112, 84, 135, 21);

		JtextF_pwd_Check = new JPasswordField(10);
		JtextF_pwd_Check.setBounds(112, 115, 135, 21);

		JtextF_name = new JTextField(10);
		JtextF_name.setBounds(112, 146, 135, 21);

		JtextF_address = new JTextField(10);
		JtextF_address.setEditable(false);
		JtextF_address.setBounds(113, 177, 236, 21);

		JtextF_phone = new JTextField(10);
		JtextF_phone.setBounds(113, 241, 134, 21);

		Jbut_Join = new JButton("중복확인");
		Jbut_Join.addActionListener(this);
		Jbut_Join.setBounds(253, 52, 96, 23);

		Jbut_Check = new JButton("회원가입");
		Jbut_Check.addActionListener(this);
		Jbut_Check.setBounds(248, 279, 101, 32);

		Jbut_Back = new JButton("메인으로");
		Jbut_Back.addActionListener(this);
		Jbut_Back.setBounds(12, 10, 101, 23);

		getContentPane().add(JtextF_Id);
		getContentPane().add(JtextF_pwd);
		getContentPane().add(JtextF_pwd_Check);
		getContentPane().add(JtextF_name);
		getContentPane().add(JtextF_address);

		JtextF_address2 = new JTextField(10);
		JtextF_address2.setBounds(113, 210, 158, 21);
		getContentPane().add(JtextF_address2);
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
		JLabel_phone.setBounds(12, 244, 89, 15);
		getContentPane().add(JLabel_phone);

		JBut_Search = new JButton("검색");
		JBut_Search.setBounds(283, 209, 66, 23);
		JBut_Search.addActionListener(this);
		getContentPane().add(JBut_Search);

		setBounds(100, 100, 381, 360);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton single = Singleton.getInstance();

		// 보기쉽게 텍스트에있는거 전부다 스트린으로 옮김
		String id = JtextF_Id.getText().trim();
		String pw = new String(JtextF_pwd.getPassword()).trim();
		String name = JtextF_name.getText().trim();
		String address = JtextF_address.getText().trim();
		String address2 = JtextF_address2.getText().trim();
		String phone = JtextF_phone.getText().trim();

		if (obj == Jbut_Join) { // 아이디 중복 확인
			if (id.equals("")) { // 아이디를 입력하지 않은 경우
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

		} else if (obj == Jbut_Check) { // 회원가입

			if (!Str_ID_Check.equals(id) && Str_ID_Check.equals("")) {
				JOptionPane.showMessageDialog(null, "중복확인을 눌러주세요.");
			} else if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "닉네임을 입력해주세요");
			} else if (address.equals("") || address2.equals("")) {
				JOptionPane.showMessageDialog(null, "주소를 입력해주세요");
			} else if (phone.equals("")) {
				JOptionPane.showMessageDialog(null, "핸드폰 번호를 입력해주세요");
			} else if (!pw.equals(new String(JtextF_pwd_Check.getPassword()))) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 확인해 주세요");
			} else if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone)) {
				JOptionPane.showMessageDialog(null, "전화번호 형식이 맞지 않습니다.\n 01X-XXXX-XXXX 형태로 입력해 주세요");
			} else {
				MemberDto dto = new MemberDto(id, pw, name, 0, MemberDto.MEMBER, address + " " + address2, phone);
				single.getMemCtrl().insert(dto);
				single.backToMain(this);
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
			}
		} else if (obj == Jbut_Back) { // 뒤로가기
			Singleton.getInstance().backToMain(this);
		} else if (obj == JBut_Search) { // 주소 검색
			SelectAddressDialog add = new SelectAddressDialog(this);
			JtextF_address.setText(add.getAddress());
			JtextF_address2.setText(add.getDetailAddress());
		}
	}
}