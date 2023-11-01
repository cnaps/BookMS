package com.msa.book.framework.jpaadapter;

import com.msa.book.application.outputport.BookOutPutPort;
import com.msa.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookAdapter implements BookOutPutPort {

    private final BookRepository bookRepository;
    @Override
    public Book loadBook(long bookNo) {
        return bookRepository.findById(bookNo).get();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
