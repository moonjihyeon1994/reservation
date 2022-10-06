package com.dangunju.chatting.reservation.dto;

import com.dangunju.chatting.reservation.domain.Reservation;
import lombok.Data;

@Data
public class ReserveMessage {
    private Long id;
    private Long sellerId;
    private Long buyerId;
    private String chatRoomId; // 채팅방 아이디
    private String time; // 예약시간
    private String location; // 예약장소
    private Reservation.Status status; // 상태 (예약, 예약취소)

    public enum Status {
        RESERVED, CANCELED
    }
}
