package com.dangunju.chatting.reservation.controller;

import com.dangunju.chatting.reservation.domain.Reservation;
import com.dangunju.chatting.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/reservation/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        log.info("reservation 조회 : {}", id);
        return reservationService.getReservation(id);
    }

    @PostMapping("/reservation")
    public void makeReservation(@RequestBody Reservation reservation) {
        log.info("reservation 생성 : {}", reservation);
        reservationService.makeReservation(reservation);
    }

    @DeleteMapping("/reservation/{roomId}")
    public void cancelReservation(@PathVariable String roomId) {
        log.info("reservation 취소 : {}", roomId);
        reservationService.cancelReservation(roomId);
    }

    @GetMapping("/reservation")
    public Reservation getReservationByUser(@RequestParam("sellerId") Long sellerId,
                                            @RequestParam("buyerId") Long buyerId,
                                            @RequestParam("chatRoomId") String chatRoomId) {
        log.info("reservation 확인 : sellerId = " + sellerId + ", buyerId = " + buyerId + ", chatRoomId = " + chatRoomId);
        return reservationService.getReservationByUser(sellerId, buyerId, chatRoomId);
    }

    @GetMapping("/reservation/test")
    public String getReservationTest() {
        double x  = 0.0001;
        String hostname = "";

        for (int i = 0; i <= 1000000; i++) {
            x += Math.sqrt(x);
        }

        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostname;
    }
}
