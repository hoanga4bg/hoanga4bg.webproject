package com.laptrinhweb.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.laptrinhweb.entity.Room;
import com.laptrinhweb.entity.Service;

import lombok.Data;

@Data
public class StudentDTO {
	private Long id;
	private String studentCode;
	private String name;
	private String className;
	private String cmt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String homeTown;
	private Long roomId;
	private Room room;
	private List<Service> services;
	private boolean created;
	private boolean checkticket;
}
