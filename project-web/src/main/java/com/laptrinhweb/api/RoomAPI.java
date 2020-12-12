package com.laptrinhweb.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//Hải thêm
			@GetMapping("/{roomNumber}")
			public Room searchRoomByName(@PathVariable("roomNumber") String roomNumber) {
				Room s=roomRepository.findOneByRoomNumber(roomNumber);
				return s;
			}
			
	@GetMapping("/search")
	public Room searchRoom(@RequestBody String roomNumber) {
		Room s=roomRepository.findOneByRoomNumber(roomNumber);
		return s;
	}
	@GetMapping("/availble")
	public List<Room> getAvailbleRoom(){
		List<Room> list=new ArrayList<Room>();
		list=roomRepository.findAll();
		List<Room> availbleList=new ArrayList<Room>();
		for(Room r:list) {
			if(r.getListStudent().size()<r.getCapacity()) {
				availbleList.add(r);
			}
		}
		return availbleList;
		
	}
	
	@GetMapping("/search-id/{id}")
	public Room searchRoomById(@PathVariable("id") String id) {
		Room s=roomRepository.findOneById(Long.parseLong(id));
		return s;
	}
	
	
	@GetMapping("/search/{roomNumber}") 
	public Room searchRoomByRoomNumber(@PathVariable("roomNumber") String roomNumber) {
		Room rn = roomRepository.findOneByRoomNumber(roomNumber);
		return rn;
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
	
	
	@DeleteMapping("/{id}")
	public void deleteRoom(@PathVariable("id") String id) {		
		roomRepository.deleteById(Long.parseLong(id));		
	}
}
