package client.controller;

import client.dao.MemberDao;
import client.singleton.Singleton;
import client.view.ManageView;
import client.view.MenuManageView;
import client.view.SaleManageView;

public class MemberController {

	MemberDao memDao = new MemberDao();
	ManageView manView;
	SaleManageView saleManView;
	MenuManageView manManView;

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

	public void manageView() {
		Singleton.getInstance().getMainView().setVisible(false); // 메인 화면은 안 보이게 하고
		// 관리자 창을 보여준다
		if (manView == null) { // 없을 땐
			manView = new ManageView(); // 만들고
		} else { // 있을 땐
			manView.setVisible(true); // 보여만 준다.
		}
	}

	public void saleManageView() {
		manView.setVisible(false);// 관리자 창을 안 보이게 하고
		// 매출관리 창을 보여준다.
		if (saleManView == null) { // 없을 땐
			saleManView = new SaleManageView(); // 만들고
		} else { // 있을 땐
			saleManView.setVisible(true); // 보여만 준다.
		}
	}

	public void menuManageView() {
		manView.setVisible(false);// 관리자 창을 안 보이게 하고
		// 메뉴관리 창을 보여준다.
		if (manManView == null) { // 없을 땐
			manManView = new MenuManageView(); // 만들고
		} else { // 있을 땐
			manManView.setVisible(true); // 보여만 준다.
		}		
	}
}
