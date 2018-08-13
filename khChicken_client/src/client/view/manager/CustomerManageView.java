package client.view.manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.singleton.Singleton;
import dto.CustomerManageDto;
import dto.OrderedMenuDto;

public class CustomerManageView extends JFrame implements ActionListener, MouseListener{
	
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

		setTableByCustomerOrder();

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
		
		
		
		
		
		
		
		
		setBounds(100, 100, 640, 480);
		getContentPane().setBackground(Color.lightGray);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton s = Singleton.getInstance();
		
		if(obj== backBtn) {
			s.getMemCtrl().manageView(this);
		}
		
	}


	public void setTableByCustomerOrder() {
		Singleton s = Singleton.getInstance();

		// controller로 영수증 목록 취득() -> server에 switch문 중 4번을 실행하라!
		ArrayList<CustomerManageDto> list = s.getCusCtrl().customerOrder(1);

		int bbsNum = 1;

		rowData = new Object[list.size()][6]; // 테이블의 2차원배열

		for (int i = 0; i < list.size(); i++) {
			CustomerManageDto dto1 = list.get(i);
			
			// A.ID, A.NAME, A.ADR, A.PHONE, B.주문건수
			rowData[i][0] = bbsNum; 				// 글번호
			rowData[i][1] = dto1.getId();			// 주문자 아이디
			rowData[i][2] = dto1.getName();			// 주문자 이름
			rowData[i][3] = dto1.getAdr();			// 주소
			rowData[i][4] = dto1.getPhone();		// 전화번호
			rowData[i][5] = dto1.getOrderCount();	// 주문건수
		
			bbsNum++;
		}

		// 테이블의 폭을 설정하기 위한 Model

		model.setDataVector(rowData, columNames);

		// 테이블 생성
		jTable.setModel(model);
		jTable.addMouseListener(this);

		// 컬럼의 넓이를 설정
		jTable.getColumnModel().getColumn(0).setMaxWidth(50); 	// 글번호 폭
		jTable.getColumnModel().getColumn(1).setMaxWidth(300);	// 아이디 폭
		jTable.getColumnModel().getColumn(2).setMaxWidth(100);	// 이름 폭
		jTable.getColumnModel().getColumn(3).setMaxWidth(100);	// 주소 폭
		jTable.getColumnModel().getColumn(4).setMaxWidth(500);	// 전화번호 폭
		jTable.getColumnModel().getColumn(5).setMaxWidth(100);	// 주문건수 폭

		for (int i = 0; i < model.getColumnCount(); i++) { // 칼럼 내용 가운데 정렬
			jTable.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);

		}
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		int rowNum = jTable.getSelectedRow();
		// 마우스 클릭한 열의 id를 구함
		String selectedId = jTable.getValueAt(rowNum, 1) + "";
		
		// 선택된 고객 정보를 열람할 수 있는 관리자view 만들기.
		
		
		
	}




	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
