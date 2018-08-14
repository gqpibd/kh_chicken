package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import client.controller.MenuController;
import client.dao.MenuDao;
import client.singleton.Singleton;
import dto.MenuShowDto;
import dto.ReviewDto;
import net.miginfocom.swing.MigLayout;
import utils.images.ImageUtils;

public class ReviewDialog extends JDialog implements ActionListener {
	private MenuShowDto menu; // 메뉴 이름

	private JButton JBut_SelectMenu; // 장바구니 담기
	private JButton JBut_Back; // 뒤로가기
	private JButton JBut_Write; // 리뷰 작성
	
	// 새로 리뷰가 추가되면 업데이트 해야하는 것들
	private JProgressBar avgStarBar; // 별점(그림)
	private JLabel scoreLabel; // 별점(숫자)
	private JPanel panel_menu; // 리뷰패널

	private boolean select = false; // 장바구니에 담았는지 여부

	public ReviewDialog(JFrame parent, String menuName) {
		super(parent, true);
		getContentPane().setLayout(null);
		setBounds(100, 100, 566, 557);
		Singleton s = Singleton.getInstance();

		MenuController mCtrl = s.getMenuCtrl();
		this.menu = mCtrl.getMenuDto(menuName);

		// 메뉴 사진
		String imgPath = MenuDao.FOLDER_PATH + menuName.replaceAll(" ", "_") + ".jpg";
		JLabel JLabel_Menu = new JLabel();
		JLabel_Menu.setBounds(14, 12, 207, 184);
		ImageUtils.setResizedImage(JLabel_Menu, imgPath);
		getContentPane().add(JLabel_Menu);

		// 메뉴 설명
		JTextArea menuStory = new JTextArea();
		menuStory.setText(menu.getDescription());
		menuStory.setLineWrap(true);
		menuStory.setEditable(false);
		menuStory.setFocusable(false);
		menuStory.setBounds(235, 54, 299, 142);
		menuStory.setBackground(Color.white);
		menuStory.setOpaque(true);
		getContentPane().add(menuStory);

		// 각 리뷰 정보를 담는 리스트
		List<ReviewDto> list = new ArrayList<ReviewDto>();
		JPanel panel_bigmenu = new JPanel();
		panel_bigmenu.setBounds(14, 209, 520, 204);
		panel_bigmenu.setLayout(new MigLayout());

		panel_menu = new JPanel(); // 리뷰 하나하나 들어갈 패널 (스크롤)
		panel_menu.setLayout(new MigLayout());
		panel_menu.setBackground(Color.white);
		
		JScrollPane scroll = new JScrollPane(panel_menu);
		scroll.getVerticalScrollBar().setUnitIncrement(15); // 스크롤 할 때 움직이는 양
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	

		scroll.setPreferredSize(new Dimension(800, 204));
		panel_bigmenu.add(scroll);
		list = Singleton.getInstance().getRevCtrl().getList();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			panel_menu.add(getEachReviewPanel(list.get(i)), "wrap");
		}
		getContentPane().add(panel_bigmenu);

		JBut_Write = new JButton("리뷰쓰기");
		if (s.getMemCtrl().getCurrentUser() == null) { // 로그인 상태가 아니면 리뷰쓰기 버튼이 안보이게 한다.
			JBut_Write.setVisible(false);
		} else {
			JBut_Write.addActionListener(this);
		}

		JBut_Write.setBounds(216, 468, 93, 32);
		getContentPane().add(JBut_Write);

		JBut_Back = new JButton("이전으로");
		JBut_Back.addActionListener(this);
		JBut_Back.setBounds(323, 468, 93, 32);
		getContentPane().add(JBut_Back);

		JBut_SelectMenu = new JButton("장바구니");
		JBut_SelectMenu.setBounds(428, 466, 93, 32);
		JBut_SelectMenu.addActionListener(this);
		getContentPane().add(JBut_SelectMenu);

		JLabel JLabel_Logo = new JLabel();
		JLabel_Logo.setBackground(Color.WHITE);
		JLabel_Logo.setOpaque(true);
		JLabel_Logo.setBounds(14, 425, 187, 75);
		getContentPane().add(JLabel_Logo);

		JLabel menuNameLabel = new JLabel(menuName);
		menuNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		menuNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuNameLabel.setBounds(233, 12, 301, 32);
		getContentPane().add(menuNameLabel);

