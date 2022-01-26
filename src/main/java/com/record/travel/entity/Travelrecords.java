package com.record.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "writer")
public class Travelrecords extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tnum;
	
	@Column(length=100, nullable=false)
	private String title;
	
	@Column(length=1000, nullable=false)
	private String content;
	
	@ManyToOne
	private User writer;
	
	@Column(length=100, nullable=false)
	private String travelDate;
	
	public void changTitle(String title) {
		this.title = title;
	}
	
	public void changeContent(String content) {
		this.content = content;
	}
	
	public void changeTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}
}