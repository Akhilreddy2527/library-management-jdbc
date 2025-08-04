# Library Management System (Java + JDBC + MySQL)

A beginner-friendly **Java console application** to manage books using **JDBC** and **MySQL**. Built as a CRUD backend project to learn how Java applications interact with databases.

---

## Features

-  Add new books to MySQL
-  View all stored books
-  Search books by title
-  Remove books by ISBN
-  Data persists in a MySQL table `books`

---

## Tech Stack

| Technology     | Usage                         |
|----------------|-------------------------------|
| Java           | Core backend logic            |
| JDBC           | Database connectivity         |
| MySQL          | Data storage (local DB)       |
| IntelliJ IDEA  | Development Environment       |
| Git & GitHub   | Version control & hosting     |

---

## Database Setup

1. Install MySQL & create a database:

```sql
CREATE DATABASE library_db;
USE library_db;

CREATE TABLE books (
  title VARCHAR(255),
  author VARCHAR(255),
  isbn VARCHAR(100) PRIMARY KEY
);

2.How to Run
    Clone this repo or download ZIP
    Open in IntelliJ
    Add JDBC driver (MySQL connector) to the project
    Run Main.java
    Use the menu to add, search, view, or delete books
    
3.What I Learned
    Java object-oriented programming
    Connecting Java apps to MySQL using JDBC
    Writing clean backend logic
    Using Git & GitHub effectively

4.Future Improvements
    Add login system for users/admins
    GUI version using JavaFX or Swing
    Export book list as CSV/PDF