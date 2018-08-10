package client.controller;

import javax.swing.JFrame;

import client.dao.MemberDao;
import client.singleton.Singleton;
import client.view.manager.ManageView;
import client.view.manager.SaleManageView;

public class MemberController {

	MemberDao memDao = new MemberDao();
	ManageView manView;
	SaleManageView salManView;

	public void insert() {
		memDao.insert();
	}

	public void select() {
		memDao.select();
	}

	public void delete() {
		memDao.delete();
	}

	public void update() {
		memDao.update();
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
	
}
