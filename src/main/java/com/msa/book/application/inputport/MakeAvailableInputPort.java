package com.msa.book.application.inputport;

import com.msa.book.application.outputport.BookOutPutPort;
import com.msa.book.application.usecase.MakeAvailableUsecase;
import com.msa.book.domain.model.Book;
import com.msa.book.framework.web.dto.BookOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MakeAvailableInputPort implements MakeAvailableUsecase {

    private final BookOutPutPort bookOutPutPort;
    @Override
    public BookOutPutDTO available(Long bookNo) {
        Book loadBook = bookOutPutPort.loadBook(bookNo);
        loadBook.makeAvailable();
        return BookOutPutDTO.mapToDTO(loadBook);
    }
}
