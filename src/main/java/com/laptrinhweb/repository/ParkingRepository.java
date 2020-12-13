package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.Parking;


@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

	Parking findOneById(Long id);

}
