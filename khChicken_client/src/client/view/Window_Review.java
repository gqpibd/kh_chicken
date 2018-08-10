package client.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;

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

import client.dto.ReviewDto;
import client.singleton.Singleton;



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
		
		List<ReviewDto> list =new ArrayList<ReviewDto>();
		
		JPanel jp[];
		JLabel jl[];
		JPanel jpp;
		
	public Window_Review() {

		super("리뷰");
		setLayout(null);
		
		jpp = new JPanel();
		
		Singleton single = Singleton.getInstance();
		list = single.revCtrl.select();
		jp = new JPanel[list.size()];
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i);
			jp = new JPanel[i+1];
			jl =new JLabel[i+1];
			jp[i] = new JPanel();
			
			jp[i].setLayout(new FlowLayout());
			jp[i].setBounds(100, 250*i, 200, 200);
			//jl[i] = new JLabel(list.get(i).getUserId());
			jl[i] = new JLabel(list.get(i).getReview());
			System.out.println(jl[i].getText());
			jp[i].add(jl[i]);
			//JTextArea jjta = new JTextArea(list.get(i).getReview());
			//jjta.setBounds(0, 50, 400, 400);
			//add(jjta);
			//jp[i].setBackground(Color.WHITE);
			jpp.add(jp[i]);
		}
		jpp.setBounds(100, 250, 200, 200);
		add(jpp);
			Toolkit Toolk_Fired = Toolkit.getDefaultToolkit();
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
			
		JLabe_explanation = new JLabel(/*"<html>?��것�? ?��무나?�� 맛있?�� 치킨?��?��?��<br/>?���? 맛있?�� 치킨?��?��?�� <br/> ?��?���? 맛있?�� 치킨?��?��?��. <br/> 진짜 개맛?��?�� 치킨?��?��?��. <br/> ?��번먹?���? 빠져?��?��?�� ?��?�� 치킨?��?��?��. <br/> 그것?? 바로 ?��?��?��?�� 치킨?��?��?��</html>"*/);
		JLabe_explanation.setBounds(98, 301, 226, 126);
		//JLabe_explanation.setBackground(Color.white);
		JLabe_explanation.setOpaque(true);
		//add(JLabe_explanation);
		
		JBut_ReviewRead = new JButton("리뷰");
		JBut_ReviewRead.addActionListener(this);
		JBut_ReviewRead.setBounds(0, 0, 100, 50);
		add(JBut_ReviewRead);
		
		
		
		
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
			ja[i] = new JLabel(i+"");
			ja[i].setBounds(0, -10, 500, 50);
			jta[i] = new JTextArea(""+i);
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
		//add(JSc);
		

		setBounds(100, 100, 450, 589);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Singleton single = Singleton.getInstance();
		Object obj = e.getSource();
		if(obj == JBut_ReviewRead) {
		
			if(JBut_ReviewRead.getText().equals("닫기")) {
			JBut_ReviewRead.setText("리뷰");
			
			
			jpp.setVisible(false);
			//scrPane.setVisible(false);
			//repaint();
			//dispose();
			//new Window_Review();
		}else if(JBut_ReviewRead.getText().equals("리뷰")) {
			JBut_ReviewRead.setText("닫기");
			for(int i = 0; i < list.size(); i++) {
				jpp.setVisible(true);
		}
		
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
