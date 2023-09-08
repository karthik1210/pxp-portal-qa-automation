package com.pxp.setup;

public class BrowserTypeUtil {

        public BrowserTypeUtil() {
        }

        public static BrowserTypeUtil.BrowserType getBrowserType(String browser) {
            if (browser == null) {
                return BrowserTypeUtil.BrowserType.htmlunit;
            } else if (!browser.equalsIgnoreCase("*edge") && !browser.equalsIgnoreCase("edge")) {
                if (!browser.equalsIgnoreCase("*firefox") && !browser.equalsIgnoreCase("firefox")) {
                    if (!browser.equalsIgnoreCase("*safari") && !browser.equalsIgnoreCase("safari")) {
                        if (!browser.equalsIgnoreCase("*chrome") && !browser.equalsIgnoreCase("chrome")) {
                            return !browser.equalsIgnoreCase("*opera") && !browser.equalsIgnoreCase("opera") ? BrowserTypeUtil.BrowserType.htmlunit : BrowserTypeUtil.BrowserType.opera;
                        } else {
                            return BrowserTypeUtil.BrowserType.chrome;
                        }
                    } else {
                        return BrowserTypeUtil.BrowserType.safari;
                    }
                } else {
                    return BrowserTypeUtil.BrowserType.firefox;
                }
            } else {
                return BrowserTypeUtil.BrowserType.edge;
            }
        }

        public static enum BrowserType {
            edge,
            firefox,
            safari,
            chrome,
            opera,
            htmlunit;

            private BrowserType() {
            }
        }
}

