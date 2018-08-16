package client.service.interfaces;

import java.util.List;

import dto.MenuShowDto;

public interface MenuServiceImpl {
	public void insert(MenuShowDto dto, String imgFilePath);

	public void initList();

	public void delete(MenuShowDto menu);

	public void update(MenuShowDto menu);

	public void updateImage(MenuShowDto menu, String newImgPath);

	public int getSize();

	public MenuShowDto get(int i);

	public MenuShowDto getMenuByName(String name);

	public List<MenuShowDto> get_List();
}
