package lesson21;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        ArrayUtils arrayUtils = new ArrayUtils();

        int[] array = {13, 1, 41, 21, 35, 99};

        int max = arrayUtils.findMax(array);
        int min = arrayUtils.findMin(array);

        System.out.println("Max: " + max);
        System.out.println("Min: " + min);

        Method[] methods = ArrayUtils.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MethodInfo.class)) {
                MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                System.out.println("Method: " + methodInfo.nameMethod());
                System.out.println("Return Type: " + methodInfo.returnType());
                System.out.println("Description: " + methodInfo.description());
            }

            if (method.isAnnotationPresent(Author.class)) {
                Author author = method.getAnnotation(Author.class);
                System.out.println("Author: " + author.firstName() + " " + author.lastName());
            }
            System.out.println();
        }
    }
}
