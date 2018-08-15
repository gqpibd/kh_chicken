package client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import client.singleton.Singleton;
import dto.MemberDto;
import dto.OrderedMenuDto;

public class OrderView extends JFrame implements ActionListener {
	private int beverageCounts = 0;
	int myCoupons = 0;
	private JLabel availableCoupons;
	private JLabel couponLabel;

	private JButton addSearchBtn;
	private JButton paymentBtn;
	private JButton cancelBtn;
	private DefaultTableModel dtm; // 상세 주문내역을 보관하고 있는 모델
	private List<OrderedMenuDto> oList;
	private JTable menuTable;

	private JTextField nameField;
	private JTextField phoneField1;
	private JTextField phoneField2;
	private JTextField phoneField3;
	private JTextField addressField;
	private JTextField timeField;
	private JTextField couponField;

	public OrderView() {
		getContentPane().setLocation(0, 212);
		setTitle("주문 내역");
		getContentPane().setLayout(null);

		setBounds(100, 100, 646, 583);

		JLabel lblNewLabel = new JLabel("<주문자 정보>");
		lblNewLabel.setFont(new Font("나눔고딕", Font.PLAIN, 22));
		lblNewLabel.setBounds(26, 20, 130, 25);
		getContentPane().add(lblNewLabel);

		JLabel label_4 = new JLabel("<주문 내역>");
		label_4.setFont(new Font("나눔고딕", Font.PLAIN, 22));
		label_4.setBounds(26, 208, 120, 25);
		getContentPane().add(label_4);

		// 주문자와 배달지정보
		setDeliveryInfo();

		// 주문 상세내역 테이블
		setOrderTable();

		// 쿠폰------
		JLabel label_6 = new JLabel("<음료 쿠폰>");
		label_6.setFont(new Font("나눔고딕", Font.PLAIN, 22));
		label_6.setBounds(399, 30, 151, 25);
		getContentPane().add(label_6);

		// 사용 가능한 쿠폰의 수를 보여줄 라벨

		JLabel lblNewLabel_3 = new JLabel("장");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(51, 506, 41, 18);
		getContentPane().add(lblNewLabel_3);

		// 눌렀을 때 memberDto의 Coupon - 1
		// 위의 변수 - 1 : 라벨에 바로 적용

		JButton couponUseBtn = new JButton("사용");
		couponUseBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		couponUseBtn.setBounds(531, 150, 64, 22);
		getContentPane().add(couponUseBtn);

		// memberDto에서 쿠폰 수를 바로 받아와서 라벨에 띄우기
		// SELECT 로 COUNT(리뷰) / 3 + 1 을 보여줄껀데.
		Singleton s = Singleton.getInstance();
		myCoupons = Integer.parseInt(s.getOrderCtrl().getCoupons());
		if (s.getMemCtrl().getLoginId() != null) { // 회원이면
			myCoupons++;
		}

		couponLabel = new JLabel();
		couponLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		couponLabel.setBounds(399, 97, 130, 15);
		//couponLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		couponLabel.setText("보유 쿠폰 : " + myCoupons + " 장");
		couponLabel.setBounds(14, 452, 130, 18);
		getContentPane().add(couponLabel);

		availableCoupons = new JLabel();
		//availableCoupons.setHorizontalAlignment(SwingConstants.RIGHT);
		availableCoupons.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		availableCoupons.setBounds(480, 154, 26, 15);
		getContentPane().add(availableCoupons);

		setUsableCouponCount();
		
		JLabel lblNewLabel_1 = new JLabel("마스코트");
		lblNewLabel_1.setBounds(317, 400, 282, 106);
		getContentPane().add(lblNewLabel_1);
		
		// Order DB와 member DB에 INSERT 하는 버튼
		paymentBtn = new JButton("결제");
		paymentBtn.setFont(new Font("나눔고딕", Font.PLAIN, 22));
		paymentBtn.setBounds(503, 516, 105, 35);
		paymentBtn.addActionListener(this);
		getContentPane().add(paymentBtn);

		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(this);
		cancelBtn.setFont(new Font("나눔고딕", Font.PLAIN, 22));
		cancelBtn.setBounds(399, 516, 95, 35);
		getContentPane().add(cancelBtn);


		JLabel paytitle_label = new JLabel("결제금액");
		paytitle_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		paytitle_label.setBounds(26, 494, 95, 25);
		getContentPane().add(paytitle_label);
		
		JLabel lblNewLabel_5 = new JLabel("결제 금액");
		lblNewLabel_5.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(26, 430, 82, 15);
		getContentPane().add(lblNewLabel_5);
		//lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);

		// 결제금액을 보여줄 라벨
		JLabel label_7 = new JLabel("15000원"); // 결제금액 == 치킨값 * 수량 넣기.
		label_7.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		label_7.setBounds(135, 494, 89, 20);
		getContentPane().add(label_7);
		//label_7.setHorizontalAlignment(SwingConstants.CENTER);
		

		JLabel label = new JLabel("주문 금액");
		//label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("나눔고딕", Font.PLAIN, 22));
		label.setBounds(26, 430, 82, 15);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("할인 금액");
		//label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		label_1.setBounds(26, 455, 57, 15);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("15000원");//할인금액
		//label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		label_2.setBounds(135, 455, 57, 15);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("15000원");	//기존결제금액
		//label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		label_3.setBounds(135, 430, 69, 15);
		getContentPane().add(label_3);


		couponField = new JTextField();
		couponField.setBounds(399, 150, 95, 21);
		getContentPane().add(couponField);
		couponField.setColumns(10);
		
		setVisible(true);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	private void setUsableCouponCount() {
		int num = 0;
		if (myCoupons > beverageCounts) {
			num = beverageCounts;
		} else {
			num = myCoupons;
		}
		availableCoupons.setText("적용 가능 쿠폰 : " + num + " 장");

	}

	private void setDeliveryInfo() {

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		nameLabel.setBounds(26, 68, 39, 15);
		//nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(nameLabel);

		JLabel addressLabel = new JLabel("주소");
		addressLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		addressLabel.setBounds(26, 126, 51, 15);
		//addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(addressLabel);

		JLabel phoneLabel = new JLabel("연락처");
		phoneLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		phoneLabel.setBounds(26, 97, 57, 15);
		//phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(phoneLabel);


		JLabel timeLabel = new JLabel("주문시간");
		timeLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		timeLabel.setBounds(26, 154, 57, 15);
		getContentPane().add(timeLabel);

		// ---------------------------내용 설정---------------------------------- //
		Singleton s = Singleton.getInstance();
		MemberDto dto = s.getMemCtrl().getCurrentUser();

		if (dto == null) {
			dto = new MemberDto();
		}
		nameField = new JTextField(dto.getName());
		nameField.setBounds(88, 65, 268, 21);
		getContentPane().add(nameField);
		nameField.setColumns(10);

		phoneField1 = new JTextField(dto.getPhone().split("-")[0]);
		phoneField1.setBounds(88, 93, 50, 21);
		phoneField1.setBounds(350, 60, 57, 21);
		getContentPane().add(phoneField1);
		phoneField1.setColumns(10);

		phoneField2 = new JTextField(dto.getPhone().split("-")[1]);
		phoneField2.setColumns(10);
		phoneField2.setBounds(140, 60, 50, 21);
		getContentPane().add(phoneField2);

		phoneField3 = new JTextField(dto.getPhone().split("-")[2]);
		phoneField3.setColumns(10);
		phoneField3.setBounds(200, 60, 50, 21);
		getContentPane().add(phoneField3);

		addressField = new JTextField(dto.getAddress());
		addressField.setBounds(88, 122, 268, 21);
		getContentPane().add(addressField);
		addressField.setColumns(10);

		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("YYYY년 MM월 dd일 HH:mm");

		timeField = new JTextField(date.format(today));
		timeField.setEditable(false);
		timeField.setBounds(88, 150, 268, 21);		
		getContentPane().add(timeField);
		timeField.setColumns(10);

		addSearchBtn = new JButton("검색");
		addSearchBtn.setBounds(530, 97, 72, 23);
		addSearchBtn.addActionListener(this);
		getContentPane().add(addSearchBtn);

	}

	private void setOrderTable() {
		Object[] colNames = { "선택", "타입", "메뉴", "가격", "수량", " ", " " };
		Object[][] datas;

		// 주문한 메뉴를 받아와서 테이블로 띄워줌
		Singleton s = Singleton.getInstance();

		/////////
		oList = s.getOrderCtrl().getList();
		datas = new Object[oList.size()][colNames.length]; // 테이블의 2차원배열

		for (int i = 0; i < oList.size(); i++) {
			OrderedMenuDto Odto = oList.get(i);

			// "선택", "메뉴", "가격", "수량", " +", "- " //여기도 DB에서 SELECT로 검색해서 가져와야 되나...
			datas[i][0] = true; // 초기 값 설정 해주고.
			datas[i][1] = Odto.getType(); // 타입
			if (Odto.getType().equals("음료")) {
				beverageCounts++;
			}
			datas[i][2] = Odto.getMenu_name(); // 메뉴
			datas[i][3] = Odto.getPrice(); // 가격
			datas[i][4] = Odto.getCount(); // 수량
			datas[i][5] = i;
			datas[i][6] = i;
		}

		////////

		dtm = new DefaultTableModel(datas, colNames) {
			boolean editable[] = { true, false, false, false, false, true, true };

			@Override
			public boolean isCellEditable(int row, int column) {
				return editable[column];
			}
		}; // DefaultTableModel
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
		menuTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		menuTable.getColumnModel().getColumn(0).setPreferredWidth(30); // 선택(체크박스)
		menuTable.getColumnModel().getColumn(1).setPreferredWidth(60); // 타입
		menuTable.getColumnModel().getColumn(2).setPreferredWidth(180); // 메뉴
		menuTable.getColumnModel().getColumn(3).setPreferredWidth(100); // 가격
		menuTable.getColumnModel().getColumn(4).setPreferredWidth(50); // 수량
		menuTable.getColumnModel().getColumn(5).setPreferredWidth(50); // + (버튼)
		menuTable.getColumnModel().getColumn(6).setPreferredWidth(50); // - (버튼)

		menuTable.getColumn("선택").setCellRenderer(dcr); // ChkBox라는 곳에 넣을꺼야.
		// menuTable.getColumn("선택").setCellRenderer(new ChkBoxCell()); // ChkBox라는 곳에
		// 넣을꺼야.
		// menuTable.getColumn("선택").setCellEditor(new ChkBoxEditor()); // 체크박스 = box,
		// ChkBox 에 넣겟다. 설정하곗다.
		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);

		menuTable.getColumn("선택").setCellEditor(new DefaultCellEditor(box) {
			int row = 0;

			@Override
			public void addCellEditorListener(CellEditorListener l) {
				super.addCellEditorListener(l);

				int num = 0;
				if (dtm.getValueAt(row, 1).toString().equals("음료")) {

					if (box.isSelected()) { // 선택이 취소 됐을 때
						num = Integer.parseInt(dtm.getValueAt(row, 4).toString()); // 수량	
						dtm.setValueAt(0, row, 4);					
						beverageCounts -= num;
					} else { // 선택 됐을 때						
						dtm.setValueAt(1, row, 4);
						num = Integer.parseInt(dtm.getValueAt(row, 4).toString()); // 수량
						beverageCounts += num;
					}

					System.out.println("bevrageCounts : " + beverageCounts + " row : " + row);
					setUsableCouponCount();
				}

			}

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				box.setSelected(((Boolean) value).booleanValue()); // 체크박스가 선택 되었는가?
				this.row = row;
				return box;
			}
		}); // 체크박스 = box, ChkBox 에 넣겟다.

