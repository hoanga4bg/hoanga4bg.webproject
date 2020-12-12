package com.laptrinhweb;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Convert;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laptrinhweb.convert.StudentConvert;
import com.laptrinhweb.dto.StudentDTO;
import com.laptrinhweb.entity.Bill;
import com.laptrinhweb.entity.Room;
import com.laptrinhweb.entity.Service;
import com.laptrinhweb.entity.Student;


@Controller
@RequestMapping("/bill")
public class BillController {
	
	private RestTemplate rest=new RestTemplate();
	private StudentConvert studentConvert=new StudentConvert();
	
	@GetMapping
	public String home( Model model) {
		List<Student> students=Arrays.asList(rest.getForObject("http://localhost:8080/student-api", Student[].class));
		List<Bill> allBill=Arrays.asList(rest.getForObject("http://localhost:8080/bill-api/month-search", Bill[].class));
		Boolean check=true;
		if(allBill.size()==students.size()) {
			check=false;
			model.addAttribute("check", check);
		}
		else {
			check=true;
			model.addAttribute("check", check);
		}
		List<StudentDTO> studentDTO=new ArrayList<StudentDTO>();
		for(Student s: students) {
			List<Bill> bills=new ArrayList<Bill>
			(Arrays.asList(rest.getForObject("http://localhost:8080/bill-api/search-month/{id}",
							Bill[].class, s.getId().toString())));
			if(bills.size()==0) {
				StudentDTO sd=studentConvert.toStudenDTO(s);
				sd.setCreated(true);
				studentDTO.add(sd);
			
			}
			else {
				StudentDTO sd=studentConvert.toStudenDTO(s);
				sd.setCreated(false);
				studentDTO.add(sd);
			}
			
		}	
		
		StudentDTO[] sArray= new StudentDTO[studentDTO.size()];
		sArray=studentDTO.toArray(sArray);
			
		model.addAttribute("dto",sArray);
		model.addAttribute("students",students);
	
		return "bill/billHome";
	}
	
	
	@GetMapping("/search")
	public String searchStudent(@ModelAttribute("msv") String msv,Model model) {
		if(msv==null||msv.equals("")) {
			return "redirect:/bill";
		}
		else {
			List<Student> students=new ArrayList<Student>(
					Arrays.asList(rest.getForObject("http://localhost:8080/student-api/search/{studentCode}", 
													Student[].class, msv)));
			List<Bill> allBill=Arrays.asList(rest.getForObject("http://localhost:8080/bill-api/month-search", Bill[].class));
			Boolean check=true;
			if(allBill.size()==students.size()) {
				check=false;
				model.addAttribute("check", check);
			}
			else {
				check=true;
				model.addAttribute("check", check);
			}
			List<StudentDTO> studentDTO=new ArrayList<StudentDTO>();
			for(Student s: students) {
				List<Bill> bills=new ArrayList<Bill>
				(Arrays.asList(rest.getForObject("http://localhost:8080/bill-api/search-month/{id}",
								Bill[].class, s.getId().toString())));
				if(bills.size()==0) {
					StudentDTO sd=studentConvert.toStudenDTO(s);
					sd.setCreated(true);
					studentDTO.add(sd);
				
				}
				else {
					StudentDTO sd=studentConvert.toStudenDTO(s);
					sd.setCreated(false);
					studentDTO.add(sd);
				}
				
			}	
			
			StudentDTO[] sArray= new StudentDTO[studentDTO.size()];
			sArray=studentDTO.toArray(sArray);
				
			model.addAttribute("dto",sArray);
			model.addAttribute("students",students);

			
			return "bill/billHome";
		}
	}
	
	@GetMapping("/add")
	public String addBill(@ModelAttribute("id") String id,Model model,RedirectAttributes redirect){
		String message="";
		Date date=new Date();
		List<Bill> bills=new ArrayList<Bill>
						(Arrays.asList(rest.getForObject("http://localhost:8080/bill-api/search-month/{id}",
										Bill[].class, id)));
		
		if(bills.size()==0) {
			Student student=rest.getForObject("http://localhost:8080/student-api/search-id/{id}", 
					Student.class, Long.parseLong(id));
			List<Service> services=new ArrayList<Service>(student.getServices());
			
			
			Bill bill=new Bill();
			bill.setStudent(student);
			Double total=student.getRoom().getPrice();
			for(Service s:student.getServices()) {
				total+=s.getPrice();
			}
			
			bill.setTotalPrice(total);
			bill.setStatus(true);
			bill.setCreateDate(date);
			bill.setServices(services);
			rest.postForObject("http://localhost:8080/bill-api", bill, Bill.class);
			
			model.addAttribute("services",services);
			model.addAttribute("student",student);
			model.addAttribute("bill",bill);
			return "bill/billDetail";
		}	
	
		
		return "redirect:/bill";
	}
	
	
	@GetMapping("/show")
	public String showAllBill(@ModelAttribute("id") String id,Model model) {
		List<Bill> bills=new ArrayList(Arrays.asList(rest.getForObject("http://localhost:8080/bill-api/student-search/{id}", Bill[].class,id)));
		Student student=rest.getForObject("http://localhost:8080/student-api/search-id/{id}", Student.class, id);
		model.addAttribute("student",student);
		model.addAttribute("bills", bills);
		return "bill/allBill";
		
	}
	
	@GetMapping("/pay")
	public String payBill(@ModelAttribute("id") String id,Model model) {
		Bill bill=rest.getForObject("http://localhost:8080/bill-api/search/{id}", Bill.class,id);
		bill.setStatus(false);
		rest.put("http://localhost:8080/bill-api", bill);
		String path="redirect:/bill/show?id="+bill.getStudent().getId();
		return path;
		
	}
	
	
	@GetMapping("/add_all")
	public String addAllBill(Model model) {
		Date date=new Date();
		List<Student> students=new ArrayList(Arrays.asList(rest.getForObject("http://localhost:8080/student-api", Student[].class)));
		for(Student student: students) {
			List<Bill> bills=new ArrayList<Bill>
			(Arrays.asList(rest.getForObject("http://localhost:8080/bill-api/search-month/{id}",
							Bill[].class, student.getId().toString())));
			if(bills.size()==0) {
				
				List<Service> services=new ArrayList<Service>(student.getServices());
				
				
				Bill bill=new Bill();
				bill.setStudent(student);
				Double total=student.getRoom().getPrice();
				for(Service s:student.getServices()) {
					total+=s.getPrice();
				}
				
				bill.setTotalPrice(total);
				bill.setStatus(true);
				bill.setCreateDate(date);
				bill.setServices(services);
				rest.postForObject("http://localhost:8080/bill-api", bill, Bill.class);
				
				
				
			}	
			
			
		}			
				
		
		return "redirect:/bill";
		
	}
	
	
	@GetMapping("/showbill")
	public String showBill(@ModelAttribute("id") String id, Model model) {
		Bill bill=rest.getForObject("http://localhost:8080/bill-api/search/{id}", Bill.class,id);
		model.addAttribute("bill", bill);
		return "bill/billDetail";
	}
	@GetMapping("/delete")
	public String deleteBill(@ModelAttribute("id") String id) {
		rest.delete("http://localhost:8080/bill-api/{id}", id);
		return "redirect:/bill";
	}
}	
