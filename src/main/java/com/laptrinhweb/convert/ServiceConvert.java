package com.laptrinhweb.convert;

import org.springframework.stereotype.Component;

import com.laptrinhweb.dto.ServiceDTO;
import com.laptrinhweb.entity.Service;

@Component
public class ServiceConvert {
	
	public Service toService(ServiceDTO serviceDTO) {
		Service service=new Service();
		service.setId(serviceDTO.getId());
		service.setName(serviceDTO.getName());
		service.setPrice(serviceDTO.getPrice());
		
		return service;
	}
	
	
	public ServiceDTO toServiceDTO(Service service) {
		ServiceDTO serviceDTO=new ServiceDTO();
		serviceDTO.setId(service.getId());
		serviceDTO.setName(service.getName());
		serviceDTO.setPrice(service.getPrice());

		return serviceDTO;
	}
}
