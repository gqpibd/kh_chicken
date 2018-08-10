package client.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.singleton.Singleton;
import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;

public class SaleManageView extends JFrame implements ActionListener {
	
	private JTable jTable;
	private JScrollPane jScrPane;
	
	String columNames[] = {
			"번호", "주문일자", "아이디", "메뉴타입",  "주문메뉴", "수량", "음료쿠폰", "총액"
	};
	
	
	public Object obj;
	Object rowData[][];
	
	DefaultTableModel model;
	
	ArrayList<Object> list = new ArrayList<Object>();
	
	
	// 정렬 기준
	private JComboBox<String> choiceList;
	
	public SaleManageView() {
		super("판매 내역");
		setLayout(null);
		
		Singleton s = Singleton.getInstance();
		
		
		// controller로 영수증 목록 취득() -> server에 switch문 중 6번을 실행하라!
		list = s.getOrderCtrl().select(6);
		
		
		JLabel label = new JLabel("판매 내역");
		label.setBounds(10, 10, 120, 15);
		add(label);
		
		
		
		int bbsNum = 1;
		
		rowData = new Object[list.size()][8];	// 테이블의 2차원배열
		
		for (int i = 0; i < list.size(); i++) {
			OrderedMenuDto dto1 = (OrderedMenuDto) list.get(i);
			int bev_price = 0;
			
			if(dto1.getMenu_type().equals("drink") && dto1.getCoupon() != 0) {
				bev_price = dto1.getPrice() * dto1.getCoupon();
			};
			//Date order_date, String id, String menu_type, String menu_name, int count, int coupon, int price
			rowData[i][0] = bbsNum;					// 글번호
			rowData[i][1] = dto1.getOrder_date();	// 주문일자
			rowData[i][2] = dto1.getId();			// 주문자 아이디
			rowData[i][3] = dto1.getMenu_type();	// 메뉴타입
			rowData[i][4] = dto1.getMenu_name();	// 주문메뉴
			rowData[i][5] = dto1.getCount();		// 수량
			rowData[i][6] = dto1.getCoupon();		// 음료쿠폰
			rowData[i][7] = (dto1.getPrice() * dto1.getCount()) - bev_price;
			bbsNum++;
		}
		
		// 테이블의 폭을 설정하기 위한 Model
		model = new DefaultTableModel(columNames, 0);
		model.setDataVector(rowData, columNames);
		
		// 테이블 생성
		jTable = new JTable(model);
		//jTable.addMouseListener(this);
		
		// 컬럼의 넓이를 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50);	// 글번호 폭
		jTable.getColumnModel().getColumn(1).setMaxWidth(500);	// 주문일자 폭
		jTable.getColumnModel().getColumn(2).setMaxWidth(100);	// 아이디 폭
		jTable.getColumnModel().getColumn(3).setMaxWidth(100);	// 메뉴타입 폭
		jTable.getColumnModel().getColumn(4).setMaxWidth(500);	// 주문메뉴 폭
		jTable.getColumnModel().getColumn(5).setMaxWidth(50);	// 수량 폭
		jTable.getColumnModel().getColumn(6).setMaxWidth(100);	// 음료쿠폰 폭
		jTable.getColumnModel().getColumn(7).setMaxWidth(100);	// 총액 폭
		
		// 테이블 안의 컬럼의 쓰기 설정(왼쪽, 오른쪽, 중간)
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		//"번호", "주문일자", "주문메뉴", "수량", "음료쿠폰", "총액"
		jTable.getColumn("번호").setCellRenderer(celAlignCenter);
		jTable.getColumn("주문일자").setCellRenderer(celAlignCenter);
		jTable.getColumn("아이디").setCellRenderer(celAlignCenter);
		jTable.getColumn("메뉴타입").setCellRenderer(celAlignCenter);
		jTable.getColumn("주문메뉴").setCellRenderer(celAlignCenter);
		jTable.getColumn("수량").setCellRenderer(celAlignCenter);
		jTable.getColumn("음료쿠폰").setCellRenderer(celAlignCenter);
		jTable.getColumn("총액").setCellRenderer(celAlignCenter);
		
		jScrPane = new JScrollPane(jTable);
		jScrPane.setBounds(10, 50, 600, 300);
		add(jScrPane);
		
		
		
		
		
		
		// 정렬기준 바꾸기
		String[] selects = new String[] {"날짜순", "별점순"};		
		choiceList = new JComboBox<>(selects);
		choiceList.setBounds(20, 380, 80, 20);
		choiceList.addActionListener(this);
		add(choiceList);
		
		
		
		
		
		
		
		
		
		setBounds(100, 100, 640, 480);
		getContentPane().setBackground(Color.lightGray);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> jcb = (JComboBox<String>)e.getSource();
		String selectedItem = jcb.getSelectedItem().toString();
		Singleton s = Singleton.getInstance();
		
		int number = 0;
		
		if(selectedItem.equals("날짜순")) {
			list = s.getOrderCtrl().select(6);
			number = 6;
		} else if(selectedItem.equals("별점순")) {
			list = s.getOrderCtrl().select(7);
			number = 7;
		}
		
		setList(list, number);
		
		
	}
	
	
	
