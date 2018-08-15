package server.dao.interfaces;

import java.net.Socket;

import dto.MemberDto;

public interface MemberDaoImpl {

	public void execute(int number, MemberDto dto, Socket sock);

	public void insert(MemberDto dto);

	public void update(MemberDto dto);

	public void select(MemberDto dto, Socket sock);

	public void select_login(MemberDto dto, Socket sock);

}
