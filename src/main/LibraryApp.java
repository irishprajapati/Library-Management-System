package main;

import dao.BookDAO;
import dao.MemberDAO;
import dao.IssuedBookDAO;
import model.Book;
import model.Member;
import model.IssuedBook;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    static BookDAO bookDAO = new BookDAO();
    static MemberDAO memberDAO = new MemberDAO();
    static IssuedBookDAO issuedBookDAO = new IssuedBookDAO();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Available Books");
            System.out.println("6. View Issue Record By ID");
            System.out.println("7. Delete Member");
            System.out.println("8. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> addMember();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> viewAvailableBooks();
                case 6 -> viewIssueBookById();
                case 7 -> deleteMember();
                case 8 -> {
                    System.out.println("Exiting.");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static void addBook() {
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();

            System.out.print("Enter author name: ");
            String author = scanner.nextLine();

            System.out.print("Enter genre: ");
            String genre = scanner.nextLine();

            Book book = new Book(0, title, author, genre, true);
            bookDAO.addBook(book);

        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    static void addMember() {
        try {
            System.out.print("Enter member name: ");
            String name = scanner.nextLine();

            System.out.print("Enter member email: ");
            String email = scanner.nextLine();

            Member member = new Member(0, name, email, LocalDateTime.now());
            memberDAO.addMember(member);

        } catch (SQLException e) {
            System.out.println("Error adding member: " + e.getMessage());
        }
    }

    static void issueBook() {
        try {
            System.out.print("Enter book ID: ");
            int bookId = scanner.nextInt();

            System.out.print("Enter member ID: ");
            int memberId = scanner.nextInt();
            scanner.nextLine();

            IssuedBook issuedBook = new IssuedBook(
                    0,
                    bookId,
                    memberId,
                    LocalDateTime.now(),
                    null
            );

            issuedBookDAO.addIssueBook(issuedBook);
            bookDAO.updateAvailability(bookId, false);

        } catch (SQLException e) {
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }

    static void returnBook() {
        try {
            System.out.print("Enter issue record ID: ");
            int issueId = scanner.nextInt();
            scanner.nextLine();

            issuedBookDAO.returnBook(issueId , Timestamp.valueOf(LocalDateTime. now()));

        } catch (SQLException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }

    static void viewAvailableBooks() {
        try {
            List<Book> books = bookDAO.getAllAvailableBooks();

            if (books.isEmpty()) {
                System.out.println("No available books");
                return;
            }

            System.out.println("\n--- Available Books ---");
            for (Book book : books) {
                System.out.println("ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Available: " + book.isAvailable());
                System.out.println("------------ ----------");
            }

        } catch (Exception e) {
            System.out.println("Error fetching available books: " + e.getMessage());
        }
    }

    static void viewIssueBookById() {
        try {
            System.out.print("Enter issue record ID: ");
            int issueId = scanner.nextInt();
            scanner.nextLine();

            issuedBookDAO.getIssueBookById(issueId);

        } catch (SQLException e) {
            System.out.println("Error fetching issue record: " + e.getMessage());
        }
    }

    static void deleteMember() {
        try {
            System.out.print("Enter member ID: ");
            int memberId = scanner.nextInt();
            scanner.nextLine();

            memberDAO.deleteMember(memberId) ;

        } catch (SQLException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        }
    }
}
