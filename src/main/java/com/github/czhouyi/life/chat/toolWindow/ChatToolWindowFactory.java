package com.github.czhouyi.life.chat.toolWindow;

import com.github.czhouyi.life.config.AppSettingsChangedListener;
import com.github.czhouyi.life.config.ConfigUtil;
import com.github.czhouyi.life.config.OpenPluginSettingsAction;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChatToolWindowFactory implements ToolWindowFactory {
    public static final String TOOL_WINDOW_ID = "Chat";

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ChatToolWindow chatToolWindow = new ChatToolWindow(toolWindow);
        Content content = ApplicationManager.getApplication().getService(ContentFactory.class)
                .createContent(chatToolWindow.getPanel(), "", false);
        toolWindow.getContentManager().addContent(content);

        DefaultActionGroup defaultActionGroup = new DefaultActionGroup();
        defaultActionGroup.add(new OpenPluginSettingsAction("Chat Settings..."));
        defaultActionGroup.addSeparator();
        toolWindow.setAdditionalGearActions(defaultActionGroup);
        List<AnAction> titleActions = new ArrayList<>();
        createTitleActions(titleActions);
        if (!titleActions.isEmpty()) {
            toolWindow.setTitleActions(titleActions);
        }
        project.getService(AppSettingsChangedListener.class);
    }

    public void createTitleActions(List<AnAction> actions) {
        AnAction action = ActionManager.getInstance().getAction("ChatActionsGroup");
        if (action != null) {
            actions.add(action);
        }
    }

    @Override
    public boolean shouldBeAvailable(@NotNull Project project) {
        return ConfigUtil.isChatEnabled();
    }
}
