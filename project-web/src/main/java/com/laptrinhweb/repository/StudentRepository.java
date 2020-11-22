package com.laptrinhweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.Student;



@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findOneByStudentCode(String student_code);
	List<Student> findByStudentCode(String student_code);
	Student findOneById(Long id);
	Student findOneByName(String name);
}
