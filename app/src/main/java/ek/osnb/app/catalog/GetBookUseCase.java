package ek.osnb.app.catalog;

import java.util.List;

public interface GetBookUseCase {
    BookResponse getBookById(Long id);
    List<BookResponse> getBooks();
}
