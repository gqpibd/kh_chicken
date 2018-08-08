package client.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class Window_Review extends JFrame implements ActionListener,  MouseListener {

	
		Image Image_Fired; // 후라이드치킨
		ImageIcon Imageicon_Fired; // 후라이드치킨 사이즈맞춘거
		JLabel JLabe_Fired; // 후라이드 치킨 간판
		
		
		ImageIcon ImageIcon_LogoFired; //후라이드 로그 
		JLabel JLabe_LogoFired; // 후라이드로그
		
		JLabel JLabe_explanation; // 음식소개글
		
		JButton JBut_Review;
		
	
		
	public Window_Review() {
		super("리뷰창");
		setLayout(null);
			
		
			Toolkit Toolk_Fired = Toolkit.getDefaultToolkit();
			Image_Fired = Toolk_Fired.getImage("Fried.png");
			Image_Fired = Image_Fired.getScaledInstance(261, 189, 200); // 사진 사이즈 조절
			Imageicon_Fired = new ImageIcon(Image_Fired);
			JLabe_Fired = new JLabel(Imageicon_Fired);
			JLabe_Fired.setBounds(88, 100, 261, 191);
			add(JLabe_Fired);
			
			ImageIcon_LogoFired = new ImageIcon("LogoFired.png");
			JLabe_LogoFired = new JLabel(ImageIcon_LogoFired);
			JLabe_LogoFired.setBounds(88, 10, 261, 67);
			add(JLabe_LogoFired);
			
		JLabe_explanation = new JLabel("<html>이것은 너무나도 맛있는 치킨입니다<br/>정말 맛있는 치킨입니다 <br/> 레알로 맛있는 치킨입니다. <br/> 진짜 개맛있는 치킨입니다. <br/> 한번먹으면 빠져나올수 없는 치킨입니다. <br/> 그것은 바로 후라이드 치킨입니다</html>");
		JLabe_explanation.setBounds(98, 301, 226, 126);
		JLabe_explanation.setBackground(Color.white);
		JLabe_explanation.setOpaque(true);
		add(JLabe_explanation);
		
		
		JBut_Review = new JButton("리뷰");
		JBut_Review.addActionListener(this);
		JBut_Review.setBounds(200, 500, 100, 50);
		add(JBut_Review);
		
		
		
		
		
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 589);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if(obj == JBut_Review) {
			System.out.println("aa");
		JPanel container = new JPanel(); 
		
		JScrollPane scrPane = new JScrollPane(container);
		scrPane.setBounds(80, 200, 300, 200);
		JLabel ja[] = new JLabel[100];
		int i = 0;
		for (i = 0; i < ja.length; i++) {
				
			ja[i] = new JLabel(i+"");
			container.add(ja[i]);
		}
		container.setLayout(new GridLayout(i, 0));
		
		add(scrPane);
		repaint();
		if(JBut_Review.getText().equals("닫기")) {
			JBut_Review.setText("리뷰");
			dispose();
			new Window_Review();
		}else if(JBut_Review.getText().equals("리뷰")) {
			JBut_Review.setText("닫기");
		}
		
		}
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
