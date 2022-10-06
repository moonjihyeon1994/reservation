package com.dangunju.chatting.reservation.repository;

import com.dangunju.chatting.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findBySellerIdAndBuyerIdAndChatRoomId(Long sellerId, Long buyerId, String chatRoomId);

    Optional<Reservation> findByChatRoomId(String chatRoomId);
}
