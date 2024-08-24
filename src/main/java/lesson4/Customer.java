package lesson4;

public class Customer {
    private String name;
    private String phone;

    public Customer(String name, String phone) {
        this.phone = phone;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
