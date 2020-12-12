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

import com.laptrinhweb.entity.Invitee;
import com.laptrinhweb.repository.InviteeRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/invitee-api", produces = "application/json")
public class InviteeAPI {
	@Autowired
	private InviteeRepository inviteeRepository;
	

	
	@GetMapping
	public List<Invitee> getAll(){
		List<Invitee> list=new ArrayList<Invitee>();
		list=inviteeRepository.findAll();
		return list;
	}
	
	//thêm mới
		@GetMapping("/{id}")
		public Invitee FindOneById(@PathVariable("id") String id) {
			Invitee invitee=inviteeRepository.findOneById(Long.parseLong(id));
			return invitee;
		}
		//thêm mới
		@GetMapping("/searchByName/{name}")
		public List<Invitee> FindByName(@PathVariable("name") String name){
			List<Invitee> invitees = inviteeRepository.findByName(name);
			return invitees;
		}
		//thêm mới
		@GetMapping("/sort")
		public List<Invitee> SortInvite(){
			List<Invitee> invites = inviteeRepository.sortByName();
			return invites;
		}
		//thêm mới
		@GetMapping("/thongke/{startdate}/{enddate}")
		public List<Invitee> thongKe(@PathVariable("startdate") String startdate,@PathVariable("enddate") String enddate){
			List<Invitee> invites = inviteeRepository.thongke(startdate, enddate);
			return invites;
		}
	
	@GetMapping("/search")
	public Invitee searchInvitee(@RequestBody String name) {
		Invitee invitee=inviteeRepository.findOneByName(name);
		return invitee;
	}
	
	@GetMapping("/search-id")
	public Invitee searchInvitee(@RequestBody Long id) {
		Invitee invitee=inviteeRepository.findOneById(id);
		return invitee;
	}
	
	@PostMapping
	public Invitee saveInvitee(@RequestBody Invitee invitee) {
		return inviteeRepository.save(invitee);
	}
	
	@PutMapping
	public Invitee updateInvitee(@RequestBody Invitee invitee) {
		inviteeRepository.save(invitee);
		return invitee;
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteInvitee(@PathVariable("id") String id) {		
		inviteeRepository.deleteById(Long.parseLong(id));		
	}
}
