package ru.anseranser.task_management_system.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@Service
public class RequestSender {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    public ResultActions sendGetRequest(String path) throws Exception {
        return mockMvc.perform(get(path).with(jwt()));
    }

    public ResultActions sendGetRequest(String path, String bearerToken) throws Exception {
        return mockMvc.perform(get(path)
                .header("Authorization", "Bearer " + bearerToken));
    }


    public <T> ResultActions sendPostRequest(String path, T entity) throws Exception {
        return mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity)));
    }

    public <T> ResultActions sendPostRequest(String path, T entity, String bearerToken) throws Exception {
        return mockMvc.perform(post(path)
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity)));
    }

    public <T> ResultActions sendPutRequest(String path, T entity, String bearerToken) throws Exception {
        return mockMvc.perform(put(path)
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity)));
    }

    public <T> ResultActions sendPatchRequest(String path, T entity, String bearerToken) throws Exception {
        return mockMvc.perform(patch(path)
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entity)));
    }

    public ResultActions sendDeleteRequest(String path) throws Exception {
        return mockMvc.perform(delete(path).with(jwt()));
    }
}
