package model;
import java.time.LocalDate;

public class IssuedBook {
    private int id;
    private int bookId;
    private int memberId;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssuedBook(int id, int bookId, int memberId, LocalDate issueDate, LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}
