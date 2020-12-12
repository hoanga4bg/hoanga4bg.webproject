package com.laptrinhweb.entity;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="room_id")
    private Room room;
	
	
	@ManyToMany(targetEntity = Service.class)
	private List<Service> services=new ArrayList<Service>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	private List<Bill> bills;
	
	@OneToMany(mappedBy = "student")
	private List<Parking> parkings;
	
	@JsonIgnore
	@OneToMany(mappedBy = "student")
	private List<Ticket> tickets;
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentCode=" + studentCode + ", name=" + name + ", className=" + className
				+ ", cmt=" + cmt + ", dateOfBirth=" + dateOfBirth + ", homeTown=" + homeTown + ", room=" + room
				+ ", services=" + services + ", bills=" + bills + ", parkings=" + parkings + "]";
	}
    
}
