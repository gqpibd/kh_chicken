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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import client.singleton.Singleton;
import dto.MemberDto;
import dto.OrderedMenuDto;
import utils.images.ImageUtils;
import utils.images.LabelEventListener;

public class OrderView extends JFrame implements ActionListener {
	private int beverageCounts = 0; // 현재 음료
	private int myCoupons = 0; // 보유쿠폰
	private int usableCoupons = 0; // 적용 가능한 쿠폰
	private int appliedCoupons = 0; // 적용한 쿠폰 수
	private int orderPrice; // 주문 금액

	/* 값이 변하는 라벨들 */
	private JLabel availableCoupons; // 적용가능 쿠폰 라벨
	private JLabel discountPriceLabel; // 할인 금액
	private JLabel totalPriceLabel; // 결제 금액
	private JLabel actualPriceLabel; // 주문 금액

	/* 버튼 */
	private JLabel addSearchBtn; // 주소검색 버튼
	private JLabel paymentBtn; // 결제 버튼
	private JLabel cancelBtn; // 취소 버튼
	private JLabel couMinBtn; // 사용쿠폰 감소
	private JLabel couPluBtn; // 사용쿠폰 증가

	/* 주문내역을 가진 테이블과 데이터 */
	private DefaultTableModel dtm; // 상세 주문내역을 보관하고 있는 모델
	private List<OrderedMenuDto> oList;
	private JTable menuTable;

	/* 배달지 정보를 담는 JTextField */
	private JTextField nameField;
	private JTextField phoneField1;
	private JTextField phoneField2;
	private JTextField phoneField3;
	private JTextField addressField;
	private JTextField timeField;

	private JTextField couponField; // 사용할 쿠폰의 수를 표시함.

	MemberDto currentUser = Singleton.getInstance().getMemCtrl().getCurrentUser(); // 주문자 정보

	private static final String PATH = "images/orderView/";

	public OrderView() {
		getContentPane().setLocation(0, 212);
		setTitle("주문 내역");
		setContentPane(new JLabel(new ImageIcon(PATH + "orderView.jpg")));
		setResizable(false);
		getContentPane().setLayout(null);

		setBounds(100, 100, 646, 583);

		// 주문자와 배달지정보
		setDeliveryInfo();

		// 주문 상세내역 테이블
		setOrderTable();

		// memberDto에서 쿠폰 수를 바로 받아와서 라벨에 띄우기
		Singleton s = Singleton.getInstance();
		myCoupons = s.getOrderCtrl().getCoupons();

		// 보유 쿠폰 수
		JLabel couponLabel = new JLabel();
		couponLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		// couponLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		couponLabel.setText("보유 쿠폰 : " + myCoupons + " 장");
		couponLabel.setBounds(400, 65, 137, 25);// 26, 68
		getContentPane().add(couponLabel);

		// 적용 가능 쿠폰수 - 선택한 음료수 숫자에 따라 달라진다.
		availableCoupons = new JLabel();
		// availableCoupons.setHorizontalAlignment(SwingConstants.RIGHT);
		availableCoupons.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		availableCoupons.setBounds(400, 87, 137, 25);
		getContentPane().add(availableCoupons);

		setUsableCouponCount();

		// 가게 로고
		JLabel logoLabel = new JLabel(new ImageIcon(PATH + "charactor.gif"));
		logoLabel.setBounds(332, 380, 282, 106);
		getContentPane().add(logoLabel);

		// 주문 완료 버튼. DB에 주문 결과를 전공한다.
		paymentBtn = new JLabel(new ImageIcon(PATH + "payBtn.jpg"));
		paymentBtn.setFont(new Font("나눔고딕", Font.PLAIN, 22));
		paymentBtn.setBounds(513, 495, paymentBtn.getIcon().getIconWidth(), paymentBtn.getIcon().getIconHeight());
		paymentBtn.addMouseListener(new LabelEventListener(this));
		getContentPane().add(paymentBtn);

		// 취소 버튼. 메인으로 돌아간다.
		cancelBtn = new JLabel(new ImageIcon(PATH + "orderCancelBtn.jpg"));
		cancelBtn.addMouseListener(new LabelEventListener(this));
		cancelBtn.setFont(new Font("나눔고딕", Font.PLAIN, 22));
		cancelBtn.setBounds(411, 495, cancelBtn.getIcon().getIconWidth(), cancelBtn.getIcon().getIconHeight());
		getContentPane().add(cancelBtn);

		JLabel label = new JLabel("주문 금액");
		label.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		label.setBounds(26, 460, 82, 15);
		getContentPane().add(label);

		// 주문 총액을 보여줄 라벨
		orderPrice = OrderCalc();
		totalPriceLabel = new JLabel(orderPrice + "원"); // 결제금액 == 치킨값 * 수량 넣기.
		totalPriceLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		totalPriceLabel.setBounds(135, 460, 69, 15);
		getContentPane().add(totalPriceLabel);

		JLabel label_1 = new JLabel("할인 금액");
		// label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		label_1.setBounds(26, 480, 57, 15);
		getContentPane().add(label_1);

		// 할인금액을 보여줄 라벨
		discountPriceLabel = new JLabel(" 0 원 ");
		discountPriceLabel.setForeground(Color.RED);
		discountPriceLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		discountPriceLabel.setBounds(135, 480, 76, 15);
		getContentPane().add(discountPriceLabel);

		JLabel paytitle_label = new JLabel("총 결제금액");
		paytitle_label.setBounds(26, 510, 105, 25);
		paytitle_label.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		getContentPane().add(paytitle_label);

		// 결제금액을 보여줄 라벨
		actualPriceLabel = new JLabel(orderPrice + "원");
		actualPriceLabel.setBounds(135, 510, 150, 20);
		actualPriceLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		getContentPane().add(actualPriceLabel);

		// 사용하는 쿠폰의 수
		couponField = new JTextField(appliedCoupons + "");
		couponField.setBounds(400, 122, 51, 20);
		getContentPane().add(couponField);
		couponField.setEditable(false);
		// 쿠폰사용 증가 버튼
		couPluBtn = new JLabel(new ImageIcon(PATH + "plusBtn.jpg"));
		couPluBtn.setBounds(455, 122, couPluBtn.getIcon().getIconWidth(), couPluBtn.getIcon().getIconHeight());
		getContentPane().add(couPluBtn);
		couPluBtn.addMouseListener(new LabelEventListener(this));

		// 쿠폰 사용 감소 버튼
		couMinBtn = new JLabel(new ImageIcon(PATH + "minusBtn.jpg"));
		couMinBtn.setBounds(483, 122, couMinBtn.getIcon().getIconWidth(), couMinBtn.getIcon().getIconHeight());
		getContentPane().add(couMinBtn);
		couMinBtn.addMouseListener(new LabelEventListener(this));

		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setLocationRelativeTo(null);
	}

