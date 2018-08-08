package client.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.singleton.Singleton;
import dto.MenuDto;
import dto.MenuShowDto;

public class UpdateMenuView extends JFrame implements ActionListener {
	private final int NAME_COL = 0;
	private final int PRICE_COL = 1;
	private final int IMG_COL = 2;
	private final String FOLDER_PATH = "d:\\images\\";
	private JTextField nameField;
	private JTextField priceField;
	private JTextField newPriceField;
	private JLabel imgLabel;
	private JButton applyBtn;
	private JButton delBtn;
	private JButton backBtn;
	private JButton searchBtn;
	private JTable menuTable;

	private DefaultTableModel model; // 추가 버튼을 눌렀을 때 실시간으로 주문 내역 창에 반영하기 위해서 필요하다
	private JTextField imgFileField;

	public UpdateMenuView() {
		setTitle("메뉴 수정, 삭제");
		int frameWidth = 400;

		setBounds(300, 150, frameWidth, 505);
		getContentPane().setLayout(null);

		setTable();

		JLabel titleLabel = new JLabel("메뉴 수정, 삭제");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("돋움체", Font.BOLD, 20));
		titleLabel.setBounds(0, 10, 384, 30);
		getContentPane().add(titleLabel);

		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setBounds(95, 185, 173, 21);
		getContentPane().add(nameField);
		nameField.setColumns(10);

		JLabel menuNamelabel = new JLabel("메뉴 이름");
		menuNamelabel.setBounds(20, 188, 57, 15);
		getContentPane().add(menuNamelabel);

		JLabel priceLabel = new JLabel("가격");
		priceLabel.setBounds(20, 219, 57, 15);
		getContentPane().add(priceLabel);

		priceField = new JTextField();
		priceField.setEditable(false);
		priceField.setBounds(95, 216, 90, 21);
		getContentPane().add(priceField);
		priceField.setColumns(10);

		applyBtn = new JButton("적용");
		applyBtn.setBounds(282, 334, 90, 34);
		applyBtn.addActionListener(this);
		getContentPane().add(applyBtn);

		delBtn = new JButton("메뉴 삭제");
		delBtn.setBounds(282, 378, 90, 34);
		delBtn.addActionListener(this);
		getContentPane().add(delBtn);

		backBtn = new JButton("뒤로가기");
		backBtn.setBounds(282, 422, 90, 34);
		backBtn.addActionListener(this);
		getContentPane().add(backBtn);

		imgFileField = new JTextField();
		imgFileField.setEditable(false);
		imgFileField.setColumns(10);
		imgFileField.setBounds(95, 245, 173, 21);
		getContentPane().add(imgFileField);

		searchBtn = new JButton("검색");
		searchBtn.addActionListener(this);
		searchBtn.setBounds(307, 244, 65, 23);
		getContentPane().add(searchBtn);

		JLabel wonLabel = new JLabel("원");
		wonLabel.setBounds(327, 219, 35, 15);
		getContentPane().add(wonLabel);

		JScrollPane scrollPane = new JScrollPane(menuTable);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setBounds((frameWidth-menuTable.getPreferredSize().width)/2, 28,
		// menuTable.getPreferredSize().width, 211);
		scrollPane.setBounds((frameWidth - menuTable.getPreferredSize().width) / 2, 47,
				menuTable.getPreferredSize().width, 122);
		getContentPane().add(scrollPane);

		newPriceField = new JTextField();
		newPriceField.setColumns(10);
		newPriceField.setBounds(225, 216, 90, 21);
		getContentPane().add(newPriceField);

		JLabel lblNewLabel = new JLabel("==>");
		lblNewLabel.setBounds(198, 219, 27, 15);
		getContentPane().add(lblNewLabel);

		JLabel ilabel = new JLabel("이미지 파일");
		ilabel.setBounds(20, 248, 83, 15);
		getContentPane().add(ilabel);

		imgLabel = new JLabel();
		imgLabel.setBounds(30, 274, 225, 170);
		getContentPane().add(imgLabel);

		// 나머지 필드가 기본적으로 맨 위에 로우값으로 보이게 한다.
		Singleton s = Singleton.getInstance();
		MenuShowDto topDto = (MenuShowDto) s.getMenuCtrl().getMenDao().get(0);
		System.out.println(topDto.toString());
		nameField.setText(topDto.getMenu_name());
		priceField.setText(topDto.getPrice() + "");
		imgFileField.setText(topDto.getMenu_name() + ".jpg");
		setImage(FOLDER_PATH + topDto.getMenu_name().replaceAll(" ", "_") + ".jpg");

		setVisible(true);
	}

