package client.dao;

<<<<<<< HEAD
import java.io.IOException;
import java.io.ObjectOutputStream;
=======
import java.text.SimpleDateFormat;
>>>>>>> branch 'jinyoung' of https://github.com/gqpibd/kh_semi.git
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

<<<<<<< HEAD
import client.communicator.Communicator;
import client.singleton.Singleton;
import dto.BestSaleMenuDto;
import dto.OrderedMenuDto;
=======
import dto.OrderedMenuDto;


>>>>>>> branch 'jinyoung' of https://github.com/gqpibd/kh_semi.git

public class OrderDao {
<<<<<<< HEAD

	List<OrderedMenuDto> orderList = new ArrayList<>();

=======
	
	List<OrderedMenuDto> oList = new ArrayList<OrderedMenuDto>();
	//메뉴를 여러개 시킬 수 있어.
	
>>>>>>> branch 'jinyoung' of https://github.com/gqpibd/kh_semi.git
	public OrderDao() {
	}

	
<<<<<<< HEAD
	
	public void insert(ArrayList<OrderedMenuDto> confirmedList) {

		Singleton s = Singleton.getInstance();
		for (int i = 0; i < confirmedList.size(); i++) {
			s.getComm().SendMessage(Communicator.INSERT, confirmedList.get(i));
		}

		// 리스트 초기화
		orderList.clear(); // 주문이 끝났으니 리스트를 비워준다.
=======
	public List<OrderedMenuDto> insert() {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("YY/MM/dd hh:mm:ss");
		//Date time = date.format(today);
		OrderedMenuDto oDto = new OrderedMenuDto(today, "test1", "main", "후라이드치킨", 1, 1, 15000);
		oList.add(oDto);
		
		return oList;
>>>>>>> branch 'jinyoung' of https://github.com/gqpibd/kh_semi.git
	}

	public String getCoupon() { //사용할수 있는 쿠폰 (writer 번호, 자신의 id)
		ObjectOutputStream oos = null;
		Singleton s = Singleton.getInstance();		
		
		// id 보내기
		String id = s.getMemCtrl().getLoginId();
		OrderedMenuDto oDto = new OrderedMenuDto(null, id, null, null, 0, 0, 0);
		s.getComm().SendMessage(Communicator.SELECT, oDto);

		//다시 받아오기
		String couponEA = (String)s.getComm().receiveObject();
		System.out.println("message : "+couponEA);
		return couponEA;
	}

	public List<OrderedMenuDto> getList() {
		return orderList;
	}


	public void clearList() {
		orderList.clear();
		
	}

}
