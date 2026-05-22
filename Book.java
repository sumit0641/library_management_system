public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    // Getters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    // Behavior
    public void toggleAvailability() {
        this.isAvailable = !this.isAvailable;
    }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Borrowed";
        return "'" + title + "' by " + author + " [" + status + "]";
    }
}