package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.AccidentsApplication;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 21.04.2023
 */
@SpringBootTest(classes = AccidentsApplication.class)
@AutoConfigureMockMvc
class AccidentControllerTest {
    @MockBean
    private AccidentService accidents;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void whenGetAllAccidentsThenReturnPageListStatusOk() throws Exception {
        this.mockMvc.perform(get("/accidents/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/list"));
    }

    @Test
    @WithMockUser
    void whenGetCreateAccidentThenReturnCreatePageStatusOk() throws Exception {
        this.mockMvc.perform(get("/accidents/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/create"));
    }

    @Test
    void saveAccident() {
    }

    @Test
    @WithMockUser
    void whenGetEditAccidentThenReturnEditPageStatusOk() throws Exception {
        var accident = new Accident(1, "", "", "", null, Collections.emptySet());
        when(accidents.findById(accident.getId())).thenReturn(Optional.of(accident));
        this.mockMvc.perform(get("/accidents/edit?id=" + accident.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/edit"));
    }

    @Test
    @WithMockUser
    void whenGetEditAccidentThenReturnErrorPageStatusOk() throws Exception {
        when(accidents.findById(anyInt())).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/accidents/edit?id=" + anyInt()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("errors/404"));
    }

    @Test
    void editAccident() {
    }

    @Test
    void deleteAccident() {
    }
}