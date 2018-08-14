package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import client.singleton.Singleton;
import dto.ReviewDto;
import net.miginfocom.swing.MigLayout;

public class Window_Testview extends JFrame {
	
	
	ImageIcon ImageIcon_Menu;
	Image Image_Menu;
	String Str_MenuName;
	
	JLabel JLabel_Menu;
	
	JButton JBut_ReviewRead;
	JButton JBut_Back;
	JPanel container;
	JScrollPane scrPane;
	JPanel panel_menu;

	JTextArea JTextA_Review;
	JScrollPane JScroll_Review;
	JButton JBut_reviewInput;
	
	
	public Window_Testview(String _Str_MenuName) {
	setLayout(null);
	this.Str_MenuName = "images\\"+_Str_MenuName.replaceAll(" ", "_") + ".jpg";
	
	Toolkit Tool_Menu = Toolkit.getDefaultToolkit();
	Image_Menu =  Tool_Menu.getImage(Str_MenuName);
	Image_Menu = Image_Menu.getScaledInstance(207, 184, Image.SCALE_SMOOTH);
	ImageIcon_Menu = new ImageIcon(Image_Menu);
	
	//JLabel_Menu = new JLabel(_ImageIcon_Menu);
	JLabel_Menu = new JLabel(ImageIcon_Menu);
	JLabel_Menu.setBounds(14, 12, 207, 184);
	add(JLabel_Menu);
	
	JLabel JLabel_MenuStory = new JLabel("<html>?��것�? ?��무나?�� 맛있?�� 치킨?��?��?��<br/>?���? 맛있?�� 치킨?��?��?�� <br/> ?��?���? 맛있?�� 치킨?��?��?��. <br/> 진짜 개맛?��?�� 치킨?��?��?��. <br/> ?��번먹?���? 빠져?��?��?�� ?��?�� 치킨?��?��?��. <br/> 그것?? 바로 ?��?��?��?�� 치킨?��?��?��</html>");
	JLabel_MenuStory.setBounds(235, 12, 299, 184);
	JLabel_MenuStory.setBackground(Color.white);
	JLabel_MenuStory.setOpaque(true);
	add(JLabel_MenuStory);
	
	// ===========================================
	
	List<ReviewDto> list = new ArrayList<ReviewDto>();
	JPanel panel_bigmenu = new JPanel();
	panel_bigmenu.setBounds(14, 209, 520, 204);
	panel_bigmenu.setLayout(new MigLayout());

	panel_menu = new JPanel(); // 리뷰 하나하나 들어갈 패널 (스크롤)
	panel_menu.setLayout(new MigLayout());

	JScrollPane scroll = new JScrollPane(panel_menu);
	scroll.getVerticalScrollBar().setUnitIncrement(15); // 스크롤 할 때 움직이는 양
	scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

	scroll.setAutoscrolls(false);
	scroll.setPreferredSize(new Dimension(520, 204));
	panel_bigmenu.add(scroll);
	list = Singleton.getInstance().getRevCtrl().getList();
	
	for (int i = 0; i < list.size(); i++) {
		System.out.println(list.get(i));
		panel_menu.add(getEachReviewPanel(list.get(i)), "wrap");
	}
	add(panel_bigmenu);
	JButton JBut1 = new JButton("이전으로");
	JBut1.setBounds(215, 425, 93, 32);
	add(JBut1);
	JButton JBut2 = new JButton("리뷰쓰기");
	JBut2.setBounds(322, 425, 93, 32);
	add(JBut2);
	JButton JBut3 = new JButton("장바구니");
	JBut3.setBounds(215, 468, 93, 32);
	add(JBut3);
	JButton JBut4 = new JButton("사운드듣기");
	JBut4.setBounds(428, 425, 106, 75);
	add(JBut4);

	
	JLabel JLabel_Logo = new JLabel();
	JLabel_Logo.setBackground(Color.WHITE);
	JLabel_Logo.setOpaque(true);
	JLabel_Logo.setBounds(14, 425, 187, 75);
	add(JLabel_Logo);
	
	setVisible(true);
	setBounds(100, 100, 566, 557);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	

}
