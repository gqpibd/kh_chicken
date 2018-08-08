package client.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ManageView extends JFrame {
	
	private JButton btn_menu;
	private JButton btn_sale;
	private JButton btn_back;
	
	public ManageView() {
		super("매니저 창");
		setLayout(null);
		
		btn_menu = new JButton("1. 메뉴 관리");
		btn_menu.setBounds(60, 30, 150, 50);
		btn_menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btn_menu.setBackground(Color.pink);
		add(btn_menu);
		
		btn_sale = new JButton("2. 판매 관리");
		btn_sale.setBounds(60, 100, 150, 50);
		btn_sale.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SaleManageView().setVisible(true);
				
				
			}
		});
		btn_sale.setBackground(Color.pink);
		add(btn_sale);
		
		btn_back = new JButton("뒤로");
		btn_back.setBounds(60, 170, 150, 50);
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btn_back.setBackground(Color.white);
		add(btn_back);
		
		setBounds(100, 100, 300, 280);
		getContentPane().setBackground(Color.black);
		setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

}
