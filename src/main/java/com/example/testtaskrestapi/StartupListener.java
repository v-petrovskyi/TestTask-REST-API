package com.example.testtaskrestapi;

import com.example.testtaskrestapi.entity.JobData;
import com.example.testtaskrestapi.entity.JobTypes;
import com.example.testtaskrestapi.entity.Tag;
import com.example.testtaskrestapi.services.JobDataService;
import com.example.testtaskrestapi.services.JobTypesService;
import com.example.testtaskrestapi.services.TagService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Component
public class StartupListener {

    private final RestTemplate restTemplate;
    private final JobDataService jobDataService;
    private final TagService tagService;
    private final JobTypesService jobTypesService;

    @Autowired
    public StartupListener(RestTemplateBuilder restTemplateBuilder, JobDataService jobDataService, TagService tagService, JobTypesService jobTypesService) {
        this.restTemplate = restTemplateBuilder.build();
        this.jobDataService = jobDataService;
        this.tagService = tagService;
        this.jobTypesService = jobTypesService;
    }

    @PostConstruct
    public void init() {
        // Виконати підготовчі операції тут
    }

    @Scheduled(fixedDelay = 10000) // Інтервал виконання у мілісекундах
    public void runTask() {
        // Виконати операції тут
    }

    @EventListener
    public void getDataFromApi(ContextRefreshedEvent event) throws JsonProcessingException {
        String url = "https://www.arbeitnow.com/api/job-board-api";
        String response = restTemplate.getForObject(url, String.class);
//        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);

        JsonNode data = jsonNode.get("data");
//        for (int i = 0; i < data.size(); i++) {
        for (int i = 0; i < 1; i++) {
            String slug = data.get(i).get("slug").asText();
            System.out.println(slug);
            String company_name = data.get(i).get("company_name").asText();
            System.out.println(company_name);
            String title = data.get(i).get("title").asText();
            System.out.println(title);
            String description = data.get(i).get("description").asText();
            System.out.println(description);
            boolean remote = data.get(i).get("remote").asBoolean();
            System.out.println(remote);
            String jobUrl = data.get(i).get("url").asText();
            System.out.println(jobUrl);


            JsonNode tags = data.get(i).get("tags");
            List<Tag> tagList = new ArrayList<>();
            for (int i1 = 0; i1 < tags.size(); i1++) {
                tagList.add(tagService.add(new Tag(tags.get(i1).asText())));
            }
            System.out.println("------------------------------------");
            System.out.println(tagList);
            System.out.println("------------------------------------");


            JsonNode job_types = data.get(i).get("job_types");
            List<JobTypes> jobTypesList = new ArrayList<>();
            for (int i1 = 0; i1 < job_types.size(); i1++) {
                jobTypesList.add(jobTypesService.add(new JobTypes(job_types.get(i1).asText())));
            }

            System.out.println("------------------------------------");
            System.out.println(jobTypesList);
            System.out.println("------------------------------------");

            String location = data.get(i).get("location").asText();
            System.out.println(location);
            long created_at = data.get(i).get("created_at").asLong();
            LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(created_at, 0, ZoneOffset.UTC);
            System.out.println(localDateTime);

            JobData jobData = new JobData(slug, company_name, title, description, remote, jobUrl, tagList, jobTypesList, location, localDateTime);

            jobDataService.add(jobData);

            System.out.println(jobDataService.getAll());
        }

    }
}
