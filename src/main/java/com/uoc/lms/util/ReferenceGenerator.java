package com.uoc.lms.util;

import java.util.UUID;

public class ReferenceGenerator {

    public static String generate() {
        return "UOC-LTR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
