package client.view.manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import dto.OrderedMenuDto;
import utils.images.LabelEventListener;
import client.controller.MemberController;
import client.controller.MenuController;
import client.singleton.Singleton;
import javax.swing.JPanel;

public class ManageView extends JFrame implements ActionListener {

	private JLabel btn_menu; // 메뉴관리 버튼
	private JLabel btn_sale; // 매출관리 버튼
	private JLabel btn_customer; // 고객관리 버튼
	private JLabel btn_back; // 뒤로가기 버튼
	private static final String PATH = "images/manageView/";

	JLabel lblAsd;

	public ManageView() { 
		super("매니저 창");
		
		getContentPane().setLayout(null);
		setContentPane(new JLabel(new ImageIcon(PATH + "manageView2.jpg")));
		setResizable(false);
		
		btn_menu = new JLabel(new ImageIcon(PATH + "menuManageBtn.png"));	
		btn_menu.setBounds(200, 100, btn_menu.getIcon().getIconWidth(), btn_menu.getIcon().getIconHeight());
		btn_menu.addMouseListener(new LabelEventListener(this));
		getContentPane().add(btn_menu);

		btn_sale = new JLabel(new ImageIcon(PATH + "saleManageBtn.png"));
		btn_sale.setBounds(200, 160, btn_sale.getIcon().getIconWidth(), btn_sale.getIcon().getIconHeight());
		btn_sale.addMouseListener(new LabelEventListener(this));
		getContentPane().add(btn_sale);
		
		btn_customer = new JLabel(new ImageIcon(PATH + "customerBtn.png"));
		btn_customer.setBounds(200, 220, btn_customer.getIcon().getIconWidth(), btn_customer.getIcon().getIconHeight());
		btn_customer.addMouseListener(new LabelEventListener(this));
		getContentPane().add(btn_customer);

		btn_back = new JLabel(new ImageIcon(PATH + "returnBtn.png"));
		btn_back.setBounds(200, 280, btn_back.getIcon().getIconWidth(), btn_back.getIcon().getIconHeight());
		btn_back.addMouseListener(new LabelEventListener(this));
		btn_back.setBackground(Color.white);
		getContentPane().add(btn_back);

		setBounds(100, 100, 350, 400);
		getContentPane().setBackground(Color.black);
		setVisible(true);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		
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
		}else if (e.getSource() == lblAsd) {
			JOptionPane.showMessageDialog(null, "wow");
		}

	}

}
