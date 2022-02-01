package com.record.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.record.travel.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
	
	//댓글 삭제 JPQL
	@Modifying //삭제 + 수정 같이 실행하기 위해 필요함
	@Query("delete from Reply r where r.travelrecord.tnum = :tnum")
	void deleteByTnum(@Param("tnum") Long tnum);
}
