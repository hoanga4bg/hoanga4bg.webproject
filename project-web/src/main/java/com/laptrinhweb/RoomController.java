package com.laptrinhweb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.laptrinhweb.entity.Room;
import com.laptrinhweb.entity.Student;
import com.laptrinhweb.repository.RoomRepository;



@Controller
@RequestMapping("/room")
public class RoomController {
	private RestTemplate rest=new RestTemplate();

	@GetMapping 
	public String showRoom(Model model) {
		List<Room> rooms = new ArrayList<Room>(Arrays.asList(rest.getForObject("http://localhost:8080/room-api", Room[].class)));
		model.addAttribute("rooms", rooms);
		return "room/roomHome";
	}
	@GetMapping("/add")
	public String addFrom(Model model) {
		model.addAttribute("room", new Room());
		//System.out.print(new Room().getType());
		return "room/addForm";
	}
	
	@PostMapping("/add")
	public String add(Room room) {
		rest.postForObject("http://localhost:8080/room-api", room, Room.class);
	
		return "redirect:/room";
	}
	
	@GetMapping("/edit")
	public String editForm(@ModelAttribute("id") String id, Model model) {
		Room room = rest.getForObject("http://localhost:8080/room-api/search-id/{id}", Room.class, id);
		model.addAttribute("room", room);
		return "room/editForm";
	}
	
	@PostMapping("/edit")
	public String edit(Room room) {
		rest.put("http://localhost:8080/room-api", room, Room.class);
		
		return "redirect:/room";
	}	
	
	@GetMapping("/delete")
	public String showForm(@ModelAttribute("id") String id) {
		rest.delete("http://localhost:8080/room-api/{id}",id);
		return "redirect:/room";
	}
	
	@GetMapping("/search")
	public String searchRoom(@ModelAttribute("roomNumber") String roomNumber,Model model) {
		if(roomNumber.equals("")) {
			return "redirect:/room";
		} else {
			Room rooms =rest.getForObject("http://localhost:8080/room-api/search/{roomNumber}",Room.class, roomNumber);
			model.addAttribute("rooms",rooms);
			return "room/roomHome";
		}
	}
	
	
}
