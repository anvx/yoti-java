package com.yoti.sample.hoover.clean;

import com.yoti.sample.hoover.clean.persistence.repositories.HooverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HooverControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HooverRepository hooverRepository;

    public static Stream<Arguments> provideRqBody() {
        return Stream.of(
                Arguments.of("{\"roomSize\" : [hello, world], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [-5, 5], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [2147483648, 1], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [0, 0], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [0, 5], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 0], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, -5], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5, 5], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5], \"coords\" : [-1, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"coords\" : [-1, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),

                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [hello, world], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0, -1], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [-1, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [2147483648, 1], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [1, 1, 1], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),

                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0, 0], \"patches\" : [[-1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0, 0], \"patches\" : [[0, 2147483648], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0, 0], \"patches\" : [[0, -1], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0, 0], \"patches\" : [[0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0, 0], \"patches\" : [[hello, world], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNNNNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0, 0], \"instructions\" : \"EEEEEENNNNNN\"}"),

                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"EEEEEENNN1NNN\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]], \"instructions\" : \"west\"}"),
                Arguments.of("{\"roomSize\" : [5, 5], \"coords\" : [0, 0], \"patches\" : [[1, 0], [2, 2], [2, 3]]}")
        );
    }

    @Test
    public void correctInputShouldNotFailed() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/hoover/v1/clean")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\n" +
                                        "  \"roomSize\" : [5, 5],\n" +
                                        "  \"coords\" : [0, 0],\n" +
                                        "  \"patches\" : [\n" +
                                        "    [1, 0],\n" +
                                        "    [2, 2],\n" +
                                        "    [2, 3]\n" +
                                        "  ],\n" +
                                        "  \"instructions\" : \"EEEEEENNNNNN\"\n" +
                                        "}"
                        ))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldSaveResultToDatabase() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/hoover/v1/clean")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\n" +
                                        "  \"roomSize\" : [5, 5],\n" +
                                        "  \"coords\" : [0, 0],\n" +
                                        "  \"patches\" : [\n" +
                                        "    [1, 0],\n" +
                                        "    [2, 2],\n" +
                                        "    [2, 3]\n" +
                                        "  ],\n" +
                                        "  \"instructions\" : \"EEEEEENNNNNN\"\n" +
                                        "}"
                        ))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        assertTrue(hooverRepository.findById(1L).isPresent());
    }

    @ParameterizedTest
    @MethodSource("provideRqBody")
    public void invalidInputShouldFailWith4xx(String body) throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/hoover/v1/clean")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}