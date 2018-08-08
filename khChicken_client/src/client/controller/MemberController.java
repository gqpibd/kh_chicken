package client.controller;

import java.net.Socket;

import client.dao.MemberDao;
import client.dto.MemberDto;

public class MemberController {
	
	
	MemberDao memDao = new MemberDao();
	
	
	public void Sockdao(Socket sock) {
		 memDao.MemberDao(sock);
	}
	
	public void insert(MemberDto dto) {
		System.out.println(dto.getId() + "컨트롤");
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
	
	public boolean select_loging(MemberDto dto) {
		return memDao.select_loging(dto);
	}

}
