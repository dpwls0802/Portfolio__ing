package com.record.travel.entity;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "travelrecord")
public class Reply extends BaseEntity {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rnum;

	private String replyText;
	private String replyer;

	@ManyToOne
	private Travelrecord travelrecord;

}
