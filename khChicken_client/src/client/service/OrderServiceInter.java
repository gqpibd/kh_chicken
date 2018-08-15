package client.service;

import java.util.ArrayList;
import java.util.List;

import dto.OrderedMenuDto;

public interface OrderServiceInter {

	public void insert(ArrayList<OrderedMenuDto> confirmedList);

	public String getCoupon();

	public List<OrderedMenuDto> getList();

	public void clearList();
}
