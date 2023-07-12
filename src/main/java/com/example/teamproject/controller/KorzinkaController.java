package com.example.teamproject.controller;

import com.example.teamproject.dto.KorzinkaFoodDTO;
import com.example.teamproject.entity.Foods;
import com.example.teamproject.entity.Korzinka;
import com.example.teamproject.repository.FoodsRepository;
import com.example.teamproject.repository.KorzinkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class KorzinkaController {
    @Autowired
    KorzinkaRepository korzinkaRepository;

    @Autowired
    FoodsRepository foodsRepository;

    @RequestMapping(value = "/korzinka",method = RequestMethod.POST)
    public String addKorzinka(@RequestBody KorzinkaFoodDTO korzinkaFoodDTO) {
        Korzinka korzinka = new Korzinka();
        korzinka.setAllPrice(korzinkaFoodDTO.getAllPrice());
        Optional<Foods> optionalFoods = foodsRepository.findById(korzinkaFoodDTO.getFoods_id());
        if (optionalFoods.isPresent()){
            korzinka.setFoods(optionalFoods.get());
            korzinkaRepository.save(korzinka);
            return "Korzinka saqlandi";

        }else {
            return "Eror!";
        }

            }
    @RequestMapping(value = "/korzinka",method = RequestMethod.GET)
    public List<Korzinka> getAllKorzinka(){
        return korzinkaRepository.findAll();
    }

    @RequestMapping(value = "/korzinka/{food_id}",method = RequestMethod.GET)
    public List<Korzinka> getKorzinka(@PathVariable Integer foods_id){
         List<Korzinka> allByFoodId = korzinkaRepository.findAllByFoodsId(foods_id);

        return allByFoodId;
    }

    @RequestMapping(value = "/korzinka/{id}",method = RequestMethod.DELETE)
    public String deleteKorzinka(@RequestBody Korzinka korzinka,@PathVariable Integer id){
        korzinkaRepository.deleteById(id);
        return "Delete korzinka";
    }

    @RequestMapping(value = "/korzinka/{id}", method = RequestMethod.PUT)
    public String editKorzinka(@RequestBody KorzinkaFoodDTO korzinkaFoodDTO,@PathVariable Integer id){
        Optional<Korzinka> optionalKorzinka = korzinkaRepository.findById(id);
        if (optionalKorzinka.isPresent()){
            Korzinka korzinka = optionalKorzinka.get();
            korzinka.setAllPrice(korzinkaFoodDTO.getAllPrice());
            korzinkaRepository.save(korzinka);

        }
        return "Edited korinka";

    }











}
