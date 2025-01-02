package lessons.lesson31;

import lesson31.homework.Book;
import lesson31.homework.HibernateUtil;
import lesson31.homework.Library;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibaryTest {

    private static Library library;
    private Book tempBook;

    @BeforeAll
    static void setup() {
        library = new Library();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String createTableSQL = " CREATE TABLE IF NOT EXISTS books ( " +
                    "id BIGSERIAL PRIMARY KEY, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "author VARCHAR(255) NOT NULL)";

            session.createNativeQuery(createTableSQL).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.err.println("error " + e.getMessage());
        }
    }

    @AfterAll
    public static void tearDown() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String dropTable = "DROP TABLE IF EXISTS books";
            session.createNativeQuery(dropTable).executeUpdate();
            transaction.commit();
        }
    }

    @Test
    @Order(1)
    public void testCreateUser() {
        Book testBook = new Book("Kobzar", "Shevchenko");
        Book saveBook = library.addBook(testBook);
        assertNotNull(saveBook.getId());
    }

    @Test
    @Order(2)
    public void testCreateUserException(){
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(new Book(null, ""));
        });
        assertEquals("Invalid Book", illegalArgumentException.getMessage());
    }

    @Test
    @Order(3)
    public void testRemoveBook() {
        Book newBook = library.addBook(new Book("Removable Book", "Author"));
        boolean isRemoved = library.removeBook(newBook.getId());
        assertTrue(isRemoved);
    }

    @Test
    @Order(4)
    public void testRemoveBookException() {
        boolean isRemoved = library.removeBook(9999L);
        assertFalse(isRemoved);
    }

    @Test
    @Order(5)
    public void testGetBooks() {
        Book book1 = library.addBook(new Book("Book 1", "Author 1"));
        Book book2 = library.addBook(new Book("Book 2", "Author 2"));

        List<Book> books = library.getBooks();
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
    }


    @Test
    @Order(5)
    public void testGetCountBooks() {
        int initialCount = library.getBookCount();
        library.addBook(new Book("Book 1", "Author 1"));
        library.addBook(new Book("Book 2", "Author 2"));

        assertEquals(initialCount + 2, library.getBookCount());
    }

}


