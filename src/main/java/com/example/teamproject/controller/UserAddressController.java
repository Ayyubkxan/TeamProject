package com.example.teamproject.controller;

import com.example.teamproject.dto.UserAddressUsersDTO;
import com.example.teamproject.entity.Address;
import com.example.teamproject.entity.UserAddress;
import com.example.teamproject.entity.Users;
import com.example.teamproject.repository.UserAddressRepository;
import com.example.teamproject.repository.UsersRepository;
import com.fasterxml.jackson.core.PrettyPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserAddressController {
    @Autowired
    UserAddressRepository userAddressRepository;

    @Autowired
    UsersRepository usersRepository;

    @RequestMapping(value = "/address",method = RequestMethod.POST)
    public String addAddress(@RequestBody UserAddressUsersDTO userAddressUsersDTO){
        UserAddress userAddress = new UserAddress();
        userAddress.setDistrict(userAddressUsersDTO.getDistrict());
        userAddress.setStreet(userAddressUsersDTO.getStreet());
        userAddress.setHomeNumber(userAddressUsersDTO.getHomeNumber());
        Optional<Users> optionalUsers = usersRepository.findById(userAddressUsersDTO.getUsers_id());
        if (optionalUsers.isPresent()){
            userAddress.setUsers(optionalUsers.get());
            userAddressRepository.save(userAddress);
            return "saved Address";

        }else {
            return "Eror!!";
        }


    }


    @RequestMapping(value = "/address",method = RequestMethod.GET)
    public List<UserAddress> getAllUserAddress(){
        return userAddressRepository.findAll();
    }


    @RequestMapping(value = "/address/{users_id}",method = RequestMethod.GET)
    public List<UserAddress> getUserAddress(@PathVariable Integer users_id){
        List<UserAddress> allByUsersId = userAddressRepository.findAllByUsersId(users_id);
        return allByUsersId;
    }


    @RequestMapping(value = "/address/{id}",method = RequestMethod.DELETE)
    public String deleteUsersAddress(@RequestBody UserAddress userAddress, @PathVariable Integer id){
        userAddressRepository.deleteById(id);
        return "delete userAddress";


    }


    @RequestMapping(value = "/address/{users_id}",method = RequestMethod.PUT)
    public String editUserAddress(@RequestBody UserAddressUsersDTO userAddressUsersDTO,@PathVariable Integer users_id){
        Optional<UserAddress> optionalUserAddress = userAddressRepository.findById(users_id);
        if (optionalUserAddress.isPresent()){
            UserAddress userAddress = optionalUserAddress.get();
            userAddress.setDistrict(userAddressUsersDTO.getDistrict());
            userAddress.setStreet(userAddressUsersDTO.getStreet());
            userAddress.setHomeNumber(userAddressUsersDTO.getHomeNumber());
            userAddressRepository.save(userAddress);
        }
        return "Edit userAddress";
    }
}
