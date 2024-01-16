package com.pfa.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.springjwt.models.Services;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {
}
