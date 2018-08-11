package client.view.manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.singleton.Singleton;
import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;

public class SaleManageView extends JFrame implements ActionListener {
	private JTable jTable;
	private JScrollPane jScrPane;
	private String columNames[] = { "번호", "주문일자", "아이디", "메뉴타입", "주문메뉴", "수량", "음료쿠폰", "총액" };
	Object rowData[][];
	DefaultTableModel model;
	ArrayList<Object> list = new ArrayList<Object>();
	DefaultTableCellRenderer celAlignCenter; // 셀 가운데 정렬용
	JButton backBtn; // 돌아가기 버튼
	// 정렬 기준
	private JComboBox<String> choiceList;

	public SaleManageView() {
		super("판매 내역");
		setLayout(null);

		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		jTable = new JTable();
		celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(SwingConstants.CENTER);

		setTableByDate();

		jScrPane = new JScrollPane(jTable);
		jScrPane.setBounds(10, 50, 600, 300);
		add(jScrPane);

		JLabel label = new JLabel("판매 내역");
		label.setBounds(10, 10, 120, 15);
		add(label);

		backBtn = new JButton("돌아가기");
		backBtn.setBounds(500, 370, 90, 40);
		backBtn.addActionListener(this);
		add(backBtn);

		// 정렬기준 바꾸기
//		String[] selects = new String[] { "날짜순", "별점순" };
		String[] selects = new String[] { "날짜순", "매출순" };
		choiceList = new JComboBox<>(selects);
		choiceList.setBounds(20, 380, 80, 20);
		choiceList.addActionListener(this);
		add(choiceList);

		setBounds(100, 100, 640, 480);
		getContentPane().setBackground(Color.lightGray);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		if (e.getSource() == choiceList) {
			String selectedItem = choiceList.getSelectedItem().toString();

			if (selectedItem.equals("날짜순")) {
				list = s.getOrderCtrl().select(4);
				setTableByDate();
			//} else if (selectedItem.equals("별점순")) {
			} else if (selectedItem.equals("매출순")) {
				list = s.getOrderCtrl().select(5);
				setTableByScore();
			}
		} else if ( e.getSource() == backBtn) {
			s.getMemCtrl().manageView(this);
		}
	}

	public void setTableByDate() {
		Singleton s = Singleton.getInstance();

		// controller로 영수증 목록 취득() -> server에 switch문 중 6번을 실행하라!
		list = s.getOrderCtrl().select(6);

		int bbsNum = 1;

		rowData = new Object[list.size()][8]; // 테이블의 2차원배열

		for (int i = 0; i < list.size(); i++) {
			OrderedMenuDto dto1 = (OrderedMenuDto) list.get(i);
			int bev_price = 0;

			if (dto1.getMenu_type().equals("drink") && dto1.getCoupon() != 0) {
				bev_price = dto1.getPrice() * dto1.getCoupon();
			}
			// Date order_date, String id, String menu_type, String menu_name, int count,
			// int coupon, int price
			rowData[i][0] = bbsNum; // 글번호
			rowData[i][1] = dto1.getOrder_date(); // 주문일자
			rowData[i][2] = dto1.getId(); // 주문자 아이디
			rowData[i][3] = dto1.getMenu_type(); // 메뉴타입
			rowData[i][4] = dto1.getMenu_name(); // 주문메뉴
			rowData[i][5] = dto1.getCount(); // 수량
			rowData[i][6] = dto1.getCoupon(); // 음료쿠폰
			rowData[i][7] = (dto1.getPrice() * dto1.getCount()) - bev_price;
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
		jTable.getColumnModel().getColumn(5).setMaxWidth(50); // 수량 폭
		jTable.getColumnModel().getColumn(6).setMaxWidth(100); // 음료쿠폰 폭
		jTable.getColumnModel().getColumn(7).setMaxWidth(100); // 총액 폭

		for (int i = 0; i < model.getColumnCount(); i++) { // 칼럼 내용 가운데 정렬
			jTable.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);

		}
	}

	public void setTableByScore() {
		rowData = new Object[list.size()][6];
		int bbsNum = 1;
		String[] columNames2 = { "번호", "메뉴타입", "메뉴이름", "총 판매 수량", "총 사용 쿠폰", "총 판매액" };

		for (int i = 0; i < list.size(); i++) {
			BestSaleMenuDto dto = (BestSaleMenuDto) list.get(i);

			// String menu_type, String menu_name, int total_sale, int total_coupon, int
			// total_price
			rowData[i][0] = bbsNum; // 글번호
			rowData[i][1] = dto.getMenu_type(); // 메뉴타입
			rowData[i][2] = dto.getMenu_name(); // 메뉴이름
			rowData[i][3] = dto.getTotal_sale(); // 총 판매 수량
			rowData[i][4] = dto.getTotal_coupon(); // 총 사용 쿠폰
			rowData[i][5] = dto.getTotal_price(); // 총 판매액
			bbsNum++;
		}

		// 테이블의 폭을 설정하기 위한 Model(중요!)
		model.setDataVector(rowData, columNames2);

		// 컬럼의 넓이를 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50); // 글번호 폭
		jTable.getColumnModel().getColumn(1).setMaxWidth(200); // 메뉴타입 폭
		jTable.getColumnModel().getColumn(2).setMaxWidth(500); // 메뉴이름 폭
		jTable.getColumnModel().getColumn(3).setMaxWidth(200); // 총 판매 수량 폭
		jTable.getColumnModel().getColumn(4).setMaxWidth(200); // 총 사용 쿠폰 폭
		jTable.getColumnModel().getColumn(5).setMaxWidth(200); // 총 판매액 폭

		for (int i = 0; i < model.getColumnCount(); i++) { // 칼럼 내용 가운데 정렬
			jTable.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);

		}

	}

}
