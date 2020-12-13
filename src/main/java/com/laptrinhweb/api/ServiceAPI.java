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

import com.laptrinhweb.entity.Service;
import com.laptrinhweb.repository.ServiceRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/service-api", produces = "application/json")
public class ServiceAPI {
	@Autowired
	private ServiceRepository serviceRepository;
	

	
	@GetMapping
	public List<Service> getAll(){
		List<Service> list=new ArrayList<Service>();
		list=serviceRepository.findAll();
		return list;
	}
	
	@GetMapping("/search")
	public Service searchService(@RequestBody String name) {
		Service service=serviceRepository.findOneByName(name);
		return service;
	}
	
	@GetMapping("/search-id/{id}")
	public Service searchServiceById(@PathVariable("id") String id) {
		Service service=serviceRepository.findOneById(Long.parseLong(id));
		return service;
	}
	
	@PostMapping
	public Service saveService(@RequestBody Service service) {
		return serviceRepository.save(service);
	}
	
	@PutMapping
	public Service updateService(@RequestBody Service service) {
		serviceRepository.save(service);
		return service;
	}
	
	
//	@DeleteMapping
//	public void deleteService(@RequestBody Long id) {		
//		serviceRepository.deleteById(id);		
//	}
//	
	
	// Huy thêm
	@GetMapping("/test")
	public String test()
	{
		return "WELCOME";
	}
	
	@PostMapping("/addemp1")
	public Service addemployee(@RequestBody Service emp)
	{
		serviceRepository.save(emp);
		return emp;
		
	}
	@GetMapping("/getemp")
	public List<Service> employees()
	{
		return serviceRepository.findAll();
	}
	
	@GetMapping("/employess/{name}")
	public List<Service> findbyname(@PathVariable("name") String name)
	{
		
		return serviceRepository.FindByName(name);
	}
	
	
	@GetMapping("/employess-id/{id}")
	public Service findbyId(@PathVariable("id") String id)
	{
		Service service=serviceRepository.findOneById(Long.parseLong(id));
		return service;
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id)
	{	
		serviceRepository.deleteById(Long.parseLong(id));
		
	}
	
	@PutMapping("/empupdate")
	public Service updateemp(@RequestBody Service emp)
	{
		serviceRepository.save(emp);
		return emp;
	}
}
