package client.controller;

import javax.swing.JFrame;
<<<<<<< HEAD
=======

>>>>>>> branch 'yun_2' of https://github.com/gqpibd/kh_semi

import client.dao.MemberDao;
import client.singleton.Singleton;
import client.view.Window_Account;
import client.view.Window_Login;
import client.view.manager.ManageView;
import dto.MemberDto;

public class MemberController { 

	private MemberDao memDao = new MemberDao();

	private ManageView manView;
	private Window_Account accView;
	private Window_Login logView;

	public void insert(MemberDto dto) {
		memDao.insert(dto);
	}

	public boolean select(MemberDto dto) { // 아이디 중복 확인
		return memDao.select(dto);
	}

	public void delete() {
		memDao.delete();
	}

	public void update() {
		memDao.update();
	}

	public boolean select_login(MemberDto dto) {
		boolean loginSuccess = memDao.select_login(dto);
		
<<<<<<< HEAD
=======
		
>>>>>>> branch 'yun_2' of https://github.com/gqpibd/kh_semi
		if (loginSuccess) {
			Singleton.getInstance().getMainView().Login();			
		}
		
		return loginSuccess;

	}

	// ------------------ 로그인아이디 / auth 얻는 메소드
	public String getLoginId() {
		return memDao.getLoginId();
	}

	public int getAuth() {
		return memDao.getAuth();
	}

	public void manageView(JFrame currentView) {
		currentView.setVisible(false);
		// 관리자 창을 보여준다
		if (manView == null) { // 없을 땐
			manView = new ManageView(); // 만들고
		} else { // 있을 땐
			manView.setVisible(true); // 보여만 준다.
		}
	}


	public void AccountView(JFrame currentView) {
		currentView.setVisible(false);
		if (accView == null) { // 없을 땐
			accView = new Window_Account(); // 만들고
		} else { // 있을 땐
			accView.setVisible(true); // 보여만 준다.
		}

	}

<<<<<<< HEAD
	public void loginView(JFrame currentView) {
		currentView.setVisible(false);
		if (logView == null) { // 없을 땐
			logView = new Window_Login(); // 만들고
=======
	public void loginView(JFrame currentView, int number) {
		currentView.setVisible(false);
		if (logView == null) { // 없을 땐
			logView = new Window_Login(number); // 만들고
>>>>>>> branch 'yun_2' of https://github.com/gqpibd/kh_semi
		} else { // 있을 땐
			logView.setVisible(true); // 보여만 준다.
		}

	}

	public MemberDto getCurrentUser() {
		return memDao.getCurrentUser();
	}
}
