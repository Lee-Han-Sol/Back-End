package com.springboot.lookoutside.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lookoutside.Service.UserService;
import com.springboot.lookoutside.domain.User;
import com.springboot.lookoutside.dto.ResponseDto;
import com.springboot.lookoutside.repository.UserRepository;

@RestController
@RequestMapping("/user") // ���� ���� ȸ���� �� ���Ӱ���
public class UserController {

	@Autowired 
	private UserService userService;

	
	//ȸ������
	@PostMapping("/sign-up")
	public ResponseDto<Integer> signUp(@RequestBody User user) { // json Ÿ���Ͻ� RequestBody
		System.out.println("UserController : signUp ȣ��");
		userService.signUp(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); // Java ������Ʈ�� Json���� ����
	}
	/*
	//�α���
	@PostMapping("/sign-in")
	public ResponseDto<Integer> signIn(@RequestBody User user, HttpSession session) { 
		System.out.println("UserController : signIn ȣ��");
		User login = userService.signIn(user);
		
		if(login != null) {
			session.setAttribute("pricipal", login);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); // data : 1 ȣ��� ����
	}
	*/
	
	//ȸ�� ��� ��ȸ (����)
	@GetMapping
	public ResponseDto<Integer> userList() {
		System.out.println("UserController : userList ȣ��");
		userService.userList();
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	/*
	//�г��� �ߺ�Ȯ��
  
	//ID �ߺ�Ȯ��
	
	
	
	//ȸ������ ����
	@Transactional //�Լ� ����� �ڵ� commit
	@PutMapping("/{use_no}")
	public User updateUser(@PathVariable int use_no, @RequestBody User requestUser) { //json �����͸� ��û => java Object�� ��ȯ (RequestBody)
		System.out.println("ȸ����ȣ : " + use_no);
		System.out.println("��й�ȣ : " + requestUser.getUse_pw());
		System.out.println("�г��� : " + requestUser.getUse_nick());
		System.out.println("E-mail : " + requestUser.getUse_email());
		
		User user = userReposiory.findById(use_no).orElseThrow(()->{
			return new IllegalArgumentException("������ �����Ͽ����ϴ�");
		});
		user.setUse_pw(requestUser.getUse_pw());
		user.setUse_email(requestUser.getUse_email());
		
//		userReposiory.save(user); //@Transactional ���� �Ƚᵵ �ȴ�.
		return user; 
	}
	
	//ȸ�� Ż��, ����
	@DeleteMapping("/{use_no}")
	public String deleteUser(@PathVariable int use_no) {
		try {
			userReposiory.deleteById(use_no);			
		} catch (Exception e) {
			return "������ �����Ͽ����ϴ�. �ش� id�� DB�� �����ϴ�.";
		}
		return "ȸ�� ����";
	}
	*/
	
	
	
}
