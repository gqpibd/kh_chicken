package client.view;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import client.singleton.Singleton;
import dto.MenuShowDto;
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

public class mainView extends JFrame implements ItemListener {
	private final String FOLDER_PATH = "\\\\192.168.30.35\\share\\images\\";
	Singleton s = Singleton.getInstance();
	MenuController menCtrl = Singleton.getInstance().getMenuCtrl();
	//List<MenuShowDto> list_showMenu;
	List<String> checkedMenu = new ArrayList<>();
	int i = 0;

	// 클릭시 review화면 띄울 메뉴이름
	String menu_name = "";

	JLabel imgLabel;
	JLabel resLabel;
	JLabel priceLabel;
	Checkbox chk;

	public mainView() {
		super("KH CHICKEN");
		setLayout(null);

		// 로고2, 버튼 4, 패널 1
		JButton btn_Login;
		JButton btn_Register;
		JButton btn_Order;
		JButton btn_Manage;

		JButton btn_good;
		JLabel label_goodCount;

		boolean managerBtnOpen = false;

		// 버튼글씨
		String loginStr = "로그인";

		if (s.getMemCtrl().getLoginId() != null) {
			loginStr = "로그아웃";
			System.out.println(s.getMemCtrl().getAuth());
			if (s.getMemCtrl().getAuth() == 1)
				managerBtnOpen = true;
		}

		// 버튼설정
		btn_Login = new JButton(loginStr); // 로그아웃 / 인 으로 변환됨
		btn_Login.setBounds(370, 50, 97, 30);
		btn_Login.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		btn_Login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "로그인");
				// 로그인 view open
			}
		});
		btn_Register = new JButton("회원가입");
		btn_Register.setBounds(479, 50, 97, 30);
		btn_Register.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		btn_Register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "회원가입");
			}
		});

		btn_Order = new JButton("주문하기");
		btn_Order.setBounds(475, 700, 97, 55);
		btn_Order.setFont(new Font("다음_Regular", Font.PLAIN, 15));
		btn_Order.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "주문하기");
				String loginId = s.getMemCtrl().getLoginId();
				new MenuManagementView();
				System.out.println(loginId);
				for (int i = 0; i < checkedMenu.size(); i++) {
					System.out.println(checkedMenu.get(i));
					
					
				}
				// 주문 뷰 new OrderView(loginId, checkedMenu );
			}
		});

		btn_Manage = new JButton("관리");
		btn_Manage.setBounds(394, 720, 66, 34);
		btn_Manage.setVisible(managerBtnOpen);
		btn_Manage.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		btn_Manage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "관리");
				// 관리자 view open
				// new ManageView();
			}
		});

		// 메뉴출력
		// DB에서 메뉴가져오기 (server통신)		
		menCtrl.initMenuList(); // menuDao에 있는 리스트를 초기화한다(서버에서 불러옴)

		// 메뉴들을 넣을 큰 패널 설정
		JPanel panel_bigmenu = new JPanel();
		panel_bigmenu.setBounds(10, 100, 570, 600);
		panel_bigmenu.setLayout(new MigLayout("wrap", "", ""));

		// 메뉴하나의 작은패널
		JPanel panel_menu = new JPanel();
		panel_menu.setLayout(new MigLayout());

		// 메뉴 출력
		for (int i = 0; i < menCtrl.getSize() ; i++) {

			if (i % 2 == 1) {
				panel_menu.add(setFrontPanel(menCtrl.get(i)), "wrap");
				// 작은패널에 setFrontPanel 한 panel을 넣되 i가 홀수면 다음줄로 이동
			} else {
				panel_menu.add(setFrontPanel(menCtrl.get(i)));
			}
			panel_bigmenu.validate();
		}
		JScrollPane scroll = new JScrollPane(panel_menu);
		scroll.setPreferredSize(new Dimension(700, 600));
		panel_bigmenu.add(scroll);

		// 바탕에 저장
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
		
		// 하나하나의 패널사이즈
		JPanel frontpanel = new JPanel();
		frontpanel.setLayout(new MigLayout("", "20", "40"));
		frontpanel.setSize(300, 100);

		for (i = 0; i < menCtrl.getSize(); i++) {

			BufferedImage im = null;
			try {
				// 이미지넣기
				// server에서 가져온 이미지 넣는 곳
				String img = menCtrl.get(i).getMenu_name().replaceAll(" ", "_") + ".jpg";
				// 패널당 메뉴이름을 저장시켜줌
				menu_name = menCtrl.get(i).getMenu_name();

				im = ImageIO.read(new File(FOLDER_PATH + img));

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// FOLDER_PATH를 icon으로 변환
			ImageIcon icon = new ImageIcon(im);

			// 레이블에 icon을 넣음
			JLabel imgLabel = new JLabel(icon);
			imgLabel.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {// 누르면

					JOptionPane.showMessageDialog(null, "리뷰 open");
					System.out.println("menu Name : " + menu_name);
					// 리뷰 view open (menu_name);
				}

			});

			frontpanel.add(imgLabel, "wrap");

			// 이름
			resLabel = new JLabel(menCtrl.get(i).getMenu_name());
			resLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 20));
			frontpanel.add(resLabel, "center, wrap");

			// 가격
			priceLabel = new JLabel(menCtrl.get(i).getPrice() + " 원");
			priceLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
			frontpanel.add(priceLabel, "center, wrap");

			// 체크박스
			chk = new Checkbox(menCtrl.get(i).getMenu_name() + " 선택");
			chk.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
			chk.addItemListener(this);
			/*
			 * chk.addItemListener(new ItemListener() {
			 * 
			 * @Override public void itemStateChanged(ItemEvent e) {
			 * 
			 * //체크된 이름을 저장 checkedMenu.add(list_showMenu.get(i).getMenu_name()); } });
			 */
			frontpanel.add(chk, "center, wrap");

			// 별점
			JLabel scoreLabel = new JLabel("별점 : " + menCtrl.get(i).getavgScore() + "");
			scoreLabel.setFont(new Font("다음_Regular", Font.PLAIN, 14));
			frontpanel.add(scoreLabel, "center, wrap");

		}
		return frontpanel;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		Checkbox chk = (Checkbox) e.getSource();

		// 체크된 이름을 저장
		checkedMenu.add(menCtrl.get(i).getMenu_name());

	}
}
