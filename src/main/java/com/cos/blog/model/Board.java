package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//오토인크리멘트 사용
	private int id;
	
	@Column(nullable=false,length=100)
	private String title;//제목
	
	@Lob//대용량 데이터 전용 어노테이션
	private String content;//섬머노트 라이브러리 사용<html> 태그가 섞여서 디자인이 됨.
	
	@ColumnDefault("0")
	private int count;//조회수
	//eager전략은 무조건 들고옴. lazy는 필요하면 들고옴
	@ManyToOne(fetch=FetchType.EAGER)//보드가 many, 유저는 one. 한명의 유저는 여러개의 게시글을 게시 가능.
	@JoinColumn(name="userId")
	private User user;//작성자. DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장 가능
	
	@OneToMany(mappedBy="board",fetch=FetchType.LAZY)//하나의게시글은 여러개의 reply.연관관계의 주인이 아니고 FK니깐 컬럼을 만들지 마세요
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;//작성, 업데이트시 자동 시간 등록
	
	
}
