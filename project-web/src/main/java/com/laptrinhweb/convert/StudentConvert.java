package com.laptrinhweb.convert;

import org.springframework.stereotype.Component;

import com.laptrinhweb.dto.StudentDTO;
import com.laptrinhweb.entity.Student;

@Component
public class StudentConvert {
	
	public Student toStudent(StudentDTO studentDTO) {
		Student student = new Student();
		student.setId(studentDTO.getId());
		student.setName(studentDTO.getName());
		student.setClassName(studentDTO.getClassName());
		student.setCmt(studentDTO.getCmt());
		student.setDateOfBirth(studentDTO.getDateOfBirth());
		student.setStudentCode(studentDTO.getStudentCode());
		student.setHomeTown(studentDTO.getHomeTown());
		student.setServices(studentDTO.getServices());
		
		return student;
	}
	
	
	public StudentDTO toStudenDTO(Student student) {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setId(student.getId());
		studentDTO.setName(student.getName());
		studentDTO.setClassName(student.getClassName());
		studentDTO.setCmt(student.getCmt());
		studentDTO.setDateOfBirth(student.getDateOfBirth());
		studentDTO.setStudentCode(student.getStudentCode());
		studentDTO.setHomeTown(student.getHomeTown());
		studentDTO.setServices(student.getServices());
		studentDTO.setRoomId(student.getRoom().getId());
		return studentDTO;
	}
}
