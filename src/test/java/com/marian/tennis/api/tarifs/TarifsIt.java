package com.marian.tennis.api.tarifs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marian.tennis.api.tarifs.model.RequestBodyTarif;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(value = "h2")
public class TarifsIt {

    @Autowired
    protected MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void getTarifs() throws Exception {
        mockMvc.perform(get("/tarifs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tarifList", hasSize(2)))
                .andExpect(jsonPath("$.tarifList[0].name").value("test1"))
                .andExpect(jsonPath("$.tarifList[0].prix").value(12.0f))
                .andExpect(jsonPath("$.tarifList[0].startDate").value("2020-10-30"))
                .andExpect(jsonPath("$.tarifList[0].endDate").value("2020-10-30"))
                .andExpect(jsonPath("$.tarifList[0].startTime").value("08:00"))
                .andExpect(jsonPath("$.tarifList[0].endTime").value("21:00"))
                .andExpect(jsonPath("$.tarifList[0].weekend").value("false"))
                .andExpect(jsonPath("$.tarifList[0].actif").value("true"))
                .andExpect(jsonPath("$.tarifList[0].specialTarif").value("false"))
                .andExpect(jsonPath("$.tarifList[0].defaultTarif").value("false"));


        //add others
    }

    @Test
    public void getTarifsByName_should_return_tarif() throws Exception {
        String name = "test";
        mockMvc.perform(get("/tarifs/" + name))
                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name").value("test"));
        //add others
    }

    @Test
    public void getTarifsByName_tarifNotFoundThrowNotFoundException() throws Exception {
        String name = "notFound";
        mockMvc.perform(get("/tarifs/" + name))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(containsString("tarif not found with this name")));
    }

    @Test
    public void createTarifs() throws Exception {

        RequestBodyTarif requestBodyTarif = RequestBodyTarif.builder()
                .name("tarif ete")
                .prix(12.5f)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .startTime("08:00")
                .endTime("10:00")
                .build();
        String s = objectMapper.writeValueAsString(requestBodyTarif);


        mockMvc.perform(
                post("/tarifs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(s))

                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.name").value("tarif ete"))
                .andExpect(jsonPath("$.prix").value(12.5f));
    }

    @Test
    public void createTarifs_endDateBefore_should_return_422() throws Exception {

        RequestBodyTarif requestBodyTarif = RequestBodyTarif.builder()
                .name("tarif ete")
                .prix(12.5f)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().minusDays(1))
                .startTime("08:00")
                .endTime("10:00")
                .build();

        String s = objectMapper.writeValueAsString(requestBodyTarif);


        mockMvc.perform(
                post("/tarifs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(s))
                .andDo(print())
                .andExpect(status().is(422))
                .andExpect(status().reason(containsString("The End date should be after the starting date")));

    }

    @Test
    public void createTarifs_name_already_exists_in_db_should_return_500() throws Exception {

        RequestBodyTarif requestBodyTarif = RequestBodyTarif.builder()
                .name("test")
                .prix(12.5f)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .startTime("08:00")
                .endTime("10:00")
                .build();
        String s = objectMapper.writeValueAsString(requestBodyTarif);


        mockMvc.perform(
                post("/tarifs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(s))
                .andDo(print())
                .andExpect(status().is(500));
    }
}
