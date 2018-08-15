package client.view.manager;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
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
import utils.images.LabelEventListener;

public class MenuManageView extends JFrame implements ActionListener{
	JLabel updMenuBtn; // 메뉴 수정 버튼
	JLabel addMenuBtn; // 메뉴 추가 버튼
	JLabel backBtn; // 뒤로가기 버튼
	
	private static final String PATH = "images/manageView/";

	
	public MenuManageView() {
		setTitle("메뉴 관리");
		setContentPane(new JLabel(new ImageIcon(PATH + "menuManageView.jpg")));
		setResizable(false);
		
		getContentPane().setLayout(null);
		setBounds(300, 150, 274, 323);
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true);
		
	/*	JLabel titleLabel = new JLabel("메뉴 관리");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(1, 10, 258, 52);
		getContentPane().add(titleLabel);*/
		
		updMenuBtn = new JLabel(new ImageIcon(PATH + "oldMenuManageBtn.jpg"));
		updMenuBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		updMenuBtn.addMouseListener(new LabelEventListener(this));
		updMenuBtn.setBackground(Color.pink);
		updMenuBtn.setBounds(40, 143, updMenuBtn.getIcon().getIconWidth(), updMenuBtn.getIcon().getIconHeight());
		getContentPane().add(updMenuBtn);
		
		addMenuBtn = new JLabel(new ImageIcon(PATH + "addNewMenuBtn.jpg"));
		addMenuBtn.addMouseListener(new LabelEventListener(this));
		addMenuBtn.setBackground(Color.pink);
		addMenuBtn.setBounds(40, 81, 180, 41);
		getContentPane().add(addMenuBtn);
			
		backBtn = new JLabel(new ImageIcon(PATH + "MenuManageReturnBtn.jpg"));
		backBtn.setBounds(40, 205, backBtn.getIcon().getIconWidth(), backBtn.getIcon().getIconHeight());
		backBtn.addMouseListener(new LabelEventListener(this));
		backBtn.setBackground(Color.white);
		getContentPane().add(backBtn);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuController menCtrl = Singleton.getInstance().getMenuCtrl();
		if(e.getSource() == updMenuBtn) { // 메뉴 수정 버튼
			menCtrl.updateMenuView();
		} else if(e.getSource() == addMenuBtn) { // 메튜 추가 버튼
			menCtrl.addMenuView();
		} else if (e.getSource() == backBtn) { // 뒤로가기 버튼
			Singleton.getInstance().getMemCtrl().manageView(this);
			
		}
		
	}
}
