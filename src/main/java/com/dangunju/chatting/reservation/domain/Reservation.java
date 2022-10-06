package com.dangunju.chatting.reservation.domain;

import com.dangunju.chatting.config.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    private Long buyerId;
    private String chatRoomId; // 채팅방 아이디
    private String time; // 예약시간
    private String location; // 예약장소
    @Enumerated(EnumType.STRING)
    private Status status; // 상태 (예약, 예약취소)

    public enum Status {
        RESERVED, CANCELED
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
