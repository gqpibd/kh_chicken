package client.singleton;

import client.communicator.Communicator;
import client.controller.MemberController;
import client.controller.MenuController;
import client.controller.OrderController;
import client.controller.ReviewController;
import client.view.Window_ACount;
import client.view.Window_Loging;

public class Singleton {
	
	private static Singleton single = new Singleton();
	
	public MemberController memCtrl = new MemberController();
	MenuController menCtrl = new MenuController();
	OrderController ordCtrl = new OrderController();
	public ReviewController revCtrl = new ReviewController();
	public Communicator comm = new Communicator();
	public Window_ACount Win_Acount;
	public Window_Loging Win_Loging;
	
	
	private Singleton() {
	}	
	
	public static Singleton getInstance() {
		return single;
	}

}
