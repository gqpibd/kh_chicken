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

import client.controller.StatisticsController;
import client.dao.StatisticsDao;
import client.singleton.Singleton;
import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;

public class SaleManageView extends JFrame implements ActionListener {


	private JTable jTable;
	private JScrollPane jScrPane;
	private String columNames[] = { "번호", "주문일자", "아이디", "메뉴타입", "주문메뉴", "단가", "수량", "음료쿠폰", "총액" };

	

	Object rowData[][];
	DefaultTableModel model;
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
		String[] selects = new String[] { "날짜순", "매출순", "별점순" };
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
				setTableByDate();
			} else if (selectedItem.equals("매출순")) {
				setTableBySales();
			} else if (selectedItem.equals("별점순")) {
				setTableByScore();
			}
		} else if (e.getSource() == backBtn) {
			s.getMemCtrl().manageView(this);
		}
	}

	// 날짜순
	public void setTableByDate() {

		Singleton s = Singleton.getInstance();

		// controller로 영수증 목록 취득()
		// ArrayList<OrderedMenuDto> list = s.getStaCtrl().selectByDate(4);
		ArrayList<OrderedMenuDto> list = (ArrayList<OrderedMenuDto>) s.getStaCtrl()
				.getStatistics(StatisticsController.DATE);

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
			rowData[i][5] = dto1.getPrice(); // 단가
			rowData[i][6] = dto1.getCount(); // 수량
			rowData[i][7] = dto1.getCoupon(); // 음료쿠폰
			rowData[i][8] = (dto1.getPrice() * dto1.getCount()) - bev_price; // 총액

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
	}

	// 매출순
	public void setTableBySales() {
		Singleton s = Singleton.getInstance();
		// ArrayList<BestSaleMenuDto> list = s.getStaCtrl().selectBySales(5);
		ArrayList<BestSaleMenuDto> list = (ArrayList<BestSaleMenuDto>) s.getStaCtrl()
				.getStatistics(StatisticsController.SALES);

		rowData = new Object[list.size()][7];
		int bbsNum = 1;
		String[] columNames2 = { "번호", "메뉴타입", "메뉴이름", "단가", "총 판매 수량", "총 사용 쿠폰", "총 판매액" };

		for (int i = 0; i < list.size(); i++) {
			BestSaleMenuDto dto = list.get(i);

			// String menu_type, String menu_name, int price, int total_sale, int
			// total_coupon, int total_price
			rowData[i][0] = bbsNum; // 글번호
			rowData[i][1] = dto.getType(); // 메뉴타입
			rowData[i][2] = dto.getMenu_name(); // 메뉴이름
			rowData[i][3] = dto.getPrice(); // 단가
			rowData[i][4] = dto.getTotal_sale(); // 총 판매 수량
			rowData[i][5] = dto.getTotal_coupon(); // 총 사용 쿠폰
			rowData[i][6] = dto.getTotal_price(); // 총 판매액
			bbsNum++;
		}

		// 테이블의 폭을 설정하기 위한 Model(중요!)
		model.setDataVector(rowData, columNames2);

		// 컬럼의 넓이를 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50); // 글번호 폭
		jTable.getColumnModel().getColumn(1).setMaxWidth(200); // 메뉴타입 폭
		jTable.getColumnModel().getColumn(2).setMaxWidth(500); // 메뉴이름 폭
		jTable.getColumnModel().getColumn(3).setMaxWidth(200); // 단가 폭
		jTable.getColumnModel().getColumn(4).setMaxWidth(200); // 총 판매 수량 폭
		jTable.getColumnModel().getColumn(5).setMaxWidth(200); // 총 사용 쿠폰 폭
		jTable.getColumnModel().getColumn(6).setMaxWidth(200); // 총 판매액 폭

		for (int i = 0; i < model.getColumnCount(); i++) { // 칼럼 내용 가운데 정렬
			jTable.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);

		}

	}

	// 별점순
	public void setTableByScore() {
		Singleton s = Singleton.getInstance();
		ArrayList<BestSaleMenuDto> list = (ArrayList<BestSaleMenuDto>) s.getStaCtrl()
				.getStatistics(StatisticsController.SCORE);

		rowData = new Object[list.size()][8];
		int bbsNum = 1;
		String[] columNames2 = { "번호", "메뉴타입", "메뉴이름", "단가", "총 판매 수량", "총 사용 쿠폰", "총 판매액", "평균 별점" };

		for (int i = 0; i < list.size(); i++) {
			BestSaleMenuDto dto = list.get(i);

			// String menu_type, String menu_name, int price, int total_sale, int
			// total_coupon, int total_price, double score
			rowData[i][0] = bbsNum; // 글번호
			rowData[i][1] = dto.getType(); // 메뉴타입
			rowData[i][2] = dto.getMenu_name(); // 메뉴이름
			rowData[i][3] = dto.getPrice(); // 개당가
			rowData[i][4] = dto.getTotal_sale(); // 총 판매 수량
			rowData[i][5] = dto.getTotal_coupon(); // 총 사용 쿠폰
			rowData[i][6] = dto.getTotal_price(); // 총 판매액
			rowData[i][7] = dto.getScore(); // 평균 별점
			bbsNum++;
		}

		// 테이블의 폭을 설정하기 위한 Model(중요!)
		model.setDataVector(rowData, columNames2);

		// 컬럼의 넓이를 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50); // 글번호 폭
		jTable.getColumnModel().getColumn(1).setMaxWidth(200); // 메뉴타입 폭
		jTable.getColumnModel().getColumn(2).setMaxWidth(550); // 메뉴이름 폭
		jTable.getColumnModel().getColumn(3).setMaxWidth(200); // 개당가 폭
		jTable.getColumnModel().getColumn(4).setMaxWidth(200); // 총 판매 수량 폭
		jTable.getColumnModel().getColumn(5).setMaxWidth(200); // 총 사용 쿠폰 폭
		jTable.getColumnModel().getColumn(6).setMaxWidth(200); // 총 판매액 폭
		jTable.getColumnModel().getColumn(7).setMaxWidth(200); // 평균 별점 폭

		for (int i = 0; i < model.getColumnCount(); i++) { // 칼럼 내용 가운데 정렬
			jTable.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);

		}

	}

}
