package com.springboot.lookoutside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lookoutside.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{ // <���̺��, PK�� Ÿ��>

	
}
