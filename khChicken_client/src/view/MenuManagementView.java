package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MenuManagementView extends JFrame {

	public MenuManagementView() {
		setTitle("메뉴 관리");

		setBounds(0, 0, 274, 253);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE); // 나중에 삭제하기
		getContentPane().setLayout(null);
		
		JButton updMenuBtn = new JButton("메뉴 수정");
		updMenuBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 22));
		updMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		updMenuBtn.setBounds(39, 22, 178, 41);
		getContentPane().add(updMenuBtn);
		
		JButton addMenuBtn = new JButton("메뉴 추가");
		addMenuBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 22));
		addMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddMenuView();
			}
		});
		addMenuBtn.setBounds(39, 148, 178, 41);
		getContentPane().add(addMenuBtn);
		
		JButton delMenuBtn = new JButton("메뉴 삭제");
		delMenuBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 22));
		delMenuBtn.setBounds(39, 85, 178, 41);
		getContentPane().add(delMenuBtn);
	}
}
