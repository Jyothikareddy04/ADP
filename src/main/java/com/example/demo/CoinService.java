package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.*;
@Service
public class CoinService {

    ArrayList<Integer> validBills = new ArrayList<>(Arrays.asList(1,2,5,10,20,50,100));

    double[] coins = {0.25,0.10,0.05,0.01};
    int[] numCoins = {100,100,100,100};
    public Map<Double, Integer> fetchChange(int id) {
        Map<Double,Integer> hm = new HashMap<>();
        for(int i=0;i<coins.length;i++){
            int j = getNumCoins(i,id);
            hm.put(coins[i],j);
            double sum = coins[i]*j;
            if(id-sum == 0)
                return hm;
            id-=sum;
        }
        if(id ==  0){
            return hm;
        }else{
            for(Map.Entry<Double,Integer> d:hm.entrySet()){
                int index = getIndex(d.getKey());
                numCoins[index]+=d.getValue();
            }
            return  new HashMap<>();
        }
    }

    private int getIndex(Double key) {
        for(int i=0;i<coins.length;i++){
            if(coins[i] == key)
                return i;
        }
        return -1;
    }

    private int getNumCoins(int i, int id) {
        int div = (int) (id/coins[i]);
        if(div > numCoins[i]){
            int temp = numCoins[i] ;
            numCoins[i] = 0;
            return temp;
        }else{
            numCoins[i]-=div;
            return div;
        }
    }

}
