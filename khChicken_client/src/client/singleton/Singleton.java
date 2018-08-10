package client.singleton;

import java.util.ArrayList;
import java.util.List;

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
	public String reviewMenu = "";
	public List<String> checkedMenu = new ArrayList<>();
	
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		return single;
	}
	
	public String getLoginId() {
		String loginId = "admin";
		return loginId;
	}
	public int getAuth() {
		int auth = 1;
		return auth;
		
	}

}
