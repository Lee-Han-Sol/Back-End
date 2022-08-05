package com.springboot.lookoutside.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.lookoutside.domain.User;

// ������ ��ť��Ƽ�� �α��� ��û�� ����ä�� �α����� �����ϰ� �Ϸᰡ �Ǹ� UserDetails Ÿ���� ������Ʈ��
// ������ ��ť��Ƽ�� ������ ��������ҿ� ������ ���ش�. �� �� ����Ǵ°��� PricipalDetail
public class PrincipalDetail implements UserDetails{
	private User user;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		
		return user.getUsePw();
	}

	@Override
	public String getUsername() {
		
		return user.getUseName();
	}

	//������ ������� �ʾҴ��� ����. (true : ����ȵ�)
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	//������ ����ִ��� ���� ( true : ����� ����)
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	// ��й�ȣ�� ������� �ʾҴ��� ���� (true : ����ȵ�)
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	//������ Ȱ��ȭ(��밡��) ���� (true : Ȱ��ȭ)
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	//������ ���� ����
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();
		/*
		collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				
				return "ROLE_" + user.getUseRole(); // ROLE_ �� �ٿ����Ѵ�. //ROLE_USER
			}
		});
		*/
		//�� �Ʒ��� ���� �Լ� 
		
		collectors.add(() -> {
			return "ROLE_" + user.getUseRole();
		});
		
		return collectors;
	}
	
}
