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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private static final String PATH = "images/manageView/";

	public ManageView() { 
		super("매니저 창");
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon(PATH + "manageView2.jpg")));
		setResizable(false);
		
		

		btn_menu = new JButton(new ImageIcon(PATH + "menuManageBtn.png"));
		btn_menu.setBounds(200, 100, 98, 50);
		btn_menu.addActionListener(this);
		btn_menu.setBackground(Color.pink);
		add(btn_menu);

		btn_sale = new JButton(new ImageIcon(PATH + "saleManageBtn.png"));
		btn_sale.setBounds(200, 160, 98, 50);
		btn_sale.addActionListener(this);
		btn_sale.setBackground(Color.pink);
		add(btn_sale);
		
		btn_customer = new JButton(new ImageIcon(PATH + "customerBtn.png"));
		btn_customer.setBounds(200, 220, 98, 50);
		btn_customer.addActionListener(this);
		btn_customer.setBackground(Color.pink);
		add(btn_customer);

		btn_back = new JButton(new ImageIcon(PATH + "returnBtn.png"));
		btn_back.setBounds(200, 280, 98, 50);
		btn_back.addActionListener(this);
		btn_back.setBackground(Color.white);
		add(btn_back);

		setBounds(100, 100, 350, 400);
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
			s.getStaCtrl().saleManageView(this);			
		} else if (e.getSource() == btn_customer) {
			s.getStaCtrl().CustomerManageView(this);
		}
		
		else if (e.getSource() == btn_back) {
			s.backToMain(this);
		}

	}

}
