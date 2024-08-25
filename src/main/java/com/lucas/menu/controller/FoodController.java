package com.lucas.menu.controller;

import com.lucas.menu.food.Food;
import com.lucas.menu.food.FoodRepository;
import com.lucas.menu.food.FoodRequestDTO;
import com.lucas.menu.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll(){
        List<FoodResponseDTO> foodList = foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public FoodResponseDTO saveFood(@RequestBody FoodRequestDTO food) {
        Food foodData = new Food(food);
        foodRepository.save(foodData);
//        return "Saved - " + food.title() + " - " + food.price() + " - " + food.image();
        return new FoodResponseDTO(foodData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable int id) {
        foodRepository.deleteById((long) id);
        return "Deleted - " + id;
    }

}
