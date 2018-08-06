package client.controller;

import java.util.List;

import client.dao.MemberDao;
import client.dto.OrderedMenuDto;

public class MemberController {
	
	MemberDao memDao = new MemberDao();
	
	public void insert() {
		memDao.insert();
	}
	
	public List<OrderedMenuDto> select() {
		return memDao.select();
	}
	
	public void delete() {
		memDao.delete();
	}
	
	public void update() {
		memDao.update();
	}

}
