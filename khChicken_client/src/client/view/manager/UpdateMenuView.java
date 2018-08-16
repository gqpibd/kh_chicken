package client.view.manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.controller.MenuController;
import client.service.MenuService;
import client.singleton.Singleton;
import dto.MenuShowDto;
import utils.images.ImageUtils;
import utils.images.LabelEventListener;

public class UpdateMenuView extends JFrame implements ActionListener {
	private final int TYPE_COL = 0;
	private final int NAME_COL = 1;
	private final int PRICE_COL = 2;
	private final int IMG_COL = 3;

	private JTextField nameField;
	private JTextField priceField;
	private JTextField newPriceField;
	private JLabel imgLabel;
	private JLabel applyBtn;
	private JLabel delBtn;
	private JLabel backBtn;
	private JLabel searchBtn;
	private JTable menuTable;

	private DefaultTableModel model; // 추가 버튼을 눌렀을 때 실시간으로 주문 내역 창에 반영하기 위해서 필요하다
	private JTextField imgFileField;
	private JTextField typeField;

	private JTextArea descriptionArea;
	private String NewImgPath = "";

	private static final String PATH = "images/manageView/";

	public UpdateMenuView() {
		setTitle("메뉴 수정, 삭제");

		setContentPane(new JLabel(new ImageIcon(PATH + "updateView.jpg")));
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		setBounds(300, 150, 482, 505);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setTable();
		// 메뉴 테이블
		menuTable.setFont(new Font("나눔고딕", Font.PLAIN, 14));

		// 메뉴 이름
		nameField = new JTextField();
		nameField.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		nameField.setEditable(false);
		nameField.setBounds(240, 178, 155, 21);
		getContentPane().add(nameField);
		nameField.setColumns(10);

		// 가격
		priceField = new JTextField();
		priceField.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		priceField.setEditable(false);
		priceField.setBounds(59, 207, 90, 21);
		getContentPane().add(priceField);
		priceField.setColumns(10);

		// 이미지 파일(파일이름)
		imgFileField = new JTextField();
		imgFileField.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		imgFileField.setEditable(false);
		imgFileField.setColumns(10);
		imgFileField.setBounds(59, 238, 139, 21);
		getContentPane().add(imgFileField);

		// 검색 버튼
		searchBtn = new JLabel(new ImageIcon(PATH + "updateSearchBtn.jpg"));
		searchBtn.addMouseListener(new LabelEventListener(this));
		searchBtn.setBounds(210, 238, searchBtn.getIcon().getIconWidth(), searchBtn.getIcon().getIconHeight());
		getContentPane().add(searchBtn);

		// 변경된 가격
		newPriceField = new JTextField();
		newPriceField.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		newPriceField.setColumns(10);
		newPriceField.setBounds(210, 207, 90, 21);
		getContentPane().add(newPriceField);

		// 제품 타입
		typeField = new JTextField();
		typeField.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		typeField.setText((String) null);
		typeField.setEditable(false);
		typeField.setColumns(10);
		typeField.setBounds(59, 175, 90, 21);
		getContentPane().add(typeField);

		// 제품 이미지
		imgLabel = new JLabel();
		double scale = 0.82;
		int width = (int) (277 * scale);
		int height = (int) (182 * scale);
		imgLabel.setBounds(40, 267, width, height);
		getContentPane().add(imgLabel);

		// 제품 설명
		descriptionArea = new JTextArea();
		descriptionArea.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		descriptionArea.setLineWrap(true);
		JScrollPane descPane = new JScrollPane(descriptionArea);
		descPane.setBounds(290, 271, 149, 141);
		getContentPane().add(descPane);

		JScrollPane scrollPane = new JScrollPane(menuTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setBounds((frameWidth-menuTable.getPreferredSize().width)/2, 28,
		// menuTable.getPreferredSize().width, 211);
		int frameWidth = menuTable.getPreferredSize().width + 70;
		scrollPane.setBounds((frameWidth - (menuTable.getPreferredSize().width + 20)) / 2, 47,
				menuTable.getPreferredSize().width + 17, 122);
		getContentPane().add(scrollPane);

		// 완료 버튼
		applyBtn = new JLabel(new ImageIcon(PATH + "updateMenuBtn.jpg"));
		// applyBtn.setBounds(16, 422, 90, 34);
		applyBtn.setBounds((int) ((frameWidth / 3.0 - 90) / 2.0), 422, applyBtn.getIcon().getIconWidth(),
				applyBtn.getIcon().getIconHeight());
		applyBtn.addMouseListener(new LabelEventListener(this));
		getContentPane().add(applyBtn);

		// 삭제 버튼
		delBtn = new JLabel(new ImageIcon(PATH + "delMenuBtn.jpg"));
		delBtn.setBounds((int) ((frameWidth / 3.0 - 90) / 2 + frameWidth / 3.0), 422, delBtn.getIcon().getIconWidth(),
				delBtn.getIcon().getIconHeight());
		delBtn.addMouseListener(new LabelEventListener(this));
		getContentPane().add(delBtn);

		// 뒤로가기 버튼
		backBtn = new JLabel(new ImageIcon(PATH + "updateReturnBtn.jpg"));
		// backBtn.setBounds(320, 422, 90, 34);
		backBtn.setBounds((int) ((frameWidth / 3.0 - 90) / 2 + frameWidth / 3.0 * 2), 422,
				backBtn.getIcon().getIconWidth(), backBtn.getIcon().getIconHeight());
		backBtn.addMouseListener(new LabelEventListener(this));
		getContentPane().add(backBtn);

		// ------ 나머지 라벨------//

		JLabel wonLabel = new JLabel("원");
		wonLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		wonLabel.setBounds(312, 211, 35, 15);
		getContentPane().add(wonLabel);

		JLabel menuNamelabel = new JLabel("메뉴 이름");
		menuNamelabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		menuNamelabel.setBounds(171, 181, 57, 15);
		getContentPane().add(menuNamelabel);

		JLabel priceLabel = new JLabel("가격");
		priceLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		priceLabel.setBounds(12, 211, 57, 15);
		getContentPane().add(priceLabel);

		JLabel lblNewLabel = new JLabel("==>");
		lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		lblNewLabel.setBounds(171, 211, 50, 15);
		getContentPane().add(lblNewLabel);

		JLabel ilabel = new JLabel("이미지");
		ilabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		ilabel.setBounds(12, 241, 83, 15);
		getContentPane().add(ilabel);

		JLabel typeLabel = new JLabel("타입");
		typeLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		typeLabel.setBounds(12, 178, 57, 15);
		getContentPane().add(typeLabel);

		JLabel descriptionLabel = new JLabel("제품 설명");
		descriptionLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		descriptionLabel.setBounds(322, 241, 57, 15);
		getContentPane().add(descriptionLabel);

		initFields();
		setLocationRelativeTo(null);
	}

	public void initFields() { // 처음 창을 켰을 때 모슴
		// 나머지 필드가 기본적으로 맨 위에 로우값으로 보이게 한다.
		Singleton s = Singleton.getInstance();
		MenuShowDto topDto = s.getMenuCtrl().get(0);
		nameField.setText(topDto.getMenu_name());
		priceField.setText(topDto.getPrice() + "");
		newPriceField.setText(topDto.getPrice() + "");
		imgFileField.setText(topDto.getMenu_name() + ".jpg");
		typeField.setText(topDto.getType());
		descriptionArea.setText(topDto.getDescription());

		ImageUtils.setResizedImage(imgLabel,
				MenuService.FOLDER_PATH + topDto.getMenu_name().replaceAll(" ", "_") + ".jpg");
		menuTable.setRowSelectionInterval(0, 0); // 맨 위 칼럼이 선택된 상태로 표시함
	}

	public void setTable() {
		String colNames[] = { "타입", "이름", "가격", "이미지파일" };
		Object rowData[][] = setDataForTable();
		model = new DefaultTableModel(rowData, colNames) {
			// 테이블의 각 셀에 입력되어 있는 값을 임의로 변결할 수 없게 해 준다.
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		menuTable = new JTable(model);
		menuTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // 칼럼 크기가 모두 동일하게 들어가면 보기 좋지 않으므로 각각 셋팅해준다.
		menuTable.getColumnModel().getColumn(TYPE_COL).setPreferredWidth(60);
		menuTable.getColumnModel().getColumn(NAME_COL).setPreferredWidth(120);
		menuTable.getColumnModel().getColumn(PRICE_COL).setPreferredWidth(70);
		menuTable.getColumnModel().getColumn(IMG_COL).setPreferredWidth(170);

		menuTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Singleton s = Singleton.getInstance();
				int row = menuTable.getSelectedRow();

				MenuShowDto dto = s.getMenuCtrl().getMenuDto(model.getValueAt(row, NAME_COL).toString());

				typeField.setText(dto.getType());
				nameField.setText(dto.getMenu_name());
				priceField.setText(dto.getPrice() + "");
				newPriceField.setText(model.getValueAt(row, PRICE_COL).toString());
				imgFileField.setText(model.getValueAt(row, IMG_COL).toString());

				descriptionArea.setText(dto.getDescription());
				ImageUtils.setResizedImage(imgLabel,
						MenuService.FOLDER_PATH + model.getValueAt(row, IMG_COL).toString());
			}
		});

		// 번호랑 작성자는 가운데 정렬로 표현해주자
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		menuTable.getColumn("타입").setCellRenderer(celAlignCenter);
		menuTable.getColumn("가격").setCellRenderer(celAlignCenter);
		menuTable.getColumn("이미지파일").setCellRenderer(celAlignCenter);
		menuTable.setColumnSelectionAllowed(false);

	}

