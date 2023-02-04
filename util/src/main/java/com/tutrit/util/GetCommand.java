package com.tutrit.util;

public class GetCommand {
    public static String getCommand(String inputMsg) {
        if (inputMsg.contains("-")) {
            return inputMsg.substring(0, inputMsg.indexOf('-')).strip();
        } else {
            return inputMsg.strip();
        }
    }

    private GetCommand() {
        throw new IllegalStateException("Utility class");
    }
}
