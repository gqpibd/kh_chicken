package client.view;

import java.awt.Color;

import javax.swing.JFrame;

import client.singleton.Singleton;

public class SaleManageView extends JFrame{
	
	public SaleManageView() {
		super("판매 내역");
		setLayout(null);
		
		Singleton s = Singleton.getInstance();
		
		
		
		setBounds(100, 100, 640, 480);
		getContentPane().setBackground(Color.black);
		setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}

}
