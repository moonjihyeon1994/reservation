package com.dangunju.chatting.Integration;

import com.dangunju.chatting.reservation.domain.Reservation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("예약 API")
    public class 예약API {

        @Nested
        @DisplayName("성공")
        public class HappyCase {

//            @Test
            @DisplayName("예약 생성")
            public void 예약_조회() throws Exception {
                String url = "/reservation";
                String content = objectMapper.writeValueAsString(
                        Reservation.builder()
                                .sellerId(123L)
                                .buyerId(456L)
                                .chatRoomId(UUID.randomUUID().toString())
                                .time("2022-04-06")
                                .location("서울")
                                .status(Reservation.Status.RESERVED)
                                .build()
                );

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
    }
}