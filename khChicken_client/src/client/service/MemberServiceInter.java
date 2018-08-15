package client.service;

import dto.MemberDto;

public interface MemberServiceInter {

	public void insert(MemberDto dto);
	public boolean select(MemberDto dto);
	public void update(MemberDto dto);
	public void delete();
	public boolean select_login(MemberDto dto);
	public String getLoginId();
	public int getAuth();
	public MemberDto getCurrentUser();
	public void logout();

}
