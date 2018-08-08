package client.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.dao.MemberDao;
import client.dto.MemberDto;
import client.dto.OrderedMenuDto;

public class order extends JFrame implements ActionListener, ItemListener {

	JButton btnArrPlus[]= new JButton[6];
	JButton btnArr[]= new JButton[6];
	
	private final Object[] colNames = { "ChkBox", "메뉴", "가격", "수량" };
	Object[][] datas = { { false, "후라이드치킨", "13000", 1 }, { true, "양념치킨", "15000", 1 } };
	
	private final Object[] OrderCol = { "ID", "주소", "전화번호", "날짜"};
	//ID 주소 전화번호 날짜==SYSDATE
	Object[][] OrderData = {
			{"Kong", "강남 역세권", "010-2222-2222", "2018-01-08"}
	};
	
	DefaultTableModel model;	// 테이블의 넓이 설정
	List<MemberDto> list;	
	
	public order() {
		super("주문 내역");
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<주문 정보>");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(14, 12, 129, 38);
		add(lblNewLabel);
		
		/*JLabel label = new JLabel("주문자 ID");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(14, 51, 62, 18);
		add(label);
		
		JLabel label_1 = new JLabel("주소");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(125, 51, 223, 18);
		add(label_1);
		
		JLabel label_2 = new JLabel("전화번호");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(418, 51, 62, 18);
		add(label_2);
		
		JLabel label_3 = new JLabel("날짜");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(553, 51, 62, 18);
		add(label_3);
		*/
		
		JLabel label_4 = new JLabel("<주문 내역>");
		label_4.setFont(new Font("굴림", Font.BOLD, 18));
		label_4.setBounds(14, 150, 129, 38);
		add(label_4);

		//수량을 정해줄 버튼들 [ -1 ]
	   String btnName[] = { "-", "-", "-", "-", "-", "-" };
	      
	      for (int i = 0; i < btnArr.length; i++) {         
	         btnArr[i] = new JButton(btnName[i]);
	         btnArr[i].setBounds(602, 218+(24*i), 46, 27);
	         btnArr[i].addActionListener(this);
	         add(btnArr[i]);
	      }
	      
	      //[ +1 ]
	    String btnNamePl[] = { "+", "+", "+", "+", "+", "+" };
	      
	    for (int i = 0; i < btnArrPlus.length; i++) {         
    	  btnArrPlus[i] = new JButton(btnNamePl[i]);
    	  btnArrPlus[i].setBounds(553, 218+(24*i), 46, 27);
    	  btnArr[i].addActionListener(this);
    	  add(btnArrPlus[i]);
	    }

	    //쿠폰------
	    JLabel label_6 = new JLabel("<음료 쿠폰>");
		label_6.setFont(new Font("굴림", Font.BOLD, 18));
		label_6.setBounds(14, 378, 129, 38);
		add(label_6);
		
		//사용 가능한 쿠폰의 수를 보여줄 라벨
		
		JLabel lblNewLabel_3 = new JLabel("0"+"장");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(14, 428, 62, 18);
		add(lblNewLabel_3);
		
		//눌렀을 때 memberDto의 Coupon - 1
		JButton btnNewButton_1 = new JButton("사용");
		btnNewButton_1.setBounds(80, 424, 63, 27);
		add(btnNewButton_1);
		
		/*JButton button_5 = new JButton("조회");
		button_5.setBounds(143, 424, 63, 27);
		add(button_5);*/
		
		//memberDto에서 쿠폰 수를 바로 받아와서 라벨에 띄우기
		JLabel lblNewLabel_4 = new JLabel("사용 가능한 쿠폰 : "+"0"+"장");
		lblNewLabel_4.setBounds(14, 454, 192, 49);
		add(lblNewLabel_4);
		
		//주문한 메뉴를 받아와서 테이블로 띄워줌
		DefaultTableModel dtm = new DefaultTableModel(datas, colNames);		//DefaultTableModel 
		JTable menuTable = new JTable(dtm);
		
		menuTable.getColumn("ChkBox").setCellRenderer(dcr);		//setCellRenderer
		JCheckBox box = new JCheckBox();	//checkbox를 중앙정렬
		box.setHorizontalAlignment(JLabel.CENTER);
		menuTable.getColumn("ChkBox").setCellEditor(new DefaultCellEditor(box));		//setCellEditor	DefaultCellEditor
		
		JScrollPane scrollPaneMenu = new JScrollPane();
		scrollPaneMenu.setBounds(14, 200, 532, 166);
		add(scrollPaneMenu);
		
		scrollPaneMenu.setViewportView(menuTable);
		pack();	
		
		//주문한 사람의 개인정보를 테이블로 띄워줌
		//현재날짜 : 주문한 날짜
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("YYYY/MM/DD");
		
		//주문자 정보를 받아올 2차원 배열 ( ID , ADR, PHONE )
		//dao
		//list 
		//OrderData = new Object[list.size()][3];	// 테이블의 2차원배열
		
		
		/*
		for (int i = 0; i < list.size(); i++) {
			MemberDto Odto = list.get(i);
			//BbsDto dto = list.get(i);
			
			OrderData[i][0] = Odto.getId();	// 아이디 , 
			OrderData[i][1] = Odto.getAddress();	// 주소
			OrderData[i][2] = Odto.getPhone();
			OrderData[i][3] = date.format(today); //현재날짜
		}
		 */			
		
		// 테이블의 폭을 설정하기 위한 Model
		model = new DefaultTableModel(OrderCol, 0);
		model.setDataVector(OrderData, OrderCol);
		
		// 테이블 생성
		JTable orderTable = new JTable(model);
		
		// 컬럼의 넓이를 설정
		orderTable.getColumnModel().getColumn(0).setMaxWidth(100);	//ID 폭
		orderTable.getColumnModel().getColumn(1).setMaxWidth(400);	// 주소 폭
		orderTable.getColumnModel().getColumn(2).setMaxWidth(400);	// 폰번호 폭
		orderTable.getColumnModel().getColumn(3).setMaxWidth(300);	//날짜 폭
		
		// 테이블 안의 컬럼의 쓰기 설정(왼쪽, 오른쪽, 중간)
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		orderTable.getColumn("ID").setCellRenderer(celAlignCenter);
		orderTable.getColumn("주소").setCellRenderer(celAlignCenter);
		orderTable.getColumn("전화번호").setCellRenderer(celAlignCenter);
		orderTable.getColumn("날짜").setCellRenderer(celAlignCenter);
		
		JScrollPane scrollPaneOrder = new JScrollPane();
		scrollPaneOrder.setBounds(14, 83, 634, 66);
		add(scrollPaneOrder);
		scrollPaneOrder.setViewportView(orderTable);
		
		////
		
		JLabel lblNewLabel_5 = new JLabel("결제 금액");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(347, 408, 158, 49);
		add(lblNewLabel_5);
		
		//결제금액을 보여줄 라벨
		JLabel label_7 = new JLabel("15000원");	//결제금액 == 치킨값 * 수량 넣기.
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("굴림", Font.BOLD, 18));
		label_7.setBounds(347, 460, 158, 49);
		add(label_7);
		
		//Order DB와 member DB에 INSERT 하는 버튼
		JButton paymentBtn = new JButton("결제");
		paymentBtn.setFont(new Font("굴림", Font.BOLD, 18));
		paymentBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 관리자 DB에 INSERT시켜주고, 확인창을 띄워주자.
				int yesNoBtn = JOptionPane.showConfirmDialog(null, "이대로 주문하시겠습니까?", "최종 주문 확인", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (yesNoBtn == JOptionPane.YES_OPTION) {
					//DB에 INSERT 해주고 주문창 닫기
					JOptionPane.showMessageDialog(null, "DB INSERT");
					//this.dispose();
				}
			}
		});
		paymentBtn.setBounds(510, 414, 105, 88);
		add(paymentBtn);
		
		setBounds(100, 100, 680, 600);
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponent // 셀렌더러
		  (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JCheckBox box = new JCheckBox();
			box.setSelected(((Boolean) value).booleanValue());		//체크박스가 선택 되었는가?
			box.setHorizontalAlignment(JLabel.CENTER);
			return box;
		}
	};
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		

	}

}
