package com.laptrinhweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.Bill;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	List<Bill> findByStudentId(Long id);
	Bill findOneById(Long id);
	@Query(value = "SELECT * FROM webproject.Bill WHERE student_id=?1 AND MONTH(create_date)=?2",nativeQuery = true)
	List<Bill> findAllBillInMonth(Long id,Integer month);
	
	
	@Query(value = "SELECT * FROM webproject.Bill WHERE MONTH(create_date)=?",nativeQuery = true)
	List<Bill> findAllByMonth(Integer month);
}
