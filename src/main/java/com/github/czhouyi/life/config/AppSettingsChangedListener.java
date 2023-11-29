package com.github.czhouyi.life.config;

import com.github.czhouyi.life.chat.toolWindow.ChatToolWindowFactory;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;

@Service(Service.Level.PROJECT)
public final class AppSettingsChangedListener extends ChangedListener {

    public AppSettingsChangedListener(Project project) {
        super(project);
        init();
    }

    private void init() {
        connection.subscribe(SettingsChangedNotifier.SETTINGS_CHANGED_NOTIFIER_TOPIC,
                (SettingsChangedNotifier) changeContext -> {
                    ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(ChatToolWindowFactory.TOOL_WINDOW_ID);
                    if (toolWindow == null) {
                        return;
                    }
                    if (!changeContext.newChatEnabled && changeContext.oldChatEnabled) {
                        toolWindow.setAvailable(false);
                    } else if (changeContext.newChatEnabled && !changeContext.oldChatEnabled) {
                        toolWindow.setAvailable(true);
                    }
                });
    }
}
