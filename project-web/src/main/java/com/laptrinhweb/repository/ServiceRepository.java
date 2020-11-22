package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.Service;



@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

	Service findOneByName(String name);

	Service findOneById(Long id);

}
