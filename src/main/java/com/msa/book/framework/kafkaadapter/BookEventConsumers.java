package com.msa.book.framework.kafkaadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.book.application.usecase.MakeAvailableUsecase;
import com.msa.book.application.usecase.MakeUnAvailableUsecase;
import com.msa.book.domain.model.event.EventResult;
import com.msa.book.domain.model.event.EventType;
import com.msa.book.domain.model.event.ItemRented;
import com.msa.book.domain.model.event.ItemReturned;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BookEventConsumers {

    //private final Logger log = LoggerFactory.getLogger(BookEventConsumers.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MakeAvailableUsecase makeAvailableUsecase;
    private final MakeUnAvailableUsecase makeUnavailable;
    private final BookEventProducer eventProducer;

    @KafkaListener(topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}")
    public void consumeRental(ConsumerRecord<String, String> record) throws IOException {
        System.out.printf("rental_rent:" + record.value());
        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);

        EventResult eventResult = new EventResult();
        eventResult.setEventType(EventType.RENT);
        eventResult.setIdName(itemRented.getIdName());
        eventResult.setItem(itemRented.getItem());
        eventResult.setPoint(itemRented.getPoint());

        try {
            System.out.println("전송받은 값 :" + record.value());
            makeUnavailable.unavailable(Long.valueOf(itemRented.getItem().getNo()));
            eventResult.setSuccessed(true);
        }
        catch (Exception e)
        {
            System.out.println("도서 상태가 논리적으로 맞지 않음");
            eventResult.setSuccessed(false);
        }

        eventProducer.occurEvent(eventResult);
    }

    @KafkaListener(topics = "${consumer.topic2.name}", groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> record) throws IOException {
        ItemReturned itemReturned = objectMapper.readValue(record.value(), ItemReturned.class);

        EventResult eventResult = new EventResult();
        eventResult.setEventType(EventType.RETURN);
        eventResult.setIdName(itemReturned.getIdName());
        eventResult.setItem(itemReturned.getItem());
        eventResult.setPoint(itemReturned.getPoint());

        try{
            System.out.printf("전송받은 값 :"  +record.value());
            makeAvailableUsecase.available(Long.valueOf(itemReturned.getItem().getNo()));
            eventResult.setSuccessed(true);
        } catch (IllegalArgumentException e) {
            System.out.println("도서 상태가 논리적으로 맞지 않은 상태임");
            eventResult.setSuccessed(false);
        } catch (Exception e) {
            eventResult.setSuccessed(false);
        }
        eventProducer.occurEvent(eventResult);
    }
}