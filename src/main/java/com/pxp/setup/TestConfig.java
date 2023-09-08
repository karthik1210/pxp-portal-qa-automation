package com.pxp.setup;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import com.pxp.setup.BrowserTypeUtil;

public class TestConfig {

        private static final String DEFAULT_PROP_FILENAME = "testConfig.properties";
        public static Properties properties = new Properties();
        private static String testRoot;
        private static String testResultVideos;
        private static String testResultScreenshots;
        private static String testConfigFile;
        private static String testJavascriptErrors;

        public TestConfig() {
        }

        public static String[] getUsernames() {
            int max_number_of_username = 10;
            ArrayList<String> usernames = new ArrayList();
            String url = null;

            for(int i = 0; i < max_number_of_username; ++i) {
                url = String.valueOf(properties.getProperty("username." + i + ""));
                if (url != null && !url.equals("null") && !url.equals("")) {
                    usernames.add(url.trim());
                }
            }

            String[] s = new String[usernames.size()];
            s = (String[])usernames.toArray(s);
            return s;
        }

        public static String[] getPasswords() {
            int max_number_of_password = 10;
            ArrayList<String> passwords = new ArrayList();
            String url = null;

            for(int i = 0; i < max_number_of_password; ++i) {
                url = String.valueOf(properties.getProperty("password." + i + ""));
                if (url != null && !url.equals("null") && !url.equals("")) {
                    passwords.add(url.trim());
                }
            }

            String[] s = new String[passwords.size()];
            s = (String[])passwords.toArray(s);
            return s;
        }

        public static String[] getApplicationURLs() {
            int max_number_of_url = 30;
            ArrayList<String> urls = new ArrayList();
            String url = null;

            for(int i = 0; i < max_number_of_url; ++i) {
                url = String.valueOf(properties.getProperty("application." + i + ".url"));
                if (url != null && !url.equals("null") && !url.equals("")) {
                    urls.add(url.trim());
                }
            }

            String[] s = new String[urls.size()];
            s = (String[])urls.toArray(s);
            return s;
        }

        public static BrowserTypeUtil.BrowserType getBrowserType() {
            String browser = System.getProperty("selenium.browser");
            return browser != null ? BrowserTypeUtil.getBrowserType(browser) : BrowserTypeUtil.getBrowserType(String.valueOf(properties.getProperty("selenium.browser")).trim());
        }

        public static String getExecutionMode() {
            String mode = System.getProperty("execution.mode");
            return mode != null ? mode : properties.getProperty("execution.mode");
        }

        public static boolean isClearBrowserCache() {
            String clearBrowserCache = String.valueOf(properties.getProperty("clear.browser.cache")).trim();
            return clearBrowserCache.equalsIgnoreCase("true");
        }

        public static boolean isCaptureScreenshot() {
            String env_captureScreenshot = System.getProperty("capture.screenshot");
            String prop_captureScreenshot = String.valueOf(properties.getProperty("capture.screenshot"));
            if (env_captureScreenshot != null) {
                return env_captureScreenshot.trim().equalsIgnoreCase("true");
            } else if (prop_captureScreenshot != null) {
                return prop_captureScreenshot.trim().equalsIgnoreCase("true");
            } else {
                return false;
            }
        }

        public static boolean isEnableVideoRecording() {
            String env_enableVideoRecording = System.getProperty("enable.video.recording");
            String prop_enableVideoRecording = String.valueOf(properties.getProperty("enable.video.recording"));
            if (env_enableVideoRecording != null) {
                return env_enableVideoRecording.trim().equalsIgnoreCase("true");
            } else if (prop_enableVideoRecording != null) {
                return prop_enableVideoRecording.trim().equalsIgnoreCase("true");
            } else {
                return false;
            }
        }

        public static boolean isReportingFullStackTrace() {
            String sReportingFullStackTrace = String.valueOf(properties.getProperty("reporting.full.stack.trace")).trim();
            return !sReportingFullStackTrace.equalsIgnoreCase("false");
        }

        public static boolean isFirefoxtAssumeUntrustedCertificateIssuer() {
            String assumeUntrustedCertificateIssuer = String.valueOf(properties.getProperty("firefox.assumeUntrustedCertificateIssuer")).trim();
            return assumeUntrustedCertificateIssuer.equalsIgnoreCase("true");
        }

        public static boolean isFirefoxAcceptUntrustedCertificates() {
            String acceptUntrustedCertificates = String.valueOf(properties.getProperty("firefox.acceptUntrustedCertificates")).trim();
            return !acceptUntrustedCertificates.equalsIgnoreCase("false");
        }

        public static String getTestRoot() {
            return testRoot;
        }

        public static String getTestResultVideos() {
            return testResultVideos;
        }

        public static String getTestResultScreenshot() {
            return testResultScreenshots;
        }

