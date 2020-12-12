package com.laptrinhweb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.laptrinhweb.dto.StudentDTO;
import com.laptrinhweb.entity.Invitee;
import com.laptrinhweb.entity.Student;
import com.laptrinhweb.entity.Ticket;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	private RestTemplate rest = new RestTemplate();

	@GetMapping("/show")
	public String ShowTicket(Model model) {
		Date date = new Date();
		int month = date.getMonth();
		int year = date.getYear();
		List<Student> students = Arrays.asList(rest.getForObject("http://localhost:8080/student-api", Student[].class));
		List<StudentDTO> students1 = new ArrayList<>();
		for (Student student : students) {
			StudentDTO studentdto = new StudentDTO();
			studentdto.setName(student.getName());
			studentdto.setStudentCode(student.getStudentCode());
			studentdto.setClassName(student.getClassName());
			studentdto.setCmt(student.getCmt());
			studentdto.setHomeTown(student.getHomeTown());
			studentdto.setRoom(student.getRoom());
			studentdto.setId(student.getId());
			students1.add(studentdto);
		}
		for (StudentDTO student : students1) {
			List<Ticket> tickets = Arrays.asList(
					rest.getForObject("http://localhost:8080/ticket-api/{id}", Ticket[].class, student.getId()));
			Boolean check = false;
			if (tickets.size() != 0) {
				for (Ticket ticket1 : tickets) {
					if (ticket1.getCreateDate().getMonth() == month && ticket1.getCreateDate().getYear() == year) {
						check = true;
						break;
					}

				}
			}

			if (check == false) {
				student.setCheckticket(true);
			} else {
				student.setCheckticket(false);
			}

		}
		model.addAttribute("students", students1);
		System.out.println("hello");

		return "ticket/ticket";
	}

	@GetMapping
	public String SaveTicket(@ModelAttribute("id") String id, Model model) {
		Ticket ticket = new Ticket();
		ticket.setCreateDate(new Date());
		Student student = rest.getForObject("http://localhost:8080/student-api/search-id/{id}", Student.class, id);
		ticket.setStudent(student);
		rest.postForObject("http://localhost:8080/ticket-api", ticket, Ticket.class);
		return "redirect:/ticket/show";

	}

	@GetMapping("/search")
	public String Search(@ModelAttribute("msv") String msv, Model model) {
		if (msv.equals("")) {
			return "redirect:/ticket/show";
		} else {
			List<Student> students = new ArrayList<Student>(Arrays.asList(
					rest.getForObject("http://localhost:8080/student-api/search/{studentCode}", Student[].class, msv)));
			Date date = new Date();
			int month = date.getMonth();
			int year = date.getYear();
			List<StudentDTO> students1 = new ArrayList<>();
			for (Student student : students) {
				StudentDTO studentdto = new StudentDTO();
				studentdto.setName(student.getName());
				studentdto.setStudentCode(student.getStudentCode());
				studentdto.setClassName(student.getClassName());
				studentdto.setCmt(student.getCmt());
				studentdto.setHomeTown(student.getHomeTown());
				studentdto.setRoom(student.getRoom());
				studentdto.setId(student.getId());
				students1.add(studentdto);
			}
			for (StudentDTO student : students1) {
				List<Ticket> tickets = Arrays.asList(
						rest.getForObject("http://localhost:8080/ticket-api/{id}", Ticket[].class, student.getId()));
				Boolean check = false;
				if (tickets.size() != 0) {
					for (Ticket ticket1 : tickets) {
						if (ticket1.getCreateDate().getMonth() == month && ticket1.getCreateDate().getYear() == year) {
							check = true;
							break;
						}

					}
				}

				if (check == false) {
					student.setCheckticket(true);
				} else {
					student.setCheckticket(false);
				}

			}
			model.addAttribute("students", students1);

			return "ticket/ticket";
		}

	}

}
