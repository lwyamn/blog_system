package com.lzj.blog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzj.blog.exception.BlogErrorCode;
import com.lzj.blog.exception.BlogException;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/28 14:50
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static String toString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new BlogException(BlogErrorCode.TO_JSON_STRING_ERROR);
        }
    }
}
