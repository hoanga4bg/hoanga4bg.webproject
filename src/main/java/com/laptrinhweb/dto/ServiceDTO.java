package com.laptrinhweb.dto;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class ServiceDTO {

	private Long id;
	private String name;
	private Double price;

	private boolean selected;
	private Double total;
	
}
