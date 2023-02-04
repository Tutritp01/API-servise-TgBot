package com.tutrit.util;

import com.tutrit.persistence.core.bean.Message;
import com.tutrit.persistence.core.bean.MessageStatus;
import com.tutrit.persistence.core.bean.User;
import com.tutrit.stoservice.controller.Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ParseMessage {
    private static final Logger logger = Logger.getLogger(ParseMessage.class.getName());

    private ParseMessage() {
        throw new IllegalStateException("Utility class");
    }

    public static Message parseCommand(Request request, User user) throws ArrayIndexOutOfBoundsException {
        var message = new Message();
        String massageInput = request.getCommand();
        String[] parseMap = massageInput.split("-d");
        parseMap[0] = parseMap[0].strip();
        parseMap[1] = parseMap[1].strip();

        Map<String, String> massageInformation = stringToMapParser(parseMap[1]);

        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0 -> message.setStatus(MessageStatus.NEW);
                case 1 -> message.setEventText(massageInformation.get("eventText"));
                case 2 -> message.setStartDate(convertToDate(massageInformation.get("startDate")));
                case 3 -> message.setAuthor(user);
                case 4 -> message.setTimestamp(LocalDateTime.now());
                default -> logger.info("No vehicle data has been saved.");
            }
        }
        return message;
    }

    private static Map<String, String> stringToMapParser(String toParse) {
        return getStringStringMap(toParse);
    }

    static Map<String, String> getStringStringMap(String toParse) {
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

    private static LocalDateTime convertToDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate date = LocalDate.parse(s, formatter);
        return date.atStartOfDay();
    }
}
