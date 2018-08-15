package client.service.interfaces;

import java.util.ArrayList;
import java.util.List;

import dto.OrderedMenuDto;

public interface OrderServiceImpl {

	public void insert(ArrayList<OrderedMenuDto> confirmedList);

	public int getCoupon();

	public List<OrderedMenuDto> getList();

	public void clearList();
}
