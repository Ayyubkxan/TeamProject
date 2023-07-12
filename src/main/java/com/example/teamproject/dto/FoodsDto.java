package com.example.teamproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodsDto {
   private String name;
   private String price;
   private Integer restaurant_id;
}
