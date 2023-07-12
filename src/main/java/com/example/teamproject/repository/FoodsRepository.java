package com.example.teamproject.repository;

import com.example.teamproject.entity.Foods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FoodsRepository extends JpaRepository<Foods,Integer> {

    List<Foods> findAllByRestaurantId(Integer id);




}
