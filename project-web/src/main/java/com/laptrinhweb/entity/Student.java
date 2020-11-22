package com.laptrinhweb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;



@Data

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String studentCode;
	private String name;
	private String className;
	private String cmt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String homeTown;
	
	@ManyToOne
	@JoinColumn(name="room_id")
    private Room room;
	
	@ManyToMany(mappedBy = "students",cascade = CascadeType.ALL)
	private List<Service> services;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	private List<Bill> bills;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	private List<Parking> parkings;

	@Override
	public String toString() {
		return "Student [id=" + id + ", studentCode=" + studentCode + ", name=" + name + ", className=" + className
				+ ", cmt=" + cmt + ", dateOfBirth=" + dateOfBirth + ", homeTown=" + homeTown + ", room=" + room
				+ ", services=" + services + ", bills=" + bills + ", parkings=" + parkings + "]";
	}
    
}
