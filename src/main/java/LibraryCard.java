
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

/**
 * Library Card associated with the Student 
 */
public class LibraryCard {
    /**
     * Card id 
     */
    private int ID;

    /**
     * Issue Date of the Card
     */
    private LocalDate IssueDate;

    /**
     * Expiry Date of the Card
     */
    private LocalDate ExpiryDate;

    /**
     * Number of books borrowed
     */
    private List<Book> borrowed = new ArrayList<Book>();

    /**
     * Fine asscoaited with the card
     */
    private double fine;

    /**
     *  Details about the cardholder
     */
    private Student student;




    public LibraryCard(Student student, LocalDate IssueDate, LocalDate ExpiryDate, int ID) {
        this.student = student;
        this.IssueDate = IssueDate;
	    this.ExpiryDate = ExpiryDate;
	    this.ID = ID;
    }


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }


    public LocalDate getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(LocalDate IssueDate) {
        this.IssueDate = IssueDate;
    }

    public LocalDate getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(LocalDate ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    
    public List<Book> getBooks() {
        return borrowed;
    }

    

    /**
     * Issue a new book
     * @param Book: book to borrow 
     * @return true if the book is successfully borrowed, false otherwise
     */

    public boolean issueBook(Book book) throws IllegalBookIssueException{

        try {

        // check if the number of books borrowed is greater than 4
        if (borrowed.size() > 4) {
            return false;
        }

        // check if book has already been issued to library card
        if (borrowed.contains(book)) {
            throw new IllegalBookIssueException("same book is already issued on the library card");
        }

        // check if library card is valid (not expired)
        LocalDate currentDate = LocalDate.now();
        if (ExpiryDate.isBefore(currentDate)) {
            return false;
        }

        // check if book is available to borrow
        if (book.getStatus() == false) {
            return false;
        }

        // check if library card has fine associated with it 
        if (fine > 0) {
            return false;
        }

        // issue book to library card if above conditions have not already returned false
        book.setStatus(false);

        if (book.getDemand() == 0) {
            book.setDays(15);
        }
        else if (book.getDemand() == 1) {
            book.setDays(3);
        }

        borrowed.add(book);

        // validate if the book has been issued to the card correctly before returning true
        if ((!(borrowed.contains(book))) && book.getStatus() == true) {
            return false;
        }

        return true;

        } catch (IllegalBookIssueException e) {
            return false;
    }




}
}