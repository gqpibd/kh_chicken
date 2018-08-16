package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.controller.MemberController;
import client.singleton.Singleton;
import dto.MemberDto;
import dto.ReviewDto;
import utils.images.LabelEventListener;

public class MyInfoView extends JFrame implements ActionListener {

	private JLabel JBut_Change;
	private JLabel JBut_back;
	private JLabel JBut_search;

	private JTextField JField_ID;
	private JTextField JField_Name;
	private JTextField JField_Coupon;
	private JTextField JField_Address;
	private JTextField JField_Phone;
	private JPasswordField JField_Pw;
	private JPasswordField JField_Pw2;
	private JTextField JField_Address2;

	private MemberController memCtrl = Singleton.getInstance().getMemCtrl();
	private static final String PATH = "images/myInfoView/";

	public MyInfoView() {

		setContentPane(new JLabel(new ImageIcon(PATH + "myInfoView.jpg")));
		setResizable(false);
		getContentPane().setLayout(null);

		setPersonalInfoPanel();

		setOrderTable();

		setVisible(true);
		setBounds(0, 0, 983, 480);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton singleton = Singleton.getInstance();

		MemberDto dto = memCtrl.getCurrentUser();
		if (obj == JBut_back) {
			singleton.backToMain(this);

		} else if (obj == JBut_Change && JBut_Change.getName().equals("정보 수정")) {
			JField_Name.setEditable(true);
			JField_Phone.setEditable(true);
			JField_Pw.setEditable(true);
			JField_Pw2.setEditable(true);
			JField_Address2.setEditable(true);
			JBut_search.setEnabled(true);
			JBut_Change.setName("수정 완료");
		} else if (obj == JBut_Change && JBut_Change.getName().equals("수정 완료")) {
			dto.setId(JField_ID.getText());
			if (!new String(JField_Pw.getPassword()).equals(new String(JField_Pw2.getPassword()))) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다");
				return;
			}
			if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", JField_Phone.getText())) {
				JOptionPane.showMessageDialog(null, "전화번호 형식이 맞지 않습니다.\n 01X-XXXX-XXXX 형태로 입력해 주세요");
				return;
			}
			dto.setPw(new String(JField_Pw.getPassword()));
			dto.setName(JField_Name.getText());
			dto.setPhone(JField_Phone.getText());
			dto.setAddress(JField_Address.getText() + " " + JField_Address2.getText());
			singleton.getMemCtrl().update(dto);
			JBut_search.setEnabled(false);
			JField_Name.setEditable(false);
			JField_Pw.setEditable(false);
			JField_Pw2.setEditable(false);
			JField_Address2.setEditable(false);
			JField_Phone.setEditable(false);
			JBut_Change.setName("정보 수정");
			System.out.println("수정이 완료되엇습니다.");
		} else if (obj == JBut_search) {
			SelectAddressDialog add = new SelectAddressDialog(this);
			JField_Address.setText(add.getAddress());
			JField_Address2.setText(add.getDetailAddress());
		}

	}

	public void setPersonalInfoPanel() { // 내정보 JLabel || JTextArea
		JLabel JLabel_ID;
		JLabel JLabel_Name;
		JLabel JLabel_Coupon;
		JLabel JLabel_Address;
		JLabel JLabel_Phone;

		JLabel JLabel_Logo;

		// 정보 수정
		JBut_Change = new JLabel(new ImageIcon(PATH + "upInfoBtn.jpg"));
		JBut_Change.setName("정보 수정");
		JBut_Change.addMouseListener(new LabelEventListener(this));
		JBut_Change.setBounds(315, 381, JBut_Change.getIcon().getIconWidth(), JBut_Change.getIcon().getIconHeight());
		getContentPane().add(JBut_Change);

		// 주소 검색
		JBut_search = new JLabel(new ImageIcon(PATH + "upSearchBtn.jpg"));
		JBut_search.setBounds(352, 273, JBut_search.getIcon().getIconWidth(), JBut_search.getIcon().getIconHeight());
		JBut_search.setEnabled(false);
		getContentPane().add(JBut_search);
		JBut_search.addMouseListener(new LabelEventListener(this));

		// 돌아가기
		JBut_back = new JLabel(new ImageIcon(PATH + "upReturnBtn.jpg"));
		JBut_back.addMouseListener(new LabelEventListener(this));
		JBut_back.setBounds(816, 385, JBut_back.getIcon().getIconWidth(), JBut_back.getIcon().getIconHeight());
		getContentPane().add(JBut_back);

		MemberDto mdto = memCtrl.getCurrentUser();

		JField_ID = new JTextField(mdto.getId());
		JField_ID.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		JField_ID.setEditable(false);
		JField_Name = new JTextField(mdto.getName());
		JField_Name.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		JField_Name.setEditable(false);
		JField_Coupon = new JTextField(Singleton.getInstance().getOrderCtrl().getCoupons() + "");
		JField_Coupon.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		JField_Coupon.setEditable(false);
		String address = mdto.getAddress();

		JField_Address = new JTextField();
		JField_Address.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		JField_Address2 = new JTextField();
		JField_Address2.setFont(new Font("나눔고딕", Font.PLAIN, 14));

		int loc = 0;
		System.out.println(mdto.getAddress());
		if (address.contains(")")) {
			loc = address.indexOf(")");
			JField_Address.setText(address.substring(0, loc + 1));

		} else {
			loc = address.indexOf(' ');
			loc = address.indexOf(' ', loc + 1);
			loc = address.indexOf(' ', loc + 1) - 1;
		}

		if (loc + 2 < address.length()) {
			JField_Address2.setText(address.substring(loc + 2));
		}
		JField_Address.setEditable(false);
		JField_Address2.setEditable(false);
		JField_Phone = new JTextField(mdto.getPhone());
		JField_Phone.setEditable(false);
		JField_Pw = new JPasswordField(mdto.getPw());
		JField_Pw.setEditable(false);
		JField_Pw2 = new JPasswordField(mdto.getPw());
		JField_Pw2.setEditable(false);

		JField_ID.setBounds(110, 77, 199, 23);
		JField_Name.setBounds(110, 109, 199, 23);
		JField_Coupon.setBounds(110, 204, 199, 23);
		JField_Address.setBounds(110, 240, 315, 23);
		JField_Phone.setBounds(110, 306, 199, 23);

		getContentPane().add(JField_ID);
		getContentPane().add(JField_Name);
		getContentPane().add(JField_Coupon);
		getContentPane().add(JField_Address);
		getContentPane().add(JField_Phone);

		JLabel_ID = new JLabel("아이디");
		JLabel_ID.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		JLabel_ID.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_Name = new JLabel("이름");
		JLabel_Name.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		JLabel_Name.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_Coupon = new JLabel("보유쿠폰");
		JLabel_Coupon.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		JLabel_Coupon.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_Address = new JLabel("주소");
		JLabel_Address.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		JLabel_Address.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_Phone = new JLabel("연락처");
		JLabel_Phone.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		JLabel_Phone.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel_ID.setBounds(53, 77, 45, 23);
		JLabel_Name.setBounds(35, 109, 63, 23);
		JLabel_Coupon.setBounds(35, 204, 63, 23);
		JLabel_Address.setBounds(53, 240, 45, 23);
		JLabel_Phone.setBounds(35, 306, 63, 23);

		getContentPane().add(JLabel_ID);
		getContentPane().add(JLabel_Name);
		getContentPane().add(JLabel_Coupon);
		getContentPane().add(JLabel_Address);
		getContentPane().add(JLabel_Phone);

		JLabel_Logo = new JLabel(new ImageIcon(PATH + "charactor.gif")); // gif넣기
		JLabel_Logo.setBounds(480, 345, 282, 106);
		JLabel_Logo.setBackground(Color.black);
		JLabel_Logo.setOpaque(true);
		getContentPane().add(JLabel_Logo);

		JLabel label = new JLabel("비밀번호");
		label.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(35, 142, 63, 23);
		getContentPane().add(label);

		JField_Pw.setBounds(110, 142, 199, 21);
		getContentPane().add(JField_Pw);
		JField_Pw.setColumns(10);

		JLabel label_1 = new JLabel("비밀번호 확인");
		label_1.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(0, 175, 98, 23);
		getContentPane().add(label_1);

		JField_Pw2.setColumns(10);
		JField_Pw2.setBounds(110, 175, 199, 21);
		getContentPane().add(JField_Pw2);

		JField_Address2.setBounds(110, 273, 230, 23);
		getContentPane().add(JField_Address2);

		setLocationRelativeTo(null);

	}

	private void setOrderTable() {
		Object[] Obj_Rview = { "메뉴 이름", "주문한 날짜", "내가쓴 리뷰", "별점" };
		Object rdats[][];
		DefaultTableModel model;
		List<ReviewDto> R_List; // 자기가 시켜먹은것의 대한 정보를 뽑아올 리스트

		ReviewDto rdto = new ReviewDto();
		rdto.setUserId(memCtrl.getLoginId());
		R_List = Singleton.getInstance().getRevCtrl().selectByUserId(rdto);
		/////////

		int index = R_List.size();

		rdats = new Object[index][4]; // 테이블의 2차원배열
		for (int i = 0; i < R_List.size(); i++) {
			rdto = R_List.get(i);

			rdats[i][0] = rdto.getMenuName(); // 메뉴이름
			rdats[i][1] = rdto.getOrderDate().substring(0, 10); // 주문한 날짜
			rdats[i][2] = rdto.getReview(); // 내가쓴리뷰
			rdats[i][3] = rdto.getScore(); // 내가준별점
		}
		////////

		model = new DefaultTableModel(rdats, Obj_Rview) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}; // DefaultTableModel
		JTable table = new JTable(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		table.getColumn("메뉴 이름").setCellRenderer(celAlignCenter);
		table.getColumn("주문한 날짜").setCellRenderer(celAlignCenter);
		table.getColumn("내가쓴 리뷰").setCellRenderer(celAlignCenter);
		table.getColumn("별점").setCellRenderer(celAlignCenter);

		// add(table);
		JScrollPane Scroll_model = new JScrollPane(table);
		Scroll_model.setBounds(435, 80, 520, 262);
		getContentPane().add(Scroll_model);

		/*
		 * JLabel lblNewLabel = new JLabel("<나의 주문 내역>"); lblNewLabel.setFont(new
		 * Font("나눔고딕", Font.BOLD, 20));
		 * lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblNewLabel.setBounds(435, 10, 520, 29); getContentPane().add(lblNewLabel);
		 */

		R_List.clear();
	}
}