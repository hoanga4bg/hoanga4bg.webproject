package com.laptrinhweb.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.entity.Bill;
import com.laptrinhweb.repository.BillRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/bill-api", produces = "application/json")
public class BillAPI {
	
	@Autowired
	private BillRepository billRepository;
	

	
	@GetMapping
	public List<Bill> getAll(){
		List<Bill> list=new ArrayList<Bill>();
		list=billRepository.findAll();
		return list;
	}
	
	@GetMapping("/search")
	public Bill search(@ModelAttribute("id") Long id) {
		Bill bill=new Bill();
		bill=billRepository.findOneByStudentId(id);
		return bill;
	}
	
	@PostMapping
	public Bill saveBill(@RequestBody Bill bill) {
		return billRepository.save(bill);
	}
	
	@PutMapping
	public Bill updateBill(@RequestBody Bill bill) {
		billRepository.save(bill);
		return bill;
	}
	
	
	@DeleteMapping
	public void deleteBill(@RequestBody Long id) {
		billRepository.deleteById(id);
	}
}
