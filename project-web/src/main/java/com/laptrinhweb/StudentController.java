package com.laptrinhweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laptrinhweb.entity.Student;
import com.laptrinhweb.repository.StudentRepository;



@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	


	@GetMapping("/add")
	public String addFrom(Model model) {
		model.addAttribute("student",new Student());
		return "formNhapSinhVien";
	}
	
	@GetMapping("/edit")
	public String editForm() {
		
		return "formEditSinhVien";
	}
	
	
	@GetMapping("/show")
	public String showForm() {
		return "thongKeSinhVien";
	}
	
	@PostMapping("/add")
	public String add(Student student) {
		studentRepository.save(student);
		return "redirect:/";
		
	}
}
