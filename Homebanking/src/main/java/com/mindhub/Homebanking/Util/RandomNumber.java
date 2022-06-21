package com.mindhub.Homebanking.Util;

public class RandomNumber {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
