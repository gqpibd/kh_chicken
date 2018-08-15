package client.view.manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.controller.StatisticsController;
import client.singleton.Singleton;
import dto.ReviewDto;
import utils.images.LabelEventListener;

public class CustomerManageView extends JFrame implements ActionListener {
	private JTable jTable; 
	private DefaultTableModel model;
	private DefaultTableCellRenderer celAlignCenter; // 셀 가운데 정렬용
	
	private JLabel backBtn; // 돌아가기 버튼
	private RowClickAdaptor rca = new RowClickAdaptor(); // 테이블 클릭했을 때 이벤트 리스너
	
	private static final String PATH = "images/manageView/";

	public CustomerManageView() {
		super("고객 관리");
		setContentPane(new JLabel(new ImageIcon(PATH + "customerView.jpg")));
		setResizable(false);
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

		// 고객 정보로 테이블 구성
		setTableByCustomerOrder();

		JScrollPane jScrPane = new JScrollPane(jTable);
		jScrPane.setBounds(10, 80, 600, 300);
		add(jScrPane);
		
		//돌아가기 backToMenu , 뒤로 return -- 상태에 따라 바뀜
		backBtn = new JLabel(new ImageIcon(PATH + "cusBackMenuBtn.jpg"));
		backBtn.setName("backToMenu");	
		backBtn.setBounds(520, 390, backBtn.getIcon().getIconWidth(), backBtn.getIcon().getIconHeight());
		backBtn.addMouseListener(new LabelEventListener(this));
		add(backBtn);

		setBounds(100, 100, 640, 480);
		getContentPane().setBackground(Color.lightGray);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		
		//돌아가기 backToMenu , 뒤로 return
		if (backBtn.getName().equals("backToMenu")) {	//돌아가기
			s.getMemCtrl().manageView(this);
		} else if (backBtn.getName().equals("return")) {	//뒤로
			backBtn.setName("backToMenu");	//돌아가기
			backBtn.setIcon(new ImageIcon(PATH + "cusBackMenuBtn.jpg"));
			setTableByCustomerOrder();
		}

	}

	public void setTableByCustomerOrder() {
		Singleton s = Singleton.getInstance();
		String columNames[] = { "번호", "아이디", "이름", "주소", "전화번호", "총 주문건수" };
		Object rowData[][];
		// controller로 영수증 목록 취득() -> server에 switch문 중 6번을 실행하라!
		ArrayList<String> list = (ArrayList<String>) s.getStaCtrl().getStatistics(StatisticsController.CUSTOMER);

		int bbsNum = 1;

		rowData = new Object[list.size()][6]; // 테이블의 2차원배열

		for (int i = 0; i < list.size(); i++) {
			String result[] = list.get(i).split("__");

			// A.ID, A.NAME, A.ADR, A.PHONE, B.주문건수
			rowData[i][0] = bbsNum; // 글번호
			rowData[i][1] = result[0]; // 주문자 아이디
			rowData[i][2] = result[1]; // 주문자 이름
			rowData[i][3] = result[2]; // 주소
			rowData[i][4] = result[3]; // 전화번호
			rowData[i][5] = result[4]; // 주문건수

			bbsNum++;
		}

		// 테이블의 폭을 설정하기 위한 Model

		model.setDataVector(rowData, columNames);

		// 테이블 생성
		jTable.setModel(model);
		jTable.addMouseListener(rca);

		// 컬럼의 넓이를 설정
		jTable.getColumnModel().getColumn(0).setPreferredWidth(50); // 글번호 폭
		jTable.getColumnModel().getColumn(1).setPreferredWidth(130); // 아이디 폭
		jTable.getColumnModel().getColumn(2).setPreferredWidth(130); // 이름 폭
		jTable.getColumnModel().getColumn(3).setPreferredWidth(500); // 주소 폭
		jTable.getColumnModel().getColumn(4).setPreferredWidth(200); // 전화번호 폭
		jTable.getColumnModel().getColumn(5).setPreferredWidth(120); // 주문건수 폭

		for (int i = 0; i < model.getColumnCount(); i++) { // 칼럼 내용 가운데 정렬
			jTable.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);

		}
	}

	class RowClickAdaptor extends MouseAdapter { // 각 고객의 자세한 정보를 볼 수 있게 한다.
		@Override
		public void mouseClicked(MouseEvent e) {
			// 특정 회원의 주문이력을 받아오기 위한 변수
			Object[] Obj_Rview = { "번호", "메뉴 이름", "주문한 날짜", "내가쓴 리뷰", "별점" };
			Object rdats[][];
			int rowNum = jTable.getSelectedRow();
			// 마우스 클릭한 열의 id를 구함
			String selectedId = jTable.getValueAt(rowNum, 1) + "";

			// 선택된 고객의 과거 주문이력 전부 받아오기
			ReviewDto revDto = new ReviewDto();
			revDto.setUserId(selectedId);
			List<ReviewDto> R_List; // 자기가 시켜먹은것의 대한 정보를 뽑아올 리스트
			Singleton s = Singleton.getInstance();
			R_List = s.getRevCtrl().selectByUserId(revDto);
			System.out.println("받아온 R_List 길이 = " + R_List.size());

			// 테이블에 깔기
			rdats = new Object[R_List.size()][5]; // 테이블의 2차원배열
			int bbsNum = 1;
			// private Object[] Obj_Rview = { "아이디", "메뉴 이름", "주문한 날짜", "내가쓴 리뷰", "별점" };
			for (int i = 0; i < R_List.size(); i++) {
				ReviewDto rdto = R_List.get(i);

				rdats[i][0] = bbsNum; // 아이디
				rdats[i][1] = rdto.getMenuName(); // 메뉴이름
				rdats[i][2] = rdto.getOrderDate(); // 주문한 날짜
				rdats[i][3] = rdto.getReview(); // 내가쓴리뷰
				rdats[i][4] = rdto.getScore(); // 내가준별점

				bbsNum++;
			}

			// 테이블의 폭을 설정하기 위한 Model
			model.setDataVector(rdats, Obj_Rview);

			// 테이블 생성
			jTable.setModel(model);
			jTable.removeMouseListener(rca);

			// 컬럼의 넓이를 설정
			jTable.getColumnModel().getColumn(0).setPreferredWidth(50); // 아이디 폭
			jTable.getColumnModel().getColumn(1).setPreferredWidth(200); // 메뉴이름 폭
			jTable.getColumnModel().getColumn(2).setPreferredWidth(200); // 주문한 날짜 폭
			jTable.getColumnModel().getColumn(3).setPreferredWidth(800); // 내가쓴리뷰 폭
			jTable.getColumnModel().getColumn(4).setPreferredWidth(50); // 내가준별점 폭

			for (int i = 0; i < model.getColumnCount(); i++) { // 칼럼 내용 가운데 정렬
				jTable.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);

			}

			backBtn.setName("return");
			backBtn.setIcon(new ImageIcon(PATH + "cusReturnBtn.jpg"));

		}
	}

}
