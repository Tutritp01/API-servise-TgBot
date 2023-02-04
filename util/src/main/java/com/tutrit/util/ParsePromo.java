package com.tutrit.util;

import com.tutrit.persistence.core.bean.Promo;
import com.tutrit.persistence.core.bean.PromoStatus;
import com.tutrit.persistence.core.bean.User;
import com.tutrit.stoservice.controller.Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ParsePromo {
    private static final Logger logger = Logger.getLogger(ParsePromo.class.getName());

    private ParsePromo() {
        throw new IllegalStateException("Utility class");
    }

    public static Promo defaultPromo(User user) {
        var promo = new Promo();
        promo.setDiscount(5);
        promo.setNamePromo(user.getName() + "-5%");
        promo.setAuthor(user);
        promo.setStartDate(LocalDateTime.now());
        return promo;
    }

    public static Promo parseCommand(Request request, User user) throws ArrayIndexOutOfBoundsException {
        var promo = new Promo();
        String massageInput = request.getCommand();
        String[] parseMap = massageInput.split("-d");
        parseMap[0] = parseMap[0].strip();
        parseMap[1] = parseMap[1].strip();

        Map<String, String> promoInformation = stringToMapParser(parseMap[1]);

        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0 -> promo.setStatus(PromoStatus.NEW);
                case 1 -> promo.setAuthor(user);
                case 2 -> promo.setStartDate(convertToDate(promoInformation.get("startDate")));
                case 3 -> promo.setDiscount(Integer.parseInt(promoInformation.get("discount")));
                case 4 -> promo.setEndDate(convertToDate(promoInformation.get("endDate")));
                case 5 -> promo.setNamePromo(promoInformation.get("namePromo"));
                case 6 -> promo.setTimeStamp(LocalDateTime.now());
                case 7 -> promo.setId(UUID.randomUUID().toString());
                default -> logger.info("No vehicle data has been saved.");
            }
        }
        return promo;
    }

    public static String[] getArray(Request request) throws ArrayIndexOutOfBoundsException {
        String massageInput = request.getCommand();
        String[] parseMap = massageInput.split("-d");
        parseMap[0] = parseMap[0].strip();
        parseMap[1] = parseMap[1].strip();

        Map<String, String> findEntity = stringToMapParser(parseMap[1]);
        String[] arrayMessage = new String[2];
        arrayMessage[0] = findEntity.get("namePromo");
        arrayMessage[1] = findEntity.get("eventText");
        return arrayMessage;
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
