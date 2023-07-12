package com.example.teamproject.repository;

import com.example.teamproject.entity.Korzinka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KorzinkaRepository extends JpaRepository<Korzinka, Integer> {

    List<Korzinka> findAllByFoodsId(Integer foods_id);
}
