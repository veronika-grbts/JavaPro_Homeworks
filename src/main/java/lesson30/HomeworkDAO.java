package lesson30;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HomeworkDAO {

    public void save(Homework entity) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
    }

}
