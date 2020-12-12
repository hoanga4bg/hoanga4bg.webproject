package com.laptrinhweb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity

public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long price = 100000l;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;

}