        public static String getJSCapture() {
            return testJavascriptErrors;
        }

        public static String dumpTestConfigInfo() {
            StringBuffer sb = new StringBuffer();
            sb.append("<pre>\n");
            sb.append("testRoot: " + testRoot + "\n");
            sb.append("testConfigFile: " + testConfigFile + "\n");
            sb.append("testResultVideos: " + getTestResultVideos() + "\n");
            sb.append("testResultScreenshots: " + getTestResultScreenshot() + "\n");
            sb.append("selenium.browser: " + getBrowserType().name() + "\n");
            sb.append("client os.type: " + getTestClientOSType() + "\n");
            String[] urls = getApplicationURLs();

            for(int i = 0; i < urls.length; ++i) {
                sb.append("application." + i + ".url: " + urls[i] + "\n");
            }

            String[] usernames = getUsernames();

            for(int i = 0; i < usernames.length; ++i) {
                sb.append("username." + i + ": " + usernames[i] + "\n");
            }

            String[] passwords = getPasswords();

            for(int i = 0; i < passwords.length; ++i) {
                sb.append("password." + i + ": " + passwords[i] + "\n");
            }

            sb.append("clear.browser.cache: " + isClearBrowserCache() + "\n");
            sb.append("enable.video.recording: " + isEnableVideoRecording() + "\n");
            sb.append("capture.screenshot: " + isCaptureScreenshot() + "\n");
            sb.append("reporting.full.stack.trace: " + isReportingFullStackTrace() + "\n");
            sb.append("isJavascriptCaptureEnabled: " + isJavascriptCaptureEnabled() + "\n");
            sb.append("isProxyEnabled: " + isProxyEnabled() + "\n");
            sb.append("logger.level: " + getLoggerLevel() + "\n");
            sb.append("firefox.profile: " + getFirefoxProfile() + "\n");
            sb.append("firefox.assumeUntrustedCertificateIssuer: " + isFirefoxtAssumeUntrustedCertificateIssuer() + " (default = false)\n");
            sb.append("firefox.acceptUntrustedCertificates: " + isFirefoxAcceptUntrustedCertificates() + " (default = true)\n");
            sb.append("firefox.isenableNativeEventEnabled: " + isFirefoxEnableNativeEventEnabled() + "\n");
            sb.append("firefox.downloadable.mime.types: " + getFirefoxDownloadableMimeTypes() + "\n");
            sb.append("firefox.download.location: " + getFirefoxDownloadLocation() + "\n");
            sb.append("</pre>");
            return sb.toString();
        }

        public static OSTypeUtil.OSType getTestClientOSType() {
            String osType = System.getProperty("os.name");
            return OSTypeUtil.getTestClientOSType(osType);
        }

