package com.example.teamproject.repository;

import com.example.teamproject.entity.Restaurant;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {


      boolean existsByName(String name);
}
