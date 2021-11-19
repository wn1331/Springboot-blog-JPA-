package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity//테이블화. User클래스 Mysql에 자동으로 테이블 생성됨
public class User {
	
	@Id//Priamry key
	@GeneratedValue(strategy=GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략(Auto increment)을 따라간다
	private int id;//시퀀스, auto_increment
	
	@Column(nullable=false,length=30)//널값 불가능
	private String username; //아이디
	
	@Column(nullable=false,length=100)//나중에 해쉬로 변경해서 암호화하기때문에 길게
	private String password;
	
	@ColumnDefault("'user'")
	private String role;
	
	@CreationTimestamp//시간이 자동으로입력이됨
	private Timestamp createDate;//생성시간
	
	
}
