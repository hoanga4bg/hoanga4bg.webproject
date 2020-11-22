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
	
	@GetMapping("/search")
	public Invitee searchInvitee(@RequestBody String name) {
		Invitee invitee=inviteeRepository.findOneByInviteeName(name);
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
	
	
	@DeleteMapping
	public void deleteInvitee(@RequestBody Long id) {		
		inviteeRepository.deleteById(id);		
	}
}
