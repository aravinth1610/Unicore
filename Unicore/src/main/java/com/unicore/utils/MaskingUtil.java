package com.unicore.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MaskingUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static String toJsonString(Object obj) {
        try {
            if (obj == null) return "null";

            if (obj instanceof Map<?, ?> map) {
                return objectMapper.writeValueAsString(maskSensitiveData(map));
            }

            return objectMapper.writeValueAsString(maskSensitiveFields(obj));
        } catch (Exception e) {
            return "Failed to parse log details";
        }
    }

    private static Map<String, Object> maskSensitiveData(Map<?, ?> map) {
        Map<String, Object> maskedMap = new HashMap<>();
        map.forEach((key, value) -> {
            if (value instanceof String && key.toString().matches("(?i).*password|token.*")) {
                maskedMap.put(key.toString(), "****");
            } else {
                maskedMap.put(key.toString(), value);
            }
        });
        return maskedMap;
    }

    private static Object maskSensitiveFields(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> maskedMap = new HashMap<>();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);

//            if (field.isAnnotationPresent(Sensitive.class)) {
//                maskedMap.put(field.getName(), "****");
//            } else {
//                maskedMap.put(field.getName(), value);
//            }
        }
        return maskedMap;
    }

}
