package com.ssafy.cadang.controller;

import com.ssafy.cadang.domain.Cafes;
import com.ssafy.cadang.domain.Drinks;
import com.ssafy.cadang.repository.CafeRepository;
import com.ssafy.cadang.repository.DrinkRepository;
import com.ssafy.cadang.service.CafeService;
import com.ssafy.cadang.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/drinks")
public class DrinkController {

    private final CafeService cafeService;
    private final DrinkService drinkService;
    @GetMapping("/cafe")
    public List<Cafes> cafeList(){
        return cafeService.cafeList();
    }

    @GetMapping("/drink")
    public List<Drinks> drinksList(@RequestParam Long cafeId){
        return drinkService.drinkList(cafeId);
    }
}