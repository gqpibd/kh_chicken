package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.api.server.Container;


public class Window_Review extends JFrame implements ActionListener,  MouseListener {
	
	
		Image Image_Fired; // 후라이드치킨
		ImageIcon Imageicon_Fired; // 후라이드치킨 사이즈맞춘거
		JLabel JLabe_Fired; // 후라이드 치킨 간판
		
		
		ImageIcon ImageIcon_LogoFired; //후라이드 로그 
		JLabel JLabe_LogoFired; // 후라이드로그
		
		JLabel JLabe_explanation; // 음식소개글
		
		JButton JBut_ReviewRead;
		JPanel container;
		JScrollPane scrPane;
		
		JTextArea JTextA_Review;
		JScrollPane JScroll_Review;
		JButton JBut_reviewInput;
		
	public Window_Review() {

		super("리뷰창");
		setLayout(null);
		
			
		
			Toolkit Toolk_Fired = Toolkit.getDefaultToolkit();
			Image_Fired = Toolk_Fired.getImage("Fried.png");
			Image_Fired = Image_Fired.getScaledInstance(261, 189, 200); // 사진 사이즈 조절
			Imageicon_Fired = new ImageIcon(Image_Fired);
			JLabe_Fired = new JLabel(Imageicon_Fired);
			JLabe_Fired.setBounds(88, 100, 261, 191);
		//	add(JLabe_Fired);
			
			ImageIcon_LogoFired = new ImageIcon("LogoFired.png");
			JLabe_LogoFired = new JLabel(ImageIcon_LogoFired);
			JLabe_LogoFired.setBounds(88, 10, 261, 67);
		//	add(JLabe_LogoFired);
			
		JLabe_explanation = new JLabel(/*"<html>이것은 너무나도 맛있는 치킨입니다<br/>정말 맛있는 치킨입니다 <br/> 레알로 맛있는 치킨입니다. <br/> 진짜 개맛있는 치킨입니다. <br/> 한번먹으면 빠져나올수 없는 치킨입니다. <br/> 그것은 바로 후라이드 치킨입니다</html>"*/);
		JLabe_explanation.setBounds(98, 301, 226, 126);
		//JLabe_explanation.setBackground(Color.white);
		JLabe_explanation.setOpaque(true);
		//add(JLabe_explanation);
		
		JBut_ReviewRead = new JButton("리뷰");
		JBut_ReviewRead.addActionListener(this);
		JBut_ReviewRead.setBounds(200, 550, 100, 50);
		//add(JBut_ReviewRead);
		
		
		
		
		container = new JPanel();

		
		//container.setBackground(Color.WHITE);
		JPanel Jpanel_Review[] = new JPanel[100];
		JLabel ja[] = new JLabel[100];
		JTextArea jta[] = new JTextArea[100];
		int i = 0;
		
		DefaultListModel<JPanel> JPan_Model = new DefaultListModel<>();
		
		
		
		for (i = 0; i < Jpanel_Review.length; i++) {
			
			Jpanel_Review[i] = new JPanel();
			Jpanel_Review[i].setLayout(null);
			Jpanel_Review[i].setBounds(0, 60 * i, 100, 50);
			ja[i] = new JLabel(i+"하하하");
			ja[i].setBounds(0, -10, 500, 50);
			jta[i] = new JTextArea("안녕하세요"+i);
			jta[i].setBounds(0, 27, 100, 52);
			//jta[i].setEditable(false);
			Jpanel_Review[i].add(ja[i]);
			Jpanel_Review[i].add(jta[i]);
			//add(Jpanel_Review[i]);
			//scrPane = new JScrollPane(Jpanel_Review[i]);
			//container.add(Jpanel_Review[i]);
			JPan_Model.addElement(Jpanel_Review[i]);
			//add(new JScrollPane(Jpanel_Review[i]));
		}
	//	container.setLayout(new GridLayout(i, 0));
		container.setLayout(new BorderLayout());
		
		JList<JPanel> list = new JList<>(JPan_Model);
		
		
		container.setBackground(Color.WHITE);
		container.add(new JScrollPane(list),BorderLayout.CENTER);
		JScrollPane JSc =  new JScrollPane(container);
		JSc.setBounds(0, 0, 400, 400);
		add(JSc);
		
		//add(JSc);
		
		
		
	//	scrPane = new JScrollPane(container);
//		container.setBounds(0, 0, 400, 500);
	//	add(container);
		
	//	JPanel JPane_Review = new JPanel();
	//	JPane_Review.setLayout(new FlowLayout());
	//	JTextA_Review =new JTextArea();
	//	JBut_reviewInput = new JButton("입력");
	//	JBut_reviewInput.addActionListener(this);
	//	container.add(JTextA_Review);
	//	container.add(JBut_reviewInput);
	//	JPane_Review.setBounds(200, 200, 100, 100);
	//	add(scrPane);
		
		
		//scrPane.setVisible(false);
		
		//getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 589);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj == JBut_ReviewRead) {
		if(JBut_ReviewRead.getText().equals("닫기")) {
			JBut_ReviewRead.setText("리뷰");
			//scrPane.setVisible(false);
			//repaint();
			//dispose();
			//new Window_Review();
		}else if(JBut_ReviewRead.getText().equals("리뷰")) {
			JBut_ReviewRead.setText("닫기");
			//scrPane.setVisible(true);
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
