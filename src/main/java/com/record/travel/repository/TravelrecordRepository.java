package com.record.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.record.travel.entity.Travelrecord;

public interface TravelrecordRepository extends JpaRepository<Travelrecord, Long>{

}