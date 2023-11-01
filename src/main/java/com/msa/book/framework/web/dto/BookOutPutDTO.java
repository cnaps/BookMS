package com.msa.book.framework.web.dto;

import com.msa.book.domain.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookOutPutDTO {
    private long bookNo;
    private String bookTitle;
    private String bookStatus;

    public static BookOutPutDTO mapToDTO(Book book)
    {
        BookOutPutDTO bookOutPutDTO = new BookOutPutDTO();
        bookOutPutDTO.setBookNo(book.getNo());
        bookOutPutDTO.setBookTitle(book.getTitle());
        bookOutPutDTO.setBookStatus(book.getBookStatus().toString());
        return bookOutPutDTO;
    }
}