	public Object[][] setDataForTable() { // 테이블에 넣을 데이터
		Singleton s = Singleton.getInstance();
		Object rowData[][];
		rowData = new Object[s.getMenuCtrl().getSize()][4];

		for (int j = 0; j < s.getMenuCtrl().getSize(); j++) { // 모든 메뉴를
			MenuShowDto menu = (MenuShowDto) s.getMenuCtrl().get(j);
			rowData[j] = addDataToRowArr(menu); // 배열에 넣어준다.
		}
		return rowData;
	}

	public Object[] addDataToRowArr(MenuShowDto menu) { // 새로운 로우를 추가할 때
		Object rowData[] = new Object[4];
		rowData[TYPE_COL] = menu.getType();
		rowData[NAME_COL] = menu.getMenu_name();
		rowData[PRICE_COL] = menu.getPrice();
		rowData[IMG_COL] = menu.getMenu_name().replaceAll(" ", "_") + ".jpg";
		return rowData;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();

		if (e.getSource() == applyBtn) { // 적용 버튼 클릭
			MenuShowDto menu = (MenuShowDto) s.getMenuCtrl().getMenuByName(nameField.getText());
			int row = menuTable.getSelectedRow();
			int newPrice = Integer.parseInt(newPriceField.getText());
			String description = descriptionArea.getText();

			model.setValueAt(newPrice, row, PRICE_COL);
			menu.setPrice(newPrice);
			menu.setDescription(description);
			s.getMenuCtrl().update(menu);

			String currentImgPath = MenuService.FOLDER_PATH + model.getValueAt(row, IMG_COL).toString();
			if (NewImgPath.length() > 0 && !NewImgPath.equals(currentImgPath)) { // 이미지 경로가 달라졌으면 소켓을 통해 새로운 이미지를 보낸다.
				s.getMenuCtrl().updateImage(menu, NewImgPath);
			}
			s.resetMainView();
			JOptionPane.showMessageDialog(null, "변경사항을 저장했습니다");
			System.out.println("변경됨");

		} else if (e.getSource() == delBtn) { // 삭제 버튼 클릭
			MenuShowDto menu = s.getMenuCtrl().getMenuByName(nameField.getText());
			int row = menuTable.getSelectedRow();
			int sel = JOptionPane.showConfirmDialog(null, "이 메뉴를 삭제하시겠습니까?", "", JOptionPane.YES_NO_OPTION);
			if (sel == 0) { // 메뉴를 삭제하는 경우
				model.removeRow(row); // JTable에서 삭제
				s.getMenuCtrl().delete(menu);
				initFields(); // 필드의 내용을 다 초기로 돌린다.
				s.resetMainView();
			}

		} else if (e.getSource() == backBtn) { // 뒤로가기 버튼 클릭
			MenuController menCtrl = Singleton.getInstance().getMenuCtrl();
			menCtrl.menuManageView(this);

		} else if (e.getSource() == searchBtn) { // 검색 버튼 클릭 ( 이미지 검색 )
			NewImgPath = ImageUtils.jFileChooserUtil();

			if (NewImgPath.length() != 0) {
				imgFileField.setText(NewImgPath.substring(NewImgPath.lastIndexOf("\\") + 1)); // 전체 경로에서 파일 이름과 확장자명만
																								// 가져온다.
				ImageUtils.setResizedImage(imgLabel, NewImgPath);
			}
		}
	}
}
