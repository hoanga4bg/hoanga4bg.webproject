package com.laptrinhweb.entity;


import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.PrePersist;


import lombok.Data;


@Data

@Entity
public class Parking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();   
	} 
}