        public static String getDataDrivenSpreadsheet() throws Exception {
            File targetDataDrivenFile = null;
            targetDataDrivenFile = new File(getTestRoot() + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data-driven" + File.separator + properties.getProperty("datadriven.spreadsheet"));
            if (targetDataDrivenFile.exists()) {
                return String.valueOf(targetDataDrivenFile.toString()).trim();
            } else {
                InputStream reader = null;
                reader = TestConfig.class.getResourceAsStream("/data-driven/" + properties.getProperty("datadriven.spreadsheet"));
                if (reader.available() <= 0) {
                    return "Cannot find Excel file in the jar";
                } else {
                    (new File(targetDataDrivenFile.getParent())).mkdirs();
                    System.out.println(targetDataDrivenFile.getParent().toString());
                    FileOutputStream writer = new FileOutputStream(targetDataDrivenFile);

                    for(int ch = reader.read(); ch != -1; ch = reader.read()) {
                        writer.write(ch);
                    }

                    writer.close();
                    reader.close();
                    return String.valueOf(targetDataDrivenFile.getAbsolutePath().toString()).trim();
                }
            }
        }

        public static String getFirefoxProfile() {
            String profile = String.valueOf(properties.getProperty("firefox.profile"));
            return profile.equalsIgnoreCase("null") ? null : profile.trim();
        }

        public static String getFirefoxDownloadableMimeTypes() {
            String types = String.valueOf(properties.getProperty("firefox.downloadable.mime.types"));
            return types.equalsIgnoreCase("null") ? null : types.trim();
        }

        public static boolean isFirefoxEnableNativeEventEnabled() {
            String enableNativeEvent = String.valueOf(properties.getProperty("firefox.enableNativeEvents"));
            return enableNativeEvent.equalsIgnoreCase("true");
        }

        public static String getFirefoxDownloadLocation() {
            String loc = getTestRoot() + File.separator + "target" + File.separator + "firefox-download";
            return loc;
        }

        public static boolean isProxyEnabled() {
            String proxy = String.valueOf(properties.getProperty("proxy.enable")).trim();
            return proxy.equalsIgnoreCase("true");
        }

        public static boolean isJavascriptCaptureEnabled() {
            String env_enableJavascriptCapture = System.getProperty("firefox.javascript.capture");
            String prop_enableJavascriptCapture = String.valueOf(properties.getProperty("firefox.javascript.capture"));
            if (env_enableJavascriptCapture != null) {
                return env_enableJavascriptCapture.trim().equalsIgnoreCase("true");
            } else if (prop_enableJavascriptCapture != null) {
                return prop_enableJavascriptCapture.trim().equalsIgnoreCase("true");
            } else {
                return false;
            }
        }

        public static String getLoggerLevel() {
            String env_level = System.getProperty("logger.level");
            String prop_level = String.valueOf(properties.getProperty("logger.level"));
            if (env_level != null) {
                return env_level.trim();
            } else {
                return prop_level != null ? prop_level.trim() : null;
            }
        }

        public static String getUserDefinedProperty(String propertyName) {
            String env_propertyName = System.getProperty(propertyName);
            if (env_propertyName == null) {
                String prop_propertyName = String.valueOf(properties.getProperty(propertyName));
                return prop_propertyName == null ? null : prop_propertyName;
            } else {
                return env_propertyName;
            }
        }

        public static String getRetryAttempts() {
            String env_retry = System.getProperty("retry.attempts");
            String prop_retry = String.valueOf(properties.getProperty("retry.attempts"));
            if (env_retry != null) {
                return env_retry.trim();
            } else {
                return !prop_retry.equals("null") ? prop_retry.trim() : null;
            }
        }

        public static String getTestEnv() {
            String env_testenv = System.getProperty("test.env");
            String prop_testenv = String.valueOf(properties.getProperty("test.env"));
            if (env_testenv != null) {
                return env_testenv.trim();
            } else {
                return !prop_testenv.equals("null") ? prop_testenv.trim() : null;
            }
        }

        public static boolean isRuntimeResultsDispEnabled() {
            String env_enableRuntimeResultsDisplay = System.getProperty("runtime.results.display");
            String prop_enableRuntimeResultsDisplay = String.valueOf(properties.getProperty("runtime.results.display"));
            if (env_enableRuntimeResultsDisplay != null) {
                return env_enableRuntimeResultsDisplay.trim().equalsIgnoreCase("true");
            } else if (prop_enableRuntimeResultsDisplay != null) {
                return prop_enableRuntimeResultsDisplay.trim().equalsIgnoreCase("true");
            } else {
                return false;
            }
        }

        public static boolean isAdaptiveConversionEnabled() {
            String env_enableAdaptiveConversion = System.getProperty("adaptive.conversion");
            String prop_enableAdaptiveConversion = String.valueOf(properties.getProperty("adaptive.conversion"));
            if (env_enableAdaptiveConversion != null) {
                return env_enableAdaptiveConversion.trim().equalsIgnoreCase("true");
            } else if (prop_enableAdaptiveConversion != null) {
                return prop_enableAdaptiveConversion.trim().equalsIgnoreCase("true");
            } else {
                return false;
            }
        }

        public static int getSessionTimeout() {
            String env_timeout = System.getProperty("session.timeout");
            String prop_timeout = String.valueOf(properties.getProperty("session.timeout"));
            if (env_timeout != null) {
                return Integer.parseInt(env_timeout.trim());
            } else {
                return !prop_timeout.equals("null") ? Integer.parseInt(prop_timeout.trim()) : 0;
            }
        }

        static {
            try {
                testRoot = System.getProperty("testRoot");
                if (testRoot == null) {
                    testRoot = System.getProperty("user.dir");
                }

                testConfigFile = System.getProperty("testConfigFile");
                FileInputStream in = null;
                if (testConfigFile == null) {
                    in = new FileInputStream(testRoot + File.separator + "testConfig.properties");
                } else {
                    in = new FileInputStream(testRoot + File.separator + testConfigFile);
                }

                properties.load(in);
                in.close();
                testResultVideos = System.getProperty("testResultVideos");
                if (testResultVideos == null) {
                    testResultVideos = testRoot + File.separator + "target" + File.separator + "failsafe-reports" + File.separator + "videos";
                }

                testResultScreenshots = System.getProperty("testResultScreenshots");
                if (testResultScreenshots == null) {
                    testResultScreenshots = testRoot + File.separator + "target" + File.separator + "failsafe-reports" + File.separator + "screenshots";
                }

                testJavascriptErrors = System.getProperty("javascriptErrors");
                if (testJavascriptErrors == null) {
                    testJavascriptErrors = testRoot + File.separator + "target" + File.separator + "firefox-javascript-errors";
                }
            } catch (IOException var1) {
                System.err.println("Failed to read: " + testConfigFile);
            }

        }
}
