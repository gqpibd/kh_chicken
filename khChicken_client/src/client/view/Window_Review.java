package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import client.singleton.Singleton;
import dto.ReviewDto;
import net.miginfocom.swing.MigLayout;

public class Window_Review extends JFrame implements ActionListener {

	Image Image_Fired;
	ImageIcon Imageicon_Fired;
	JLabel JLabe_Fired;

	ImageIcon ImageIcon_LogoFired;
	JLabel JLabe_LogoFired;

	JLabel JLabe_explanation;

	JButton JBut_ReviewRead;
	JButton JBut_Back;
	JPanel container;
	JScrollPane scrPane;
	JPanel panel_menu;

	JTextArea JTextA_Review;
	JScrollPane JScroll_Review;
	JButton JBut_reviewInput;

	private String menuName = "";
	private int myScore = 10;

	public Window_Review(String menuName) {
		super("리뷰");
		System.out.println("Review menuName = " + menuName);
		this.menuName = menuName;

		List<ReviewDto> list = new ArrayList<ReviewDto>();
		getContentPane().setLayout(null);

		JPanel panel_bigmenu = new JPanel();
		panel_bigmenu.setBounds(12, 74, 414, 250);
		panel_bigmenu.setLayout(new MigLayout());

		panel_menu = new JPanel(); // 리뷰 하나하나 들어갈 패널 (스크롤)
		panel_menu.setLayout(new MigLayout());

		JScrollPane scroll = new JScrollPane(panel_menu);
		scroll.getVerticalScrollBar().setUnitIncrement(15); // 스크롤 할 때 움직이는 양
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		scroll.setAutoscrolls(false);
		scroll.setPreferredSize(new Dimension(400, 300));
		panel_bigmenu.add(scroll);

		list = Singleton.getInstance().getRevCtrl().getList();
		// 메뉴 출력
		for (int i = 0; i < list.size(); i++) {
			panel_menu.add(getEachReviewPanel(list.get(i)), "wrap");
		}
		getContentPane().add(panel_bigmenu);
		JScroll_Review = new JScrollPane();
		JScroll_Review.setBounds(12, 365, 303, 53);
		getContentPane().add(JScroll_Review);

		JTextA_Review = new JTextArea(); // 리뷰작성란

		JBut_reviewInput = new JButton("입력");
		JBut_reviewInput.setBounds(327, 340, 99, 34);
		getContentPane().add(JBut_reviewInput);
		JScroll_Review.setViewportView(JTextA_Review);

		JBut_Back = new JButton("뒤로가기");
		JBut_Back.setBounds(327, 384, 99, 34);
		getContentPane().add(JBut_Back);

		JLabel writerLabel = new JLabel("작성자: ");
		writerLabel.setBounds(12, 334, 57, 15);
		getContentPane().add(writerLabel);

		JLabel userId = new JLabel(Singleton.getInstance().getMemCtrl().getLoginId());
		userId.setBounds(81, 334, 82, 15);
		getContentPane().add(userId);

		JProgressBar myScore = new JProgressBar();
		int scalefactor = 2;
		int width = 62 * scalefactor;
		int height = 13 * scalefactor;
		myScore.setSize(width, height);
		myScore.setLocation(162, 334);
		getActiveStarBar(myScore);
		getContentPane().add(myScore);

		JBut_Back.addActionListener(this);
		JBut_reviewInput.addActionListener(this);
		setBounds(0, 0, 470, 480);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (Singleton.getInstance().getMemCtrl().getLoginId() == null) {
			JTextA_Review.setEditable(false);
			JTextA_Review.setText("로그인 후 리뷰를 작성할 수 있습니다");
			JBut_reviewInput.setEnabled(false);
			myScore.removeMouseListener(myScore.getMouseListeners()[0]);
		}
	}

	public JProgressBar getActiveStarBar(JProgressBar progressBar) {
		progressBar.setForeground(Color.YELLOW);
		progressBar.setValue(10);
		progressBar.setMaximum(10);

		JLabel label = new JLabel();
		label.setSize(progressBar.getSize());
		progressBar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				double x = e.getX();
				double start = label.getX();
				double last = label.getWidth();
				myScore = (int) ((x - start) / (last - start) * 10);
				if (myScore < 1)
					myScore = 1;
				((JProgressBar) e.getSource()).setValue(myScore);
			}
		});
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(new File("star.png")));
			Image changedImg = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(),
					Image.SCALE_SMOOTH);
			ImageIcon resizedIcon = new ImageIcon(changedImg);

			label.setIcon(resizedIcon);

			progressBar.add(label);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return progressBar;
	}

