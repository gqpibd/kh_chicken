package client.singleton;

import client.communicator.Communicator;
import client.controller.MemberController;
import client.controller.MenuController;
import client.controller.OrderController;
import client.controller.ReviewController;
import client.view.MainView;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	private Communicator comm = new Communicator();	
	
	private MemberController memCtrl = new MemberController();
	private MenuController menCtrl = new MenuController();
	private OrderController ordCtrl = new OrderController();
	private ReviewController revCtrl = new ReviewController();
	private MainView mv;
	
	private Singleton() {
	}	
	
	public static Singleton getInstance() {
		return single;
	}
	
	public MenuController getMenuCtrl() {
		return menCtrl;
	}

	public Communicator getComm() {
		return comm;
	}
	
	public OrderController getOrderCtrl() {
		return ordCtrl;
	}
	
	public MemberController getMemCtrl() {
		return memCtrl; 
	}

	public void setMainView(MainView mainView) {
		this.mv = mainView;		
	}
	
	public MainView getMainView() {
		return mv;
	}

	public ReviewController getRevCtrl() {
		return revCtrl;
	}
}
