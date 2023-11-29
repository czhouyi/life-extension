package com.github.czhouyi.life.config.ui;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;

public class AppSettingsComponent {

    private final JPanel mainPanel;
    private final JBCheckBox cbChatEnable = new JBCheckBox("Enable chat");

    public AppSettingsComponent() {
        mainPanel = FormBuilder.createFormBuilder()
                .addComponent(cbChatEnable, 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public boolean isChatEnabled() {
        return cbChatEnable.isSelected();
    }

    public void setChatEnabled(boolean enabled) {
        cbChatEnable.setSelected(enabled);
    }
}
