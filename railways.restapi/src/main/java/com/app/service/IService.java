package com.app.service;

import java.util.List;

import com.app.entites.Category;
import com.app.entites.Railway;

public interface IService {

	Railway addNewRailway(Railway rail);

	void deleteRail(int id);

	List<Railway> getByCat(Category cat);

	String updateRailSrc(String src, int id);

	List<Railway> gellAll();
	List<Railway> sortByD();
	
	String updateRail(Railway rail);

}
