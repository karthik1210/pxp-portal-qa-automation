package com.pxp.setup;

public class OSTypeUtil {

        public static OSTypeUtil.OSType getTestClientOSType(String osType) {
            if (osType.toLowerCase().contains("win")) {
                return OSTypeUtil.OSType.windows;
            } else if (osType.toLowerCase().contains("mac")) {
                return OSTypeUtil.OSType.mac;
            } else {
                return osType.toLowerCase().contains("linux") ? OSTypeUtil.OSType.linux : OSTypeUtil.OSType.windows;
            }
        }

        public static enum OSType {
            windows,
            mac,
            linux;

            private OSType() {
            }
        }
}
