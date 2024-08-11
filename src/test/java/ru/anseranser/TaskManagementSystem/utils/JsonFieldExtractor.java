package ru.anseranser.TaskManagementSystem.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonFieldExtractor {

    @Autowired
    private ObjectMapper objectMapper;

    public Long getFieldAsLong(String json, String field) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(json);
        return jsonNode.get(field).asLong();
    }

    public String getFieldAsString(String json, String field) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(json);
        return jsonNode.get(field).asText();
    }
}
