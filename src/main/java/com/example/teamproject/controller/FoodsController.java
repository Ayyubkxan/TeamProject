package com.example.teamproject.controller;

import ch.qos.logback.core.model.INamedModel;
import com.example.teamproject.dto.FoodsDto;


import com.example.teamproject.entity.Foods;
import com.example.teamproject.entity.Restaurant;
import com.example.teamproject.repository.FoodsRepository;
import com.example.teamproject.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FoodsController {
    @Autowired
    FoodsRepository foodsRepository;

    @Autowired
    RestaurantRepository restaurantRepository;


    @RequestMapping(value = "/foods",method = RequestMethod.POST)
    public String addFoods(@RequestBody FoodsDto foodsDto){
        Foods foods = new Foods();
        foods.setName(foodsDto.getName());
        foods.setPrice(foodsDto.getPrice());
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(foodsDto.getRestaurant_id());
        if (optionalRestaurant.isPresent()){
            foods.setRestaurant(optionalRestaurant.get());
            foodsRepository.save(foods);
            return "Save foods";
        }else {
            return "Eror!";
        }
    }

    @RequestMapping(value = "/foods",method = RequestMethod.GET)
    public List<Foods> getAllFoods(){
        List<Foods> allbyRestaurantId = foodsRepository.findAll();
        return allbyRestaurantId;

    }
    @RequestMapping(value = "/foods/{restaurant_id}",method = RequestMethod.GET)
    public List<Foods> getFood(@PathVariable Integer restaurant_id){
        List<Foods> byRestaurantId = foodsRepository.findAllByRestaurantId(restaurant_id);
        return byRestaurantId;

    }

    @RequestMapping(value = "foods/{id}",method = RequestMethod.PUT)
    public String editFoods(@RequestBody FoodsDto foodsDto,@PathVariable Integer id){
        Optional<Foods> optionalFoods = foodsRepository.findById(id);
        if (optionalFoods.isPresent()){
            Foods foods = optionalFoods.get();
            foods.setName(foodsDto.getName());
            foods.setPrice(foodsDto.getPrice());
            foodsRepository.save(foods);
        }
        return "Edit Fooods";

    }

    @RequestMapping(value = "foods/{id}",method = RequestMethod.DELETE)
    public String deleteFoods(@RequestBody Foods foods, @PathVariable Integer id){
        foodsRepository.deleteById(id);
        return "delete";

    }



}
