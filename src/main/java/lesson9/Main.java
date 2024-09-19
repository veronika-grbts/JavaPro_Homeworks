package lesson9;

public class Main {
    public static void main(String[] args) {

        DataProvider provider = new DataProvider();
        DataHandler dataHandler = new DataHandler();

        // Обробка масиву даних найменувань товарів
        String namesOutput = dataHandler.handleData(provider.getProductNames());
        getOutput("Products: " + namesOutput);

        // Обробка масиву даних про суми продажів
        String salesOutput = dataHandler.handleData(provider.getSalesAmounts());
        getOutput("Sales, EUR: " + salesOutput);
    }

    // Виведення результату роботи програми
    private static void getOutput(String output) {
        System.out.println(output);
    }
}
