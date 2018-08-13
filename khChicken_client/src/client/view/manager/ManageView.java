package client.view.manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dto.OrderedMenuDto;
import client.controller.MemberController;
import client.controller.MenuController;
import client.singleton.Singleton;

public class ManageView extends JFrame implements ActionListener {

	private JButton btn_menu;
	private JButton btn_sale;
	private JButton btn_customer;
	private JButton btn_back;

	public ManageView() { 
		super("매니저 창");
		setLayout(null);

		btn_menu = new JButton("1. 메뉴 관리");
		btn_menu.setBounds(60, 30, 150, 50);
		btn_menu.addActionListener(this);
		btn_menu.setBackground(Color.pink);
		add(btn_menu);

		btn_sale = new JButton("2. 판매 관리");
		btn_sale.setBounds(60, 100, 150, 50);
		btn_sale.addActionListener(this);
		btn_sale.setBackground(Color.pink);
		add(btn_sale);
		
		btn_customer = new JButton("3. 고객 관리");
		btn_customer.setBounds(60, 170, 150, 50);
		btn_customer.addActionListener(this);
		btn_customer.setBackground(Color.pink);
		add(btn_customer);

		btn_back = new JButton("뒤로");
		btn_back.setBounds(60, 240, 150, 50);
		btn_back.addActionListener(this);
		btn_back.setBackground(Color.white);
		add(btn_back);

		setBounds(100, 100, 300, 400);
		getContentPane().setBackground(Color.black);
		setVisible(true);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		
		if (e.getSource() == btn_menu) {
			s.getMenuCtrl().menuManageView(this);
		} else if (e.getSource() == btn_sale) {
			s.getOrderCtrl().saleManageView(this);			
		} else if (e.getSource() == btn_customer) {
			s.getCusCtrl().CustomerManageView(this);
		}
		
		else if (e.getSource() == btn_back) {
			s.backToMain(this);
		}

	}

}
