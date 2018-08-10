package server.singleton;

import server.dao.BestSaleMenuDao;
import server.dao.MemberDao;
import server.dao.MenuDao;
import server.dao.OrderDao;
import server.dao.ReviewDao;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	MemberDao ctrlMember = new MemberDao();
	MenuDao ctrlMenu = new MenuDao();
	OrderDao ctrlOrder = new OrderDao();
	ReviewDao ctrlReview = new ReviewDao();
	BestSaleMenuDao ctrlBestOrder = new BestSaleMenuDao();
	
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
	

}
