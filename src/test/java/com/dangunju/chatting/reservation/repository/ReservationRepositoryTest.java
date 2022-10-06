package com.dangunju.chatting.reservation.repository;

import com.dangunju.chatting.config.JpaAuditingConfiguration;
import com.dangunju.chatting.reservation.domain.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Import(JpaAuditingConfiguration.class)
@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;


    @Nested
    @DisplayName("예약 저장")
    public class 예약 {

        @Nested
        @DisplayName("정상")
        public class HappyCase {

            @Test
            @DisplayName("예약 저장")
            public void 예약_저장() {
                Reservation reservation = Reservation.builder()
                        .sellerId(123L)
                        .buyerId(456L)
                        .chatRoomId(UUID.randomUUID().toString())
                        .time("2022-04-06")
                        .location("서울")
                        .status(Reservation.Status.RESERVED)
                        .build();

                Reservation result = reservationRepository.save(reservation);

                assertThat(result).isNotNull();
                assertThat(result.getSellerId()).isEqualTo(123L);
                assertThat(result.getStatus()).isEqualTo(Reservation.Status.RESERVED);
            }
        }
    }
}