package ek.osnb.app.catalog.web;

import ek.osnb.app.catalog.BookResponse;
import ek.osnb.app.catalog.CreateBookRequest;
import ek.osnb.app.catalog.CreateBookUseCase;
import ek.osnb.app.catalog.GetBookUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin("http://localhost:5500")
class BookController {
    private final GetBookUseCase getBookUseCase;
    private final CreateBookUseCase createBookUseCase;

    public BookController(GetBookUseCase getBookUseCase, CreateBookUseCase createBookUseCase) {
        this.getBookUseCase = getBookUseCase;
        this.createBookUseCase = createBookUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        var book = getBookUseCase.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        var book = getBookUseCase.getBooks();
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest request) {
        var book = createBookUseCase.createBook(request);
        return ResponseEntity.created(URI.create("/books/" + book.id())).body(book);
    }
}
