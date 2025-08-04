import java.util.ArrayList;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Library {
    private final String url = "jdbc:mysql://localhost:3306/library_db";
    private final String username = "root";
    private final String password = " ";
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book){
            String query = "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, book.getTitle());
                stmt.setString(2, book.getAuthor());
                stmt.setString(3, book.getIsbn());

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("✅ Book added successfully to MySQL.");
                }

            } catch (SQLException e) {
                System.out.println("❌ Failed to add book.");
                e.printStackTrace();
            }
        }

    public void viewBooks() {
        String query = "SELECT * FROM books";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet result = stmt.executeQuery()) {

            boolean hasBooks = false;

            System.out.println("\n📚 Books in the library:");

            while (result.next()) {
                hasBooks = true;

                String title = result.getString("title");
                String author = result.getString("author");
                String isbn = result.getString("isbn");

                System.out.println("📘 " + title + " by " + author + " (ISBN: " + isbn + ")");
            }

            if (!hasBooks) {
                System.out.println("❌ No books in the library.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error while retrieving books.");
            e.printStackTrace();
        }
    }

    public void searchBook(String title) {
        String query = "SELECT * FROM books WHERE title LIKE ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + title + "%"); // partial match, case-insensitive if DB collation allows

            ResultSet result = stmt.executeQuery();
            boolean found = false;

            while (result.next()) {
                found = true;
                String bookTitle = result.getString("title");
                String author = result.getString("author");
                String isbn = result.getString("isbn");
                System.out.println("🔍 Found: " + bookTitle + " by " + author + " (ISBN: " + isbn + ")");
            }

            if (!found) {
                System.out.println("❌ Book not found.");
            }
        }
        catch (SQLException e) {
            System.out.println("❌ Error while searching book.");
            e.printStackTrace();
        }
    }

    public void removeBook(String isbn) {
        String query = "DELETE FROM books WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, isbn);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Book removed.");
            } else {
                System.out.println("❌ Book with that ISBN not found.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error while removing book.");
            e.printStackTrace();
        }
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Book book : books) {
                writer.println(book.getTitle() + ",,1" + book.getAuthor() + "," + book.getIsbn());
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving books: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        books.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Book book = new Book(parts[0], parts[1], parts[2]);
                    books.add(book);
                }
            }
        } catch (IOException e) {
            System.out.println("📁 No saved books found or error reading file.");
        }
    }
}
