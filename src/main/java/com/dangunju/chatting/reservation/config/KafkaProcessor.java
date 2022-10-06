package com.dangunju.chatting.reservation.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaProcessor {

//    String CHATTING_INPUT = "chatting-in";
//    String CHATTING_OUTPUT = "chatting-out";
    String RESERVATION_OUTPUT = "sale-out";
    String HISTORY_OUTPUT = "history-out";

//    @Input(CHATTING_INPUT)
//    SubscribableChannel chattingInboundTopic();
//    @Output(CHATTING_OUTPUT)
//    MessageChannel chattingOutboundTopic();

    @Output(RESERVATION_OUTPUT)
    MessageChannel saleOutboundTopic();

    @Output(HISTORY_OUTPUT)
    MessageChannel historyOutboundTopic();
}
