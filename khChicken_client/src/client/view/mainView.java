package client.view;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import client.controller.MenuController;
import client.controller.OrderController;
import dto.MenuShowDto;
import client.singleton.Singleton;
import net.miginfocom.swing.MigLayout;

/*CREATE TABLE MEMBER(
	    NAME VARCHAR2(20) NOT NULL,
	    ID VARCHAR2(10) PRIMARY KEY,
	    PW VARCHAR2(20) NOT NULL,
	    COUPON NUMBER(1),
	    AUTH NUMBER(1) NOT NULL,
	    ADR VARCHAR2(30) NOT NULL,
	    PHONE VARCHAR2(20) NOT NULL
	);

	CREATE TABLE MENU(
	    MENU_NAME VARCHAR2(50) PRIMARY KEY,
	    PRICE NUMBER(5) NOT NULL
	);

	CREATE TABLE ORDER_DETAIL(
	    ID VARCHAR2(10),
	    MENU_NAME VARCHAR2(15),
	    COUNT NUMBER(10) NOT NULL,
	    BEV_COUPON NUMBER(10),
	    ORDER_DATE DATE NOT NULL,
	    REVIEW VARCHAR2(1000),
	    SCORE NUMBER(5),
	    CONSTRAINT FK_ID FOREIGN KEY(ID)
	    REFERENCES MEMBER(ID),
	    CONSTRAINT FK_MENU FOREIGN KEY(MENU_NAME)
	    REFERENCES MENU(MENU_NAME)
	);*/


public class mainView extends JFrame {
	
	Singleton s = Singleton.getInstance();
	MenuController menuCtrl = new MenuController();
	List<MenuShowDto> list_showMenu;
	
	public mainView() {
		super("KH CHICKEN");
		setLayout(null);
		
		//로고2, 버튼 4, 패널 1
		JButton btn_Login;
		JButton btn_Register;
		JButton btn_Order;
		JButton btn_Manage;
		
		JButton btn_good;
		JLabel label_goodCount;

		boolean managerBtnOpen = false;
		
		//버튼글씨
		String loginStr = "로그인";
		
		if (  s.memCtrl.getLoginId() != null ) {
			loginStr = "로그아웃"; 
			if ( s.memCtrl.getAuth() ==1)	managerBtnOpen = true;
	}

		//버튼설정 
		btn_Login = new JButton(loginStr);	//로그아웃 / 인 으로 변환됨
		btn_Login.setBounds(370, 50, 97, 30);
		btn_Login.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		btn_Login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "로그인");
				
				//로그인 view open
			}
		});
		btn_Register = new JButton("회원가입");
		btn_Register.setBounds(479,50, 97, 30);
		btn_Register.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		btn_Register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "회원가입");
				
				//회원가입 view open
				
			}
		});
		
		btn_Order = new JButton("주문하기");
		btn_Order.setBounds(475, 700, 97, 55);
		btn_Order.setFont(new Font("다음_Regular", Font.PLAIN, 15));
		btn_Order.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//loginStr , 체크한 메뉴 보내줌  List <String> orderedMenus = new ArrayList<>(); 
				List<String> orderedMenus = new ArrayList<>();
				orderedMenus.add("");
				
				JOptionPane.showMessageDialog(null, "주문하기");
				
				//주문하기 view open
				//new OrderView(String loginStr, List <String> orderedMenus );
				
			}
		});
		
		btn_Manage = new JButton("관리");		
		btn_Manage.setBounds(394,720,66, 34);	
		btn_Manage.setVisible(managerBtnOpen);
		btn_Manage.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		btn_Manage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "관리");
				
				//관리자 view open
				//new ManageView();  
				
			}
		});
		
		//메뉴리스트 
		//List<MenuDto> list_menudto = new ArrayList<>();
		
		//메뉴 리스트에 DB에서 가져온 메뉴 넣기 
		list_showMenu  = menuCtrl.getShowMenu();
		
		//메뉴들을 넣을 패널 설정   
		JPanel panel_bigmenu = new JPanel();
		panel_bigmenu.setBounds(10, 100, 570, 600);
		panel_bigmenu.setLayout(new MigLayout("wrap", "", ""));

		//메뉴하나의 패널
		JPanel panel_menu = new JPanel();
		panel_menu.setLayout(new MigLayout());
		
		//메뉴 출력 
		for (int i = 0; i < list_showMenu.size(); i++) {
			
			if (i%2 == 1) {
				panel_menu.add(setFrontPanel(list_showMenu.get(i)),"wrap");
				
			}else {
				
				panel_menu.add(setFrontPanel(list_showMenu.get(i)));
			}
			panel_bigmenu.validate();
		}
		JScrollPane scroll = new JScrollPane(panel_menu);
		scroll.setPreferredSize(new Dimension(700, 600));
		panel_bigmenu.add(scroll);
		
		 
		//바탕에 저장
		add(btn_Login);
		add(btn_Register);
		add(btn_Order);
		add(btn_Manage);
		add(panel_bigmenu);
		
		setBounds(400, 0, 600, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public JPanel setFrontPanel(MenuShowDto showDto) {
		
		
		
		//하나하나의 패널사이즈
		JPanel frontpanel = new JPanel();
		frontpanel.setLayout(new MigLayout("","20","40"));
		frontpanel.setSize(300, 100);

		//이미지

		//메뉴, 사진 넣기

		for (int i = 0; i < list_showMenu.size(); i++) {
			
			//DB에서 가져온 이미지 String
			String path = list_showMenu.get(i).getImage();
			BufferedImage im = null;
			
			try {
				
				im = ImageIO.read(new File(path));
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//String 을 icon으로 변환
			ImageIcon icon = new ImageIcon(im);	

			//이미지 들어가는 레이블
			JLabel imgLabel = new JLabel(icon);
			imgLabel.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					String pressedImg = (String)e.getSource();
				
				for (int j = 0; j < list_showMenu.size(); j++) {
					
					if (pressedImg == list_showMenu.get(j).getImage()) {
						
							String pressedMenu = list_showMenu.get(j).getMenu_name();
					}
				}
				JOptionPane.showMessageDialog(null, "리뷰 open");
				
				//리뷰 view open (pressedMenu);
				
					
				}
				
			});
		
		frontpanel.add(imgLabel, "wrap");
		
		//이름 
		JLabel resLabel = new JLabel(list_showMenu.get(i).getMenu_name());
		resLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 20));
		frontpanel.add(resLabel, "center, wrap");
		
		//가격
		JLabel priceLabel = new JLabel(list_showMenu.get(i).getPrice()+" 원");
		priceLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		frontpanel.add(priceLabel, "center, wrap");

		
		//체크박스
		Checkbox chk = new Checkbox("선택");
		chk.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		frontpanel.add(chk,"center, wrap");
		
		//별점
		MenuShowDto menushowDto = new MenuShowDto();
		JLabel scoreLabel = new JLabel("별점 : "+ list_showMenu.get(i).getavgScore()+"");
		scoreLabel.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		frontpanel.add(scoreLabel, "center, wrap");
		
		}
		
		return frontpanel;

	}
	

}
