package com.app.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.entites.Category;
import com.app.entites.Railway;
import com.app.service.IService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	public AdminController() {
		System.out.println("in Admin Controller!");
	}

	@Autowired
	private IService services;

	@PostMapping("/add")
	public ResponseEntity<?> addRailway(@Valid @RequestBody Railway rail) {
		if (rail.getStart_time().isBefore(rail.getEnd_time())) {
			Railway newrail = services.addNewRailway(rail);
			return new ResponseEntity<>(newrail, HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Check Date", HttpStatus.NOT_ACCEPTABLE);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<Railway> deleteRailway(@Valid @RequestParam int id) {
		services.deleteRail(id);
		return new ResponseEntity<Railway>(HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Railway>> listByCat(@RequestParam String cat) {
		List<Railway> ls = services.getByCat(Category.valueOf(cat.toUpperCase()));
		return new ResponseEntity<List<Railway>>(ls, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<Railway> editRail(@RequestParam int id, @RequestParam String src) {

		services.updateRailSrc(src, id);

		return new ResponseEntity<Railway>(HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Railway>> gellAllRailways() {
		List<Railway> rails = services.gellAll();
		return new ResponseEntity<List<Railway>>(rails, HttpStatus.OK);
	}

	@GetMapping("/sort")
	public ResponseEntity<List<Railway>> sortByDestination() {
		List<Railway> ls = services.sortByD();
		return new ResponseEntity<List<Railway>>(ls, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Railway> editRail(@RequestParam int id, Railway rail) {
		if (rail.getId() == id) {
			services.updateRail(rail);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
