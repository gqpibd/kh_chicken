package client.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.MemberDto;

public class MemberDao { 

	List<MemberDto> mList = new ArrayList<MemberDto>();
	MemberDto CurrentUser;

	public MemberDao() {
	}

	public void insert(MemberDto dto) {
		Singleton.getInstance().getComm().SendMessage(Communicator.INSERT, dto);
	}

	public boolean select(MemberDto dto) { // 아이디 중복 확인
		Communicator comm = Singleton.getInstance().getComm();
		boolean exsitingId = false;

		comm.SendMessage(Communicator.SELECT, dto);
		exsitingId = (Boolean) comm.receiveObject();
		
		return exsitingId;
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
			JOptionPane.showMessageDialog(null, dto.getId() + "님 환영합니다");
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

	public MemberDto getCurrentUser() {
		return CurrentUser;
	}
}
