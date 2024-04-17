package com.fastcampus.fastcampusprojectpnoga.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest -> 얘는 컨트롤러만 가져올 수 있기 때문에 Rest data configuration은 알 수가 없음
@DisplayName("Data Rest - API 테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    private final MockMvc mockMvc;

    public DataRestTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[api] 가계부 리스트 조회")
    @Test
    void givenNothing_whenRequestingLedgers_thenReturnsLedgersJsonResponse() throws Exception {
        //Given

        // When & Then
        mockMvc.perform(get("/api/ledgers"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 가계부 상세 리스트 조회")
    @Test
    void givenNothing_whenRequestingLedgerDetails_thenReturnsLedgerDetailsJsonResponse() throws Exception {
        //Given

        // When & Then
        mockMvc.perform(get("/api/ledgerDetails"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
}
