package client.view.manager;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import client.controller.MemberController;
import client.controller.MenuController;
import client.singleton.Singleton;

public class MenuManageView extends JFrame implements ActionListener{
	JButton updMenuBtn;
	JButton addMenuBtn;
	JButton backBtn;
	public MenuManageView() {
		setTitle("메뉴 관리");

		getContentPane().setLayout(null);
		setBounds(300, 150, 274, 323);
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true);
		
		JLabel titleLabel = new JLabel("메뉴 관리");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(1, 10, 258, 52);
		getContentPane().add(titleLabel);
		
		updMenuBtn = new JButton("기존 메뉴 관리");
		updMenuBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		updMenuBtn.addActionListener(this);
		updMenuBtn.setBackground(Color.pink);
		updMenuBtn.setBounds(29, 143, 202, 41);
		getContentPane().add(updMenuBtn);
		
		addMenuBtn = new JButton("새 메뉴 추가");
		addMenuBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		addMenuBtn.addActionListener(this);
		addMenuBtn.setBackground(Color.pink);
		addMenuBtn.setBounds(29, 81, 202, 41);
		getContentPane().add(addMenuBtn);

		backBtn = new JButton("뒤로");
		backBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		backBtn.setBounds(29, 205, 202, 41);
		backBtn.addActionListener(this);
		backBtn.setBackground(Color.white);
		getContentPane().add(backBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuController menCtrl = Singleton.getInstance().getMenuCtrl();
		if(e.getSource() == updMenuBtn) {
			menCtrl.updateMenuView();
		} else if(e.getSource() == addMenuBtn) {
			menCtrl.addMenuView();
		} else if (e.getSource() == backBtn) {
			Singleton.getInstance().getMemCtrl().manageView(this);
			
		}
		
	}
}
