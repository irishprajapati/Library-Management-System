package dao;
import db.DBConnection;
import model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books(title, author, genre, available) VALUES(?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getGenre());
            ps.setBoolean(4, book.isAvailable());   // ← index added
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Book added successfully");
            }
        } catch (SQLException e) {                  // ← moved outside
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public List<Book> getAllAvailableBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE available=true";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getBoolean("available")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }
        return books;
    }

    public void updateAvailability(int bookId, boolean available) {
        String sql = "UPDATE books SET available = ? where id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, available);
            ps.setInt(2, bookId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Book availability updated successfully");
            } else {
                System.out.println("Book not found");
            }
        } catch (SQLException e) {
            System.out.println("Error updating availability: " + e.getMessage());
        }
    }

    public void deleteBook(int bookId) throws SQLException {
        String sql = "DELETE FROM books where id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Book deleted succesfully");
            } else {
                System.out.println("Book not found");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }
}