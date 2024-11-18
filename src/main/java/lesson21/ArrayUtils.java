package lesson21;

public class ArrayUtils {

    @Author(firstName = "Ivan", lastName = "Ivanov")
    public int findMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    @MethodInfo(nameMethod = "findMin", returnType = "int", description = "Finds the min  value in an array")
    public int findMin(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int num : array) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
}
