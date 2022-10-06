package com.dangunju.chatting.controller;

import com.dangunju.chatting.error.ErrorCode;
import com.dangunju.chatting.error.exception.ReservationNotFoundException;
import com.dangunju.chatting.reservation.controller.ReservationController;
import com.dangunju.chatting.reservation.domain.Reservation;
import com.dangunju.chatting.reservation.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("예약 API")
    public class 예약API {

        @Nested
        @DisplayName("성공")
        public class HappyCase {

            @Test
            @DisplayName("예약 조회")
            public void 예약_조회() throws Exception {

                String url = "/reservation/123";

                doReturn(Reservation.builder().build()).when(reservationService).getReservation(anyLong());

                mockMvc
                        .perform(
                                get(url)
                        )
                        .andExpect(status().isOk());
            }

            @Test
            @DisplayName("예약 삭제")
            public void 예약_삭제() throws Exception {
                String url = "/reservation/123";

                doNothing().when(reservationService).cancelReservation(anyString());

                mockMvc
                        .perform(
                                delete(url)
                        )
                        .andExpect(status().isOk());
            }

            @Test
            @DisplayName("예약 생성")
            public void 예약_생성() throws Exception {
                String url = "/reservation";
                String content = objectMapper.writeValueAsString(
                        Reservation.builder().id(1L).build()
                );

                doNothing().when(reservationService).makeReservation(Reservation.builder().build());

                mockMvc
                        .perform(
                                post(url)
                                        .content(content)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("실패")
        public class FailCase {

            @Test
            @DisplayName("예약 번호가 없을때")
            public void 사용자id_길이오류() throws Exception {
                String url = "/reservation/123";

                doThrow(new ReservationNotFoundException(ErrorCode.NOT_EXISTS_RESERVATION_ERROR.getMessage())).when(reservationService).getReservation(anyLong());

                mockMvc
                        .perform(
                                get(url)
                        )
                        .andExpect(status().is4xxClientError());
            }
        }
    }
}