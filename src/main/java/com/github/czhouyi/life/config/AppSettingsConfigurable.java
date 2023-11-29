package com.github.czhouyi.life.config;

import com.github.czhouyi.life.config.ui.AppSettingsComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;

import javax.swing.*;

public class AppSettingsConfigurable implements Configurable {
    private final Project project;
    private AppSettingsComponent appSettingsComponent;

    public AppSettingsConfigurable(Project project) {
        this.project = project;
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Life Extension Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return null;
    }

    @Override
    public JComponent createComponent() {
        appSettingsComponent = new AppSettingsComponent();
        return appSettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        AppSettingsState settingsState = AppSettingsState.getInstance();
        return appSettingsComponent.isChatEnabled() != settingsState.chatEnabled;
    }

    @Override
    public void apply() {
        AppSettingsState settingsState = AppSettingsState.getInstance();
        AppSettingsChangeContext changeContext = new AppSettingsChangeContext();
        changeContext.oldChatEnabled = settingsState.chatEnabled;
        changeContext.newChatEnabled = appSettingsComponent.isChatEnabled();
        settingsState.chatEnabled = appSettingsComponent.isChatEnabled();
        publish(changeContext);
    }

    private void publish(AppSettingsChangeContext changeContext) {
        SettingsChangedNotifier publisher = project.getMessageBus()
                .syncPublisher(SettingsChangedNotifier.SETTINGS_CHANGED_NOTIFIER_TOPIC);
        publisher.afterAction(changeContext);
    }

    @Override
    public void reset() {
        AppSettingsState settingsState = AppSettingsState.getInstance();
        appSettingsComponent.setChatEnabled(settingsState.chatEnabled);
    }

    @Override
    public void disposeUIResources() {
        appSettingsComponent = null;
    }
}
