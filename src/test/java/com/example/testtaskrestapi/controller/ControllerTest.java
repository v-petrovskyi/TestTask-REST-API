package com.example.testtaskrestapi.controller;

import com.example.testtaskrestapi.entity.JobData;
import com.example.testtaskrestapi.entity.JobTypes;
import com.example.testtaskrestapi.entity.Tag;
import com.example.testtaskrestapi.services.JobDataService;
import com.example.testtaskrestapi.util.GroupedResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
class ControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobDataService service;

    @Test
    void returnPageOfJobs() throws Exception {
        int page = 2;
        int size = 3;
        String sort = "id";
        ArrayList<JobData> returnList = new ArrayList<>();
        returnList.add(new JobData(1, "junior-pmo-automotive-all-gender-wolfsburg-187433",
                "ALTEN",
                "Junior PMO Automotive (all gender)",
                "desc",
                true,
                "some url",
                Arrays.asList(new Tag(1, "tag"), new Tag(2, "tag2")),
                Arrays.asList(new JobTypes(1, "type1"), new JobTypes(2, "type2")),
                "location",
                LocalDateTime.now()
        ));
        returnList.add(new JobData(2, "187433",
                "ALTEN",
                "Junior PMO Automotive (all gender)",
                "desc",
                true,
                "some url",
                Arrays.asList(new Tag(1, "tag")),
                Arrays.asList(new JobTypes(1, "type1"), new JobTypes(2, "type2")),
                "location",
                LocalDateTime.now()
        ));
        returnList.add(new JobData(3, "junior-pmo-automotive-all-gender-wolfsburg-187433",
                "ALTEN",
                "Junior PMO Automotive (all gender)",
                "desc",
                true,
                "some url",
                Arrays.asList(new Tag(2, "tag2")),
                Arrays.asList(new JobTypes(1, "type1")),
                "location",
                LocalDateTime.now()
        ));

        when(service.getPage(page, size, sort)).thenReturn(returnList);

        mockMvc.perform(get("/home?page=2&size=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(size)))
                .andExpect(jsonPath("$[*].id", containsInRelativeOrder(1, 2, 3)));
    }

    @Test
    void test_getGroupedEntities() throws Exception {
        String url = "/grouped";
        List<Object[]> returnArray = Arrays.asList(new Object[]{"location1", 2L}, new Object[]{"location2", 5L}, new Object[]{"location3", 3L});
        when(service.groupByLocation()).thenReturn(returnArray);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].location", containsInRelativeOrder("location1", "location2", "location3")))
                .andExpect(jsonPath("$[*].count", containsInRelativeOrder(2, 5, 3)));
    }

}