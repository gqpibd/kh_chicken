package client.dao;

import java.util.ArrayList;
import java.util.List;

import dto.MemberDto;

public class MemberDao {
	
	List<MemberDto> mList = new ArrayList<MemberDto>();
	MemberDto CurrentUser;
	
	public MemberDao() {
	}
	
	public void insert() {
		
	}
	
	public void select() {
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

	
	public String getLoginId() {	//로그인 아이디 가져오기
		
		String loginId = CurrentUser.getId();
		return loginId;
	}
	
	public int getAuth() {
		
		int auth = CurrentUser.getAuth();
		return auth;
		
	}
	

}
