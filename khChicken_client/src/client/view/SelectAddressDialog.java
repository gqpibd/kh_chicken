package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.communicator.Communicator;
import client.controller.StatisticsController;
import client.singleton.Singleton;

public class SelectAddressDialog extends JDialog implements ActionListener {
	private JTextField loadNameField;
	private JList<String> list;
	private JButton searchBtn;
	private JButton confirmBtn;
	private JTextField selectedAddField;
	private JTextField detailAddField;
	
	
	public SelectAddressDialog(JFrame parent, boolean modal) {
		super(parent,modal);
		setBounds(300, 200, 392, 413);
		getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("도로명 주소 검색");
		titleLabel.setBounds(12, 10, 167, 15);
		getContentPane().add(titleLabel);

		JLabel seoulLabel = new JLabel("서울특별시");
		seoulLabel.setBounds(111, 38, 101, 15);
		getContentPane().add(seoulLabel);

		loadNameField = new JTextField();
		loadNameField.setBounds(111, 63, 116, 21);
		getContentPane().add(loadNameField);
		loadNameField.addActionListener(this);
		loadNameField.setColumns(10);

		JLabel sidoLabel = new JLabel("시/도");
		sidoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sidoLabel.setBounds(42, 38, 57, 15);
		getContentPane().add(sidoLabel);

		JLabel loadLabel = new JLabel("도로명");
		loadLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		loadLabel.setBounds(42, 66, 57, 15);
		getContentPane().add(loadLabel);

		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedAddField.setText(list.getSelectedValue());
			}
		});

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(12, 192, 349, 130);
		getContentPane().add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("검색 결과");
		lblNewLabel_1.setBounds(12, 167, 57, 15);
		getContentPane().add(lblNewLabel_1);

		searchBtn = new JButton("검색");
		searchBtn.setBounds(239, 62, 87, 23);
		searchBtn.addActionListener(this);
		getContentPane().add(searchBtn);

		confirmBtn = new JButton("확인");
		confirmBtn.setBounds(283, 332, 81, 32);
		confirmBtn.addActionListener(this);
		getContentPane().add(confirmBtn);

		selectedAddField = new JTextField();
		selectedAddField.setEditable(false);
		selectedAddField.setBounds(111, 94, 215, 21);
		getContentPane().add(selectedAddField);
		selectedAddField.setColumns(10);

		JLabel detailAddLabel = new JLabel("상세 주소");
		detailAddLabel.setBounds(42, 126, 57, 15);
		getContentPane().add(detailAddLabel);

		detailAddField = new JTextField();
		detailAddField.setBounds(111, 123, 215, 21);
		getContentPane().add(detailAddField);
		detailAddField.setColumns(10);
		
		setVisible(true);

		
	}
	
	public String getAddress() {
		return selectedAddField.getText();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn || e.getSource() == loadNameField) {
			String load = loadNameField.getText();
			Communicator comm = Singleton.getInstance().getComm();
			comm.SendMessage(StatisticsController.ADDRESS, load);
			ArrayList<String> results = (ArrayList<String>) comm.receiveObject();
			String resArr[] = new String[results.size()];
			for (int i = 0; i < resArr.length; i++) {
				resArr[i] = results.get(i).toString();
				
			}
			
			list.setListData(resArr);
		}else if (e.getSource() == confirmBtn) {
			if(detailAddField.getText().length() == 0 || selectedAddField.getText().length() ==0) {
				JOptionPane.showMessageDialog(null, "주소를 모두 입력해 주세요");
			}else {
				dispose();
			}			
		}
	}

	public String getDetailAddress() {
		return detailAddField.getText();
	}
}
