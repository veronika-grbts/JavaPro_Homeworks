package lesson19;

public class ArrayUtils {

    public static int[] mergeSort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        mergeSort1(arr, low, high);
        return arr;
    }

    public static void mergeSort1(int[] arr, int low, int hight) {

        if (low < hight) {
            int middle = (low + hight) / 2;

            mergeSort1(arr, low, middle);
            mergeSort1(arr, middle + 1, hight);
            mergeSort2(arr, low, hight, middle);
        }

    }


    private static void mergeSort2(int[] subArr, int low, int hight, int middle) {
        int n = hight - low + 1;

        int[] temp = new int[n];

        int i = low;
        int j = middle + 1;
        int k = 0;

        while (i <= middle || j <= hight) {
            if (i > middle) {
                temp[k++] = subArr[j++];
            } else if (j > hight) {
                temp[k++] = subArr[i++];
            } else if (subArr[i] < subArr[j]) {
                temp[k++] = subArr[i++];
            } else {
                temp[k++] = subArr[j++];
            }
        }
        for (j = 0; j < n; j++) {
            subArr[low + j] = temp[j];
        }
    }


    public static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if(array[middle] == target) {
                return middle;
            }else if(array[middle] > target) {
                high = middle - 1;
            }else {
                low = middle + 1;
            }
        }
        return -1;
    }
}


