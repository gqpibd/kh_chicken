package client.singleton;

import client.communicator.Communicator;
import client.controller.MemberController;
import client.controller.MenuController;
import client.controller.OrderController;
import client.controller.ReviewController;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	public MemberController memCtrl;
	public MenuController menCtrl;
	public OrderController ordCtrl;
	public ReviewController revCtrl;
	public Communicator comm;
	
	private Singleton() {
		
		 memCtrl = new MemberController();
		 menCtrl = new MenuController();
		 ordCtrl = new OrderController();
		 revCtrl = new ReviewController();
		 comm = new Communicator();
	}
	
	public static Singleton getInstance() {
		return single;
	}

}
