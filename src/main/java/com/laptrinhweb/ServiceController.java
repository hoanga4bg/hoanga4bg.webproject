package com.laptrinhweb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.laptrinhweb.convert.ServiceConvert;
import com.laptrinhweb.dto.ServiceDTO;
import com.laptrinhweb.entity.Bill;
import com.laptrinhweb.entity.Service;

@Controller
@RequestMapping("/service")
public class ServiceController {
	
	private RestTemplate rest=new RestTemplate();
	private ServiceConvert serviceConvert=new ServiceConvert();
	
	
	@GetMapping
	public String showAll(Model model) {
		List<Service> services=new ArrayList<Service>(Arrays.asList(rest.getForObject("http://localhost:8080/service-api/getemp", Service[].class)));
		model.addAttribute("services", services);
		return "service/index";
	}
	
	
	@GetMapping("/statistic")
	public String statistic(@ModelAttribute("id") String id, Model model) {
		List<Integer> months=new ArrayList<Integer>(Arrays.asList(rest.getForObject("http://localhost:8080/bill-api/get-month", Integer[].class)));
		Service service=rest.getForObject("http://localhost:8080/service-api/search-id/{id}", Service.class, id);
		List<Double> totals=new ArrayList<Double>();
		
		for(Integer month:months) {
			List<Bill> bills=new ArrayList<Bill>(Arrays.asList(rest.getForObject("http://localhost:8080/bill-api/month-search/{month}", Bill[].class,month)));
			int count=0;
			for(Bill bill:bills) {
				List<Service> services=bill.getServices();
				for(Service s:services) {
					if(s.getId()==service.getId()) {
						count++;
					}
				}
			}	
			totals.add(service.getPrice()*count);
		}
		model.addAttribute("service",service);
		model.addAttribute("months",months);
		model.addAttribute("totals",totals);
		return "service/statisticService";
	}
	@GetMapping("/search")
	public String search(@ModelAttribute("name") String name, Model model) {
		if(name.equals("")) {
			return "redirect:/service";
		}
		List<Service> services=Arrays.asList(rest.getForObject("http://localhost:8080/service-api/employess/{name}",Service[].class, name));
		model.addAttribute("services", services);
		return "service/index";
	}
	

	//Huy thêm
	
	@GetMapping("/add")
	public String addForm(Model model) {
		Service service=new Service();
		model.addAttribute("service",service);
		return "service/addnew-emp";
	}
	@PostMapping("/add")
	public String addemployee(Service emp)
	{
		//Gọi post api để lưu dịch vụ
		rest.postForObject("http://localhost:8080/service-api/addemp1", emp, Service.class);
		return "redirect:/service";
	}
	
	@GetMapping("/edit/{id}")
	public String editform(@PathVariable("id")String id, Model model)
	{
		//Gọi get API để lấy thông tin dịch vụ cần sửa rồi truyền qua giao diện edit-emp
		Service service=rest.getForObject("http://localhost:8080/service-api/employess-id/{id}", Service.class, id);
		model.addAttribute("service", service);
		return "service/edit-emp";
	}
	
	
	@PostMapping("/edit")
	public String editService(Service service)
	{
		// Gọi put api để update dịch vụ
		rest.put("http://localhost:8080/service-api/empupdate",service);
		return "redirect:/service";
	}
		
	
	@GetMapping("/delete/{id}")
	public String deleteemp(@PathVariable("id") String id)
	{
		//gọi delete api để xóa dịch vụ
		rest.delete("http://localhost:8080/service-api/{id}",id);
		return "redirect:/service";
	}
	
}
