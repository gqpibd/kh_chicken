package client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import client.singleton.Singleton;
import dto.MemberDto;
import dto.OrderedMenuDto;
import dto.ReviewDto;
import sun.misc.Signal;

public class Window_information extends JFrame implements ActionListener{
	
	
	
	private Object[] Obj_Mem = { "아이디", "닉네임", "보유 쿠폰",  "집 주소", "휴대폰 번호" };
	private Object mdats[][];
	
	//private Object[] Obj_Order = { "아이디", "메뉴 이름", "시킨 횟수", "보유 쿠폰", "주문한 날짜", "내가쓴 리뷰", "별점" };
	private Object[] Obj_Rview = { "아이디", "메뉴 이름", "주문한 날짜", "내가쓴 리뷰", "별점" };
	private Object rdats[][];
	
	DefaultTableModel Model_Mem; //자신의정보를 테이블에 띄워줌
	DefaultTableModel Model_Order; // 자신이 시켜먹은것의 대한 정보를 테이블에 띄워줌
	DefaultTableModel model;
	
	List<MemberDto> M_List; // 자기 자신의 정보를 뽑아올 리스트
	List<ReviewDto> R_List; // 자기가 시켜먹은것의 대한 정보를 뽑아올 리스트
	
	
	
	JLabel JLabel_breakdown;
	
	
	JButton JBut_Change;
	JButton JBut_Change_Check;
/*	JButton JBut_ID_Change;
	JButton JBut_ID_Change;
	JButton JBut_ID_Change;*/
	
	JButton JBut_back;
	
	JTextArea JTextA_ID;
	JTextArea JTextA_Name;
	JTextArea JTextA_Coupon;
	JTextArea JTextA_Address;
	JTextArea JTextA_Phone;
	
	JLabel JLabel_ID;
	JLabel JLabel_Name;
	JLabel JLabel_Coupon;
	JLabel JLabel_Address;
	JLabel JLabel_Phone;
	
	JLabel JLabel_Logo;
	
