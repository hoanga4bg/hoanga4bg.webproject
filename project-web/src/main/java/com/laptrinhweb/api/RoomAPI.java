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

import com.laptrinhweb.entity.Room;
import com.laptrinhweb.repository.RoomRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/room-api", produces = "application/json")
public class RoomAPI {
	@Autowired
	private RoomRepository roomRepository;
	

	
	@GetMapping
	public List<Room> getAll(){
		List<Room> list=new ArrayList<Room>();
		list=roomRepository.findAll();
		return list;
	}
	
	@GetMapping("/search")
	public Room searchRoom(@RequestBody String name) {
		Room s=roomRepository.findOneByName(name);
		return s;
	}
	
	@GetMapping("/search-id")
	public Room searchRoom(@RequestBody Long id) {
		Room s=roomRepository.findOneById(id);
		return s;
	}
	
	@PostMapping
	public Room saveRoom(@RequestBody Room room) {
		return roomRepository.save(room);
	}
	
	@PutMapping
	public Room updateRoom(@RequestBody Room room) {
		roomRepository.save(room);
		return room;
	}
	
	
	@DeleteMapping
	public void deleteRoom(@RequestBody Long id) {		
		roomRepository.deleteById(id);		
	}
}
