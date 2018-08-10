package client.singleton;

import client.communicator.Communicator;
import client.controller.MemberController;
import client.controller.MenuController;
import client.controller.OrderController;
import client.controller.ReviewController;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	public MemberController memCtrl = new MemberController();
	public MenuController menCtrl = new MenuController();
	public OrderController ordCtrl = new OrderController();
	public ReviewController revCtrl = new ReviewController();
	public Communicator comm = new Communicator();
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		return single;
	}

}
