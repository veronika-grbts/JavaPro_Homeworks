package lesson30;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAO implements GenericDao<Student, Long>{
    @Override
    public void save(Student entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
    }

    @Override
    public Student findById(Long aLong) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, aLong);
        }
    }

    @Override
    public List<Student> findByEmail(String email) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);
            Root<Student> personRoot = criteriaQuery.from(Student.class);
            criteriaQuery.select(personRoot).where(cb.equal(personRoot.get("email"), email));
            Query<Student> query = session.createQuery(criteriaQuery);
            List<Student> result = query.getResultList();
            transaction.commit();
            return result;
        }

    }

    @Override
    public List<Student> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> fromStudent = session.createQuery("from Student", Student.class);
            return fromStudent.list();
        }
    }

    @Override
    public Student update(Student entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return entity;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
                transaction.commit();
                return true;
            }
            transaction.rollback();
            return false;
        }
    }
}
