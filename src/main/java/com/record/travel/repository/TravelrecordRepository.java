package com.record.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.record.travel.entity.Travelrecords;

public interface TravelrecordRepository extends JpaRepository<Travelrecords, Long>{

}
