package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//여기는 데이터를 return하는게 아니라 file을 리턴한다.
public class TempControllertest {
	
	
	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temp-home");
		//파일리턴 기본경로: src/main/resources/static
		//리턴명: /home/html
		//풀경로: src/main/resources/static/home.html
		return "/home.html";
		
	}
	@GetMapping("/temp/img")
	public String tempimg() {
		return "/abx.jpg";
	}
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//prefix: /WEB-INF/views/
		//suffix: jsp
		//fullname: /WEB-INF/views//test.jsp.jsp
		return "test";
	}
}
