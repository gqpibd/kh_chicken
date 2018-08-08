package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import client.dto.MemberDto;
import client.singleton.Singleton;

public class Window_ACount extends JFrame implements ActionListener{

	JTextField JtextF_Id;
	JTextField JtextF_pwd;
	JTextField JtextF_pwd_Check;
	JTextField JtextF_name;
	JTextField JtextF_address;
	JTextField JtextF_phone;
	
	JButton Jbut_Check;
	JButton Jbut_Join;
	JButton Jbut_Back;
	
	public Window_ACount() {

		setTitle("회원가입창");
		setLayout(null);
	
		JtextF_Id = new JTextField(10);
		JtextF_Id.setBounds(65, 53, 116, 21);
		
		JtextF_pwd = new JTextField(10);
		JtextF_pwd.setBounds(64, 84, 116, 21);
		
		JtextF_pwd_Check = new JTextField(10);
		JtextF_pwd_Check.setBounds(64, 115, 116, 21);
		
		JtextF_name = new JTextField(10);
		JtextF_name.setBounds(64, 146, 116, 21);
		
		JtextF_address =new JTextField(10);
		JtextF_address.setBounds(65, 177, 116, 21);
		
		JtextF_phone = new JTextField(10);
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
		public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		Singleton single = Singleton.getInstance();
		
		
		String id = JtextF_Id.getText();
		String pw = JtextF_pwd.getText();
		String name = JtextF_name.getText();
		String address = JtextF_address.getText();
		String phone = JtextF_phone.getText();
		System.out.println(id + "뷰");
		
		
		if(obj == Jbut_Check) {
		MemberDto dto = new MemberDto(id, pw, name, 0, 0, address, phone);
		single.memCtrl.insert(dto);

		
		}else if (obj == Jbut_Join) {
			id = JtextF_Id.getText();
			
			MemberDto dto = new MemberDto();
			dto.setId(id);
			boolean Join = single.memCtrl.select(dto);
			
			System.out.println(Join);
			
		}else if (obj == Jbut_Check) {
			///sig.memCtrl.insert(dto);
			

		}	
	}
}