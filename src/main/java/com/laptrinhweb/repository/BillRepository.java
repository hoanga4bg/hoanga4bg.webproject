package com.laptrinhweb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.Bill;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	List<Bill> findByStudentId(Long id);
	Bill findOneById(Long id);
	@Query(value = "SELECT * FROM bill WHERE student_id=?1 AND MONTH(create_date)=?2",nativeQuery = true)
	List<Bill> findAllBillInMonth(Long id,Integer month);
	
	
	@Query(value = "SELECT * FROM bill WHERE MONTH(create_date)=?",nativeQuery = true)
	List<Bill> findAllByMonth(Integer month);
	
	
	
	
	@Query(value = "SELECT month(create_date) "
			+ "FROM bill "
			+ "group by(month(create_date))",nativeQuery = true)
	List<Integer> getMonth();
	
	

	List<Bill> findByStudentIdAndCreateDateBetween(Long parseLong, Date start, Date end);
	
	
	@Query(value = "SELECT *"
			+ "	FROM webproject.bill"
			+ "	where "
			+ "student_id=?1 AND month(create_date)>=?2 AND month(create_date)<=?3",nativeQuery = true)
	List<Bill> findByStudentIdBetweenMonth(Long id, Integer Integer,  Integer endMonth);
}