//	public JProgressBar getStarBar(JProgressBar progressBar, int value) {
//		progressBar.setForeground(Color.YELLOW);
//		progressBar.setValue(value);
//		progressBar.setMaximum(10);
//
//		// int scalefactor = 5;
//		// int width = 62 / scalefactor;
//		// int height = 13 / scalefactor;
//		// 비율 : 가로 62, 세로 13
//		int height = (int) progressBar.getPreferredSize().getHeight();
//		int width = (int) height * 62 / 13;
//		JLabel label = new JLabel();
////		label.setSize(progressBar.getPreferredSize());
//		label.setPreferredSize(progressBar.getPreferredSize());
//		//Size(progressBar.getPreferredSize().width,progressBar.getPreferredSize().height);
//		//label.setSize(width,height);
//		try {
//			ImageIcon icon = new ImageIcon(ImageIO.read(new File("star.png")));
//			Image changedImg = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
//			ImageIcon resizedIcon = new ImageIcon(changedImg);
//
//			label.setIcon(resizedIcon);
//
//			progressBar.add(label);
//			
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		return progressBar;
//	}
	
	public JPanel getStarBarPan(JPanel panel, int value) {
		panel.setLayout(null);
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.YELLOW);
		progressBar.setValue(value);
		progressBar.setMaximum(10);
		 int scalefactor = 2;
		 int width = 62 * scalefactor;
		 int height = 13 * scalefactor;
		// 비율 : 가로 62, 세로 13
		

		progressBar.setSize(new Dimension(width, height));
		JLabel label = new JLabel();
		label.setSize(progressBar.getSize());
		label.setLocation(progressBar.getLocation());
	//	label.setPreferredSize(progressBar.getPreferredSize());
		//Size(progressBar.getPreferredSize().width,progressBar.getPreferredSize().height);
		//label.setSize(width,height);
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(new File("star.png")));
			Image changedImg = icon.getImage().getScaledInstance(progressBar.getWidth(), progressBar.getHeight(), Image.SCALE_SMOOTH);
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

		ReviewDto dto = new ReviewDto();

		dto.setUserId(single.getMemCtrl().getLoginId());
		dto.setMenuName(menuName);
		dto.setReview(JTextA_Review.getText());
		
		dto.setScore(myScore);

		if (obj == JBut_reviewInput) {
			single.getRevCtrl().update(dto);
			JOptionPane.showMessageDialog(null, "작성이 완료되었습니다.");
			//panel_menu.add(getEachReviewPanel(dto));

		} else if (obj == JBut_Back) {
			single.backToMain(this);
		}
		

		// 밑에 기능 무시해두됨
		/*
		 * if(obj == JBut_ReviewRead) {
		 * 
		 * if(JBut_ReviewRead.getText().equals("닫기")) { JBut_ReviewRead.setText("리뷰");
		 * 
		 * 
		 * //scrPane.setVisible(false); //repaint(); //dispose(); //new Window_Review();
		 * }else if(JBut_ReviewRead.getText().equals("리뷰")) {
		 * JBut_ReviewRead.setText("닫기"); for(int i = 0; i < list.size(); i++) { }
		 * 
		 * } }
		 */

	}

	public JPanel getEachReviewPanel(ReviewDto dto) { // 각 리뷰 패널 생성
		JLabel JLabel_ReviewName = new JLabel(); // 리뷰 작성자
		JTextArea JTextA_Review = new JTextArea(); // 리뷰
		JPanel JPanel_Review = new JPanel(); // 리뷰 패널

		// 메뉴 출력

		JPanel_Review = new JPanel(new MigLayout());
		JLabel_ReviewName = new JLabel(dto.getUserId());
		JTextA_Review = new JTextArea(dto.getReview());
		JTextA_Review.setBorder(new LineBorder(new Color(0, 0, 0)));
		JTextA_Review.setEditable(false);

		JPanel_Review.add(new JLabel("작성자 : "), "cell 0 0 1 1");
		JPanel_Review.add(JLabel_ReviewName, "cell 1 0 1 1");

		//JProgressBar star = new JProgressBar();
		// JPanel_Review[i].add(getStarBar(star, list.get(i).getScore()), "cell 3 0 1 1,
		// gapleft 30");
//		JPanel_Review.add(getStarBar(star, dto.getScore()), "w :66:, wrap, gapleft 30");
		JPanel_Review.add(getStarBarPan(new JPanel(), dto.getScore()), "w :180:, h :30:, wrap, gapleft 30");
		JPanel_Review.add(JTextA_Review, "span 4, w 300!, h 40:60:80");

		return JPanel_Review;

	}
}
