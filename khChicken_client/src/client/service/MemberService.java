package client.service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import client.communicator.Communicator;
import client.service.interfaces.MemberServiceImpl;
import client.singleton.Singleton;
import dto.MemberDto;

public class MemberService implements MemberServiceImpl {

	List<MemberDto> mList = new ArrayList<MemberDto>();
	MemberDto CurrentUser = null;

	public MemberService() {
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

	public void update(MemberDto dto) { // 회원 정보 수정
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.UPDATE, dto);
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

	public int getAuth() { // 회원 권한 확인
		if (CurrentUser == null) {
			return 0;
		} else {
			return CurrentUser.getAuth();
		}

	}

	public MemberDto getCurrentUser() { // 현재 로그인한 사람
		return CurrentUser;
	}

	public void logout() { // 로그아웃
		CurrentUser = null;
	}

}
