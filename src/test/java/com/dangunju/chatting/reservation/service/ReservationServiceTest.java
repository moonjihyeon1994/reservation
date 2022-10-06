package com.dangunju.chatting.reservation.service;

import com.dangunju.chatting.error.ErrorCode;
import com.dangunju.chatting.error.exception.ReservationNotFoundException;
import com.dangunju.chatting.reservation.domain.Reservation;
import com.dangunju.chatting.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;


    @Nested
    @DisplayName("예약 API")
    public class 예약 {

        @Nested
        @DisplayName("정상")
        public class HappyCase {

            @Test
            @DisplayName("예약 확인")
            public void 예약_확인() {
                Reservation reservation = Reservation.builder()
                        .sellerId(123L)
                        .buyerId(456L)
                        .chatRoomId(UUID.randomUUID().toString())
                        .time("2022-04-06")
                        .location("서울")
                        .status(Reservation.Status.RESERVED)
                        .build();

                doReturn(Optional.of(reservation)).when(reservationRepository).findById(anyLong());

                Reservation find = reservationService.getReservation(1L);

                assertThat(find.getId()).isEqualTo(reservation.getId());
            }
        }

        @Nested
        @DisplayName("실패")
        public class FailCase {

            @Test
            @DisplayName("예약 확인 실패 없는 아이디")
            public void 예약_실패() {
                Reservation reservation = Reservation.builder()
                        .sellerId(123L)
                        .buyerId(456L)
                        .chatRoomId(UUID.randomUUID().toString())
                        .time("2022-04-06")
                        .location("서울")
                        .status(Reservation.Status.RESERVED)
                        .build();

                doReturn(Optional.of(reservation)).when(reservationRepository).findById(anyLong());

                Reservation find = reservationService.getReservation(1L);

                doThrow(new ReservationNotFoundException(ErrorCode.NOT_EXISTS_RESERVATION_ERROR.getMessage()))
                        .when(reservationRepository).findById(anyLong());

                ReservationNotFoundException exception = assertThrows(ReservationNotFoundException.class,
                        () -> reservationRepository.findById(anyLong()));

                assertThat(exception.getMessage()).isEqualTo("등록되지 않은 예약번호");
            }
        }
    }
}