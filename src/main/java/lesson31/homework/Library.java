package lesson31.homework;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Library {

    public Book addBook (Book book) {

        if(book == null || !book.isValid()){
            throw new IllegalArgumentException("Invalid Book");
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        }
        return book;
    }

    public boolean removeBook(Long id) {
       try(Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction transaction = session.beginTransaction();
           Book book = session.get(Book.class, id);
           if (book != null) {
               session.delete(book);
               transaction.commit();
               return true;
           }
           transaction.rollback();
           return false;
       }
    }

    public List<Book> getBooks() {
      try(Session session = HibernateUtil.getSessionFactory().openSession()) {
          Query<Book> fromBook = session.createQuery("from Book ", Book.class);
          return fromBook.list();
      }
    }

    public int getBookCount() {
       try(Session session = HibernateUtil.getSessionFactory().openSession()) {
           Long count = session.createQuery("SELECT COUNT(b) FROM Book b", Long.class).uniqueResult();
           return count != null ? count.intValue() : 0;
       }
    }
}
