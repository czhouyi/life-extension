package com.github.czhouyi.life.config;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBusConnection;

public class ChangedListener implements Disposable {
    protected final Project project;
    protected final MessageBusConnection connection;

    public ChangedListener(Project project) {
        this.project = project;
        connection = project.getMessageBus().connect();
    }

    @Override
    public void dispose() {
        connection.disconnect();
    }
}
