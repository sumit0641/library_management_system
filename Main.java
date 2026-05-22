import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Initializing System ---");
        Library cityLibrary = new Library();

        // 1. Create Users
        Librarian adminAlice = new Librarian("L01", "Alice Smith");
        Patron patronBob = new Patron("P01", "Bob Jones");

        cityLibrary.registerUser(adminAlice);
        cityLibrary.registerUser(patronBob);

        // 2. Create Books
        Book book1 = new Book("978-0261102214", "The Hobbit", "J.R.R. Tolkien");
        Book book2 = new Book("978-0451524935", "1984", "George Orwell");

        System.out.println("\n--- Adding Books to Catalog ---");
        // Succeeds because Alice is a Librarian
        cityLibrary.addBook(book1, adminAlice);
        // Fails because Bob is a Patron
        cityLibrary.addBook(book2, patronBob);
        
        // Alice adds the second book
        cityLibrary.addBook(book2, adminAlice);

        System.out.println("\n--- Borrowing Process ---");
        List<Book> foundBooks = cityLibrary.searchBook("hobbit");
        
        if (!foundBooks.isEmpty()) {
            Book targetBook = foundBooks.get(0);
            
            // Bob borrows the book
            cityLibrary.borrowBook(patronBob, targetBook);
            
            // Bob attempts to borrow it again (should fail)
            cityLibrary.borrowBook(patronBob, targetBook);
        }

        System.out.println("Bob's borrowed books: ");
        for(Book b : patronBob.getBorrowedBooks()) {
            System.out.println("- " + b.getTitle());
        }

        System.out.println("\n--- Returning Process ---");
        cityLibrary.returnBook(patronBob, book1);
        
        System.out.println("Bob's borrowed books after return: " + patronBob.getBorrowedBooks().size());
    }
}