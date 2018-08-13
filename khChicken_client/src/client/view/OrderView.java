package client.view;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import client.singleton.Singleton;
import dto.MemberDto;
import dto.OrderedMenuDto;

public class OrderView extends JFrame implements ActionListener {
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

	public OrderView() {
		setTitle("주문 내역");
		getContentPane().setLayout(null);

		setBounds(100, 100, 646, 557);

		JLabel lblNewLabel = new JLabel("<주문자 정보>");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(14, 12, 140, 38);
		getContentPane().add(lblNewLabel);

		JLabel label_4 = new JLabel("<주문 내역>");
		label_4.setFont(new Font("굴림", Font.BOLD, 18));
		label_4.setBounds(14, 163, 129, 38);
		getContentPane().add(label_4);

		// 쿠폰------
		JLabel label_6 = new JLabel("<음료 쿠폰>");
		label_6.setFont(new Font("굴림", Font.BOLD, 18));
		label_6.setBounds(14, 378, 129, 38);
		getContentPane().add(label_6);

		// 사용 가능한 쿠폰의 수를 보여줄 라벨

		JLabel lblNewLabel_3 = new JLabel("0" + "장");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(14, 428, 62, 18);
		getContentPane().add(lblNewLabel_3);

		// 눌렀을 때 memberDto의 Coupon - 1
		// 위의 변수 - 1 : 라벨에 바로 적용

		JButton couponUseBtn = new JButton("사용");
		couponUseBtn.setBounds(80, 424, 63, 27);
		getContentPane().add(couponUseBtn);

		// memberDto에서 쿠폰 수를 바로 받아와서 라벨에 띄우기
		// SELECT 로 COUNT(리뷰) / 3 + 1 을 보여줄껀데.

		// String coupons = s.getOrderCtrl().getCoupons();
		couponLabel = new JLabel("사용 가능한 쿠폰 : " + "0" + "장");
		// System.out.println(coupons);
		// couponLabel.setText("사용 가능한 쿠폰 : " + coupons + "장");
		couponLabel.setBounds(14, 454, 192, 49);
		getContentPane().add(couponLabel);

		// 주문자와 배달지에 대한 테이블
		setDeliveryInfo();

		// 주문 상세내역 테이블
		setOrderTable();

		// Order DB와 member DB에 INSERT 하는 버튼
		paymentBtn = new JButton("결제");
		paymentBtn.setFont(new Font("굴림", Font.BOLD, 18));
		paymentBtn.addActionListener(this);
		paymentBtn.setBounds(510, 414, 105, 43);
		getContentPane().add(paymentBtn);

		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(this);
		cancelBtn.setFont(new Font("굴림", Font.BOLD, 18));
		cancelBtn.setBounds(510, 467, 105, 43);
		getContentPane().add(cancelBtn);

		JPanel panel = new JPanel();
		panel.setBounds(173, 388, 314, 120);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("결제 금액");
		lblNewLabel_5.setBounds(219, 5, 83, 21);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);

		// 결제금액을 보여줄 라벨
		JLabel label_7 = new JLabel("15000원"); // 결제금액 == 치킨값 * 수량 넣기.
		label_7.setBounds(228, 50, 74, 21);
		panel.add(label_7);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("굴림", Font.BOLD, 18));

		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		MemberDto dto = s.getMemCtrl().getCurrentUser();

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
			datas[i][2] = Odto.getMenu_name(); // 메뉴
			datas[i][3] = Odto.getPrice(); // 가격
			datas[i][4] = Odto.getCount(); // 수량
			datas[i][5] = i;
			datas[i][6] = i;
		}
		////////

		dtm = new DefaultTableModel(datas, colNames); // DefaultTableModel
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
		JCheckBox box = new JCheckBox(); // checkbox를 중앙정렬
		box.setHorizontalAlignment(JLabel.CENTER);
		menuTable.getColumn("선택").setCellEditor(new DefaultCellEditor(box)); // 체크박스 = box, ChkBox 에 넣겟다. 설정하곗다.

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
		scrollPaneMenu.setBounds(51, 211, menuTable.getPreferredSize().width + 2, 156);
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
					if (sign.equals("+")) {
						menuTable.getModel().setValueAt(num + 1, row, 4);
					} else {
						if (num <= 1)
							return;
						menuTable.getModel().setValueAt(num - 1, row, 4);
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
			s.backToMain(this);
		}
	}
}
