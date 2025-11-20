package com.mycompany.portscanner;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class ScanTime {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static String getCurrentTime() {
        return OffsetDateTime.now().format(formatter);
    }

    public static void printFinishedTime() {
        System.out.println("Scan Finished at: " + getCurrentTime());
    }
}
