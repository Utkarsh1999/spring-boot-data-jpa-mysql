package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Signon;

public interface SignonRepository extends JpaRepository<Signon, Long> {
	List<Signon> findByUsername(String username);
	List<Signon> findByUsernameAndPassword(String username, String password);
}
