package com.example.teamproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantAddressDTO {
    private String name;
    private String district;
    private String street;



}
