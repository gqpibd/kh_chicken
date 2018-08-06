package client.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import client.dto.OrderedMenuDto;
import client.singleton.Singleton;

public class SaleManageView extends JFrame{
	
	List<OrderedMenuDto> list = new ArrayList<OrderedMenuDto>();
	
	public SaleManageView() {
		super("판매 내역");
		setLayout(null);
		
		Singleton s = Singleton.getInstance();
		
		list = s.ordCtrl.select();
		
		setBounds(100, 100, 640, 480);
		getContentPane().setBackground(Color.black);
		setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}

}