		// 설정하곗다.

		menuTable.getColumnModel().getColumn(5).setCellRenderer(new TableCell("+"));
		menuTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor("+"));
		menuTable.getColumnModel().getColumn(6).setCellRenderer(new TableCell("-"));
		menuTable.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor("-"));

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 1; i < dtm.getColumnCount() - 2; i++) { // 칼럼 내용 가운데 정렬 -- 버튼이랑 체크박스는 제외해야함
			menuTable.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);

		}

		JScrollPane scrollPaneMenu = new JScrollPane(menuTable);
		scrollPaneMenu.setBounds(26, 243, 588, 120);
		scrollPaneMenu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPaneMenu);
		// scrollPaneMenu.setViewportView(menuTable);

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
					int num = (int) menuTable.getModel().getValueAt(row, 4);
					if((Boolean)dtm.getValueAt(row, 0) == false) {
						return;
					}
					if (sign.equals("+")) {
						dtm.setValueAt(num + 1, row, 4);
						if(dtm.getValueAt(row, 1).toString().equals("음료")) {
							beverageCounts++;
						}
					} else {
						if (num <= 1)
							return;
						dtm.setValueAt(num - 1, row, 4);
						if(dtm.getValueAt(row, 1).toString().equals("음료")) {
							beverageCounts--;
						}
					}
					setUsableCouponCount();

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
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();

		if (e.getSource() == paymentBtn) { // 결제버튼 눌렀을 때
			// 관리자 DB에 INSERT시켜주고, 확인창을 띄워주자.
			int yesNoBtn = JOptionPane.showConfirmDialog(null, "이대로 주문하시겠습니까?", "최종 주문 확인", JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			if (yesNoBtn == JOptionPane.YES_OPTION) {
				oList = s.getOrderCtrl().getList();
				ArrayList<OrderedMenuDto> confirmedList = new ArrayList<>();
				for (int i = 0; i < dtm.getRowCount(); i++) {
					if ((boolean) dtm.getValueAt(i, 0) == true) { // 체크되어 있으면
						System.out.println("주문");
						OrderedMenuDto dto = oList.get(i);
						dto.setId(Singleton.getInstance().getMemCtrl().getLoginId());
						dto.setCount(Integer.parseInt(dtm.getValueAt(i, 3).toString()));
						confirmedList.add(dto);
					}
				}
				if (confirmedList.size() > 0) {
					s.getOrderCtrl().insert(confirmedList);
				} else {
					JOptionPane.showMessageDialog(null, "주문 내역이 없습니다");
				}
			}
		} else if (e.getSource() == cancelBtn) {
			s.getOrderCtrl().clearList();
			s.backToMain(this);
		}
	}
}
