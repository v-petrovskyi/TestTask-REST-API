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
    public void init() throws JsonProcessingException {
        getDataFromApi();
    }

    @Scheduled(fixedDelay = 60000)
    public void runTask() throws JsonProcessingException {
        getDataFromApi();
    }

    private void getDataFromApi() throws JsonProcessingException {
        String url = "https://www.arbeitnow.com/api/job-board-api";
        ObjectMapper mapper = new ObjectMapper();
        for (int c = 0; c < 5; c++) {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode jsonNode = mapper.readTree(response);

            JsonNode data = jsonNode.get("data");
            for (int i = 0; i < data.size(); i++) {
                String slug = data.get(i).get("slug").asText();
                if (jobDataService.findBySlug(slug).isPresent()) continue;
                String company_name = data.get(i).get("company_name").asText();
                String title = data.get(i).get("title").asText();
                String description = data.get(i).get("description").asText().replaceAll("\n", "");
                boolean remote = data.get(i).get("remote").asBoolean();
                String jobUrl = data.get(i).get("url").asText();
                JsonNode tags = data.get(i).get("tags");
                List<Tag> tagList = new ArrayList<>();
                for (int i1 = 0; i1 < tags.size(); i1++) {
                    tagList.add(tagService.saveOrUpdate(new Tag(tags.get(i1).asText())));
                }
                JsonNode job_types = data.get(i).get("job_types");
                List<JobTypes> jobTypesList = new ArrayList<>();
                for (int i1 = 0; i1 < job_types.size(); i1++) {
                    jobTypesList.add(jobTypesService.saveOrUpdate(new JobTypes(job_types.get(i1).asText())));
                }
                String location = data.get(i).get("location").asText();
                long created_at = data.get(i).get("created_at").asLong();
                LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(created_at, 0, ZoneOffset.UTC);

                JobData jobData = new JobData(slug, company_name, title, description, remote, jobUrl, tagList, jobTypesList, location, localDateTime);

                jobDataService.add(jobData);
            }
            url = jsonNode.get("links").get("next").asText();

        }
    }
}
