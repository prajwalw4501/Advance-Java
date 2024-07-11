package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entites.Category;
import com.app.entites.Railway;

@Repository
public interface RailwayRepository extends JpaRepository<Railway, Integer> {

	@Query("select  r from Railway r where r.category=:cat")
	List<Railway> getListByCategory(Category cat);

	@Modifying
	@Query("Update Railway r set r.source=:src where r.id=:id")
	void updateRailSrc(String src, int id);

	@Query("select r from Railway r order by r.destination")
	List<Railway> sortByDest();

}
