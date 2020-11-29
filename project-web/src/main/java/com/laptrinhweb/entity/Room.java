package com.laptrinhweb.entity;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String roomNumber;
	
	@Enumerated(EnumType.STRING)
	private final Type type;
	public static enum Type {
		NORMAL,VIP
	}	
	private Double price;
	private Integer capacity;
	
	@JsonIgnore
	@OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
	private List<Student> listStudent;

}
