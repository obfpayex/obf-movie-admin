package com.payex.vas.util;

public final class Constants {

    private Constants() {
        // Class should not be instantiated.
    }

    public static final String APPLICATION_NAME = "VasAccountingReceiptfileImport";


    public class ResponseStatus {
        public static final String OK = "OK";
        public static final String FAILED = "FAILED";
    }

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_STAGING = "stage";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_SWAGGER = "swagger";

    public static final String SESSION_ID = "Session-Id";
}
