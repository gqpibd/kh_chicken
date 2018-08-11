package client.singleton;

import client.communicator.Communicator;
import client.controller.MemberController;
import client.controller.MenuController;
import client.controller.OrderController;
import client.controller.ReviewController;
<<<<<<< HEAD
import client.view.Window_ACount;
import client.view.Window_Loging;
=======
import client.view.MainView;
>>>>>>> refs/remotes/origin/도현+다슬+승지

public class Singleton {
	
	private static Singleton single = new Singleton();
	
<<<<<<< HEAD
	public MemberController memCtrl = new MemberController();
	MenuController menCtrl = new MenuController();
	OrderController ordCtrl = new OrderController();
	public ReviewController revCtrl = new ReviewController();
	public Communicator comm = new Communicator();
	public Window_ACount Win_Acount;
	public Window_Loging Win_Loging;
	
=======
	private Communicator comm = new Communicator();	
	
	private MemberController memCtrl = new MemberController();
	private MenuController menCtrl = new MenuController();
	private OrderController ordCtrl = new OrderController();
	private ReviewController revCtrl = new ReviewController();
	private MainView mv;
>>>>>>> refs/remotes/origin/도현+다슬+승지
	
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
}
