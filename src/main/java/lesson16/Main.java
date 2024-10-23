package lesson16;

import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        MathOperation mathOperation = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return (a + b);
            }
        };

        StringManipulator stringManipulator = s -> s.toUpperCase();

        Function<String, Integer> function = StringListProcessor::countUppercase;

        Supplier<Integer> randomIntegerSupplier = () -> RandomNumberGenerator.generateRandomNumber(1, 100);

        System.out.println("mathOperation -> " + mathOperation.operate(1, 2));
        System.out.println("String in upper case -> " + stringManipulator.changeRegister("hello"));
        System.out.println("Count Upper case letter -> "+function.apply("Java Pro Course"));
        System.out.println("Random int -> "+randomIntegerSupplier.get());
    }
}
