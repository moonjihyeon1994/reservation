package com.dangunju.chatting.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ObjectMapperUtil {

    private final ObjectMapper objectMapper;

    public Object str2Obj(String str, Class<?> classType) {
        Object object = null;
        try {
            object = objectMapper.readValue(str, classType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return object;
    }

    public String obj2Str(Object classType) {
        String str = null;
        try {
            str = objectMapper.writeValueAsString(classType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
