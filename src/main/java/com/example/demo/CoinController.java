package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CoinController {
    @Autowired
    CoinService coinService;

    @GetMapping("/coin/{id}")
    public ResponseEntity<java.lang.Object> getChange(@PathVariable int id){
        if(coinService.validBills.contains(id)){
            System.out.println(id);
            Map<Double,Integer> coins = new HashMap<>();
            coins = this.coinService.fetchChange(id);
            System.out.println(coins);
            if(coins.size()==0)
            {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No Enough coins");
            }
            else{
                return ResponseEntity.ok("Number of coins for "+id+" :"+coins.toString());
            }


        }else{
            System.out.println(new ResponseEntity(HttpStatus.BAD_REQUEST));
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Bill");
        }
    }

}
