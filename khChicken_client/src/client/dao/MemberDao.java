package client.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.MemberDto;

public class MemberDao {

	List<MemberDto> mList = new ArrayList<MemberDto>();
	MemberDto CurrentUser = null;

	public MemberDao() {
	}

	public void insert(MemberDto dto) { // 회원가입
		Singleton.getInstance().getComm().SendMessage(Communicator.INSERT, dto);
	}

	public boolean select(MemberDto dto) { // 아이디 중복 확인
		Communicator comm = Singleton.getInstance().getComm();
		boolean exsitingId = false;

		comm.SendMessage(Communicator.SELECT, dto);
		exsitingId = (Boolean) comm.receiveObject();

		return exsitingId;
	}

	public void update(MemberDto dto) {
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.UPDATE, dto);
	}

	public void delete() {

	}

	public boolean select_login(MemberDto dto) { // 로그인
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(4, dto);
		CurrentUser = (MemberDto) comm.receiveObject();
		if (CurrentUser != null) {
			JOptionPane.showMessageDialog(null, CurrentUser.getId() + "님 환영합니다");
			return true;
		}
		return false;
	}

	public String getLoginId() { // 로그인 아이디 가져오기
		if (CurrentUser == null) {
			return null;
		} else {
			return CurrentUser.getId();
		}
	}

	public int getAuth() {
		if (CurrentUser == null) {
			return 0;
		} else {
			return CurrentUser.getAuth();
		}

	}

	public MemberDto getCurrentUser() {
		return CurrentUser;
	}

	public void logout() {
		CurrentUser = null;
	}

}