	public Window_information() {
		setLayout(null);
		
		
		JLabel_breakdown = new JLabel();
		JLabel_breakdown.setBounds(174, 15, 225, 51);
		JLabel_breakdown.setOpaque(true);
		JLabel_breakdown.setBackground(Color.white);
		add(JLabel_breakdown,"Center");
		
		JBut_Change = new JButton("변");
		JBut_Change.addActionListener(this);
		JBut_Change.setBounds(0, 0, 50, 50);
		add(JBut_Change);
		JBut_Change_Check = new JButton("수정");
		JBut_Change_Check.addActionListener(this);
		JBut_Change_Check.setBounds(0, 50, 50, 50);
		add(JBut_Change_Check);
		
		
		setDeliveryPanel();
		
		setVisible(true);
		setBounds(0, 0, 640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton singleton = Singleton.getInstance();
		
		MemberDto dto = new MemberDto();
		if(obj == JBut_back) {
			singleton.backToMain(this);
			
		}else if(obj == JBut_Change) {
			System.out.println("dd");
			JTextA_Name.setEditable(true);
			JTextA_Address.setEditable(true);
			JTextA_Phone.setEditable(true);
			
			
		}else if(obj == JBut_Change_Check) {
			dto.setId(JTextA_ID.getText());
			dto.setName(JTextA_Name.getText());
			dto.setPhone(JTextA_Phone.getText());
			dto.setAddress(JTextA_Address.getText());
			singleton.getMemCtrl().Inform_Update(dto);
			System.out.println("수정이 완료되엇습니다.");
		}
			
		
		
	}
	
	public void setDeliveryPanel() { // 내정보 JLabel || JTextArea
		Singleton single = Singleton.getInstance();
		
		
		
		MemberDto mdto = single.getMemCtrl().getCurrentUser();
		String id = mdto.getId();
		
		ReviewDto rdto = new ReviewDto();
		rdto.setUserId(id);
		
		R_List= single.getRevCtrl().my_getList(rdto);
		
		JTextA_ID =  new JTextArea(mdto.getId());
		JTextA_ID.setEditable(false);
		JTextA_Name = new JTextArea(mdto.getName());
		JTextA_Name.setEditable(false);
		JTextA_Coupon = new JTextArea(mdto.getCoupon()+"");
		JTextA_Coupon.setEditable(false);
		JTextA_Address = new JTextArea(mdto.getAddress());
		JTextA_Address.setEditable(false);
		JTextA_Phone = new JTextArea(mdto.getPhone());
		JTextA_Phone.setEditable(false);
		
		
		JTextA_ID.setBounds(69, 76, 199, 23);
		JTextA_Name.setBounds(69, 109, 199, 23);
		JTextA_Coupon.setBounds(69, 142, 199, 23);
		JTextA_Address.setBounds(69, 175, 199, 23);
		JTextA_Phone.setBounds(69, 208, 199, 23);
		
		
		add(JTextA_ID);
		add(JTextA_Name);
		add(JTextA_Coupon);
		add(JTextA_Address);
		add(JTextA_Phone);
		
		JLabel_ID = new JLabel("ID");
		JLabel_Name = new JLabel("Name");
		JLabel_Coupon = new JLabel("Coupon");
		JLabel_Address = new JLabel("Address");
		JLabel_Phone = new JLabel("Phone");
		
		JLabel_ID.setBounds(12, 76, 45, 23);
		JLabel_Name.setBounds(12, 109, 45, 23);
		JLabel_Coupon.setBounds(12, 142, 45, 23);
		JLabel_Address.setBounds(12, 175, 45, 23);
		JLabel_Phone.setBounds(12, 208, 45, 23);	
		
		add(JLabel_ID);
		add(JLabel_Name);
		add(JLabel_Coupon);
		add(JLabel_Address);
		add(JLabel_Phone);
		
		JLabel_Logo = new JLabel("");
		JLabel_Logo.setBounds(12, 297, 256, 134);
		JLabel_Logo.setBackground(Color.black);
		JLabel_Logo.setOpaque(true);
		add(JLabel_Logo);
		
		JBut_back = new JButton("뒤로가기");
		JBut_back.addActionListener(this);
		JBut_back.setBounds(538, 15, 74, 46);
		add(JBut_back);
		
		setOrderTable();
		
	}
	

	private void setOrderTable() {
		// 주문한 메뉴를 받아와서 테이블로 띄워줌
		Singleton s = Singleton.getInstance();
		
		/////////
		
		int index = R_List.size();
		System.out.println("index = " + R_List.size());
		
		rdats = new Object[index][5]; // 테이블의 2차원배열
		//private Object[] Obj_Rview = { "아이디", "메뉴 이름", "주문한 날짜", "내가쓴 리뷰", "별점" };
		for (int i = 0; i < R_List.size(); i++) {
			ReviewDto rdto = R_List.get(i);
			
			rdats[i][0] = rdto.getUserId();  //아이디
			rdats[i][1] = rdto.getMenuName(); //메뉴이름
			rdats[i][2] = rdto.getOrderDate().substring(0,10); //주문한 날짜
			rdats[i][3] = rdto.getReview(); // 내가쓴리뷰
			rdats[i][4] = rdto.getScore(); // 내가준별점
		}
		////////

		model = new DefaultTableModel(rdats, Obj_Rview); // DefaultTableModel
		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setMaxWidth(50); 
		table.getColumnModel().getColumn(1).setMaxWidth(600);
		table.getColumnModel().getColumn(2).setMaxWidth(600);
		table.getColumnModel().getColumn(3).setMaxWidth(400);
		table.getColumnModel().getColumn(4).setMaxWidth(50);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		table.getColumn("아이디").setCellRenderer(celAlignCenter);
		table.getColumn("메뉴 이름").setCellRenderer(celAlignCenter);
		table.getColumn("주문한 날짜").setCellRenderer(celAlignCenter);
		table.getColumn("내가쓴 리뷰").setCellRenderer(celAlignCenter);
		table.getColumn("별점").setCellRenderer(celAlignCenter);
		
		
		
		
		
		//add(table);
		JScrollPane Scroll_model = new JScrollPane(table);
		Scroll_model.setBounds(270, 76, 350, 355);
		add(Scroll_model);
		
		R_List.clear();
		
	}
}