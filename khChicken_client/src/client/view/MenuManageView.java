package client.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MenuManageView extends JFrame implements ActionListener{
	JButton updMenuBtn;
	JButton addMenuBtn;
	public MenuManageView() {
		setTitle("메뉴 관리");

		setBounds(300, 150, 274, 253);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE); // 나중에 삭제하기
		getContentPane().setLayout(null);
		
		updMenuBtn = new JButton("기존 메뉴 관리");
		updMenuBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 22));
		updMenuBtn.addActionListener(this);
		updMenuBtn.setBounds(29, 143, 202, 41);
		getContentPane().add(updMenuBtn);
		
		addMenuBtn = new JButton("새 메뉴 추가");
		addMenuBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 22));
		addMenuBtn.addActionListener(this);
		addMenuBtn.setBounds(41, 81, 178, 41);
		getContentPane().add(addMenuBtn);
		
		JLabel titleLabel = new JLabel("메뉴 관리");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(1, 10, 258, 52);
		getContentPane().add(titleLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == updMenuBtn) {
			new UpdateMenuView();
		} else if(e.getSource() == addMenuBtn) {
			new AddMenuView();
		}
		
	}
}
