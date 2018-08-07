package io.colligence.config;

public class CommonDefs {
    public static final String CHARSET = "UTF-8";

    public static enum EXITCODE {
        OK(0),
        CHARSET_NOT_SUPPORTED(1);

        private final int code;

        EXITCODE(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
