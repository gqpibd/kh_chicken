package client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private int beverageCounts = 0; // 현재 음료
	private int myCoupons = 0; // 보유쿠폰
	private int usableCoupons = 0; // 적용 가능한 쿠폰
	private int appliedCoupons = 0; // 적용한 쿠폰 수
	private int orderPrice;

	private JLabel availableCoupons;
	private JLabel couponLabel;
	private JLabel salePayment;
	private JLabel paidPayment;
	private JLabel orderPayment;

	private JButton addSearchBtn; // 주소검색 버튼
	private JButton paymentBtn; // 결제 버튼
	private JButton cancelBtn; // 취소 버튼
	private JButton couMinBtn; // 사용쿠폰 감소
	private JButton couPluBtn; // 사용쿠폰 증가

	private DefaultTableModel dtm; // 상세 주문내역을 보관하고 있는 모델
	private List<OrderedMenuDto> oList;
	private JTable menuTable;

	private JTextField nameField;
	private JTextField phoneField1;
	private JTextField phoneField2;
	private JTextField phoneField3;
	private JTextField addressField;
	private JTextField timeField;
	private JTextField couponField; // 사용할 쿠폰

	MemberDto dto = Singleton.getInstance().getMemCtrl().getCurrentUser();

	public OrderView() {
		getContentPane().setLocation(0, 212);
		setTitle("주문 내역");
		getContentPane().setLayout(null);

		setBounds(100, 100, 646, 600);

		JLabel lblNewLabel = new JLabel("<주문자 정보>");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(14, 12, 140, 38);
		getContentPane().add(lblNewLabel);

		JLabel label_4 = new JLabel("<주문 내역>");
		label_4.setFont(new Font("굴림", Font.BOLD, 18));
		label_4.setBounds(14, 163, 129, 38);
		getContentPane().add(label_4);

		// 주문자와 배달지정보
		setDeliveryInfo();

		// 주문 상세내역 테이블
		setOrderTable();

		// 쿠폰------
		JLabel label_6 = new JLabel("<음료 쿠폰>");
		label_6.setFont(new Font("굴림", Font.BOLD, 18));
		label_6.setBounds(14, 409, 129, 38);
		getContentPane().add(label_6);

		// 눌렀을 때 memberDto의 Coupon - 1
		// 위의 변수 - 1 : 라벨에 바로 적용

		couMinBtn = new JButton("-");
		couMinBtn.setBounds(100, 502, 30, 30);
		getContentPane().add(couMinBtn);
		couMinBtn.addActionListener(this);

		couPluBtn = new JButton("+");
		couPluBtn.setBounds(40, 502, 30, 30);
		getContentPane().add(couPluBtn);
		couPluBtn.addActionListener(this);

		// memberDto에서 쿠폰 수를 바로 받아와서 라벨에 띄우기
		// SELECT 로 COUNT(리뷰) / 3 + 1 을 보여줄껀데.
		Singleton s = Singleton.getInstance();
		myCoupons = Integer.parseInt(s.getOrderCtrl().getCoupons());
		if (s.getMemCtrl().getLoginId() != null) { // 회원이면
			myCoupons++;
		}
		myCoupons -= dto.getCoupon(); // 획득한 쿠폰에서 사용한 쿠폰만큼 빼준다.
		couponLabel = new JLabel();
		couponLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		couponLabel.setText("보유 쿠폰 : " + myCoupons + " 장");
		couponLabel.setBounds(14, 452, 130, 18);
		getContentPane().add(couponLabel);

		availableCoupons = new JLabel();
		availableCoupons.setHorizontalAlignment(SwingConstants.RIGHT);
		availableCoupons.setBounds(24, 474, 120, 18);
		getContentPane().add(availableCoupons);

		setUsableCouponCount();

		// Order DB와 member DB에 INSERT 하는 버튼
		paymentBtn = new JButton("결제");
		paymentBtn.setFont(new Font("굴림", Font.BOLD, 18));
		paymentBtn.addActionListener(this);
		paymentBtn.setBounds(521, 433, 94, 43);
		getContentPane().add(paymentBtn);

		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(this);
		cancelBtn.setFont(new Font("굴림", Font.BOLD, 18));
		cancelBtn.setBounds(521, 481, 94, 43);
		getContentPane().add(cancelBtn);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(192, 433, 320, 75);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("결제 금액");
		lblNewLabel_5.setBounds(225, 10, 83, 21);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);

		// 결제금액을 보여줄 라벨
		orderPrice = OrderCalc();
		paidPayment = new JLabel(orderPrice + "원"); // 결제금액 == 치킨값 * 수량 넣기.
		paidPayment.setBounds(230, 41, 74, 21);
		panel.add(paidPayment);
		paidPayment.setHorizontalAlignment(SwingConstants.CENTER);
		paidPayment.setFont(new Font("굴림", Font.BOLD, 15));

		JLabel label = new JLabel("주문 금액");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.BOLD, 15));
		label.setBounds(8, 10, 83, 21);
		panel.add(label);

		JLabel label_1 = new JLabel("할인 금액");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("굴림", Font.BOLD, 15));
		label_1.setBounds(120, 10, 83, 21);
		panel.add(label_1);

		salePayment = new JLabel(" 0 원 ");
		salePayment.setHorizontalAlignment(SwingConstants.CENTER);
		salePayment.setFont(new Font("굴림", Font.BOLD, 15));
		salePayment.setBounds(122, 41, 74, 21);
		panel.add(salePayment);

		orderPayment = new JLabel(orderPrice + "원");
		orderPayment.setHorizontalAlignment(SwingConstants.CENTER);
		orderPayment.setFont(new Font("굴림", Font.BOLD, 15));
		orderPayment.setBounds(12, 41, 74, 21);
		panel.add(orderPayment);

		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(85, 44, 26, 15);
		panel.add(lblNewLabel_1);

		JLabel label_5 = new JLabel("=");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(202, 44, 26, 15);
		panel.add(label_5);

		// 적용하는 쿠폰
		couponField = new JTextField(appliedCoupons + "");
		couponField.setHorizontalAlignment(SwingConstants.CENTER);
		couponField.setBounds(70, 502, 30, 30);
		getContentPane().add(couponField);
		couponField.setEditable(false);

		setVisible(true);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void setCouponCount() { // 사용한 쿠폰은 멤버 정보에 업데이트
		if (dto == null) { // 회원 아니면 의미없다.
			return;
		} else if (appliedCoupons > 0) {
			dto.setCoupon(dto.getCoupon() + appliedCoupons);
			Singleton.getInstance().getMemCtrl().update(dto);
		}

	}

	private void setUsableCouponCount() { /////////////////////////// 적용가능 쿠폰

		// 내쿠폰 사용가능한 쿠폰
		if (myCoupons > beverageCounts) {
			usableCoupons = beverageCounts;
		} else {
			usableCoupons = myCoupons;
		}
		availableCoupons.setText("적용 가능 쿠폰 : " + usableCoupons + " 장");

	}

	private void setDeliveryInfo() {

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(19, 63, 57, 15);
		getContentPane().add(nameLabel);

		JLabel addressLabel = new JLabel("주소");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setBounds(19, 101, 57, 15);
		getContentPane().add(addressLabel);

		JLabel phoneLabel = new JLabel("전화번호");
		phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneLabel.setBounds(269, 63, 57, 15);
		getContentPane().add(phoneLabel);

		JLabel label = new JLabel("-");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(406, 63, 24, 15);
		getContentPane().add(label);

		label = new JLabel("-");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(498, 63, 24, 15);
		getContentPane().add(label);

		JLabel timeLabel = new JLabel("주문시간");
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timeLabel.setBounds(19, 135, 57, 15);
		getContentPane().add(timeLabel);

		// ---------------------------내용 설정---------------------------------- //
		Singleton s = Singleton.getInstance();

		if (dto == null) {
			dto = new MemberDto();
		}
		nameField = new JTextField(dto.getName());
		nameField.setBounds(90, 60, 116, 21);
		getContentPane().add(nameField);
		nameField.setColumns(10);

		phoneField1 = new JTextField(dto.getPhone().split("-")[0]);
		phoneField1.setBounds(350, 60, 57, 21);
		getContentPane().add(phoneField1);
		phoneField1.setColumns(10);

		phoneField2 = new JTextField(dto.getPhone().split("-")[1]);
		phoneField2.setColumns(10);
		phoneField2.setBounds(429, 60, 72, 21);
		getContentPane().add(phoneField2);

		phoneField3 = new JTextField(dto.getPhone().split("-")[2]);
		phoneField3.setColumns(10);
		phoneField3.setBounds(521, 60, 72, 21);
		getContentPane().add(phoneField3);

		addressField = new JTextField(dto.getAddress());
		addressField.setBounds(90, 98, 410, 21);
		getContentPane().add(addressField);
		addressField.setColumns(10);

		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("YYYY년 MM월 dd일 HH:mm");

		timeField = new JTextField(date.format(today));
		timeField.setEditable(false);
		timeField.setBounds(90, 132, 192, 21);
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
			JCheckBox box = new JCheckBox();

			public Component getTableCellRendererComponent // 셀렌더러
			(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

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

		JCheckBox box = new JCheckBox();
		box.setHorizontalAlignment(JLabel.CENTER);
		menuTable.getColumn("선택").setCellEditor(new DefaultCellEditor(box) {

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				box.setSelected(((Boolean) value).booleanValue()); // 체크박스가 선택 되었는가?

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
		menuTable.addMouseListener(new checkLister());

		JScrollPane scrollPaneMenu = new JScrollPane(menuTable);
		scrollPaneMenu.setBounds(51, 211, 527, 188);
		scrollPaneMenu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPaneMenu);
		// scrollPaneMenu.setViewportView(menuTable);

	}

	class checkLister extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			int rowNum = menuTable.getSelectedRow();
			boolean check = Boolean.parseBoolean(menuTable.getValueAt(rowNum, 0).toString());
			int num = 0;
			if (!check) { // 선택이 취소 됐을 때
				num = Integer.parseInt(dtm.getValueAt(rowNum, 4).toString()); // 수량
				dtm.setValueAt(0, rowNum, 4);
				if (dtm.getValueAt(rowNum, 1).toString().equals("음료")) {
					beverageCounts -= num;
					setUsableCouponCount();
					updateAppliedCoupon();
				}
			} else { // 선택 됐을 때
				dtm.setValueAt(1, rowNum, 4);
				num = Integer.parseInt(dtm.getValueAt(rowNum, 4).toString()); // 수량
				if (dtm.getValueAt(rowNum, 1).toString().equals("음료")) {
					beverageCounts += num;
					setUsableCouponCount();
				}
			}
			refreshPrice();
		}

		private void updateAppliedCoupon() {
			if (beverageCounts < appliedCoupons) { // 음료수가 적용된 쿠폰 수보다 적으면
				appliedCoupons = beverageCounts;
				couponField.setText(appliedCoupons + "");
			}

		}

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

		public ButtonEditor(String sign) { // + - 버튼 클릭했을 때
			super(new JCheckBox());
			button = new JButton(sign);
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int num = (int) menuTable.getModel().getValueAt(row, 4);
					if ((Boolean) dtm.getValueAt(row, 0) == false) {
						return;
					}
					if (sign.equals("+")) {
						num++;
						dtm.setValueAt(num, row, 4);
						oList.get(row).setCount(num);
						refreshPrice();
						if (dtm.getValueAt(row, 1).toString().equals("음료")) {
							beverageCounts++;
						}
					} else {
						if (num <= 1)
							return;
						num--;
						dtm.setValueAt(num, row, 4);
						oList.get(row).setCount(num);
						refreshPrice();
						if (dtm.getValueAt(row, 1).toString().equals("음료")) {
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
			if (yesNoBtn == JOptionPane.YES_OPTION) { // 예 눌렀을 시,
				oList = s.getOrderCtrl().getList();
				ArrayList<OrderedMenuDto> confirmedList = new ArrayList<>();

				for (int i = 0; i < dtm.getRowCount(); i++) {
					if ((boolean) dtm.getValueAt(i, 0) == true) { // 체크되어 있으면
						System.out.println("주문");
						OrderedMenuDto dto = oList.get(i);
						if (dto.getType().equals("음료") && appliedCoupons > 0) {
							if (dto.getCount() >= appliedCoupons) { // 음료 쿠폰보다 주문 개수가 많으면 여기서 다쓴다.								
								dto.setCoupon(appliedCoupons);
								appliedCoupons = 0;
							}else { // 음료쿠폰이 더 많은 경우는 이 주문의 수량만큼 쓴다.
								dto.getCount();
								dto.setCoupon(dto.getCount());
								appliedCoupons -= dto.getCount();
							}
						}
						dto.setId(Singleton.getInstance().getMemCtrl().getLoginId());
						dto.setCount(Integer.parseInt(dtm.getValueAt(i, 4).toString()));
						System.out.println(dto);
						confirmedList.add(dto);

					}
				}

				if (confirmedList.size() > 0) {
					s.getOrderCtrl().insert(confirmedList);
					setCouponCount(); // 사용한 쿠폰에 대해 업데이트 해준다
				} else {
					JOptionPane.showMessageDialog(null, "주문 내역이 없습니다");
				}
			}
		} else if (e.getSource() == cancelBtn) { // 취소 버튼
			s.getOrderCtrl().clearList();

			s.backToMain(this);
		} else if (e.getSource() == addSearchBtn) { // 주소 검색
			SelectAddressDialog a = new SelectAddressDialog(this);
			addressField.setText(a.getAddress() + " " + a.getDetailAddress());
		} else if (e.getSource() == couPluBtn || e.getSource() == couMinBtn) {
			appliedCoupons = Integer.parseInt(couponField.getText().toString());
			if (e.getSource() == couPluBtn) {
				if (appliedCoupons < usableCoupons) { // 최대 적용 가능한
					appliedCoupons++;
				}
			} else {
				if (appliedCoupons > 0) {
					appliedCoupons--;
				}
			}
			couponField.setText(appliedCoupons + "");
			refreshPrice();

		}
	}

	public int OrderCalc() {
		int totalPrice = 0;
		for (int i = 0; i < oList.size(); i++) {
			if ((boolean) dtm.getValueAt(i, 0) == true) { // 체크되어 있으면
				OrderedMenuDto mdto = oList.get(i);
				System.out.println(" 선택 : " + mdto);
				totalPrice += mdto.getPrice() * mdto.getCount();
			}
		}
		System.out.println("--------------");

		return totalPrice;
	}

	public void refreshPrice() {
		int orderPrice = OrderCalc();
		int totalDiscount = appliedCoupons * 3000;
		orderPayment.setText(orderPrice + "원");
		salePayment.setText(totalDiscount + "원");
		paidPayment.setText(String.valueOf(orderPrice - totalDiscount) + "원");

	}
}
