package com.laptrinhweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.entity.Invitee;


@Repository
public interface InviteeRepository extends JpaRepository<Invitee, Long>{

	Invitee findOneByName(String name);

	Invitee findOneById(Long id);

}
