package lime1st.bookshop.catalog_service.demo;

import lime1st.bookshop.catalog_service.domain.Book;
import lime1st.bookshop.catalog_service.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        var book1 = new Book("1234567891", "너는 꿈을 꾸는가",
                "Lime1st", 9.90);
        var book2 = new Book("1234567892", "꿈을 쫓는자",
                "LovePapa", 12.90);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
