package com.fleet.managament.utils;

import org.springframework.util.ObjectUtils;

public class StringTrimmer {
    public static String trimString(String value) {
        if (ObjectUtils.isEmpty(value)) return "";
        return value.trim();
    }
}
