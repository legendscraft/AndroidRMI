package com.example.mysecondapp;

/**
 * Created by asif on 2/4/18.
 */

public interface RMIInterface {

    public String addFruit(Integer fruitID, Integer fruitPrice);

    public String getAllFruits();

    public String getAllFruitsWithoutPrice();

    public String getAllFruitsWithPrice();
}