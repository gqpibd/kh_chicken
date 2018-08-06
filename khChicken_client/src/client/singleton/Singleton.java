package client.singleton;

import client.communicator.Communicator;
import client.controller.MemberController;
import client.controller.MenuController;
import client.controller.OrderController;
import client.controller.ReviewController;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	MemberController memCtrl = new MemberController();
	MenuController menCtrl = new MenuController();
	OrderController ordCtrl = new OrderController();
	ReviewController revCtrl = new ReviewController();
	Communicator comm = new Communicator();
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		return single;
	}

}
