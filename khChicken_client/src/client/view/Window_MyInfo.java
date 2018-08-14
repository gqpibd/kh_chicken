package client.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.singleton.Singleton;
import dto.MemberDto;
import dto.ReviewDto;

public class Window_MyInfo extends JFrame implements ActionListener{	
		
	private Object[] Obj_Mem = { "아이디", "닉네임", "보유 쿠폰",  "집 주소", "휴대폰 번호" };
	private Object mdats[][];
	
	//private Object[] Obj_Order = { "아이디", "메뉴 이름", "시킨 횟수", "보유 쿠폰", "주문한 날짜", "내가쓴 리뷰", "별점" };
	private Object[] Obj_Rview = { "아이디", "메뉴 이름", "주문한 날짜", "내가쓴 리뷰", "별점" };
	private Object rdats[][];
	
	DefaultTableModel Model_Mem; //자신의정보를 테이블에 띄워줌
	DefaultTableModel Model_Order; // 자신이 시켜먹은것의 대한 정보를 테이블에 띄워줌
	
	List<MemberDto> M_List; // 자기 자신의 정보를 뽑아올 리스트
	List<ReviewDto> R_List; // 자기가 시켜먹은것의 대한 정보를 뽑아올 리스트
	
	DefaultTableModel model;
	
	JLabel JLabel_breakdown;
	
	public Window_MyInfo() {
		setLayout(null);
		
		
		JLabel_breakdown = new JLabel();
		JLabel_breakdown.setBounds(174, 22, 278, 53);
		JLabel_breakdown.setOpaque(true);
		JLabel_breakdown.setBackground(Color.white);
		add(JLabel_breakdown,"Center");
		
		setDeliveryTable();
		setVisible(true);
		setBounds(0, 0, 640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public void setDeliveryTable() {
		Singleton single = Singleton.getInstance();
		
		Date today = new Date();
		
		
		MemberDto mdto = single.getMemCtrl().getCurrentUser();
		String id = mdto.getId();
		
		ReviewDto rdto = new ReviewDto();
		rdto.setUserId(id);
		R_List= single.getRevCtrl().my_getList(rdto);
		System.out.println("R_List = " + R_List.size());
		mdats = new  Object[1][5];
		
		mdats[0][0] = mdto.getId();
		mdats[0][1] = mdto.getName();
		mdats[0][2] = mdto.getCoupon();
		mdats[0][3] = mdto.getAddress();
		mdats[0][4] = mdto.getPhone();
		
		
		
		model = new DefaultTableModel(Obj_Mem, 0);
		model.setDataVector(mdats, Obj_Mem);
		
		JTable orderTable = new JTable(model);
		
		orderTable.getColumnModel().getColumn(0).setMaxWidth(100); // ID 폭
		orderTable.getColumnModel().getColumn(1).setMaxWidth(400); // 주소 폭
		orderTable.getColumnModel().getColumn(2).setMaxWidth(400); // 폰번호 폭
		orderTable.getColumnModel().getColumn(3).setMaxWidth(300); // 날짜 폭
		orderTable.getColumnModel().getColumn(4).setMaxWidth(300);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		orderTable.getColumn("아이디").setCellRenderer(celAlignCenter);
		orderTable.getColumn("닉네임").setCellRenderer(celAlignCenter);
		orderTable.getColumn("보유 쿠폰").setCellRenderer(celAlignCenter);
		orderTable.getColumn("집 주소").setCellRenderer(celAlignCenter);
		orderTable.getColumn("휴대폰 번호").setCellRenderer(celAlignCenter);
		
		
		JScrollPane scrollPaneOrder = new JScrollPane();
		scrollPaneOrder.setBounds(14, 360, 594, 66);
		scrollPaneOrder.setViewportView(orderTable);
		add(scrollPaneOrder);
		
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
			rdats[i][2] = rdto.getOrderDate(); //주문한 날짜
			rdats[i][3] = rdto.getReview(); // 내가쓴리뷰
			rdats[i][4] = rdto.getScore(); // 내가준별점
		}
		////////

		model = new DefaultTableModel(rdats, Obj_Rview); // DefaultTableModel
		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setMaxWidth(100); // ID 폭
		table.getColumnModel().getColumn(1).setMaxWidth(400); // 주소 폭
		table.getColumnModel().getColumn(2).setMaxWidth(600); // 폰번호 폭
		table.getColumnModel().getColumn(3).setMaxWidth(300); // 날짜 폭
		table.getColumnModel().getColumn(4).setMaxWidth(300);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		table.getColumn("아이디").setCellRenderer(celAlignCenter);
		table.getColumn("메뉴 이름").setCellRenderer(celAlignCenter);
		table.getColumn("주문한 날짜").setCellRenderer(celAlignCenter);
		table.getColumn("내가쓴 리뷰").setCellRenderer(celAlignCenter);
		table.getColumn("별점").setCellRenderer(celAlignCenter);
		
		
		//add(table);
		JScrollPane Scroll_model = new JScrollPane(table);
		Scroll_model.setBounds(14, 90, 594, 232);
		add(Scroll_model);
	}
}