package com.github.czhouyi.life.config;

public class ConfigUtil {

    public static boolean isChatEnabled() {
        return AppSettingsState.getInstance().chatEnabled;
    }
}