		JPanel panel = new JPanel();
		panel.setBounds(216, 423, 322, 32);
		getContentPane().add(panel);

		panel.setLayout(new GridLayout(1, 2));
		scoreLabel = new JLabel("고객 만족도 : " + menu.getavgScore() + "   ");
		scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreLabel.setFont(new Font("나눔고딕", Font.BOLD, 18));
		panel.add(scoreLabel);

		
		JPanel avgScoPanel = getStarBarPan(menu.getavgScore());
		avgStarBar = (JProgressBar) avgScoPanel.getComponent(0);
		panel.add(avgScoPanel);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public JPanel getEachReviewPanel(ReviewDto dto) { // 각 리뷰 패널 생성
		JLabel JLabel_ReviewName; // 리뷰 작성자
		JTextArea JTextA_Review; // 리뷰
		JPanel JPanel_Review; // 리뷰 패널
		// 메뉴 출력
		JPanel_Review = new JPanel(new MigLayout());
		JPanel_Review.setBackground(Color.white);
		JLabel_ReviewName = new JLabel(dto.getUserId());
		JTextA_Review = new JTextArea(dto.getReview());
		JTextA_Review.setBorder(new LineBorder(new Color(0, 0, 0)));
		JTextA_Review.setEditable(false);
		JTextA_Review.setLineWrap(true);

		JPanel_Review.add(new JLabel("작성자 : "), "cell 0 0 1 1");
		JPanel_Review.add(JLabel_ReviewName, "cell 1 0 1 1");

		// JProgressBar star = new JProgressBar();
		// JPanel_Review[i].add(getStarBar(star, list.get(i).getScore()), "cell 3 0 1 1,
		// gapleft 30");
		// JPanel_Review.add(getStarBar(star, dto.getScore()), "w :66:, wrap, gapleft
		// 30");
		JPanel_Review.add(getStarBarPan(dto.getScore()), "w :180:, h :30:, wrap, gapleft 30");
		JPanel_Review.add(JTextA_Review, "span 4, w 450!, h 40:60:80");

		return JPanel_Review;

	}

	public JPanel getStarBarPan(double value) {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		panel.setBackground(Color.white);
		int _value = (int) value;
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.YELLOW);
		progressBar.setValue(_value);
		progressBar.setMaximum(10);
		int scalefactor = 2;
		int width = 62 * scalefactor;
		int height = 13 * scalefactor;
		// 비율 : 가로 62, 세로 13

		progressBar.setSize(new Dimension(width, height));
		JLabel label = new JLabel();
		label.setSize(progressBar.getSize());
		label.setLocation(progressBar.getLocation());

		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(new File("star.png")));
			Image changedImg = icon.getImage().getScaledInstance(progressBar.getWidth(), progressBar.getHeight(),
					Image.SCALE_SMOOTH);
			ImageIcon resizedIcon = new ImageIcon(changedImg);

			label.setIcon(resizedIcon);

			progressBar.add(label);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		panel.add(progressBar);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton single = Singleton.getInstance();
		if (JBut_Write == obj) {
			ReviewDto rDto = new ReviewDto();
			rDto.setMenuName(menu.getMenu_name());
			rDto.setUserId(single.getMemCtrl().getLoginId());
			rDto = single.getRevCtrl().WritableReview(rDto); // 작성 가능한 리뷰가 있으면 받는다. 이걸 업데이트 할거임		
			if (rDto == null) {
				JOptionPane.showMessageDialog(null, "작성 가능한 리뷰가 없습니다");
				return;
			} else { // 리뷰 작성창을 띄워준다
				single.getRevCtrl().Write_review(this, rDto);
				double newScore = single.getRevCtrl().getNewScore();
				
				if(newScore != -1) {
					avgStarBar.setValue((int) menu.getavgScore() );
					scoreLabel.setText("고객 만족도 : " + menu.getavgScore() + "   ");
					panel_menu.add(getEachReviewPanel(single.getRevCtrl().getNewReview()));
					
				}
			}
		} else if (JBut_Back == obj) {
			dispose();
			single.getMainView().setVisible(true);
		} else if (obj == JBut_SelectMenu) {
			select = true;
			dispose();
			single.getMainView().setVisible(true);
		}
	}

	public boolean getSelection() {
		return select;
	}
}
