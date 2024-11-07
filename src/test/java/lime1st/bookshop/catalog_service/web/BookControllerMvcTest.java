package lime1st.bookshop.catalog_service.web;

import lime1st.bookshop.catalog_service.domain.BookNotFoundException;
import lime1st.bookshop.catalog_service.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 스프링 MVC 컴포넌트에 중점을 두고, 명시적으로는 BookController 클래스를
 * 타깃으로 하는 테스트 클래스임을 나타내고 있다.
* */
@WebMvcTest(BookController.class)
class BookControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

//    스프링 애플리케이션 콘텍스트에 BookService 의 모의 객체를 추가한다.
    @MockBean
    private BookService bookService;

    @Test
    void 요청한_책이_없으면_404() throws Exception{
        String isbn = "73737313940";

//        모의 빈이 어떻게 작동할 것인지 지정한다.
        given(bookService.viewBookDetails(isbn))
                .willThrow(BookNotFoundException.class);

//        MockMVC 는 HTTP GET 요청을 수행하고 결과를 확인하기 위해 사용한다.
//        응답이 "404 발견되지 않음" 상태를 가질 것으로 예상한다.
        mockMvc.perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());
    }
}