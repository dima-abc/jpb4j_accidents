package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.AccidentsApplication;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Tests
 * 1. Spring mock [#2096]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 21.04.2023
 */
@SpringBootTest(classes = AccidentsApplication.class)
@AutoConfigureMockMvc
class AccidentControllerTest {
    @MockBean
    private AccidentService accidents;
    @MockBean
    private AccidentTypeService types;
    @MockBean
    private RuleService rules;

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
    @WithMockUser
    void whenPostSaveAccidentThenRedirectListPageAndAccidentCaptureParam() throws Exception {
        var rIds = Set.of(1, 2, 3);
        var accident = new Accident(0, "textName", "accidentText", "accidentAddress",
                new AccidentType(1, "Две машины"), Collections.emptySet());
        this.mockMvc.perform(post("/accidents/save")
                        .param("name", "textName")
                        .param("text", "accidentText")
                        .param("address", "accidentAddress")
                        .param("type", "1")
                        .param("rIds", "1", "2", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/accidents/list"));

        ArgumentCaptor<Accident> accidentCapture = ArgumentCaptor.forClass(Accident.class);
        ArgumentCaptor<Set<Integer>> rIdsCapture = ArgumentCaptor.forClass(Set.class);

        verify(accidents).save(accidentCapture.capture(), rIdsCapture.capture());

        assertThat(accidentCapture.getValue()).usingRecursiveComparison()
                .isEqualTo(accident);
        assertThat(rIdsCapture.getValue()).usingRecursiveComparison()
                .isEqualTo(rIds);
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
    @WithMockUser
    void whenPostEditAccidentThenReturnRedirectListAndArgumentCaptureEquals() throws Exception {
        var rIds = Set.of(1, 2, 3);
        var accident = new Accident(2, "textName", "accidentText", "accidentAddress",
                new AccidentType(1, "Две машины"), Collections.emptySet());
        this.mockMvc.perform(post("/accidents/edit")
                        .param("id", "2")
                        .param("name", "textName")
                        .param("text", "accidentText")
                        .param("address", "accidentAddress")
                        .param("type", "1")
                        .param("rIds", "1", "2", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/accidents/list"));

        ArgumentCaptor<Accident> accidentCapture = ArgumentCaptor.forClass(Accident.class);
        ArgumentCaptor<Set<Integer>> rIdsCapture = ArgumentCaptor.forClass(Set.class);

        verify(accidents).update(accidentCapture.capture(), rIdsCapture.capture());

        assertThat(accidentCapture.getValue()).usingRecursiveComparison()
                .isEqualTo(accident);
        assertThat(rIdsCapture.getValue()).usingRecursiveComparison()
                .isEqualTo(rIds);
    }

    @Test
    @WithMockUser
    void whenGetDeleteAccidentTrueThenReturnListPage() throws Exception {
        when(accidents.delete(anyInt())).thenReturn(true);
        this.mockMvc.perform(get("/accidents/delete?id=" + anyInt()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/accidents/list"));
    }

    @Test
    @WithMockUser
    void whenGetDeleteAccidentFalseThenReturnErrorsPage() throws Exception {
        var id = -1;
        var message = "Accident by ID: " + id + ",  not found";
        when(accidents.delete(id)).thenReturn(false);
        this.mockMvc.perform(get("/accidents/delete?id=" + id))
                .andDo(print())
                .andExpect(model().attribute("message", message))
                .andExpect(status().isOk())
                .andExpect(view().name("errors/404"));
    }
}