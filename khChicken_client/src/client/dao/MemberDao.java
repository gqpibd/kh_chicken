package client.dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.MemberDto;

public class MemberDao {

	List<MemberDto> mList = new ArrayList<MemberDto>();
	MemberDto CurrentUser;

	public MemberDao() {
	}

	public void MemberDao() {
	}

	public void insert(MemberDto dto) {
		Singleton.getInstance().getComm().SendMessage(Communicator.INSERT, dto);
	}

	public boolean select(MemberDto dto) {
		Communicator comm = Singleton.getInstance().getComm();
		boolean join = false;

		comm.SendMessage(Communicator.SELECT, dto);
		join = (Boolean) comm.receiveObject();
		
		return join;
	}

	public void update() {

	}

	public void delete() {

	}

	public boolean select_login(MemberDto dto) {
		boolean loginSuccess = false;
		Communicator comm = Singleton.getInstance().getComm();
		
		comm.SendMessage(4, dto);
		loginSuccess = (Boolean) comm.receiveObject();
		if(loginSuccess) {
			CurrentUser = dto;
		}
		return loginSuccess;
	}

	public String getLoginId() { // 로그인 아이디 가져오기
		if (CurrentUser == null) {
			return null;
		} else {
			return CurrentUser.getId();
		}
	}

	public int getAuth() {
		int auth = CurrentUser.getAuth();
		return auth;

	}
}
