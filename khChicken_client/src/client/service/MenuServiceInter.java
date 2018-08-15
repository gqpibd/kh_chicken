package client.service;

import java.util.List;

import dto.MenuShowDto;

public interface MenuServiceInter {
	public void insert(MenuShowDto dto, String imgFilePath);

	public void initList();

	public void update();

	public void delete();

	public int getSize();

	public MenuShowDto get(int i);

	public MenuShowDto getMenuByName(String name);

	public void delete(MenuShowDto menu);

	public void update(MenuShowDto menu);

	public void updateImage(MenuShowDto menu, String newImgPath);

	public void sco_Update(MenuShowDto dto);

	public List<MenuShowDto> get_List();
}
