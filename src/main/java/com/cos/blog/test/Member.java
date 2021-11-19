package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data//게터세터 롬복
@NoArgsConstructor//빈 생성자, 이걸 정의하면 final 못씀
public class Member {
	private  int id;//final로하는이유는 불변성 유지. 패스워드를 변경하고싶으면 final로하면안됨
	private  String username;
	private  String password;
	
	@Builder
	public Member(int id, String username, String password) {
		
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	
	
	
	
}
