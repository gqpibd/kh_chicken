package client.dao;

import java.util.ArrayList;
import java.util.List;

import dto.MemberDto;



public class MemberDao {
	
	List<MemberDto> mList = new ArrayList<MemberDto>();
	
	public MemberDao() {
		
	}
	
	public List<MemberDto> insert() {
		MemberDto mDto = new MemberDto("Kong", null, "공놀이", 1, 1, "강남 역세권", "010-2222-2222");
		mList.add(mDto);
		
		return mList;
	}
	
	public void select() {
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

}
