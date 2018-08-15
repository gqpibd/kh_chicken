package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import client.controller.MemberController;
import client.controller.MenuController;
import client.controller.OrderController;
import client.dao.MenuDao;
import client.singleton.Singleton;
import dto.MemberDto;
import dto.MenuDto;
import dto.MenuShowDto;
import dto.OrderedMenuDto;
import net.miginfocom.swing.MigLayout;
import utils.images.ImageUtils;

public class MainView extends JFrame implements ItemListener {
	private static final String PATH = "images/mainView/";

	Singleton s = Singleton.getInstance();
	MenuController menCtrl = Singleton.getInstance().getMenuCtrl();
	MemberController memCtrl = Singleton.getInstance().getMemCtrl();
	List<String> checkedMenu = new ArrayList<>();
	int i = 0;

	// 버튼들
	JLabel btn_Login;
	JLabel btn_Register;
	JLabel btn_Order;
	JLabel btn_Manage;

	// JLabel btn_good;
	JLabel label_goodCount;
	Image img = null;

	public MainView() {
		super("KH CHICKEN");
		getContentPane().setLayout(null);

		setContentPane(new JLabel(new ImageIcon(PATH + "mainView.jpg")));
		setResizable(false);
		//크기변경불가
		LabelClickListener lcl = new LabelClickListener(this);

		// 버튼설정
		btn_Login = new JLabel(new ImageIcon(PATH + "loginBtn.jpg")); // 로그아웃 / 인 으로 변환됨
		btn_Login.setName("로그인");
		btn_Login.setBounds(470, 45, btn_Login.getIcon().getIconWidth(), btn_Login.getIcon().getIconHeight());
		btn_Login.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		// btn_Login.addActionListener(this);
		btn_Login.addMouseListener(lcl);
		ImageIcon registerIcon = new ImageIcon(PATH + "signBtn.jpg");
		btn_Register = new JLabel(registerIcon);
		btn_Register.setName("회원가입");
		btn_Register.setBounds(350, 45, registerIcon.getIconWidth(), registerIcon.getIconHeight());
		btn_Register.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		// btn_Register.addActionListener(this);
		btn_Register.addMouseListener(lcl);

		btn_Order = new JLabel(new ImageIcon(PATH + "orderBtn.jpg"));
		btn_Order.setBounds(475, 660, btn_Order.getIcon().getIconWidth(), btn_Order.getIcon().getIconHeight());
		btn_Order.setFont(new Font("다음_Regular", Font.PLAIN, 15));
		// btn_Order.addActionListener(this);
		btn_Order.addMouseListener(lcl);

		btn_Manage = new JLabel(new ImageIcon(PATH + "manageBtn.jpg"));
		btn_Manage.setBounds(370, 660, btn_Manage.getIcon().getIconWidth(), btn_Manage.getIcon().getIconHeight());
		btn_Manage.setVisible(false);
		btn_Manage.setFont(new Font("다음_Regular", Font.PLAIN, 15));
		// btn_Manage.addActionListener(this);
		btn_Manage.addMouseListener(lcl);

		// 메뉴출력
		// DB에서 메뉴가져오기 (server통신)
		menCtrl.initMenuList(); // menuDao에 있는 리스트를 초기화한다(서버에서 불러옴)

		// 메뉴들을 넣을 큰 패널 설정
		JPanel panel_bigmenu = new JPanel();
		panel_bigmenu.setBounds(0, 100, 580, 500);
		panel_bigmenu.setBackground(new Color(255, 255, 255));
		panel_bigmenu.setLayout(new MigLayout("wrap", "", ""));

		// 메뉴하나의 작은패널
		JPanel panel_menu = new JPanel();
		panel_menu.setSize(400, 300);
		panel_menu.setBackground(new Color(255, 255, 255));
		panel_menu.setLayout(new MigLayout());

		// 메뉴 출력
		for (int i = 0; i < menCtrl.getSize(); i++) {

			if (i % 2 == 1) {
				panel_menu.add(setFrontPanel(menCtrl.get(i), i), "wrap");
				// 작은패널에 setFrontPanel 한 panel을 넣되 i가 홀수면 다음줄로 이동
			} else {
				panel_menu.add(setFrontPanel(menCtrl.get(i), i));
			}
			panel_bigmenu.validate();
		}
		JScrollPane scroll = new JScrollPane(panel_menu);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(700, 600));
		panel_bigmenu.add(scroll);
		scroll.getVerticalScrollBar().setUnitIncrement(15);

		// 바탕에 저장

