
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Date IssueDate;

    /**
     * Expiry Date of the Card
     */
    private Date ExpiryDate;

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




    public LibraryCard(Student student, Date IssueDate, Date ExpiryDate, int ID) {
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


    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
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
        // Store the list of books borrowed into the variable borrowed
        List<Book> borrowed = getBooks();

        // check if the number of books borrowed is greater than 4
        if (borrowed.size() > 4) {
            return false;
        }

        if (borrowed.contains(book)) {
            throw new IllegalBookIssueException("same book is already issued on the library card");
        }

        Date currDate = new Date();
        if (ExpiryDate.before(currDate)) {
            return false;
        }

        if (book.getStatus() == false) {
            return false;
        }

        if (fine > 0) {
            return false;
        }

        borrowed.add(book);
        book.setStatus(false);

        if (book.getDemand() == 0) {
            book.setDays(15);
        }
        else if (book.getDemand() == 1) {
            book.setDays(3);
        }

        return true;

        } catch (IllegalBookIssueException e) {
            return false;
    }




}
}