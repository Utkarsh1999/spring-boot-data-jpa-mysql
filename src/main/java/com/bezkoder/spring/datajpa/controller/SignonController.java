package com.bezkoder.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.datajpa.model.Signon;
import com.bezkoder.spring.datajpa.repository.SignonRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SignonController {

	@Autowired
	SignonRepository signonRepository;

	@Generated("/Signons/all")
	public ResponseEntity<List<Signon>> getAllUserCredentials(){
		try{
			List<Signon> Signons = new ArrayList<Signon>();
			signonRepository.findAll().forEach(Signons::add);
			return new ResponseEntity<>(Signons, HttpStatus.OK);
		} catch(Exception e){
			//thow error here
		}
		return null;
	}


	@GetMapping("/Signons")
	public ResponseEntity<List<Signon>> getAllSignons(@RequestParam(required = false) String username) {
		try {
			List<Signon> Signons = new ArrayList<Signon>();

			if (username == null)
				signonRepository.findAll().forEach(Signons::add);
			else
				signonRepository.findByUsername(username).forEach(Signons::add);

			if (Signons.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Signons, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

// 	@GetMapping("/Signons/{username}")
// 	public ResponseEntity<Signon> getSignonByUsername(@PathVariable("username") String username) {
// 		List<Signon> SignonData = signonRepository.findByUsername(username);

// 		if (SignonData.size()>0) {
// //			return new ResponseEntity<>(SignonData.get(), HttpStatus.OK);
// 		} else {
// 			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
// 		}
// 	}

	@PostMapping("/Signons")
	public ResponseEntity<Signon> createSignon(@RequestBody Signon Signon) {
		try {
			Signon _Signon = signonRepository
					.save(new Signon(Signon.getUsername(), Signon.getPassword()));
			return new ResponseEntity<>(_Signon, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @PutMapping("/Signons/{id}")
	// public ResponseEntity<Signon> updateSignon(@PathVariable("id") long id, @RequestBody Signon Signon) {
	// 	Optional<Signon> SignonData = signonRepository.findById(id);

	// 	if (SignonData.isPresent()) {
	// 		Signon _Signon = SignonData.get();
	// 		_Signon.setTitle(Signon.getTitle());
	// 		_Signon.setDescription(Signon.getDescription());
	// 		_Signon.setPublished(Signon.isPublished());
	// 		return new ResponseEntity<>(signonRepository.save(_Signon), HttpStatus.OK);
	// 	} else {
	// 		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// 	}
	// }

	// @DeleteMapping("/Signons/{id}")
	// public ResponseEntity<HttpStatus> deleteSignon(@PathVariable("id") long id) {
	// 	try {
	// 		signonRepository.deleteById(id);
	// 		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// }

	@DeleteMapping("/Signons")
	public ResponseEntity<HttpStatus> deleteAllSignons() {
		try {
			signonRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// @GetMapping("/Signons/published")
	// public ResponseEntity<List<Signon>> findByPublished() {
	// 	try {
	// 		List<Signon> Signons = signonRepository.findByUsername();

	// 		if (Signons.isEmpty()) {
	// 			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	// 		}
	// 		return new ResponseEntity<>(Signons, HttpStatus.OK);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}
	// }

}
