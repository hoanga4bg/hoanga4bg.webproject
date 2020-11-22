package com.laptrinhweb.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.entity.Parking;
import com.laptrinhweb.repository.ParkingRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "parking-api", produces = "application/json")
public class ParkingAPI {
	@Autowired
	private ParkingRepository parkingRepository;
	

	
	@GetMapping
	public List<Parking> getAll(){
		List<Parking> list=new ArrayList<Parking>();
		list=parkingRepository.findAll();
		return list;
	}
	
	
	@GetMapping("/search-id")
	public Parking searchParking(@RequestBody Long id) {
		Parking parking=parkingRepository.findOneById(id);
		return parking;
	}
	
	@PostMapping
	public Parking saveParking(@RequestBody Parking parking) {
		return parkingRepository.save(parking);
	}
	
	@PutMapping
	public Parking updateParking(@RequestBody Parking parking) {
		parkingRepository.save(parking);
		return parking;
	}
	
	
	@DeleteMapping
	public void deleteParking(@RequestBody Long id) {		
		parkingRepository.deleteById(id);		
	}
}
