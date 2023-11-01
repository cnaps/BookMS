package com.msa.book.application.inputport;

import com.msa.book.application.outputport.BookOutPutPort;
import com.msa.book.application.usecase.InquiryUsecase;
import com.msa.book.domain.model.Book;
import com.msa.book.framework.web.dto.BookOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class InquiryInputPort implements InquiryUsecase {

    private final BookOutPutPort bookOutPort;
    @Override
    public BookOutPutDTO getBookInfo(long bookNo) {
        Book loadBook = bookOutPort.loadBook(bookNo);
        return BookOutPutDTO.mapToDTO(loadBook);
    }
}
