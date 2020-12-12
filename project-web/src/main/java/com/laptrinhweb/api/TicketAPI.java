package com.laptrinhweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.entity.Ticket;
import com.laptrinhweb.repository.TicketRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/ticket-api", produces = "application/json")
public class TicketAPI {
	@Autowired
	private TicketRepository ticketRepository;
	
	@PostMapping
	public Ticket SaveTicket(@RequestBody Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	@GetMapping("/{id}")
	public List<Ticket> CheckTicket(@PathVariable("id") String id) {
		return ticketRepository.CheckDate(Long.parseLong(id));
	}

}
