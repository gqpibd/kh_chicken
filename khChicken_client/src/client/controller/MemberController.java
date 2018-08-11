package client.controller;

import javax.swing.JFrame;

import client.dao.MemberDao;
import client.singleton.Singleton;
import client.view.Window_Account;
import client.view.Window_Login;
import client.view.manager.ManageView;
import client.view.manager.SaleManageView;
import dto.MemberDto;

public class MemberController {

	private MemberDao memDao = new MemberDao();

	private ManageView manView;
	private SaleManageView salManView;
	private Window_Account accView;
	private Window_Login logView;

	public void insert(MemberDto dto) {
		memDao.insert(dto);
	}

	public boolean select(MemberDto dto) {
		return memDao.select(dto);
	}

	public void delete() {
		memDao.delete();
	}

	public void update() {
		memDao.update();
	}

	public boolean select_login(MemberDto dto) {
		return memDao.select_login(dto);
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

	public void saleManageView(JFrame currentView) {
		currentView.setVisible(false);
		// 매출관리 창을 보여준다.
		if (salManView == null) { // 없을 땐
			salManView = new SaleManageView(); // 만들고
		} else { // 있을 땐
			salManView.setVisible(true); // 보여만 준다.
		}
	}

	public void backToMain(JFrame currentView) {
		currentView.setVisible(false);
		Singleton.getInstance().getMainView().setVisible(true); // 메인 화면을 보이게 한다.
	}

	public void AccountView(JFrame currentView) {
		currentView.setVisible(false);
		if (accView == null) { // 없을 땐
			accView = new Window_Account(); // 만들고
		} else { // 있을 땐
			accView.setVisible(true); // 보여만 준다.
		}
		
	}

	public void loginView(JFrame currentView) {
		currentView.setVisible(false);
		if ( logView == null) { // 없을 땐
			logView = new Window_Login(); // 만들고
		} else { // 있을 땐
			logView.setVisible(true); // 보여만 준다.
		}
		
	}
}
