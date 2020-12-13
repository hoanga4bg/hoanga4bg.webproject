package com.laptrinhweb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;



@Data

@Entity
public class Invitee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String cmt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	//private Date inviteTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateInvite;
	
//	@PrePersist
//	void inviteTime() {
//		this.inviteTime=inviteTime;
//	}
	

	@ManyToMany(targetEntity = Room.class)
	private List<Room> room;
}
