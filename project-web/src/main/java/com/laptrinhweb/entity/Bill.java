package com.laptrinhweb.entity;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Data;


@Data
@Entity
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String usedServices;
	private Double totalPrice;
	private Date createDate;
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
}