	public void setTable() {
		String colNames[] = { "이름", "가격", "이미지파일" };
		Object rowData[][] = setDataForTable();
		model = new DefaultTableModel(rowData, colNames) {
			// 테이블의 각 셀에 입력되어 있는 값을 임의로 변결할 수 없게 해 준다.
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		menuTable = new JTable(model);
		menuTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // 칼럼 크기가 모두 동일하게 들어가면 보기 좋지 않으므로 각각 셋팅해준다.
		menuTable.getColumnModel().getColumn(NAME_COL).setPreferredWidth(120);
		menuTable.getColumnModel().getColumn(PRICE_COL).setPreferredWidth(80);
		menuTable.getColumnModel().getColumn(IMG_COL).setPreferredWidth(140);

		menuTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = menuTable.getSelectedRow();
				nameField.setText(model.getValueAt(row, NAME_COL).toString());
				priceField.setText(model.getValueAt(row, PRICE_COL).toString());
				newPriceField.setText(model.getValueAt(row, PRICE_COL).toString());
				imgFileField.setText(model.getValueAt(row, IMG_COL).toString());
				setImage(FOLDER_PATH + model.getValueAt(row, IMG_COL).toString());
			}
		});

		// 번호랑 작성자는 가운데 정렬로 표현해주자
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		menuTable.getColumn("가격").setCellRenderer(celAlignCenter);
		menuTable.getColumn("이미지파일").setCellRenderer(celAlignCenter);

	}

	public Object[][] setDataForTable() {
		Singleton s = Singleton.getInstance();
		Object rowData[][];
		rowData = new Object[s.getMenuCtrl().getMenDao().getSize()][3];

		for (int j = 0; j < s.getMenuCtrl().getMenDao().getSize(); j++) { // 모든 메뉴를
			MenuShowDto menu = (MenuShowDto) s.getMenuCtrl().getMenDao().get(j);
			rowData[j] = addDataToRowArr(menu); // 배열에 넣어준다.
		}
		return rowData;
	}

	public Object[] addDataToRowArr(MenuShowDto menu) {
		Object rowData[] = new Object[3];
		rowData[NAME_COL] = menu.getMenu_name();
		rowData[PRICE_COL] = menu.getPrice();
		rowData[IMG_COL] = menu.getMenu_name().replaceAll(" ", "_") + ".jpg";
		return rowData;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Singleton s = Singleton.getInstance();
		if (e.getSource() == applyBtn) {
			System.out.println("변경됨");
			MenuShowDto menu = (MenuShowDto) s.getMenuCtrl().getMenDao().getMenuByName(nameField.getText());
			int row = menuTable.getSelectedRow();
			int newPrice = Integer.parseInt(newPriceField.getText());
			String image = imgFileField.getText();
			if (menu.getPrice() != newPrice) {
				model.setValueAt(newPrice, row, PRICE_COL);
				// menu.setPrice(newPrice);
				s.getMenuCtrl().getMenDao().updatePrice(menu, newPrice);
			}
			s.getMenuCtrl().getMenDao().updateImage(menu, image);
			model.setValueAt(image, row, IMG_COL);
		} else if (e.getSource() == delBtn) {
			MenuDto menu = s.getMenuCtrl().getMenDao().getMenuByName(nameField.getText());
			int row = menuTable.getSelectedRow();
			int sel = JOptionPane.showConfirmDialog(null, "이 메뉴를 삭제하시겠습니까?", "", JOptionPane.YES_NO_OPTION);
			if (sel == 0) {
				model.removeRow(row);
				s.getMenuCtrl().getMenDao().delete(menu);
			}
		} else if (e.getSource() == backBtn) {
			this.dispose();
		} else if (e.getSource() == searchBtn) {
			String path = jFileChooserUtil();
			System.out.println(path);
			imgFileField.setText(path.substring(path.lastIndexOf("\\") + 1)); // 전체 경로에서 파일 이름과 확장자명만 가져온다.
			setImage(path);

		}
	}

	public String jFileChooserUtil() {

		String folderPath = "";

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
		chooser.setCurrentDirectory(new File("d:/images")); // 현재 사용 디렉토리를 지정
		chooser.setAcceptAllFileFilterUsed(true); // Fileter 모든 파일 적용
		chooser.setDialogTitle("파일 위치 검색"); // 창의 제목
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드

		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg"); // filter 확장자 추가
		chooser.setFileFilter(filter); // 파일 필터를 추가

		int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈

		if (returnVal == JFileChooser.APPROVE_OPTION) { // 열기를 클릭
			folderPath = chooser.getSelectedFile().toString();
		} else if (returnVal == JFileChooser.CANCEL_OPTION) { // 취소를 클릭
			System.out.println("cancel");
			folderPath = "";
		}
		ImageIcon icon = new ImageIcon(folderPath);
		JLabel imgLabel = new JLabel(icon);
		getContentPane().add(imgLabel);

		return folderPath;

	}

	public void setImage(String path) {
		try {
			BufferedImage m_numberImage = ImageIO.read(new File(path));
			ImageIcon icon = new ImageIcon(m_numberImage);

			// ImageIcon에서 Image를 추출
			Image originImg = icon.getImage();

			// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
			Image changedImg = originImg.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(),
					Image.SCALE_SMOOTH);

			// 새로운 Image로 ImageIcon객체를 생성
			ImageIcon resizedIcon = new ImageIcon(changedImg);

			imgLabel.setIcon(resizedIcon);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
