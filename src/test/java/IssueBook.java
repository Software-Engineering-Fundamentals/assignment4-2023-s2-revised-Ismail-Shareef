
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Date;
import java.time.LocalDate;

/**
 *  Implement and test {Programme.addStudent } that respects the considtion given the assignment specification
 * NOTE: You are expected to verify that the constraints to borrow a new book from a library
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with "setting" method.
 */
public class IssueBook {
	
	private static Student student;
    private static LibraryCard libraryCard;
    private static Book book;

    @BeforeAll
    static void initialise() {
        student = new Student(null, 0);
        libraryCard = new LibraryCard(student, null, null, 0);
        book = new Book(0, null, 0);
    }

    /*
     * This test unit tests if whether the issueBook() method correctly returns false
     * when the library card already has more than 4 books issued to it
     */
    @Test
    void false_IfBorrowedBooksIsMoreThanFour() throws IllegalBookIssueException {
        LocalDate expiryDate = LocalDate.of(2024, 1, 1);
        libraryCard.setExpiryDate(expiryDate);
        book.setStatus(true);

        Book book1 = new Book(0, null, 0);
        Book book2 = new Book(0, null, 0);
        Book book3 = new Book(0, null, 0);
        Book book4 = new Book(0, null, 0);
        Book book5 = new Book(0, null, 0);

        libraryCard.issueBook(book1);
        libraryCard.issueBook(book2);
        libraryCard.issueBook(book3);
        libraryCard.issueBook(book4);
        libraryCard.issueBook(book5);

        Book input = book;
        boolean expected = false;

        assertEquals(expected, libraryCard.issueBook(input), "Book not issued as expected");

    }

    /*
     * This test unit tests if whether the issueBook() method correctly returns false
     * when the book is already published to the library card
     */
    @Test
    void false_IfBookIsAlreadyIssuedOnLibraryCard() throws IllegalBookIssueException {
        LocalDate expiryDate = LocalDate.of(2024, 1, 1);
        libraryCard.setExpiryDate(expiryDate);
        book.setStatus(true);

        libraryCard.issueBook(book);

        Book input = book;
        boolean expected = false;

        assertEquals(expected, libraryCard.issueBook(input), "Book not issued as expected");

    }

    /*
     * This test unit tests if whether the issueBook() method correctly returns false
     * when the library card is expired
     */
    @Test
    void false_IfLibraryCardIsExpired() throws IllegalBookIssueException {
        LocalDate expiryDate = LocalDate.of(2022, 1, 1);
        libraryCard.setExpiryDate(expiryDate);
        book.setStatus(true);

        Book input = book;
        boolean expected = false;

        assertEquals(expected, libraryCard.issueBook(input), "Book not issued as expected");

    }

    /*
     * This test unit tests if whether the issueBook() method correctly returns false
     * when the book to be issued is unavailable
     */
    @Test
    void false_IfBookUnavailable() throws IllegalBookIssueException {
        LocalDate expiryDate = LocalDate.of(2024, 1, 1);
        libraryCard.setExpiryDate(expiryDate);
        book.setStatus(false);

        Book input = book;
        boolean expected = false;

        assertEquals(expected, libraryCard.issueBook(input), "Book not issued as expected");

    }

    /*
     * This test unit tests if whether the issueBook() method correctly returns false
     * when the library card has a fine associated with the library card
     */
    @Test
    void false_IfLibraryCardHasFine() throws IllegalBookIssueException {
        LocalDate expiryDate = LocalDate.of(2024, 1, 1);
        libraryCard.setExpiryDate(expiryDate);
        libraryCard.setFine(1);
        book.setStatus(true);

        Book input = book;
        boolean expected = false;

        assertEquals(expected, libraryCard.issueBook(input), "Book not issued as expected");

    }

    /*
     * This test unit tests if whether the issueBook() method correctly returns true
     * when all conditions are met
     */
    @Test
    void true_IfAllConditionsAreMet() throws IllegalBookIssueException {
        LocalDate expiryDate = LocalDate.of(2024, 1, 1);
        libraryCard.setExpiryDate(expiryDate);
        libraryCard.setFine(0);
        book.setStatus(true);

        Book input = book;
        boolean expected = true;

        assertEquals(expected, libraryCard.issueBook(input), "Book issued as expected");

    }

}
