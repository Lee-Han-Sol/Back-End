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
	
	public List<User> userList() {
		
		List<User> user = userRepository.findAll();
		
		return user;
	}
}
