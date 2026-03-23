package ek.osnb.app.catalog;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Embedded
    private ISBN isbn;

    protected Book() {
    }

    private Book(Long id, String title, String isbn) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }

        ISBN isbnNormalized = ISBN.of(isbn);

        this.title = title;
        this.isbn = isbnNormalized;
    }

    static Book create(String title, String isbn) {
        return new Book(null, title, isbn);
    }

    static Book create(Long id, String title, String isbn) {
        return new Book(id, title, isbn);
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    ISBN getIsbn() {
        return isbn;
    }
}