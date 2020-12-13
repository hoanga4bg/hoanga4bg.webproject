package com.laptrinhweb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.laptrinhweb.convert.ServiceConvert;
import com.laptrinhweb.convert.StudentConvert;
import com.laptrinhweb.dto.ServiceDTO;
import com.laptrinhweb.dto.StudentDTO;
import com.laptrinhweb.entity.Room;
import com.laptrinhweb.entity.Service;
import com.laptrinhweb.entity.Student;
import com.laptrinhweb.entity.Bill;
import com.laptrinhweb.repository.StudentRepository;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;



@Controller
@RequestMapping("/student")
public class StudentController {
	private RestTemplate rest=new RestTemplate();
	private StudentConvert studentConvert=new StudentConvert();
	private ServiceConvert serviceConvert=new ServiceConvert();

	
	@GetMapping
	public String showAll(Model model) {
		List<Student> students=Arrays.asList(rest.getForObject("http://localhost:8080/student-api", Student[].class));
		model.addAttribute("students",students);

		return "student/student";
	}

	@GetMapping("/add")
	public String addFrom(Model model) {
		List<Service> services=Arrays.asList(rest.getForObject("http://localhost:8080/service-api", Service[].class));
		List<Room> rooms=Arrays.asList(rest.getForObject("http://localhost:8080/room-api/availble", Room[].class));
		model.addAttribute("rooms",rooms);
	
		model.addAttribute("student",new StudentDTO());
		model.addAttribute("services", services);
		return "student/addForm";
	}
	
	
	
	@GetMapping("/search")
	public String searchStudent(@ModelAttribute("msv") String msv, Model model) {
		if(msv.equals("")) {
			return "redirect:/student";
		}
		else {
			List<Student> students=new ArrayList<Student>(
									Arrays.asList(rest.getForObject("http://localhost:8080/student-api/search/{studentCode}", 
																	Student[].class, msv)));
			model.addAttribute("students",students);
			model.addAttribute("msv",msv);
			return "student/student";
		}
	}
	
	
	
	
	
	@GetMapping("/edit")
	public String editForm(@ModelAttribute("id") String id,Model model) {
		Student student=rest.getForObject("http://localhost:8080/student-api/search-id/{id}",Student.class,id);
		
		StudentDTO studentDTO=new StudentDTO();
		studentDTO=studentConvert.toStudenDTO(student);
		
		List<Room> rooms=new ArrayList<Room>(Arrays.asList(rest.getForObject("http://localhost:8080/room-api/availble", Room[].class)));
		
		
		if(rooms.indexOf(student.getRoom())<0) {
			rooms.add(student.getRoom());
		}
		
		Collections.sort((List<Room>) rooms,new Comparator<Room>() {

			@Override
			public int compare(Room arg0, Room arg1) {
				if(arg0.getId()<arg1.getId()) {
					return -1;
				}
				else if(arg0.getId()>arg1.getId()) {
					return 1;
				}
				return 0;
			}
		});
		
		List<Service> services=new ArrayList<Service>(studentDTO.getServices());
		
		
		
		List<Service> allServices=new ArrayList<Service>(Arrays.asList(rest.getForObject("http://localhost:8080/service-api", Service[].class)));
		
		List<ServiceDTO> servicesDTO=new ArrayList<ServiceDTO>();
		
		
		for(Service s:allServices) {
			ServiceDTO sd=new ServiceDTO();
			sd=serviceConvert.toServiceDTO(s);
			if(services.indexOf(s)!=-1) {
				sd.setSelected(true);			
			}
			else {
				sd.setSelected(false);
			}
			servicesDTO.add(sd);
		}
		model.addAttribute("services",servicesDTO);	
		model.addAttribute("student",studentDTO);
		model.addAttribute("rooms",rooms);
		return "student/editForm";
	}
	
	
	@GetMapping("/delete")
	public String showForm(@ModelAttribute("id") String id) {
		rest.delete("http://localhost:8080/student-api/{id}",id);
		return "redirect:/student";
	}
	
	@GetMapping("/statistic")
	public String statistic(@ModelAttribute("id") String id,
							@ModelAttribute("start") String start,
							@ModelAttribute("end") String end ,
							Model model) {
		
			Student student=rest.getForObject("http://localhost:8080/student-api/search-id/{id}", Student.class, id);
			if(start.equals("")&&end.equals("")) {
				List<Bill> bills = new ArrayList<Bill>(Arrays.asList(
						rest.getForObject("http://localhost:8080/bill-api/student-search/{id}", Bill[].class, id)));
				
				List<Service> services = new ArrayList<Service>(
						Arrays.asList(rest.getForObject("http://localhost:8080/service-api", Service[].class)));
				List<ServiceDTO> servicesDTO = new ArrayList<ServiceDTO>();
				for (Service s : services) {
					ServiceDTO serviceDTO = serviceConvert.toServiceDTO(s);
					int count = 0;
					for (Bill bill : bills) {
						List<Service> list = bill.getServices();
						for (Service l : list) {
							if (l.getId() == s.getId()) {
								count++;
							}
						}

					}
					serviceDTO.setTotal(count * s.getPrice());
					servicesDTO.add(serviceDTO);
				}
				model.addAttribute("servicesDTO", servicesDTO);
				model.addAttribute("student", student);
				model.addAttribute("start",start);
				model.addAttribute("end",end);
				return "student/statistic";
			}
			else {
				List<Bill> bills = new ArrayList<Bill>(Arrays.asList(
						rest.getForObject("http://localhost:8080/bill-api/get-by-date?id={id}&start={start}&end={end}",
								Bill[].class, id,start,end)));
				List<Service> services = new ArrayList<Service>(
						Arrays.asList(rest.getForObject("http://localhost:8080/service-api", Service[].class)));
				List<ServiceDTO> servicesDTO = new ArrayList<ServiceDTO>();
				for (Service s : services) {
					ServiceDTO serviceDTO = serviceConvert.toServiceDTO(s);
					int count = 0;
					for (Bill bill : bills) {
						List<Service> list = bill.getServices();
						for (Service l : list) {
							if (l.getId() == s.getId()) {
								count++;
							}
						}

					}
					serviceDTO.setTotal(count * s.getPrice());
					servicesDTO.add(serviceDTO);
				}
				model.addAttribute("servicesDTO", servicesDTO);
				model.addAttribute("student", student);
				model.addAttribute("start",start);
				model.addAttribute("end",end);
				return "student/statistic";
			}
	
	}
	
	
	
	@PostMapping
	public String add(StudentDTO studentDTO) {
		Room room=rest.getForObject("http://localhost:8080/room-api/search-id/{id}", Room.class,studentDTO.getRoomId());
		Student student=studentConvert.toStudent(studentDTO);
		student.setRoom(room);
		rest.postForObject("http://localhost:8080/student-api",student,Student.class);	
		return "redirect:/student";
		
	}
	
	@PostMapping("/update")
	public String updateStudent(StudentDTO studentDTO) {
		Room room=rest.getForObject("http://localhost:8080/room-api/search-id/{id}", Room.class,studentDTO.getRoomId());
		Student student=studentConvert.toStudent(studentDTO);
		student.setRoom(room);
		rest.put("http://localhost:8080/student-api",student,Student.class);	
		return "redirect:/student";
		
	}
	

}
