package lesson16;

import java.util.Random;

public class RandomNumberGenerator {
    static  int generateRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
