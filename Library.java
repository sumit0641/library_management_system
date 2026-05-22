import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;
    private List<Transaction> transactions;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("Registered user: " + user.getName());
    }

    public void addBook(Book book, User user) {
        // OOP Type Checking for Authorization
        if (user instanceof Librarian) {
            books.add(book);
            System.out.println("Librarian " + user.getName() + " added book: " + book.getTitle());
        } else {
            System.out.println("Access Denied: " + user.getName() + " does not have librarian privileges.");
        }
    }

    public List<Book> searchBook(String query) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) 
                || book.getIsbn().equals(query)) {
                results.add(book);
            }
        }
        return results;
    }

    public void borrowBook(Patron patron, Book book) {
        if (book.isAvailable()) {
            book.toggleAvailability();
            patron.addBook(book);
            Transaction newTransaction = new Transaction(patron, book);
            transactions.add(newTransaction);
            System.out.println("Success: " + patron.getName() + " borrowed '" + book.getTitle() + "'.");
        } else {
            System.out.println("Sorry, '" + book.getTitle() + "' is currently unavailable.");
        }
    }

    public void returnBook(Patron patron, Book book) {
        for (Transaction transaction : transactions) {
            // Find the active transaction
            if (transaction.getBook().equals(book) && transaction.getReturnDate() == null) {
                book.toggleAvailability();
                patron.removeBook(book);
                double fine = transaction.closeTransaction();

                System.out.println("Success: " + patron.getName() + " returned '" + book.getTitle() + "'.");
                if (fine > 0) {
                    System.out.printf("Late return! Fine assessed: $%.2f\n", fine);
                }
                return;
            }
        }
        System.out.println("Error: Active transaction not found for this book and user.");
    }
}