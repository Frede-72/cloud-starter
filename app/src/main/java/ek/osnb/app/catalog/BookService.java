package ek.osnb.app.catalog;


import ek.osnb.app.shared.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
class BookService implements GetBookUseCase, CreateBookUseCase {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        return new BookResponse(book.getId(), book.getTitle(), book.getIsbn().getValue());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getBooks() {
        var books =  bookRepository.findAll();
        List<BookResponse> response = new ArrayList<>();
        for (var book : books) {
            response.add(new BookResponse(book.getId(), book.getTitle(), book.getIsbn().getValue()));
        }
        return response;
    }


    @Override
    @Transactional
    public BookResponse createBook(CreateBookRequest request) {
        var book = Book.create(request.title(), request.isbn());
        bookRepository.save(book);
        return new BookResponse(book.getId(), book.getTitle(), book.getIsbn().getValue());
    }
}
