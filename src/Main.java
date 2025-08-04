import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        String filename="books.txt";
        library.loadFromFile(filename);

        int choice;

        System.out.println("📚 Welcome to the Library Management System!");

        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Exit");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    Book book = new Book(title, author, isbn);
                    library.addBook(book);
                    break;
                case 2:
                    library.viewBooks();
                    break;
                case 3:
                    System.out.print("Enter title to search: ");
                    String searchTitle = sc.nextLine();
                    library.searchBook(searchTitle);
                    break;
                case 4:
                    System.out.print("Enter ISBN to remove: ");
                    String removeIsbn = sc.nextLine();
                    library.removeBook(removeIsbn);
                    break;
                case 5:
                    library.saveToFile(filename);
                    System.out.println("👋 Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("❗ Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
