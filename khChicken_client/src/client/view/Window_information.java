package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

<<<<<<< HEAD
public class Window_information extends JFrame implements ActionListener{
	
	public Window_information() {
		
		setVisible(true);
		setBounds(0, 0, 640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
=======
import client.singleton.Singleton;

public class Window_information extends JFrame implements ActionListener{
	
	public Window_information(String id) {
		
		Singleton single = Singleton.getInstance();
		single.getOrderCtrl().selectBySales(number)
		
		setVisible(true);
		setBounds(0, 0, 640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
>>>>>>> branch 'yun_2' of https://github.com/gqpibd/kh_semi

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
