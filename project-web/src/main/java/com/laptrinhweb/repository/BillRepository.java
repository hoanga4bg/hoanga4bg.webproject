package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.Bill;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	Bill findOneByStudentId(Long id);
}
