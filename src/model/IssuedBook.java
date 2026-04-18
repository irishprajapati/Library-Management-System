package model;
import java.time.LocalDateTime;

public class IssuedBook {
    private int id;
    private int bookId;
    private int memberId;
    private LocalDateTime issueDate;
    private LocalDateTime returnDate;

    public IssuedBook(int id, int bookId, int memberId, LocalDateTime issueDate, LocalDateTime returnDate) {
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

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }
}
