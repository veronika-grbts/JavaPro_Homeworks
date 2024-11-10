package lesson19;

import java.util.Random;

public class ArrayRepository {

    public static int[] getData(){
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            Random random = new Random();
            array[i] = random.nextInt(100);
        }
        return array;
    }
}
