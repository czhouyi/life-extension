package com.github.czhouyi.life.config;

import com.intellij.util.messages.Topic;

public interface SettingsChangedNotifier {

    @Topic.ProjectLevel
    Topic<SettingsChangedNotifier> SETTINGS_CHANGED_NOTIFIER_TOPIC =
            Topic.create("settings_changed", SettingsChangedNotifier.class);

    void afterAction(AppSettingsChangeContext changeContext);
}
