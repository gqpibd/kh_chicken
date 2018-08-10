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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import client.singleton.Singleton;
import dto.MemberDto;
import dto.MenuDto;
import dto.OrderedMenuDto;

public class order extends JFrame implements ActionListener, ItemListener {

	JButton btnArrPlus[] = new JButton[6];
	JButton btnArr[] = new JButton[6];

	private final Object[] colNames = { "ChkBox", "메뉴", "가격", "수량", " ", " " };
	Object[][] datas;

	private final Object[] OrderCol = { "ID", "주소", "전화번호", "날짜" };
	Object[][] OrderData;

	DefaultTableModel model; // 테이블의 넓이 설정
	List<dto.MemberDto> mList;
	List<dto.OrderedMenuDto> oList;
	List<MenuDto> nList;
	JTable menuTable;

	public order() {
		super("주문 내역");
		setLayout(null);

		JLabel lblNewLabel = new JLabel("<주문 정보>");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(14, 12, 129, 38);
		add(lblNewLabel);

		JLabel label_4 = new JLabel("<주문 내역>");
		label_4.setFont(new Font("굴림", Font.BOLD, 18));
		label_4.setBounds(14, 150, 129, 38);
		add(label_4);

		// 쿠폰------
		JLabel label_6 = new JLabel("<음료 쿠폰>");
		label_6.setFont(new Font("굴림", Font.BOLD, 18));
		label_6.setBounds(14, 378, 129, 38);
		add(label_6);

		// 사용 가능한 쿠폰의 수를 보여줄 라벨
		
		JLabel lblNewLabel_3 = new JLabel("0" + "장");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(14, 428, 62, 18);
		add(lblNewLabel_3);

		// 눌렀을 때 memberDto의 Coupon - 1
		// 위의 변수 - 1 : 라벨에 바로 적용
		JButton btnNewButton_1 = new JButton("사용");
		btnNewButton_1.setBounds(80, 424, 63, 27);
		add(btnNewButton_1);

		// memberDto에서 쿠폰 수를 바로 받아와서 라벨에 띄우기
		// SELECT 로 COUNT(리뷰) / 3 + 1 을 보여줄껀데.
		JLabel lblNewLabel_4 = new JLabel("사용 가능한 쿠폰 : " + "0" + "장");
		lblNewLabel_4.setBounds(14, 454, 192, 49);
		add(lblNewLabel_4);

		// 주문자와 배달지에 대한 테이블
		setDeliveryTable();

		// 주문 상세내역 테이블
		setOrderTable();

		JLabel lblNewLabel_5 = new JLabel("결제 금액");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(347, 408, 158, 49);
		add(lblNewLabel_5);

		// 결제금액을 보여줄 라벨
		JLabel label_7 = new JLabel("15000원"); // 결제금액 == 치킨값 * 수량 넣기.
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("굴림", Font.BOLD, 18));
		label_7.setBounds(347, 460, 158, 49);
		add(label_7);

