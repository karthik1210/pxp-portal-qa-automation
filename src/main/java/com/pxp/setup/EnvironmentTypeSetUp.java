package com.pxp.setup;

public class EnvironmentTypeSetUp {

        public EnvironmentTypeSetUp() {
        }

        public static EnvironmentTypeSetUp.EnvironmentType getEnvironmentType(String env) {
            if (env == null) {
                return EnvironmentTypeSetUp.EnvironmentType.DEV3;
            } else if (env.equalsIgnoreCase("DEMO")) {
                return EnvironmentTypeSetUp.EnvironmentType.DEMO;
            } else if (env.equalsIgnoreCase("QA1")) {
                return EnvironmentTypeSetUp.EnvironmentType.QA1;
            } else if (env.equalsIgnoreCase("QA")) {
                return EnvironmentTypeSetUp.EnvironmentType.QA;
            } else if (env.equalsIgnoreCase("PROD")) {
                return EnvironmentTypeSetUp.EnvironmentType.PROD;
            } else if (env.equalsIgnoreCase("DEV3")) {
                return EnvironmentTypeSetUp.EnvironmentType.DEV3;
            } else if (env.equalsIgnoreCase("DEV")) {
                return EnvironmentTypeSetUp.EnvironmentType.DEV;
            } else {
                return env.equalsIgnoreCase("VAGRANT") ? EnvironmentTypeSetUp.EnvironmentType.VAGRANT : EnvironmentTypeSetUp.EnvironmentType.DEV3;
            }
        }

        public static enum EnvironmentType {
            DEV3,
            DEV,
            QA1,
            QA,
            DEMO,
            PROD,
            VAGRANT;

            private EnvironmentType() {
            }
        }
}
