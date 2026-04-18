package dao;

import db.DBConnection;
import model.IssuedBook;

import java.lang.reflect.Type;
import java.sql.*;
import java.sql.SQLException;

public class IssuedBookDAO {
    public void addIssueBook(IssuedBook issuedBook) throws SQLException {
        String sql = "INSERT INTO issue_book(book_id, member_id, issue_date, return_date) VALUES(?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, issuedBook.getBookId());
            ps.setInt(2, issuedBook.getMemberId());
            ps.setTimestamp(3, Timestamp.valueOf(issuedBook.getIssueDate()));
            if (issuedBook.getReturnDate() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(issuedBook.getReturnDate()));
            } else {
                ps.setNull(4, Types.TIMESTAMP);
            }
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Issue record added successfully");
            }
        } catch (SQLException e) {
            System.out.println("Error adding issue record: " + e.getMessage());
        }
    }

    //fetch issue details by ID
    public void getIssueBookById(int issueId) throws SQLException {
        String sql = "SELECT * FROM issue_book WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, issueId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Issue Book details:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Book ID: " + rs.getInt("book_id"));
                System.out.println("Member ID: " + rs.getInt("member_id"));
                System.out.println("Issue Date: " + rs.getTimestamp("issue_date"));
                System.out.println("Return Date: " + rs.getTimestamp("return_date"));
            } else {
                System.out.println("No issue record found with ID: " + issueId);
            }

        }
    }

    public void returnBook(int issueId, Timestamp returnDate) throws SQLException {
        String sql = "UPDATE issue_book SET return_Date = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, returnDate);
            ps.setInt(2, issueId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book returned successfully");
            } else {
                System.out.println("No issue record found with ID: " + issueId);
            }
        }
    }

    //delete issue record
    public void deleteIssueBook(int issueId) throws SQLException {
        String sql = "DELETE FROM issue_book WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, issueId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Issue record with ID " + issueId + " deleted successfully.");
            } else {
                System.out.println("No issue record found with ID " + issueId);
            }
        }
    }
}
