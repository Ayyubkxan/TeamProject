package com.example.teamproject.controller;

import com.example.teamproject.dto.KorzinkaUsersDTO;
import com.example.teamproject.entity.Korzinka;
import com.example.teamproject.entity.Users;
import com.example.teamproject.repository.KorzinkaRepository;
import com.example.teamproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersKorzinkaController {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    KorzinkaRepository korzinkaRepository;


    @RequestMapping(value = "/customer",method = RequestMethod.POST)
    public String addCustomer(@RequestBody KorzinkaUsersDTO korzinkaUsersDTO){
        Users users = new Users();
        users.setName(korzinkaUsersDTO.getName());
        users.setPhonenumber(korzinkaUsersDTO.getPhoneNumber());
        Optional<Korzinka> optionalKorzinka = korzinkaRepository.findById(korzinkaUsersDTO.getKorzinka_id());
        if (optionalKorzinka.isPresent()){
            users.setKorzinka(optionalKorzinka.get());
            usersRepository.save(users);
            return "Save Customer";
        }else {
            return "Eror!";
        }


    }

    @RequestMapping(value = "/customer",method = RequestMethod.GET)
    public List<Users> getAllCostumer(){
        return usersRepository.findAll();
    }

    @RequestMapping(value = "/customer/{korzinka_id}",method = RequestMethod.GET)
    public List<Users> getCostumer(@PathVariable Integer korzinka_id){
        List<Users> allByKorzinkaId = usersRepository.findAllByKorzinkaId(korzinka_id);

        return allByKorzinkaId;




    }

    @RequestMapping(value = "/customer/{id}",method = RequestMethod.DELETE)
    public String deleteCustomer(@RequestBody Users users, @PathVariable Integer id){
        usersRepository.deleteById(id);
        return "Delete customer";
    }


    @RequestMapping(value = "/customer/{id}",method = RequestMethod.PUT)
    public String  editCustomer(@RequestBody KorzinkaUsersDTO korzinkaUsersDTO,@PathVariable Integer id){
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if (optionalUsers.isPresent()){
            Users users = new Users();
            users.setName(korzinkaUsersDTO.getName());
            users.setPhonenumber(korzinkaUsersDTO.getPhoneNumber());
            usersRepository.save(users);
        }
        return "Editet customer";

    }





}
