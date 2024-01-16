package com.pfa.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.springjwt.models.Reclamation;


@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
	 List<Reclamation> findByUserId(Long userId);
	 List<Reclamation> findByUserCin(String userCin);
}
