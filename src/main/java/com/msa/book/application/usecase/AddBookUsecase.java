package com.msa.book.application.usecase;

import com.msa.book.framework.web.dto.BookInfoDTO;
import com.msa.book.framework.web.dto.BookOutPutDTO;

public interface AddBookUsecase {
    public BookOutPutDTO addBook(BookInfoDTO bookInfoDTO);
}
