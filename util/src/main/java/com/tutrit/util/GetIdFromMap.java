package com.tutrit.util;

import java.util.Map;

public class GetIdFromMap {

    private GetIdFromMap() {
        throw new IllegalStateException("Utility class");
    }

    public static String getId(Map<String, String> map) {
        return map.get("id");
    }
}
