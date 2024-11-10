package lesson19;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = ArrayRepository.getData();
        System.out.println("before : " + Arrays.toString(arr));
        int[] array = ArrayUtils.mergeSort(arr);
        System.out.println("after : " + Arrays.toString(array));
        int target = random.nextInt(100);
        System.out.println("Binary Search: target "+ target);
        int index = ArrayUtils.binarySearch(array, target);
        System.out.println("index : " + index);

    }
}
