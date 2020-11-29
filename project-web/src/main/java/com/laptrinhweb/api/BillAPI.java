package com.laptrinhweb.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/search/{id}")
	public Bill searchById(@PathVariable("id") String id) {
		
		Bill bill=billRepository.findOneById(Long.parseLong(id));
		return bill;
	}
	
	@GetMapping("/month-search")
	public List<Bill> searchByMonth(){
		Date date=new Date();
		Integer month=new Integer(date.getMonth()+1);
		List<Bill> bills=billRepository.findAllByMonth(month);
		return bills;
	}
	@GetMapping("/student-search/{id}")
	public List<Bill> search(@PathVariable("id") String id) {
		List<Bill> bills=billRepository.findByStudentId(Long.parseLong(id));
		return bills;
	}
	
	@GetMapping("/search-month/{id}")
	public List<Bill> searchInMonth( @PathVariable("id") String id){
		Date date=new Date();
		Integer month=new Integer(date.getMonth()+1);
		List<Bill> bills=billRepository.findAllBillInMonth(Long.parseLong(id),month);
		return bills;
		
	}
	
	@PostMapping
	public Bill saveBill(@RequestBody Bill bill) {
		return billRepository.save(bill);
	}
	
	@PutMapping
	public void updateBill(@RequestBody Bill bill) {
		billRepository.save(bill);
	
	}
	
	
	@DeleteMapping
	public void deleteBill(@RequestBody Long id) {
		billRepository.deleteById(id);
	}
}
