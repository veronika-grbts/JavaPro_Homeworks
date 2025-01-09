package lesson32;

import jakarta.persistence.*;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int idOder;

    @Column(name = "customer_name")
    private String customerName;

    public Order() {
    }

    public Order(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getIdOder() {
        return idOder;
    }

    public void setIdOder(int idOder) {
        this.idOder = idOder;
    }

    public boolean isValid(){
        return customerName != null && !customerName.isEmpty();
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOder=" + idOder +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
