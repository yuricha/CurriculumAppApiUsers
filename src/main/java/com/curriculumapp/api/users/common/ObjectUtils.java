package com.curriculumapp.api.users.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.curriculumapp.api.users.base.BaseComponent;

public class ObjectUtils extends BaseComponent {

	private static Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    public static String convertObjectToJsonResponse(Object obj) {
        try {
            ObjectMapper objMapper = new ObjectMapper();
            objMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            return objMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("convertObjectToJsonResponse [Exception]: " + e.getMessage());
        }
        return null;
    }

    public static <T> T listObjectfromJSON (final TypeReference<T> type, final String jsonPacket) {
        T data = null;
        try {
            data = new ObjectMapper().readValue(jsonPacket, type);
        } catch (Exception e) {
            logger.error("listObjectfromJSON [Error]: " + e.getMessage());
        }
        return data;
    }

}
