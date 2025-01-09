package lesson32;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CoffeeOrderBoard {

    private static final Logger LOGGER = Logger.getLogger(CoffeeOrderBoard.class.getName());

    public int add(Order order) {
        if(order == null || !order.isValid()){
            LOGGER.warn("Invalid order attempted to add: " + order);
            throw new IllegalArgumentException("Invalid order");
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            LOGGER.info("Order added successfully");
        }
        return order.getIdOder();
    }

    public Order deliver(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Order> orderList = session.createQuery("FROM Order ORDER BY idOder ASC", Order.class).getResultList();

            if (orderList.isEmpty()) {
                System.out.println("No orders to deliver.");
                LOGGER.warn("No orders available for delivery.");
                return null;
            }

            Order order = orderList.get(0);
            session.delete(order);
            transaction.commit();
            LOGGER.info("Order delivered successfully: " + order);
            return order;
        }
    }

    public Order deliver(int idOrder){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Order order = session.get(Order.class, idOrder);
            if (order == null) {
                System.out.println("Order with number " + idOrder + " not found.");
                LOGGER.warn("Order not found with ID : " + idOrder);
                return null;
            }
            session.delete(order);
            transaction.commit();
            LOGGER.info("Order delivered successfully  with ID : " + idOrder);
            return order;
        }
    }

    public void draw(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Order> orders = session.createQuery("FROM Order ORDER BY idOder ASC", Order.class).getResultList();
            LOGGER.debug("Fetched orders from the database: " + orders);
            System.out.println("Current order queue:");
            System.out.println("--------------------");
            for (Order order : orders) {
                System.out.println("#" + order.getIdOder() + " | " + order.getCustomerName());
            }
            System.out.println("--------------------");
        }
    }
}