	// 주문자와 배송지 정보를 보여주는 view
	private void setDeliveryInfo() {

		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		nameLabel.setBounds(26, 68, 39, 15);
		// nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(nameLabel);

		JLabel addressLabel = new JLabel("주소");
		addressLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		addressLabel.setBounds(26, 126, 51, 15);
		// addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(addressLabel);

		JLabel phoneLabel = new JLabel("연락처");
		phoneLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		phoneLabel.setBounds(26, 97, 57, 15);
		// phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(phoneLabel);

		JLabel timeLabel = new JLabel("주문시간");
		timeLabel.setFont(new Font("08서울남산체 B", Font.PLAIN, 15));
		timeLabel.setBounds(26, 154, 57, 15);
		getContentPane().add(timeLabel);

		// ---------------------------내용 설정---------------------------------- //

		if (currentUser == null) { // 비회원이면
			currentUser = new MemberDto(); // 빈 dto를 만들어 슨다.
		}
		nameField = new JTextField(currentUser.getName());
		nameField.setBounds(88, 65, 214, 21);
		getContentPane().add(nameField);
		nameField.setColumns(10);

		phoneField1 = new JTextField(currentUser.getPhone().split("-")[0]);
		phoneField1.setBounds(238, 93, 50, 21);
		getContentPane().add(phoneField1);
		phoneField1.setColumns(10);

		phoneField2 = new JTextField(currentUser.getPhone().split("-")[1]);
		phoneField2.setColumns(10);
		phoneField2.setBounds(88, 93, 50, 21);
		getContentPane().add(phoneField2);

		phoneField3 = new JTextField(currentUser.getPhone().split("-")[2]);
		phoneField3.setColumns(10);
		phoneField3.setBounds(161, 93, 50, 21);
		getContentPane().add(phoneField3);

		addressField = new JTextField(currentUser.getAddress());
		addressField.setBounds(88, 122, 214, 21);
		getContentPane().add(addressField);
		addressField.setColumns(10);

		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("YYYY년 MM월 dd일 HH:mm");

		timeField = new JTextField(date.format(today));
		timeField.setEditable(false);
		timeField.setBounds(88, 150, 214, 21);
		getContentPane().add(timeField);
		timeField.setColumns(10);

		// 주소 검색 버튼
		addSearchBtn = new JLabel(new ImageIcon(PATH + "adsSearchBtn.jpg"));
		addSearchBtn.setBounds(306, 121, addSearchBtn.getIcon().getIconWidth(), addSearchBtn.getIcon().getIconHeight());
		addSearchBtn.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		addSearchBtn.addMouseListener(new LabelEventListener(this));
		getContentPane().add(addSearchBtn);

	}

