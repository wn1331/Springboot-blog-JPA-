package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	// http://localhost:8080/http/get
	//인터넷 브라우저 요청은 무조건 get요청밖에안된다.
	
	private static final String TAG = "HttpControllerTest: ";
	
	@GetMapping("/http/lombok")
	public String LombokTest() {
		Member m =Member.builder().username("jjh").password("020512").build();
		
		System.out.println(TAG+"getter: "+m.getUsername());
		m.setUsername("123");
		System.out.println(TAG+"setter: "+m.getUsername());
		return "Lombok Test 완료";
	}
	
	
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		return "get요청, id = "+ m.getId()+", "+m.getUsername()+", "+m.getPassword();
	}
	//http://localhost:8080/http/post
	@PostMapping("/http/post")
	public String postTest() {
		return "post요청";
	}
	//http://localhost:8080/http/put
	@PutMapping("/http/put")
	public String putTest() {
		return "put요청";
	}
	//http://localhost:8080/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}
}
