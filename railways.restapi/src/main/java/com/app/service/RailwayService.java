package com.app.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entites.Category;
import com.app.entites.Railway;
import com.app.repo.RailwayRepository;

@Service
@Transactional
public class RailwayService implements IService {
	@Autowired
	public RailwayRepository railrepo;

	@Override
	public Railway addNewRailway(Railway rail) {
			return railrepo.save(rail);

	}

	@Override
	public void deleteRail(int id) {

		railrepo.deleteById(id);
		System.out.println("Railway deleted by ID No.: " + id);
	}

	@Override
	public List<Railway> getByCat(Category cat) {
		// TODO Auto-generated method stub
		return railrepo.getListByCategory(cat);
	}

	@Override
	public String updateRailSrc(String src, int id) {

		railrepo.findById(id).orElseThrow(() -> new InvalidParameterException("Invalid Id"));
		railrepo.updateRailSrc(src, id);
		return "Updated!!";

		// TODO Auto-generated method stub
	}

	@Override
	public List<Railway> gellAll() {
		return railrepo.findAll();

	}

	@Override
	public List<Railway> sortByD() {
		List<Railway> sorts = railrepo.sortByDest();
		return sorts;
	}

	@Override
	public String updateRail(Railway rail) {
		railrepo.save(rail);
		return "Updated Successfully!!";
	}

}
