import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private Patron patron;
    private Book book;
    private LocalDateTime issueDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;

    public Transaction(Patron patron, Book book) {
        this.patron = patron;
        this.book = book;
        this.issueDate = LocalDateTime.now();
        this.dueDate = this.issueDate.plusDays(14); // Due in 14 days
        this.returnDate = null;
    }

    public Book getBook() {
        return book;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public double closeTransaction() {
        this.returnDate = LocalDateTime.now();
        
        // Calculate late fine ($1.50 per day late)
        if (returnDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            return daysLate * 1.50;
        }
        return 0.0;
    }
}