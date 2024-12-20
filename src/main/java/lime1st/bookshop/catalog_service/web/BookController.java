package lime1st.bookshop.catalog_service.web;

import jakarta.validation.Valid;
import lime1st.bookshop.catalog_service.domain.Book;
import lime1st.bookshop.catalog_service.domain.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable("isbn") String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("isbn") String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    public Book put(@PathVariable("isbn") String isbn, @Valid @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }
}
