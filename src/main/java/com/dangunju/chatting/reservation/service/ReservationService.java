package com.dangunju.chatting.reservation.service;

import com.dangunju.chatting.error.ErrorCode;
import com.dangunju.chatting.error.exception.ReservationNotFoundException;
import com.dangunju.chatting.reservation.domain.Reservation;
import com.dangunju.chatting.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final KafkaPublisher kafkaPublisher;

    public void makeReservation(Reservation reservation) {
        this.publishReservation(reservationRepository.save(reservation));
    }

    private void publishReservation(Reservation reservation) {
        kafkaPublisher.sendReservationToSale(reservation);
        kafkaPublisher.sendReservationToHistory(reservation);
    }

    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(ErrorCode.NOT_EXISTS_RESERVATION_ERROR.getMessage()));
    }

    public Reservation getReservationByUser(Long sellerId, Long buyerId, String chatRoomId) {
        return reservationRepository.findByChatRoomId(chatRoomId)
                .orElseThrow(() -> new ReservationNotFoundException(ErrorCode.NOT_EXISTS_RESERVATION_ERROR.getMessage()));
    }

    @Transactional
    public void cancelReservation(String roomId) {
        Reservation reservation = reservationRepository.findByChatRoomId(roomId).orElseThrow();
        reservation.setStatus(Reservation.Status.CANCELED);
        publishReservation(reservation);
        reservationRepository.delete(reservation);
    }

}
