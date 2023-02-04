package com.tutrit.util;

import com.tutrit.stoservice.controller.Request;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class GetBodyAsMap {
    private static final Logger logger = Logger.getLogger(GetBodyAsMap.class.getName());

    public static Map<String, String> parseUserInput(Request request) throws ArrayIndexOutOfBoundsException {
        String userInput = request.getCommand();
        int index = userInput.indexOf("-d");
        String body = userInput.substring(index + 3).strip();
        return stringToMapParser(body);
    }

    private static Map<String, String> stringToMapParser(String toParse) {
        try {
            final String[] keysAndValues = toParse.split("&");
            return Arrays.stream(keysAndValues)
                    .collect(Collectors.toMap(
                            a -> a.substring(0, a.indexOf("=")),
                            b -> b.substring(b.indexOf("=") + 1)
                    ));
        } catch (NullPointerException e) {
            return Collections.emptyMap();
        }
    }
}
