package com.msa.book.application.usecase;

import com.msa.book.framework.web.dto.BookOutPutDTO;

public interface InquiryUsecase {
    public BookOutPutDTO getBookInfo(long bookNo);
}
