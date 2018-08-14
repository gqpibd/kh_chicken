package client.controller;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;

import client.dao.MemberDao;
import client.singleton.Singleton;
import client.view.AccountView;
import client.view.LoginView;
import client.view.MyInfoView;
import client.view.manager.ManageView;
import dto.MemberDto;

public class MemberController {

	private MemberDao memDao = new MemberDao();

	private ManageView manView;
	private AccountView accView;

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

		if (loginSuccess) {
			Singleton.getInstance().getMainView().login();
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

	public void accountView(JFrame currentView) {
		currentView.setVisible(false);
		if (accView == null) { // 없을 땐
			accView = new AccountView(); // 만들고
		} else { // 있을 땐
			accView.setVisible(true); // 보여만 준다.
		}

	}

	public void loginView(JFrame currentView) {
		currentView.setVisible(false);
		new LoginView(); // 만들고
	}

	public void loginView(JDialog tempDialog) {
		tempDialog.setVisible(false);
		new LoginView(); // 만들고
	}

	public MemberDto getCurrentUser() {
		return memDao.getCurrentUser();
	}

	public void logout() {
		memDao.logout();

	}

	public void myInfoView(JFrame currentView) {
		currentView.setVisible(false);
		new MyInfoView(); // 만들고
		
	}

	public void Inform_Update(MemberDto dto) {
		memDao.Inform_Update(dto);
	}

}
