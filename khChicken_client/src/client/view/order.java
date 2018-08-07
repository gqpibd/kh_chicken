package client.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class order extends JFrame implements ActionListener, ItemListener {

	JButton btnArrPlus[]= new JButton[6];
	JButton btnArr[]= new JButton[6];
	
	private final Object[] colNames = { "ChkBox", "메뉴", "가격", "수량" };
	private Object[][] datas = { { false, "후라이드치킨", "13000", 1 }, { true, "양념치킨", "15000", 1 } };
	
	public order() {
		super("주문 내역");
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<주문 정보>");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(14, 12, 129, 38);
		add(lblNewLabel);
		
		JLabel label = new JLabel("주문자 ID");
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
		
		JLabel label_4 = new JLabel("<주문 내역>");
		label_4.setFont(new Font("굴림", Font.BOLD, 18));
		label_4.setBounds(14, 150, 129, 38);
		add(label_4);

		//수량을 정해줄 버튼들 [ -1 ]
		
	   //btnArr[] = new JButton[6];
	   String btnName[] = { "-", "-", "-", "-", "-", "-" };
	      
	      for (int i = 0; i < btnArr.length; i++) {         
	         btnArr[i] = new JButton(btnName[i]);
	         btnArr[i].setBounds(602, 218+(24*i), 46, 27);
	         btnArr[i].addActionListener(this);
	         add(btnArr[i]);
	      }
	      
	      //[ +1 ]
	  	//btnArrPlus[] = new JButton[6];
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
		
		//사용할 쿠폰의 수를 보여줄 라벨
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
	/*	JScrollPane scrollPaneMenu = new JScrollPane();
		scrollPaneMenu.setBounds(14, 200, 532, 166);
		add(scrollPaneMenu);
		
		JTable menuTable = new JTable();
		scrollPaneMenu.setColumnHeaderView(menuTable);*/
		///////////
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
		/*JScrollPane scrollPaneOrder = new JScrollPane();
		scrollPaneOrder.setBounds(14, 83, 634, 66);
		add(scrollPaneOrder);
		
		JTable orderTable = new JTable();
		scrollPaneOrder.setViewportView(orderTable);*/
		////
		DefaultTableModel dtm2 = new DefaultTableModel(datas, colNames);		//DefaultTableModel 
		JTable orderTable = new JTable(dtm2);
		
		orderTable.getColumn("ChkBox").setCellRenderer(dcr);		//setCellRenderer
		JCheckBox box2 = new JCheckBox();	//checkbox를 중앙정렬
		box.setHorizontalAlignment(JLabel.CENTER);
		orderTable.getColumn("ChkBox").setCellEditor(new DefaultCellEditor(box));		//setCellEditor	DefaultCellEditor
		
		JScrollPane scrollPaneOrder = new JScrollPane();
		scrollPaneOrder.setBounds(14, 200, 532, 166);
		add(scrollPaneOrder);
		
		scrollPaneOrder.setViewportView(orderTable);
		pack();	
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
		JButton btnNewButton_2 = new JButton("결제");
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 18));
		btnNewButton_2.setBounds(510, 414, 105, 88);
		add(btnNewButton_2);
		
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
