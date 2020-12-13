package com.laptrinhweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.Invitee;


@Repository
public interface InviteeRepository extends JpaRepository<Invitee, Long>{
	Invitee findOneByName(String name);

	Invitee findOneById(Long id);

	List<Invitee> findByName(String name);

	@Query(value = "SELECT * FROM invitee order by name", nativeQuery = true)
	List<Invitee> sortByName();
	
	@Query(value="SELECT * FROM webproject.invitee where date_invite >= ?1 AND date_invite <= ?2 ",nativeQuery=true)
	List<Invitee> thongke(String startdate,String enddate);
}