		getContentPane().add(btn_Login);
		getContentPane().add(btn_Register);
		getContentPane().add(btn_Order);
		getContentPane().add(btn_Manage);
		getContentPane().add(panel_bigmenu);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(400, 0, 600, 800);
		setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public JPanel setFrontPanel(MenuShowDto showDto, int i) {
		JLabel menuLabel; // 메뉴이름
		JLabel priceLabel; // 가격
		JCheckBox chk = null; // 선택

		JLabel imgLabel = new JLabel(); //
		imgLabel.setSize(249, 200); //

		// 하나하나의 패널사이즈
		JPanel frontpanel = new JPanel();
		frontpanel.setLayout(new MigLayout("", "20", "40"));
		frontpanel.setSize(400, 300);

		frontpanel.setBackground(Color.WHITE);
		// 이미지넣기
		// server에서 가져온 이미지 넣는 곳
		String img = menCtrl.get(i).getMenu_name().replaceAll(" ", "_") + ".jpg";
		// 패널당 메뉴이름을 저장시켜줌

		ImageUtils.setResizedImage(imgLabel, MenuDao.FOLDER_PATH + img);
		imgLabel.setName(menCtrl.get(i).getMenu_name()); // 라벨 자체에 메뉴 이름을 저장해준다.

		frontpanel.add(imgLabel, "center, wrap");

		// 이름
		menuLabel = new JLabel(menCtrl.get(i).getMenu_name());
		menuLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 20));
		frontpanel.add(menuLabel, "center, wrap");

		// 가격
		priceLabel = new JLabel(menCtrl.get(i).getPrice() + " 원");
		priceLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		frontpanel.add(priceLabel, "center, wrap");

		// 체크박스
		chk = new JCheckBox(menCtrl.get(i).getMenu_name() + " 선택");
		chk.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		chk.setName(menCtrl.get(i).getMenu_name()); // 체크박스에 이름을 저장
		chk.addItemListener(this);
		chk.setBackground(new Color(255, 255, 255));
		frontpanel.add(chk, "center, wrap");

		// 별점
		JLabel scoreLabel = new JLabel("별점 : " + menCtrl.get(i).getavgScore() + "");
		scoreLabel.setFont(new Font("다음_Regular", Font.PLAIN, 14));
		// 음료별점안보이기
		if (menCtrl.get(i).getType().equals("음료")) {
			scoreLabel.setVisible(false);
		}

		frontpanel.add(scoreLabel, "center, wrap");
		chk.setName(menCtrl.get(i).getMenu_name());
		imgLabel.addMouseListener(new labelListener(chk));

		return frontpanel;
	}

	private class labelListener extends MouseAdapter { // 이미지 클릭했을 때 작동하는 리스너

		JCheckBox box;

		public labelListener(JCheckBox box) {
			this.box = box;
		}

		@Override
		public void mouseClicked(MouseEvent e) {// 누르면
			String menu_name = ((JLabel) e.getSource()).getName(); // 클릭된 라벨의 이름을 받아온다

			if (!menCtrl.get(i).getType().equals("음료")) {

				boolean sel = s.getRevCtrl().reviewView(s.getMainView(), menu_name);
				box.setSelected(sel);
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) { // 제품 선택할 때
		JCheckBox chb = (JCheckBox) e.getSource();

		if (chb.isSelected() && !checkedMenu.contains(chb.getName())) {
			checkedMenu.add(chb.getName());
		} else {
			checkedMenu.remove(chb.getName());
		}

	}

	private class LabelClickListener extends MouseAdapter {
		MainView mv = null;

		public LabelClickListener(MainView mv) {
			this.mv = mv;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			MemberController memCtrl = Singleton.getInstance().getMemCtrl();
			super.mouseClicked(e);
			Object o = e.getSource();
			if (o == btn_Login) { // 로그인
				if (btn_Login.getName().equals("로그인")) {
					login();
					memCtrl.loginView(mv);
				} else {
					logout();
				}
			} else if (o == btn_Register) { // 회원가입
				if (btn_Register.getName().equals("회원가입")) {
					memCtrl.accountView(mv);
				} else {
					memCtrl.myInfoView(mv);
				}
			} else if (o == btn_Order) { // 주문하기
				// orderMenuDto에 선택한 메뉴 이름, 타입, 가격 넣어서 넘겨주기
				OrderController ordCtrl = Singleton.getInstance().getOrderCtrl();
				if (checkedMenu.size() == 0) {
					JOptionPane.showMessageDialog(null, "선택한 메뉴가 없습니다");
					return;
				}
				for (int i = 0; i < checkedMenu.size(); i++) {
					ordCtrl.getList().add(new OrderedMenuDto((MenuDto) menCtrl.getMenuDto(checkedMenu.get(i))));
				}

				ordCtrl.OrderView(mv);

			} else if (o == btn_Manage) {
				memCtrl.manageView(mv); // 관리자창
			}

		}

	}

	public void login() { // 로그인 상태에서의 뷰
		btn_Login.setName("로그아웃");
		System.out.println(s.getMemCtrl().getAuth());
		if (s.getMemCtrl().getAuth() == MemberDto.MANAGER) {
			btn_Manage.setVisible(true);
		}
		btn_Login.setIcon(new ImageIcon(PATH + "logoutBtn.jpg"));
		btn_Register.setName("내정보");
		btn_Register.setIcon(new ImageIcon(PATH + "myPageBtn.jpg"));
	}

	public void logout() { // 로그아웃 상태에서의 뷰
		memCtrl.logout();
		btn_Login.setIcon(new ImageIcon(PATH + "loginBtn.jpg"));
		btn_Manage.setVisible(false);
		btn_Login.setName("로그인");
		btn_Register.setName("회원가입");
		btn_Register.setIcon(new ImageIcon(PATH + "signBtn.jpg"));
	}
}