	// 주문 내역 테이블
	private void setOrderTable() {
		Object[] colNames = { "선택", "타입", "메뉴", "가격", "수량", " ", " " };
		Object[][] datas;

		Singleton s = Singleton.getInstance();

		// 주문한 메뉴를 받아와서 테이블로 띄워줌
		oList = s.getOrderCtrl().getList();
		datas = new Object[oList.size()][colNames.length]; // 테이블의 2차원배열

		for (int i = 0; i < oList.size(); i++) {
			OrderedMenuDto Odto = oList.get(i);

			// "선택", "메뉴", "가격", "수량", " +", "- "
			datas[i][0] = true; // 초기 값 설정 해주고.
			datas[i][1] = Odto.getType(); // 타입
			if (Odto.getType().equals("음료")) { // 처음에 창을 띄울 때는 무조건 1개씩이니 괜찮다.
				beverageCounts++;
			}
			datas[i][2] = Odto.getMenu_name(); // 메뉴
			datas[i][3] = Odto.getPrice(); // 가격
			datas[i][4] = Odto.getCount(); // 수량
			datas[i][5] = i;
			datas[i][6] = i;
		}

		dtm = new DefaultTableModel(datas, colNames) { // 체크박스, +,- 버튼 이외에는 수정이 불가능하게 한다.
			boolean editable[] = { true, false, false, false, false, true, true };

			@Override
			public boolean isCellEditable(int row, int column) {
				return editable[column];
			}
		};

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
		menuTable.getColumnModel().getColumn(2).setPreferredWidth(230); // 메뉴
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
		menuTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor("+", this));
		menuTable.getColumnModel().getColumn(6).setCellRenderer(new TableCell("-"));
		menuTable.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor("-", this));

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 1; i < dtm.getColumnCount() - 2; i++) { // 칼럼 내용 가운데 정렬 -- 버튼이랑 체크박스는 제외해야함
			menuTable.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);

		}
		menuTable.addMouseListener(new checkLister());

		JScrollPane scrollPaneMenu = new JScrollPane(menuTable);
		scrollPaneMenu.setBounds(35, 250, menuTable.getPreferredSize().width + 2, 120);
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

	class TableCell extends JLabel implements TableCellRenderer {		
		public TableCell(String sign) {
			setSize(50, 20);
			if (sign.equals("+")) {
				ImageUtils.setResizedImage(this, PATH + "plusBtn.jpg");
				setName("plus");

			} else if (sign.equals("-")) {
				ImageUtils.setResizedImage(this, PATH + "minusBtn.jpg");
				setName("minus");
			}
			setHorizontalAlignment(SwingConstants.CENTER);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return this;
		}
	}

	public class ButtonEditor extends DefaultCellEditor {
		protected JLabel button;
		OrderView ov = null;

		public ButtonEditor(String sign, OrderView ov) {
			super(new JCheckBox());
			this.ov = ov;

			button = new JLabel();
			button.setSize(50, 20);
			
			if (sign.equals("+")) {
				ImageUtils.setResizedImage(button, PATH + "plusBtn.jpg");				
				button.setName("plus");
			} else {
				ImageUtils.setResizedImage(button, PATH + "minusBtn.jpg");
				button.setName("minus");

			}
			button.addMouseListener(new LabelEventListener(ov));
			button.setHorizontalAlignment(SwingConstants.CENTER);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
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
							} else { // 음료쿠폰이 더 많은 경우는 이 주문의 수량만큼 쓴다.
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
					updateUsedCoupon(); // 사용한 쿠폰에 대해 업데이트 해준다
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

		} else { // 나머지는 각 메뉴에서 +나 -를 클릭한 경우이다
			int row = menuTable.getSelectedRow();
			int num = (int) menuTable.getModel().getValueAt(row, 4);
			if ((Boolean) dtm.getValueAt(row, 0) == false) {
				return;
			}
			if (((JLabel) e.getSource()).getName().equals("plus")) {
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
	}

	public void updateUsedCoupon() { // 사용한 쿠폰은 멤버 정보에 업데이트
		if (currentUser == null) { // 회원 아니면 의미없다.
			return;
		} else if (appliedCoupons > 0) {
			currentUser.setCoupon(currentUser.getCoupon() + appliedCoupons);
			Singleton.getInstance().getMemCtrl().update(currentUser);
		}

	}

	// 내가 가진 쿠폰과 주문하는 음료수의 개수를 비교해 사용가능한 쿠폰을 계산해서 보여준다
	private void setUsableCouponCount() {
		if (myCoupons > beverageCounts) {
			usableCoupons = beverageCounts;
		} else {
			usableCoupons = myCoupons;
		}
		availableCoupons.setText("적용 가능 쿠폰 : " + usableCoupons + " 장");
	}

	// 선택된 메뉴에 대한 가격을 계산한다.
	public int OrderCalc() {
		int totalPrice = 0;
		for (int i = 0; i < oList.size(); i++) {
			if ((boolean) dtm.getValueAt(i, 0) == true) { // 체크되어 있으면
				OrderedMenuDto mdto = oList.get(i);
				totalPrice += mdto.getPrice() * mdto.getCount();
			}
		}
		return totalPrice;
	}

	// 화면에 나타나는 가격 라벨 값을 새로고침해준다.
	public void refreshPrice() {
		setUsableCouponCount();
		int orderPrice = OrderCalc();
		int totalDiscount = appliedCoupons * 3000;
		totalPriceLabel.setText(orderPrice + "원");
		discountPriceLabel.setText(totalDiscount + "원");
		actualPriceLabel.setText(String.valueOf(orderPrice - totalDiscount) + "원");

	}
}
