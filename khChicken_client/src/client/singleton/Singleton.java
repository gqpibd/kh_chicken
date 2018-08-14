package client.singleton;

import javax.swing.JFrame;

import client.communicator.Communicator;
import client.controller.MemberController;
import client.controller.MenuController;
import client.controller.OrderController;
import client.controller.ReviewController;
import client.controller.StatisticsController;
import client.view.MainView;

public class Singleton {
<<<<<<< HEAD

	private static Singleton single = new Singleton(); 

	private Communicator comm = new Communicator();

	private MemberController memCtrl = new MemberController();
	private MenuController menCtrl = new MenuController();
	private OrderController ordCtrl = new OrderController();
	private ReviewController revCtrl = new ReviewController();
	private StatisticsController staCtrl = new StatisticsController();
	private MainView mv;

=======
	
	private static Singleton single = new Singleton();
	
	public MemberController memCtrl = new MemberController();
	public MenuController menCtrl = new MenuController();
	public OrderController ordCtrl = new OrderController();
	public ReviewController revCtrl = new ReviewController();
	public Communicator comm = new Communicator();
	
>>>>>>> branch 'jinyoung' of https://github.com/gqpibd/kh_semi.git
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
	
	public StatisticsController getStaCtrl() {
		return staCtrl;
	}

	public void setMainView(MainView mainView) {
		this.mv = mainView;
	}

	public MainView getMainView() {
		if (mv == null) {
			mv = new MainView();
		}
		return mv;
	}

	public ReviewController getRevCtrl() {
		return revCtrl;
	}
	
	public void backToMain(JFrame currentView) {
		currentView.setVisible(false);
		mv.setVisible(true); // 메인 화면을 보이게 한다.
	}

	public void resetMainView() {
		mv = new MainView();
		mv.login();
	}

}
