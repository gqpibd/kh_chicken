package server.singleton;

import server.dao.BestSaleMenuDao;
import server.dao.MemberDao;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.dao.ReviewDao;

public class Singleton {

	private static Singleton single = new Singleton();
<<<<<<< HEAD
	
	public MemberDao ctrlMember = new MemberDao();
	public MenuDao ctrlMenu = new MenuDao();
	public OrderDao ctrlOrder = new OrderDao();
	public ReviewDao ctrlReview = new ReviewDao();
	
=======

	private MemberDao ctrlMember = new MemberDao();
	private MenuDao ctrlMenu = new MenuDao();
	private OrderDao ctrlOrder = new OrderDao();
	private ReviewDao ctrlReview = new ReviewDao();
	BestSaleMenuDao ctrlBestOrder = new BestSaleMenuDao();

>>>>>>> refs/remotes/origin/도현+다슬+승지
	private Singleton() {
	}

	public static Singleton getInstance() {
		return single;
	}

	public OrderDao getOrderCtrl() {
		return ctrlOrder;
	}

	public BestSaleMenuDao getBestCtrl() {
		return ctrlBestOrder;
	}

	public MenuDao getMenuCtrl() {
		return ctrlMenu;
	}
	
}
