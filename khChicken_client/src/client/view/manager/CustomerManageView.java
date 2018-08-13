package client.view.manager;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.singleton.Singleton;
import dto.CustomerManageDto;
import dto.OrderedMenuDto;

public class CustomerManageView extends JFrame{
	
	private JTable jTable;
	private JScrollPane jScrPane;
	private String columNames[] = { "번호", "아이디", "이름", "주소", "전화번호", "총 주문건수" };
	Object rowData[][];
	DefaultTableModel model;
//	ArrayList<Object> list = new ArrayList<Object>();
	DefaultTableCellRenderer celAlignCenter; // 셀 가운데 정렬용
	JButton backBtn; // 돌아가기 버튼
	
	
	public CustomerManageView() {
		super("고객 관리");
		setLayout(null);
		
		
		
		Singleton s = Singleton.getInstance();

		// controller로 영수증 목록 취득() -> server에 switch문 중 4번을 실행하라!
		//ArrayList<CustomerManageDto> list = s.getOrderCtrl().selectByDate(4);

		int bbsNum = 1;

		rowData = new Object[list.size()][9]; // 테이블의 2차원배열

		for (int i = 0; i < list.size(); i++) {
			OrderedMenuDto dto1 = list.get(i);
			int bev_price = 0;
			
			// 음료주문의 경우, 쿠폰을 썼을때 가격*쿠폰수량만큼 할인가를 적용.
			if (dto1.getType().equals("음료") && dto1.getCoupon() != 0) {
				bev_price = dto1.getPrice() * dto1.getCoupon();
			}
			// Date order_date, String id, String menu_type, String menu_name, int count,
			// int coupon, int price
			rowData[i][0] = bbsNum; // 글번호
			rowData[i][1] = dto1.getOrder_date(); // 주문일자
			rowData[i][2] = dto1.getId(); // 주문자 아이디
			rowData[i][3] = dto1.getType(); // 메뉴타입
			rowData[i][4] = dto1.getMenu_name(); // 주문메뉴
			rowData[i][5] = dto1.getPrice();	// 개당가
			rowData[i][6] = dto1.getCount(); // 수량
			rowData[i][7] = dto1.getCoupon(); // 음료쿠폰
			rowData[i][8] = (dto1.getPrice() * dto1.getCount()) - bev_price;	// 총액
		
			bbsNum++;
		}

		// 테이블의 폭을 설정하기 위한 Model

		model.setDataVector(rowData, columNames);

		// 테이블 생성
		jTable.setModel(model);

		// 컬럼의 넓이를 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50); // 글번호 폭
		jTable.getColumnModel().getColumn(1).setMaxWidth(500); // 주문일자 폭
		jTable.getColumnModel().getColumn(2).setMaxWidth(100); // 아이디 폭
		jTable.getColumnModel().getColumn(3).setMaxWidth(100); // 메뉴타입 폭
		jTable.getColumnModel().getColumn(4).setMaxWidth(500); // 주문메뉴 폭
		jTable.getColumnModel().getColumn(5).setMaxWidth(50); // 개당가 폭
		jTable.getColumnModel().getColumn(6).setMaxWidth(50); // 수량 폭
		jTable.getColumnModel().getColumn(7).setMaxWidth(100); // 음료쿠폰 폭
		jTable.getColumnModel().getColumn(8).setMaxWidth(100); // 총액 폭

		for (int i = 0; i < model.getColumnCount(); i++) { // 칼럼 내용 가운데 정렬
			jTable.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);

		}
		
		
		
		
		setBounds(100, 100, 640, 480);
		getContentPane().setBackground(Color.lightGray);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}
	

}