	// 검색 후에 목록을 셋팅용
	public void setList(ArrayList<Object> list, int number) {
			
		if(number == 6) {
			
			int bbsNum = 1;
			
			rowData = new Object[list.size()][8];	// 테이블의 2차원배열
			
			for (int i = 0; i < list.size(); i++) {
				OrderedMenuDto dto1 = (OrderedMenuDto) list.get(i);
				int bev_price = 0;
				
				if(dto1.getMenu_type().equals("drink") && dto1.getCoupon() != 0) {
					bev_price = dto1.getPrice() * dto1.getCoupon();
				};
				//Date order_date, String id, String menu_type, String menu_name, int count, int coupon, int price
				rowData[i][0] = bbsNum;					// 글번호
				rowData[i][1] = dto1.getOrder_date();	// 주문일자
				rowData[i][2] = dto1.getId();			// 주문자 아이디
				rowData[i][3] = dto1.getMenu_type();	// 메뉴타입
				rowData[i][4] = dto1.getMenu_name();	// 주문메뉴
				rowData[i][5] = dto1.getCount();		// 수량
				rowData[i][6] = dto1.getCoupon();		// 음료쿠폰
				rowData[i][7] = (dto1.getPrice() * dto1.getCount()) - bev_price;
				bbsNum++;
			}
			
			// 테이블의 폭을 설정하기 위한 Model
			model.setDataVector(rowData, columNames);
			
			// 테이블 생성
			jTable = new JTable(model);
			//jTable.addMouseListener(this);
			
			// 컬럼의 넓이를 설정
			jTable.getColumnModel().getColumn(0).setMaxWidth(50);	// 글번호 폭
			jTable.getColumnModel().getColumn(1).setMaxWidth(500);	// 주문일자 폭
			jTable.getColumnModel().getColumn(2).setMaxWidth(100);	// 아이디 폭
			jTable.getColumnModel().getColumn(3).setMaxWidth(100);	// 메뉴타입 폭
			jTable.getColumnModel().getColumn(4).setMaxWidth(500);	// 주문메뉴 폭
			jTable.getColumnModel().getColumn(5).setMaxWidth(50);	// 수량 폭
			jTable.getColumnModel().getColumn(6).setMaxWidth(100);	// 음료쿠폰 폭
			jTable.getColumnModel().getColumn(7).setMaxWidth(100);	// 총액 폭
			
			// 테이블 안의 컬럼의 쓰기 설정(왼쪽, 오른쪽, 중간)
			DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
			celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
			
			//"번호", "주문일자", "주문메뉴", "수량", "음료쿠폰", "총액"
			jTable.getColumn("번호").setCellRenderer(celAlignCenter);
			jTable.getColumn("주문일자").setCellRenderer(celAlignCenter);
			jTable.getColumn("아이디").setCellRenderer(celAlignCenter);
			jTable.getColumn("메뉴타입").setCellRenderer(celAlignCenter);
			jTable.getColumn("주문메뉴").setCellRenderer(celAlignCenter);
			jTable.getColumn("수량").setCellRenderer(celAlignCenter);
			jTable.getColumn("음료쿠폰").setCellRenderer(celAlignCenter);
			jTable.getColumn("총액").setCellRenderer(celAlignCenter);
			
			jScrPane = new JScrollPane(jTable);
			jScrPane.setBounds(10, 50, 600, 300);
			add(jScrPane);
			
			jTable.setModel(model);
			
		}
		else if(number == 7) {
			rowData = new Object[list.size()][6];
			int bbsNum = 1;	
			String []columNames2 = {
					"번호", "메뉴타입", "메뉴이름", "총 판매 수량", "총 사용 쿠폰", "총 판매액"
			};
			
			for (int i = 0; i < list.size(); i++) {
				BestSaleMenuDto dto = (BestSaleMenuDto) list.get(i);
				
				//String menu_type, String menu_name, int total_sale, int total_coupon, int total_price
				rowData[i][0] = bbsNum;					// 글번호
				rowData[i][1] = dto.getMenu_type();		// 메뉴타입
				rowData[i][2] = dto.getMenu_name();		// 메뉴이름
				rowData[i][3] = dto.getTotal_sale();	// 총 판매 수량
				rowData[i][4] = dto.getTotal_coupon();	// 총 사용 쿠폰
				rowData[i][5] = dto.getTotal_price();	// 총 판매액
				bbsNum++;
				
			}			
			
			
			// 테이블의 폭을 설정하기 위한 Model(중요!)
			model.setDataVector(rowData, columNames2);
			
			// 테이블 생성
			jTable = new JTable(model);
			
			// 컬럼의 넓이를 설정
			jTable.getColumnModel().getColumn(0).setMaxWidth(50);	// 글번호 폭
			jTable.getColumnModel().getColumn(1).setMaxWidth(200);	// 메뉴타입 폭
			jTable.getColumnModel().getColumn(2).setMaxWidth(500);	// 메뉴이름 폭
			jTable.getColumnModel().getColumn(3).setMaxWidth(200);	// 총 판매 수량 폭
			jTable.getColumnModel().getColumn(4).setMaxWidth(200);	// 총 사용 쿠폰 폭
			jTable.getColumnModel().getColumn(5).setMaxWidth(200);	// 총 판매액 폭
			
			// 테이블 안의 컬럼의 쓰기 설정(왼쪽, 오른쪽, 중간)
			DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
			celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
			//"번호", "메뉴타입", "메뉴이름", "총 판매 수량", "총 사용 쿠폰", "총 판매액"
			jTable.getColumn("번호").setCellRenderer(celAlignCenter);
			jTable.getColumn("메뉴타입").setCellRenderer(celAlignCenter);
			jTable.getColumn("메뉴이름").setCellRenderer(celAlignCenter);
			jTable.getColumn("총 판매 수량").setCellRenderer(celAlignCenter);
			jTable.getColumn("총 사용 쿠폰").setCellRenderer(celAlignCenter);
			jTable.getColumn("총 판매액").setCellRenderer(celAlignCenter);
			
			jScrPane = new JScrollPane(jTable);
			jScrPane.setBounds(10, 50, 600, 300);
			add(jScrPane);
			
			
			jTable.setModel(model);
		}
	
	}
	
	

}
