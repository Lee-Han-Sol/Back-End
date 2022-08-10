package com.springboot.lookoutside.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.lookoutside.domain.User;
import com.springboot.lookoutside.repository.UserRepository;

@Service //Bean ���
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//�������� �α��� ��û�� ����ç ��, username(useId), password ���� 2���� ����è
	//password �κ�ó���� �˾Ƽ� ��.
	//username�� DB�� �ִ����� Ȯ���ؼ� Ȯ��
	@Override
	public UserDetails loadUserByUsername(String useId) throws UsernameNotFoundException {
		User principal = userRepository.findByUseId(useId)
				.orElseThrow(()-> {
					return new UsernameNotFoundException("�ش� ����ڸ� ã�� �� �����ϴ�." + useId);
				});
		return new PrincipalDetail(principal); // ��ť��Ƽ ���ǿ� ���������� �����. �� �� type UserDetail
	}
}
