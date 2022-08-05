package com.springboot.lookoutside.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.lookoutside.domain.User;

//DAO�� ���
//�ڵ� Bean���
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{ // <���̺��, PK�� Ÿ��>

	//JPA Naming ����
	// SELECT * FROM user where useId = ?(useId) AND usePw = ?(usePw);
	// User findByUseIdAndUsePw(String useId, String usePw);

	
	//�Ʒ�ó�� �ص��ȴ�
	/*
	@Query(value="SELECT * FROM user WHERE use_id = ? AND use_pw = ?", nativeQuery =true)
	User singIn(String use_id, String use_pw);
	*/
	
	Optional<User> findByUseId(String useId);
}
