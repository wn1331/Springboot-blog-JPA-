package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html 파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	
	@Autowired//더미컨트롤러테스트가 메모리에 나타날때 동시에 뜸. 의존성 주입.
	private UserRepository userRepository;
	
	@GetMapping("/dummy/users")//모든유저정보 가져옴
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user")//1페이지당 2건의 데이터를 리턴받음
	public Page<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> users = userRepository.findAll(pageable);
		return users;
	}
	//{id} 주소로 파라미터를 전달받을 수 있음.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//할당받은 값이 user가 아니라 optional타입임. db에서 못찾으면 null이되니까
		//에러가 뜨면 프로그램에 문제가 있음. optional로 user객체를 감싸서 가져올테니 null인지아닌지
		//판단해서 리턴해야한다.
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("해당 유저는 존재하지 않습니다. id: "+id);//id값이 존재하지 않으면 빈 객체 생성. 이건 널이 아님.
			}
		});
		return user;
		//요청: 웹브라우저
		//user객체 = 자바 오브젝트
		//변환(웹브라우저가 이해할 수 있는 데이터) -> json
		//스프링부트 = MessageConverter라는 애가 응답시에 자동으로 작동
		//만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson이라는 라이브러리를 호출해서 
		//User 오브젝트를 Json으로 변환해서 브라우저에게 던져준다.
	}
	
	
	
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 두개의 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
