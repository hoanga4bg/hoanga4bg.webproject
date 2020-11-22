package com.laptrinhweb.entity;

import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;






import lombok.Data;


@Data
@Entity
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Double price;
	private Date timeUsing;
	
	@ManyToMany
	@JoinTable(name="student_service",
			   joinColumns = @JoinColumn(name="service_id"),
			   inverseJoinColumns = @JoinColumn(name="student_id")
			)
	private List<Student> students;
}
