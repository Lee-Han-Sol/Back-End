package com.springboot.lookoutside.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
		System.out.println("UserController : signUp ȣ�� : ȸ������");
		userService.signUp(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); // Java ������Ʈ�� Json���� ����
	}
	
	/* ��ť��Ƽ�� �̰���
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
	
	//
	
	/*
	//�г��� �ߺ�Ȯ��
	@GetMapping("/Nickname/{useNick}")
	public ResponseDto<Integer> useNickCheck(@PathVariable String useNick) {
		System.out.println("UserController : useIdCheck ȣ�� " + useNick);
		userService.useNickCheck(useNick);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//ID �ߺ�Ȯ��
	@GetMapping("/Id/{useId}")
	public ResponseDto<Integer> useIdCheck(@PathVariable String useId) {
		System.out.println("UserController : useIdCheck ȣ�� " + useId);
		userService.useIdCheck(useId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	*/
	
	//�г��� �ߺ�Ȯ��
	@GetMapping("/Nickname/{useNick}")
	public ResponseDto<Boolean> useNickCheck(@PathVariable String useNick) {
		System.out.println("UserController : useIdCheck ȣ�� " + useNick);
		return new ResponseDto<Boolean>(HttpStatus.OK.value(), userService.useNickCheck(useNick)); // false => �г��� ����, true => �г��� �Ұ���
	}
  
	//ID �ߺ�Ȯ��
	@GetMapping("/Id/{useId}")
	public ResponseDto<Boolean> useIdCheck(@PathVariable String useId) {
		System.out.println("UserController : useIdCheck ȣ�� " + useId);
		return new ResponseDto<Boolean>(HttpStatus.OK.value(), userService.useIdCheck(useId)); // false => ID ����, true => ID �Ұ���
	}
	
	//ȸ������ ����
	@PutMapping
	public ResponseDto<Integer> update(@RequestBody User user) {
		userService.updateUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//��й�ȣ����
	@PutMapping("/NewPw/{useId}")
	public ResponseDto<Integer> update(@RequestBody User user, @PathVariable String useId) {
		userService.newPw(user,useId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//���̵� ã��
	@GetMapping("/Email/{useEmail}")
	public ResponseDto<String> findMyId(@PathVariable String useEmail) {
		String myId = userService.findMyId(useEmail);
		return new ResponseDto<String>(HttpStatus.OK.value(), myId);
	}
	
	//ȸ�� Ż��, ����
	@DeleteMapping("/{useId}")
	public ResponseDto<String> deleteUser(@PathVariable String useId) {
		String result = userService.deleteUser(useId);
		return new ResponseDto<String>(HttpStatus.OK.value(), result);
	}
	
	/*
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
	*/
	
	
	
}
