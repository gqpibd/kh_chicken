package client.controller;

<<<<<<< HEAD
import java.net.Socket;
=======
import javax.swing.JFrame;
>>>>>>> refs/remotes/origin/도현+다슬+승지

import client.dao.MemberDao;
<<<<<<< HEAD
import dto.MemberDto;
=======
import client.singleton.Singleton;
import client.view.manager.ManageView;
import client.view.manager.SaleManageView;
>>>>>>> refs/remotes/origin/도현+다슬+승지

public class MemberController {
<<<<<<< HEAD
	
	
=======

>>>>>>> refs/remotes/origin/도현+다슬+승지
	MemberDao memDao = new MemberDao();
<<<<<<< HEAD
	
	
	public void Sockdao(Socket sock) {
		 memDao.MemberDao(sock);
=======
	ManageView manView;
	SaleManageView salManView;

	public void insert() {
		memDao.insert();
>>>>>>> refs/remotes/origin/도현+다슬+승지
	}
<<<<<<< HEAD
	
	public void insert(MemberDto dto) {
		System.out.println(dto.getId() + "컨트롤");
		memDao.insert(dto);
	}
	
	public boolean select(MemberDto dto) {
		return memDao.select(dto);
=======

	public void select() {
		memDao.select();
>>>>>>> refs/remotes/origin/도현+다슬+승지
	}

	public void delete() {
		memDao.delete();
	}

	public void update() {
		memDao.update();
	}
	
	public boolean select_loging(MemberDto dto) {
		return memDao.select_loging(dto);
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
