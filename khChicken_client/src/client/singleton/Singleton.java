package client.singleton;

import client.communicator.Communicator;
import client.controller.MemberController;
import client.controller.MenuController;
import client.controller.OrderController;
import client.controller.ReviewController;

public class Singleton {
	
	private static Singleton single = new Singleton();
	private Communicator comm = new Communicator();	
	
<<<<<<< HEAD
	private MemberController memCtrl = new MemberController();
	private MenuController menCtrl = new MenuController();
	private OrderController ordCtrl = new OrderController();
	private ReviewController revCtrl = new ReviewController();
=======
	public MemberController memCtrl = new MemberController();
	public MenuController menCtrl = new MenuController();
	public OrderController ordCtrl = new OrderController();
	public ReviewController revCtrl = new ReviewController();
	public Communicator comm = new Communicator();
>>>>>>> refs/remotes/origin/daseul
	
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
}
