package com.example.teamproject.controller;

import com.example.teamproject.entity.Address;
import com.example.teamproject.repository.AddressRepository;
import com.example.teamproject.repository.RestaurantRepository;
import com.example.teamproject.dto.RestaurantAddressDTO;
import com.example.teamproject.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    AddressRepository addressRepository;


    @RequestMapping(value = "/restaurant",method = RequestMethod.GET)
    public List<Restaurant> getRestaurant(){
        return restaurantRepository.findAll();



    }


    @RequestMapping(value = "/restaurant",method = RequestMethod.POST)
    public String addRestaurant(@RequestBody RestaurantAddressDTO restaurantAddressDTO){
        boolean byName = restaurantRepository.existsByName(restaurantAddressDTO.getName());
        if (!byName){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantAddressDTO.getName());
        Address address = new Address();
        address.setDistrict(restaurantAddressDTO.getDistrict());
        address.setStreet(restaurantAddressDTO.getStreet());
        addressRepository.save(address);
        restaurant.setAddress(address);
        restaurantRepository.save(restaurant);
        return "Restoran qoshildi!!";
    }else {
            return "Bunday nomli Restoran avval qoshilgan";
        }
    }



    @RequestMapping(value = "/restaurant/{id}",method = RequestMethod.POST)
    public String editRestaurant(@RequestBody RestaurantAddressDTO restaurantAddressDTO, @PathVariable Integer id){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()){
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.setName(restaurantAddressDTO.getName());
            Address address = new Address();
            address.setDistrict(restaurantAddressDTO.getDistrict());
            address.setStreet(restaurantAddressDTO.getStreet());
            addressRepository.save(address);
            restaurant.setAddress(address);
            restaurantRepository.save(restaurant);
        }


        return "Edited Restaurant";
    }




}
