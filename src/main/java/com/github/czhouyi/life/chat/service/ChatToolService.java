package com.github.czhouyi.life.chat.service;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;

import java.util.Random;

@Service(Service.Level.PROJECT)
public final class ChatToolService {
    private final Project project;

    public ChatToolService(Project project) {
        this.project = project;
    }

    public int getRandomNum() {
        return new Random().nextInt(100);
    }
}
