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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.laptrinhweb.entity.Student;

import com.laptrinhweb.repository.StudentRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/student-api", produces = "application/json")
public class StudentAPI {
	@Autowired
	private StudentRepository studentRepository;
	

	
	@GetMapping
	public List<Student> getAll(){
		List<Student> list=new ArrayList<Student>();
		list=studentRepository.findAll();
		return list;
	}
	
	@GetMapping("/search/{studentCode}")
	public List<Student> searchStudent(@PathVariable("studentCode") String studentCode) {
		List<Student> s=studentRepository.findByStudentCode(studentCode);
		return s;
	}
	
	@GetMapping("/search-id/{id}")
	public Student searchStudentById(@PathVariable("id") String id) {
		Student student=studentRepository.findOneById(Long.parseLong(id));
		return student;
	}
	
	@PostMapping
	public Student saveStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@PutMapping
	public Student updateStudent(@RequestBody Student student) {
		studentRepository.save(student);
		return student;
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable("id") String id) {		
		studentRepository.deleteById(Long.parseLong(id));		
	}
}
