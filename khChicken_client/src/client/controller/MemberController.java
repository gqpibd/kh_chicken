package client.controller;

import client.dao.MemberDao;

public class MemberController {
	
	MemberDao memDao = new MemberDao();
	
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
	
	
	//------------------ 로그인아이디 / auth 얻는 메소드
	public String getLoginId() {
		return memDao.getLoginId();
	}
	
	public int getAuth() {
		return memDao.getAuth();
	}
	

}
