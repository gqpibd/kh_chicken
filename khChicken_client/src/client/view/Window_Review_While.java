package client.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.singleton.Singleton;
import dto.ReviewDto;
import sun.misc.Signal;

public class Window_Review_While extends JFrame implements ActionListener {
	
	
	JButton JBut_back;
	JButton JBut_Review_Input;
	JButton JBut_Loging;
	
	
	JTextArea JTextA_Review_Input;
	JScrollPane JScroll_Review_Input;
	
	String menuName;
	private int myScore = 10;

	
	JLabel JLabel_Logo;
	
	public Window_Review_While(String menuName) {
	//public Window_Review_While() {
	setLayout(null);
	this.menuName=menuName;
	Singleton single = Singleton.getInstance();
	
	
	JTextA_Review_Input = new JTextArea();
	JScroll_Review_Input = new JScrollPane(JTextA_Review_Input);
	JScroll_Review_Input.setBounds(12, 56, 372, 83);
	add(JScroll_Review_Input);
	
	JBut_Review_Input = new JButton("작성하기");
	JBut_Review_Input.addActionListener(this);
	JBut_Review_Input.setBounds(287, 149, 97, 23);
	add(JBut_Review_Input);
	
	
	JLabel_Logo = new JLabel("");
	JLabel_Logo.setOpaque(true);
	JLabel_Logo.setBounds(12, 138, 97, 34);
	add(JLabel_Logo);
	
	
	JBut_back = new JButton("이전으로");
	JBut_back.addActionListener(this);
	JBut_back.setBounds(287, 12, 97, 34);
	add(JBut_back);
	
	
	JBut_Loging = new JButton("로그인");
	JBut_Loging.addActionListener(this);
	add(JBut_back);
	
	JProgressBar myScore = new JProgressBar();
	myScore.setBounds(12, 12, 130, 30);
	getActiveStarBar(myScore);
	getContentPane().add(myScore);
	
	setVisible(true);
	setBounds(100, 100, 414, 221);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	if(single.getMemCtrl().getCurrentUser() == null) {
		JBut_Review_Input.setEnabled(false);
		JTextA_Review_Input.setText("로그인을 하셔야 리뷰를 작성하실수 있습니다. \n로그인을 하실려면 클릭해주세요.");
		JTextA_Review_Input.setEditable(false);
		myScore.removeMouseListener(myScore.getMouseListeners()[0]);
		JTextA_Review_Input.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				single.getMemCtrl().loginView(Window_Review_While.this,1);
			}
		});
		
	}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		Singleton single = Singleton.getInstance();
		ReviewDto dto = new ReviewDto();

		dto.setUserId(single.getMemCtrl().getLoginId());
		dto.setMenuName(menuName);
		dto.setReview(JTextA_Review_Input.getText());
		dto.setScore(myScore);
		System.out.println(dto.toString());
		if (obj == JBut_Review_Input) {
			
			boolean Review_Check =single.getRevCtrl().update(dto);
			
			
			if(JTextA_Review_Input.equals("")) {
				JOptionPane.showMessageDialog(null, "리뷰를 작성해주세요,");
			}else {
			//single.getRevCtrl().update(dto);
			if(Review_Check == true) {
			
				JOptionPane.showMessageDialog(null, "작성이 완료되었습니다.");
			}else if(Review_Check == false) {
				JOptionPane.showMessageDialog(null, "리뷰를 작성할 자격이없습니다.");
			}
			}
		}else if(obj == JBut_back){
			dispose();
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
	
	
	
	
	

}
