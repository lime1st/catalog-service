package lime1st.bookshop.catalog_service.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

//  스프링 데이터 JPA 는 가변 객체를 사용하기 때문에 자바 레코드를 사용할 수 없다.
public record Book(
        @Id
        Long id,

        @NotBlank(message = "The book ISBN must be defined.")
                @Pattern(
                        regexp = "^([0-9]{10}|[0-9]{13})$",
                        message = "The ISBN format must be valid."
                )
        String isbn,

        @NotBlank(message = "The book title must be defined.")
        String title,

        @NotBlank(message = "The book author must be defined.")
        String author,

        @NotNull(message = "The book price must be defined.")
                @Positive(message = "The book price must be greater than zero.")
        Double price,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,

//        낙관적 잠금을 위해 사용되는 엔티티 버전 번호
        @Version
        int version
) {

    public static Book of(
            String isbn, String title, String author, Double price
    ) {
//        @Id 가 null 이고 @Version 이 0이면 새로운 엔티티로 인식한다.
        return new Book(null, isbn, title, author, price,null,null,0);
    }
}
