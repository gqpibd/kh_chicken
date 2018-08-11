package client.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.singleton.Singleton;
import dto.ReviewDto;
import net.miginfocom.swing.MigLayout;



public class Window_Review extends JFrame implements ActionListener,  MouseListener {
	
	
		Image Image_Fired;
		ImageIcon Imageicon_Fired;
		JLabel JLabe_Fired; 
		
		
		ImageIcon ImageIcon_LogoFired; 
		JLabel JLabe_LogoFired; 
		
		JLabel JLabe_explanation; 
		
		JButton JBut_ReviewRead;
		JPanel container;
		JScrollPane scrPane;
		
		JTextArea JTextA_Review;
		JScrollPane JScroll_Review;
		JButton JBut_reviewInput;
		
		

		
		
		
	public Window_Review() {

		super("리뷰");
		setLayout(null);
		
		
		List<ReviewDto> list =new ArrayList<ReviewDto>();
	 
			// 음식 설명창인데 아직 리뷰랑 연결못해서 주석처리
			/*Toolkit Toolk_Fired = Toolkit.getDefaultToolkit();
			Image_Fired = Toolk_Fired.getImage("Fried.png");
			Image_Fired = Image_Fired.getScaledInstance(261, 189, 200); // ?���? ?��?���? 조절
			Imageicon_Fired = new ImageIcon(Image_Fired);
			JLabe_Fired = new JLabel(Imageicon_Fired);
			JLabe_Fired.setBounds(88, 100, 261, 191);
		//	add(JLabe_Fired);
			
			ImageIcon_LogoFired = new ImageIcon("LogoFired.png");
			JLabe_LogoFired = new JLabel(ImageIcon_LogoFired);
			JLabe_LogoFired.setBounds(88, 10, 261, 67);
		//	add(JLabe_LogoFired);
			
		JLabe_explanation = new JLabel("<html>?��것�? ?��무나?�� 맛있?�� 치킨?��?��?��<br/>?���? 맛있?�� 치킨?��?��?�� <br/> ?��?���? 맛있?�� 치킨?��?��?��. <br/> 진짜 개맛?��?�� 치킨?��?��?��. <br/> ?��번먹?���? 빠져?��?��?�� ?��?�� 치킨?��?��?��. <br/> 그것?? 바로 ?��?��?��?�� 치킨?��?��?��</html>");
		JLabe_explanation.setBounds(98, 301, 226, 126);
		//JLabe_explanation.setBackground(Color.white);
		JLabe_explanation.setOpaque(true);
		//add(JLabe_explanation);
		
		JBut_ReviewRead = new JButton("리뷰");
		JBut_ReviewRead.addActionListener(this);
		JBut_ReviewRead.setBounds(0, 0, 100, 50);
		//add(JBut_ReviewRead);
*/		
		
		
		JPanel JPan_Review_While = new JPanel();
		JPan_Review_While.setLayout(new GridLayout(2, 1));
		JPan_Review_While.setBounds(420, 200, 200, 200);
		
		JTextA_Review = new JTextArea();
		JScroll_Review =new JScrollPane(JTextA_Review);
		
		
		JBut_reviewInput =new JButton ("입력");
		JBut_reviewInput.addActionListener(this);
		
		
		
		JPan_Review_While.add(JScroll_Review);
		JPan_Review_While.add(JBut_reviewInput);
		add(JPan_Review_While);
		
		JPanel jp = new JPanel();
		jp.setLayout(new MigLayout("wrap", "", ""));
		
		JPanel panel_bigmenu = new JPanel(); // 전체 패널
		panel_bigmenu.setBounds(10, 100, 570, 600);
		panel_bigmenu.setLayout(new MigLayout());

		
		JPanel panel_menu = new JPanel(); // 리뷰 하나하나 들어갈 패널 (스크롤)
		panel_menu.setLayout(new MigLayout());
		
	
		
		Singleton single = Singleton.getInstance();
		list = single.revCtrl.select(); //리뷰를 불러와줌
		
		int Int_Dex = list.size();
		JLabel JLabel_ReviewName[] = new JLabel[Int_Dex]; // 리뷰 작성자
		JTextArea JTextA_Review[] = new JTextArea[Int_Dex]; // 리뷰
		JPanel JPanel_Review[] = new JPanel[Int_Dex]; // 리뷰 패널
		
		
		//메뉴 출력 
		for (int i = 0; i < list.size(); i++) {
			JLabel_ReviewName[i] = new JLabel(list.get(i).getUserId());
			JLabel_ReviewName[i].setBounds(0, 0, 100, 100);
			
			JTextA_Review[i] = new JTextArea(list.get(i).getReview());
			JTextA_Review[i].setBounds(20, 20, 20, 20);
			
			JPanel_Review[i] = new JPanel();
			JPanel_Review[i].setLayout(new GridLayout(2, 1));
			JPanel_Review[i].setBounds(0, 0, 100, 100);
			JPanel_Review[i].add(JLabel_ReviewName[i]);
			JPanel_Review[i].add(JTextA_Review[i]);
			
	
			panel_menu.add(JPanel_Review[i],"wrap");
			panel_bigmenu.validate();
		}
		
		JScrollPane scroll = new JScrollPane(panel_menu);
		scroll.setPreferredSize(new Dimension(400, 300));
		panel_bigmenu.add(scroll);
		add(panel_bigmenu);
		
		setBounds(0, 0, 640, 480);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Singleton single = Singleton.getInstance();
		Object obj = e.getSource();
		Singleton single = Singleton.getInstance();
		
		ReviewDto dto = new ReviewDto();
		dto.setUserId("윤상필");
		dto.setReview(JTextA_Review.getText());
		
		if (obj == JBut_reviewInput) {
			single.revCtrl.insert(dto);
			JOptionPane.showMessageDialog(null, "작성이 완료되었습니다.");
			dispose();
			//single.Win_Review =new Window_Review();
			
		}
		
		
		
		
		
		
		
		
		
	//	밑에 기능 무시해두됨
	/*	if(obj == JBut_ReviewRead) {
		
			if(JBut_ReviewRead.getText().equals("닫기")) {
			JBut_ReviewRead.setText("리뷰");
			
			
			//scrPane.setVisible(false);
			//repaint();
			//dispose();
			//new Window_Review();
		}else if(JBut_ReviewRead.getText().equals("리뷰")) {
			JBut_ReviewRead.setText("닫기");
			for(int i = 0; i < list.size(); i++) {
		}
		
	}
		}*/
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
}
