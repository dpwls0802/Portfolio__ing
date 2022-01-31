package com.record.travel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.record.travel.entity.Travelrecord;

public interface TravelrecordRepository extends JpaRepository<Travelrecord, Long> {

	@Query(value = "select t, w, count(r) from Teavelrecord t left join t.writer w left join Reply r on r.travelrecord = t group by t" countQuery = "select count(t) from Travelrecord t")
	Page<Object[]> getTravelrecordWithReplyCount(PageRequest pageable);

}
