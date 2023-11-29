package com.github.czhouyi.life.chat.toolWindow;

import com.github.czhouyi.life.CustomBundle;
import com.github.czhouyi.life.chat.service.ChatToolService;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;

import javax.swing.*;

public class ChatToolWindow {
    private final ChatToolService chatToolService;

    public ChatToolWindow(ToolWindow toolWindow) {
        chatToolService = toolWindow.getProject().getService(ChatToolService.class);
    }

    public JComponent getPanel() {
        JBPanel<?> panel = new JBPanel<>();
        JBLabel label = new JBLabel(CustomBundle.message("randomLabel", "?"));
        panel.add(label);
        JButton shuffle = new JButton(CustomBundle.message("shuffle"));
        shuffle.addActionListener(e -> label.setText(
                CustomBundle.message("randomLabel", chatToolService.getRandomNum())));
        panel.add(shuffle);
        return panel;
    }
}
