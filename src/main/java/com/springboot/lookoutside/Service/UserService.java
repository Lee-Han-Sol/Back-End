package com.springboot.lookoutside.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.lookoutside.domain.User;
import com.springboot.lookoutside.repository.UserRepository;

//���� ���� ����
//Ʈ����� ����
//

@Service //�������� ������Ʈ ��ĵ�� ���ؼ� Bean�� ����� ���ش�. IoC
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//ȸ������
	@Transactional
	public void signUp(User user) {
		String originUsePw = user.getUsePw(); // ���� Pw
		String encUsePw = encoder.encode(originUsePw); // �ؽ���Ų Pw
		user.setUsePw(encUsePw);
		userRepository.save(user); //�ϳ��� Ʈ����� ������ ������ �������� ����
	
	}
	
	/*
	@Transactional(readOnly = true) // select �� Ʈ����� ����, ���� ����ÿ� Ʈ����� ���� ( ���ռ� ���� )
	public User signIn(User user) {
		
		return userRepository.findByUseIdAndUsePw(user.getUseId(), user.getUsePw());
		
	}
	*/
	
	//ȸ�� ��� ��ȸ
	public List<User> userList() {
		
		List<User> user = userRepository.findAll();
		
		return user;
	}
	
	//�ߺ�Ȯ��
	public void useIdCheck(String useId) {
		userRepository.findByUseId(useId);
	}
	
	//ȸ����������
	@Transactional
	public void updateUser(User user) {
		//�����ÿ��� ���Ӽ� ���ؽ�Ʈ User ������Ʈ�� ����ȭ��Ű��, ����ȭ�� User ������Ʈ�� ����
		// select�� �ؼ� User������Ʈ�� DB�� ���� �������� ������ ����ȭ�� �ϱ�����
		// ����ȭ�� ������Ʈ�� �����ϸ� �ڵ����� DB�� update�� ����
		//User persistance = userRepository.findByUseId(user.getUseId()).orElseThrow(() -> { //user.getUserId -> ���ǿ� �ö���ִ� Id�̿�
		User persistance = userRepository.findByUseId("id").orElseThrow(() -> { //�׽�Ʈ��
			return new IllegalArgumentException("ȸ��ã�� ����");
		});
		//��й�ȣ ����
		String rawPassword = user.getUsePw();
		String encPassword = encoder.encode(rawPassword);
		persistance.setUsePw(encPassword);
		
		//�̸��� ����
		persistance.setUseEmail(user.getUseEmail());
		
		//�г��� ����
		persistance.setUseNick(user.getUseNick());
		
		//ȸ������ �Լ� ����� ���� ���� Ʈ����� ���� commit�� �ڵ����� ����
		//persistance�� ��ȭ�Ǹ� �ڵ����� update�� ����
	}
	
}
