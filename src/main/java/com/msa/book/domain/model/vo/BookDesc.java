package com.msa.book.domain.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BookDesc {
    private String description;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private Source source;

    public static  BookDesc createBookDesc(String author,
                                           String isbn,
                                           String description,
                                           LocalDate publicationDate,
                                           Source source)
    {
        return new BookDesc(description,isbn,description,publicationDate,source);
    }

    public static  BookDesc sample(){
        return createBookDesc("마틴파울러",
                "12312312",
                "엔터프라이즈 아키텍처 패턴을 잘 설명해주는 도서",
                LocalDate.now(),
                Source.SUPPLY);
    }
}
