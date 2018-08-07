package client.view;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import client.dto.MemberDto;
import client.dto.MenuDto;
import client.dto.MenuShowDto;
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
		JPanel pn_menu;
		
		boolean menuPlus = false;
		boolean menuMinus = false;
		boolean managerBtn = false;
		
		//버튼글씨
		String loginOut = "로그인";
		
		//버튼설정 
		btn_Login = new JButton(loginOut);	//로그아웃으로 변환
		btn_Login.setBounds(370, 50, 97, 30);
		btn_Login.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		btn_Login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "로그인");
				
				//로그인 뷰 open return auth
				
			/*	if ( 로그인아이디 not null ) {
					
					loginOut = "로그아웃"; 
					if ( 로그인 auth ==1)	managerBtn = true;

				}
				*/
			}
		});
		
		btn_Register = new JButton("회원가입");
		btn_Register.setBounds(479,50, 97, 30);
		btn_Register.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		btn_Register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "회원가입");
				//회원가입오픈
				
			}
		});
		
		btn_Order = new JButton("주문하기");
		btn_Order.setBounds(475, 700, 97, 55);
		btn_Order.setFont(new Font("다음_Regular", Font.PLAIN, 15));
		btn_Order.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//INSERT orderedMenuDto 
				JOptionPane.showMessageDialog(null, "주문하기");
				
			}
		});
		
		btn_Manage = new JButton("관리");		
		btn_Manage.setBounds(394,720,66, 34);	
		btn_Manage.setVisible(managerBtn);
		btn_Manage.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		btn_Manage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "관리");
				
				//new ManageView();
				
			}
		});
		
		//메뉴리스트 
		List<MenuDto> list_menudto = new ArrayList<>();
		
		//메뉴들을 넣을 패널 설정   
		JPanel panel_bigmenu = new JPanel();
		panel_bigmenu.setBounds(10, 100, 570, 600);
		panel_bigmenu.setLayout(new MigLayout("wrap", "", ""));

		//메뉴하나의 패널
		JPanel panel_menu = new JPanel();
		panel_menu.setLayout(new MigLayout());
		
		
			/*//메뉴 추가삭제 
		 
		 //menudao를 list.size로 봤을때 
		if (menuPlus) {					//승지 관리자 메뉴추가 true받아야 함 
			
			list_menudto.add(menuDto);//menuDto를 리스트에 추가
			menuPlus = false;
			
		}else if (menuMinus){			//승지 삭제할 메뉴삭제 true, String 받아와야함
	
			//리스트에서 삭제
			for (int i = 0; i < list_menudto.size(); i++) {				
				list_menuPn.equals(menuDto.getMenu_name);
			}
			menuMinus = false;
		}*/
		
		for (int i = 0; i < list_menudto.size(); i++) {
			
			if (i%2 == 1) {
				panel_menu.add(setFrontPanel(list_menudto.get(i)),"wrap");
				
			}else {
				
				panel_menu.add(setFrontPanel(list_menudto.get(i)));
				
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

	public JPanel setFrontPanel(MenuDto dto) {
		
		//하나하나의 패널사이즈
		JPanel frontpanel = new JPanel();
//	frontpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frontpanel.setLayout(new MigLayout("","20","40"));
		frontpanel.setSize(300, 100);

		//이미지 판별
		ImageIcon icon = null;
		
		//for menuDto 길이 
		if (dto.getMenu_name().contains("치킨")) {	//db의 메뉴이름
			
			icon = new ImageIcon("chi.jpg");	//new ImageIcon( menuImgStr + ".jpg")
		
		}else if (dto.getMenu_name().contains("콜라")) {
			
			icon = new ImageIcon("cock.jpg");
		}
		else if (dto.getMenu_name().contains("사이다")) {
			
			icon = new ImageIcon("sprite.jpg");
		}

		//이미지 들어가는 레이블
		JLabel imgLabel = new JLabel(icon);
		imgLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				//JOptionPane.showMessageDialog(null, "치킨누름");
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				//누른 img파일명 의 menuName을 상필이한테 보내줌
				//dao-->String pressImgName () { select MENU_NAME from MenuDto where 누른이미지 return MENU_NAME; } 
				
				JOptionPane.showMessageDialog(null, "치킨누름");
				
				//review open
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		frontpanel.add(imgLabel, "wrap");
		
		//이름 
		JLabel resLabel = new JLabel(dto.getMenu_name());
		resLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 20));
		frontpanel.add(resLabel, "center, wrap");
		
		//가격
		JLabel priceLabel = new JLabel(dto.getPrice()+"");
		priceLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		frontpanel.add(priceLabel, "center, wrap");

		
		//체크박스
		Checkbox chk = new Checkbox("선택");
		chk.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		frontpanel.add(chk,"center, wrap");
		
		//별점
		MenuShowDto menushowDto = new MenuShowDto();
		JLabel scoreLabel = new JLabel("별점 : "+ menushowDto.getAvgScore()+"");
		scoreLabel.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		frontpanel.add(scoreLabel, "center, wrap");
		
		return frontpanel;

	}
	

}
