package com.github.cocomon3448.explosionbattle.utils;

import java.util.Random;

public class GenerateRandomNo {
    public static int randomRangeInt(int min, int max) {
        Random random = new Random() ;
        int randomNumber = random.nextInt(max) + min;
        return randomNumber;
    }
}
