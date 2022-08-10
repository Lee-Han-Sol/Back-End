package com.springboot.lookoutside.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lookoutside.Service.UserService;
import com.springboot.lookoutside.domain.User;
import com.springboot.lookoutside.dto.ResponseDto;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired 
	private UserService userService;
	
	//ȸ�� ��� ��ü ��ȸ
	@GetMapping
	public ResponseDto<Page<User>> userList(@PageableDefault(size=3,sort="useNo",direction = Sort.Direction.DESC ) Pageable pageable) { //���� �ֱټ� ��ȸ 3��
		System.out.println("UserController : userList ȣ��");
		Page<User> user = userService.userList(pageable);
		
		return new ResponseDto<Page<User>>(HttpStatus.OK.value(),user);
	}
	
	//ȸ�� ���� ����
	@PutMapping("/{useId}")
	public ResponseDto<Integer> changeRole(@PathVariable String useId) {
		userService.changeRole(useId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//ȸ�� ���� ����
	@DeleteMapping("/user")
	public ResponseDto<String> deleteCheckUser(int[] useNos) {
		String result = userService.deleteCheckUser(useNos);
		return new ResponseDto<String>(HttpStatus.OK.value(), result);
	}

	
}
