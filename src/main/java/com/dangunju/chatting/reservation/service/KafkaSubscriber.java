//package com.dangunju.chatting.reservation.service;
//
//import com.dangunju.chatting.reservation.config.KafkaProcessor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class KafkaSubscriber {
//
//    @StreamListener(KafkaProcessor.CHATTING_INPUT)
//    public void wheneverChatting(@Payload String str) {
//        log.info("받음 : {}", str);
//    }
//
//}