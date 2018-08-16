package client.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.singleton.Singleton;
import dto.MenuShowDto;
import dto.ReviewDto;
import utils.images.ImageUtils;

public class ReviewWriteDialog extends JDialog {

	private JLabel JBut_back; // 돌아가기
	private JLabel JBut_Review_Input; // 리뷰 작성

	private JTextArea JTextA_Review_Input; // 리뷰 내용

	private ReviewDto rDto;
	private int myScore = 10;
	double newScore = -1;
	private static final String PATH = "images/reviewView/";
	
	public ReviewWriteDialog(ReviewDto rDto) {
		setTitle("리뷰쓰기");
		this.rDto = rDto;
		setInitView(); // 화면 설정

	}

	public void setInitView() {
		
		setModal(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 414, 221);
		MyClickListener mcl = new MyClickListener();

		// 리뷰 입력란
		JTextA_Review_Input = new JTextArea();
		JTextA_Review_Input.setLineWrap(true);
		JScrollPane JScroll_Review_Input = new JScrollPane(JTextA_Review_Input);
		JScroll_Review_Input.setBounds(12, 56, 372, 83);
		add(JScroll_Review_Input);

		// 별점
		JProgressBar myScore = new JProgressBar();
		myScore.setBounds(12, 12, 130, 30);
		getActiveStarBar(myScore);
		getContentPane().add(myScore);

		JBut_Review_Input = new JLabel();
		JBut_Review_Input.addMouseListener(mcl);
		JBut_Review_Input.setBounds(287, 142, 100, 35);
		ImageUtils.setResizedImage(JBut_Review_Input, PATH + "writeEndBtn.jpg");
		add(JBut_Review_Input);
		
		JBut_back = new JLabel();
		JBut_back.addMouseListener(mcl);
		JBut_back.setBounds(287, 12, 100, 35);
		ImageUtils.setResizedImage(JBut_back, PATH + "writeReturnBtn.jpg");
		add(JBut_back);

		setVisible(true);

	}

	private class MyClickListener extends MouseAdapter {
		Singleton s = Singleton.getInstance();

		@Override
		public void mouseClicked(MouseEvent e) {
			Object obj = e.getSource();

			rDto.setReview(JTextA_Review_Input.getText());
			rDto.setScore(myScore);

			if (obj == JBut_Review_Input) { // 작성하기 클리
				if (JTextA_Review_Input.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "리뷰를 작성해주세요,");
				} else {
					s.getRevCtrl().update(rDto);

					List<ReviewDto> list = Singleton.getInstance().getRevCtrl().getList(); // 리뷰 목록을 불러온다.

					// 현재 DB에있는 별점 총계산
					double Sum_Score = 0;
					System.out.println("리스트 사이즈:" + list.size());
					// List<MenuShowDto> mlist = s.getMenuCtrl().get_List();
					for (int i = 0; i < list.size(); i++) {
						Sum_Score = Sum_Score + list.get(i).getScore();
					}

					Sum_Score = Sum_Score + rDto.getScore(); // 총별점 + 방금내가 넣어준 별점

					newScore = Sum_Score / (list.size() + 1);

					MenuShowDto menuDto = s.getMenuCtrl().getMenuDto(rDto.getMenuName());
					menuDto.setavgScore(newScore);
					System.out.println(menuDto);
					s.getMenuCtrl().update(menuDto);//

					dispose();
					
					JOptionPane.showMessageDialog(null, "작성이 완료되었습니다.");			

				}
			} else if (obj == JBut_back) { // 이전으로 클릭
				dispose();
			}

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

	public double getNewScore() {
		return newScore;
	}
	
	public ReviewDto getNewReview() {
		return rDto;
	}
}
