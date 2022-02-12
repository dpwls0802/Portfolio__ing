package com.record.travel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.record.travel.entity.Travelrecord;

public interface TravelrecordRepository extends JpaRepository<Travelrecord, Long> {
	// 목록화면 JPQL
	@Query("select t, w from Travelrecord t left join t.writer w where t.tnum = :tnum")
	Object getTravelrecordWithWriter(@Param("tnum") Long tnum);

	@Query("select t, r from Travelrecord t left join Reply r on r.travelrecord = t where t.tnum = :tnum")
	List<Object[]> getTravelrecordWithReply(@Param("tnum") Long tnum);

	@Query(value = "select t, w, count(r) from Travelrecord t "
			+ "left join t.writer w left join Reply r on r.travelrecord = t group by t", 
			countQuery = "select count(t) from Travelrecord t")
	Page<Object[]> getTravelrecordWithReplyCount(Pageable pageable);

	// 조회화면 JPQL
	@Query("select t, w, count(r) from Travelrecord t "
			+ "left join t.writer w "
			+ "left outer join Reply r on r.travelrecord = t "
			+ "where t.tnum = :tnum")
	Object getTravelrecordByTnum(@Param("tnum") Long tnum);

	// 목록 new
	@Query("select t, ti, w, td, count(distinct r) from Travelrecord t "
			+ "left outer join t.writer w"
			+ "left outer join t.travelDate td"
			+ "left outer join TravelImage ti on ti.travelrecord = t "
			+ "left outer join Reply r on r.travelrecord = t "
			+ "group by t")
	Page<Object[]> getTravelrecordList(Pageable pageable);
}
