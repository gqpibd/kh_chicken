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

}
