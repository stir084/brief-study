package com.example.java.mvcgrammers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
public class MvcControllerTest {
    @InjectMocks
    private MVCController mvcController;

    @Mock
    private MVCService mvcService;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(mvcController)
                .build();
    }

    @Test
    void test() throws Exception {
        when(mvcService.test()).thenReturn("hello");
        given(mvcService.test()).willReturn("hello");

        mockMvc.perform(get("/mvc"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello")); // MediaType은 text/plain
    }

    @Test
    void test2() throws Exception {
        mockMvc.perform(get("/mvc2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"name\":\"stir\"}"))
                .andExpect(jsonPath("$.name", Matchers.is("stir")));
    }



}
