package com.dangunju.chatting.reservation.service;

import com.dangunju.chatting.config.ObjectMapperUtil;
import com.dangunju.chatting.reservation.config.KafkaProcessor;
import com.dangunju.chatting.reservation.domain.Reservation;
import com.dangunju.chatting.reservation.dto.ReserveMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaPublisher {

    private final KafkaProcessor kafkaProcessor;
    private final ObjectMapperUtil objectMapperUtil;
    private final ModelMapper modelMapper;

    /**
     * Kafka로 String 전송
     *
     * @param message
     */
    /*
    public void sendChattingString(String message) {
        kafkaProcessor.chattingOutboundTopic().send(
                MessageBuilder.withPayload(message)
                        .build()
        );
    }
    */

    /**
     * Kafka로 Json 전송
     */
    /*
    public void sendChattingJson(Test test) {
        String message = jacksonUtil.obj2Str(test);
        kafkaProcessor.chattingOutboundTopic().send(
                MessageBuilder.withPayload(message)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                        .build()
        );
    }
    */
    public void sendReservationToSale(Reservation reservation) {
        ReserveMessage reserveMessage = modelMapper.map(reservation, ReserveMessage.class);
        String message = objectMapperUtil.obj2Str(reserveMessage);
        kafkaProcessor.saleOutboundTopic().send(
                MessageBuilder.withPayload(message)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                        .build()
        );
    }

    public void sendReservationToHistory(Reservation reservation) {
        ReserveMessage reserveMessage = modelMapper.map(reservation, ReserveMessage.class);
        String message = objectMapperUtil.obj2Str(reserveMessage);
        kafkaProcessor.historyOutboundTopic().send(
                MessageBuilder.withPayload(message)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                        .build()
        );
    }

}

