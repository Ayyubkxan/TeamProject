package com.example.teamproject.repository;

import com.example.teamproject.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress,Integer> {

    List<UserAddress> findAllByUsersId(Integer users_id);
}
