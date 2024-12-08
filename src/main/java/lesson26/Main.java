package lesson26;

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Dnipro", 47);

        User user = new User("Nika");
        user.setAddress(address);

        System.out.println("User: " + user.getName());
        System.out.println("Address: " + user.getAddress());


        // Перевірка1:
        if (user.getAddress() == address) {
            System.out.println("Перевірка: Адреса належить об'єкту класу User.");
        } else {
            System.out.println("Перевірка: Помилка! :(");
        }

        // Перевірка2:
        Address retrievedAddress = user.getAddress();
        if (retrievedAddress != null) {
            System.out.println("Перевірка: Клас User відповідає за доступ до адреси.");
            System.out.println("Отримана адреса: " + retrievedAddress);
        } else {
            System.out.println("Перевірка: Помилка! :(");
        }
    }
}