		// Order DB와 member DB에 INSERT 하는 버튼
		JButton paymentBtn = new JButton("결제");
		paymentBtn.setFont(new Font("굴림", Font.BOLD, 18));
		paymentBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 관리자 DB에 INSERT시켜주고, 확인창을 띄워주자.
				int yesNoBtn = JOptionPane.showConfirmDialog(null, "이대로 주문하시겠습니까?", "최종 주문 확인",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (yesNoBtn == JOptionPane.YES_OPTION) {
					// DB에 INSERT 해주고 주문창 닫기
					JOptionPane.showMessageDialog(null, "DB INSERT");
					// this.dispose();
				}
			}
		});
		paymentBtn.setBounds(510, 414, 105, 88);
		add(paymentBtn);

		setBounds(100, 100, 680, 600);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setDeliveryTable() {
		Singleton s = Singleton.getInstance();
		// 주문한 사람의 개인정보를 테이블로 띄워줌
		// 현재날짜 : 주문한 날짜
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("YY/MM/dd hh:mm:ss");

		// 주문자 정보를 받아올 2차원 배열 ( ID , ADR, PHONE )
		mList = s.memCtrl.memDao.insert();
		OrderData = new Object[mList.size()][4]; // 테이블의 2차원배열

		for (int i = 0; i < mList.size(); i++) {
			MemberDto Mdto = mList.get(i);

			OrderData[i][0] = Mdto.getId(); // 아이디 ,
			OrderData[i][1] = Mdto.getAddress(); // 주소
			OrderData[i][2] = Mdto.getPhone();
			OrderData[i][3] = date.format(today); // 현재날짜
		}

		// 테이블의 폭을 설정하기 위한 Model
		model = new DefaultTableModel(OrderCol, 0);
		model.setDataVector(OrderData, OrderCol);
		JTable orderTable = new JTable(model);

		// 컬럼의 넓이를 설정
		orderTable.getColumnModel().getColumn(0).setMaxWidth(100); // ID 폭
		orderTable.getColumnModel().getColumn(1).setMaxWidth(400); // 주소 폭
		orderTable.getColumnModel().getColumn(2).setMaxWidth(400); // 폰번호 폭
		orderTable.getColumnModel().getColumn(3).setMaxWidth(300); // 날짜 폭

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
	}

	private void setOrderTable() {
		// 주문한 메뉴를 받아와서 테이블로 띄워줌
		Singleton s = Singleton.getInstance();

		/////////
		oList = s.ordCtrl.ordDao.insert();
		datas = new Object[oList.size()][6]; // 테이블의 2차원배열

		for (int i = 0; i < oList.size(); i++) {
			OrderedMenuDto Odto = oList.get(i);

			// ChkBox", "메뉴", "가격", "수량", " +", "- "		//여기도 DB에서 SELECT로 검색해서 가져와야 되나...
			// ChkBox
			datas[i][0] = true; //초기 값 설정 해주고.
			datas[i][1] = Odto.getMenu_name(); // 메뉴
			datas[i][2] = Odto.getPrice(); // 가격
			datas[i][3] = Odto.getCount(); // 수량
			datas[i][4] = i;
			datas[i][5] = i;
		}
		////////

		DefaultTableModel dtm = new DefaultTableModel(datas, colNames); // DefaultTableModel
		menuTable = new JTable(dtm);

		// checkbox
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent // 셀렌더러
			(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JCheckBox box = new JCheckBox();
				box.setSelected(((Boolean) value).booleanValue()); // 체크박스가 선택 되었는가?
				box.setHorizontalAlignment(JLabel.CENTER);
				return box;
			}
		};

		menuTable.getColumn("ChkBox").setCellRenderer(dcr); // ChkBox라는 곳에 넣을꺼야.
		JCheckBox box = new JCheckBox(); // checkbox를 중앙정렬
		box.setHorizontalAlignment(JLabel.CENTER);
		menuTable.getColumn("ChkBox").setCellEditor(new DefaultCellEditor(box)); //
		// 체크박스 = box, ChkBox 에 넣겟다. 설정하곗다.

		menuTable.getColumnModel().getColumn(4).setCellRenderer(new TableCell("+"));
		menuTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor("+"));
		menuTable.getColumnModel().getColumn(5).setCellRenderer(new TableCell("-"));
		menuTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor("-"));

		JScrollPane scrollPaneMenu = new JScrollPane();
		scrollPaneMenu.setBounds(14, 200, 532, 166);
		add(scrollPaneMenu);
		scrollPaneMenu.setViewportView(menuTable);
		pack();
	}

	// button
	class TableCell extends JButton implements TableCellRenderer {

		public TableCell(String sign) {
			setOpaque(true);
			setText(sign);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return this;
		}
	}

	public class ButtonEditor extends DefaultCellEditor {
		protected JButton button;
		int row = 0;

		public ButtonEditor(String sign) {
			super(new JCheckBox());
			button = new JButton(sign);
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int num = (int) menuTable.getModel().getValueAt(row, 3);
					if (sign.equals("+")) {
						menuTable.getModel().setValueAt(num + 1, row, 3);
					} else {
						if (num <= 1)
							return;
						menuTable.getModel().setValueAt(num - 1, row, 3);
					}

				}
			});
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			this.row = row;
			return button;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
