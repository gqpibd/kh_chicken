package server.singleton;

import server.dao.MemberDao;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.dao.ReviewDao;
import server.dao.SelectionsDao;
import server.dao.interfaces.MemberDaoImpl;
import server.dao.interfaces.MenuDaoImpl;
import server.dao.interfaces.OrderDaoImpl;
import server.dao.interfaces.ReviewDaoImpl;
import server.dao.interfaces.SelectionsDaoImpl;

public class Singleton {

	public static final int INSERT = 0;
	public static final int DELETE = 1;
	public static final int UPDATE = 2;
	public static final int SELECT = 3;

	private static Singleton single = new Singleton();

	private MemberDaoImpl memDao = new MemberDao();
	private MenuDaoImpl menDao = new MenuDao();
	private OrderDaoImpl ordDao = new OrderDao();
	private ReviewDaoImpl revDao = new ReviewDao();
	private SelectionsDaoImpl selDao = new SelectionsDao(); 

	private Singleton() {
	}

	public static Singleton getInstance() {
		return single;
	}

	public OrderDaoImpl getOrderCtrl() {
		return ordDao;
	}

	public MenuDaoImpl getMenuCtrl() {
		return menDao;
	}

	public MemberDaoImpl getMemCtrl() {
		return memDao;
	}
	
	public ReviewDaoImpl getRevCtrl() {
		return revDao;
	}
	
	public SelectionsDaoImpl getSelCtrl() {
		return selDao;
	}
	
}
