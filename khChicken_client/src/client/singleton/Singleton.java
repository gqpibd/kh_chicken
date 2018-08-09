package client.singleton;

import client.communicator.Communicator;
import client.controller.MemberController;
import client.controller.MenuController;
import client.controller.OrderController;
import client.controller.ReviewController;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	private Communicator comm = new Communicator();	
	
	private MemberController memCtrl = new MemberController();
	private MenuController menCtrl = new MenuController();
	private OrderController ordCtrl = new OrderController();
	private ReviewController revCtrl = new ReviewController();
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		return single;
	}
	
	public Communicator getComm() {
		return comm;
	}
	
	public OrderController getOrderCtrl() {
		return ordCtrl;
	}

}
