# Library Management System
A console-based Library Management System built in Java using JDBC and PostgreSQL — no frameworks, no ORM. Raw SQL, manual connection handling, and clean separation of concerns.

## Purpose
Built as a structured stepping stone toward Spring Boot. Every pattern here — DAO layer, model classes, connection management — maps directly to how Spring organizes things, just without the magic.

## Tech Stack
- Java 17+
- JDBC
- PostgreSQL
- No external libraries except the PostgreSQL driver

## Project Structure
LibrarySystem/
├── src/
│   ├── db/
│   │   └── DBConnection.java       → single getConnection() method
│   ├── model/
│   │   ├── Book.java
│   │   ├── Member.java
│   │   └── IssuedBook.java
│   ├── dao/
│   │   ├── BookDAO.java
│   │   ├── MemberDAO.java
│   │   └── IssuedBookDAO.java
│   └── main/
│       └── LibraryApp.java         → menu loop, entry point
└── lib/
└── postgresql-42.x.x.jar

## Features
- Add and delete books and members
- Issue a book to a member
- Return a book
- View all available books
- View all books currently issued to a specific member

## Database Schema
```sql
CREATE TABLE members (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    joined_date DATE NOT NULL
);

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    available BOOLEAN DEFAULT TRUE
);

CREATE TABLE issued_books (
    id SERIAL PRIMARY KEY,
    book_id INT REFERENCES books(id),
    member_id INT REFERENCES members(id),
    issue_date DATE NOT NULL,
    return_date DATE
);
```

## How to Run
**1. Clone the repo**
```bash
git clone https://github.com/yourusername/LibrarySystem.git
cd LibrarySystem
```

**2. Set up the database**
Create a PostgreSQL database and run the schema above.

**3. Configure the connection**
Update `DBConnection.java` with your credentials:
```java
private static final String URL = "jdbc:postgresql://localhost:5432/your_db";
private static final String USER = "your_user";
private static final String PASSWORD = "your_password";
```

**4. Compile**
```bash
javac -cp lib/postgresql-42.x.x.jar -d out src/**/*.java
```

**5. Run**
```bash
java -cp out:lib/postgresql-42.x.x.jar main.LibraryApp
```
Spring Boot — where `DBConnection` becomes `application.properties`, DAOs become `@Repository` interfaces, and SQL becomes optional.
