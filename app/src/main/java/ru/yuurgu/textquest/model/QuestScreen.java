package ru.yuurgu.textquest.model;

import java.util.Collections;
import java.util.List;

public final class QuestScreen {

    private final String story;
    private final List<QuestChoice> choices;
    private final QuestStatus status;

    public QuestScreen(String story, List<QuestChoice> choices) {
        this(story, choices, QuestStatus.ACTIVE);
    }

    public QuestScreen(String story, List<QuestChoice> choices, QuestStatus status) {
        this.story = story;
        this.choices = choices;
        this.status = status;
    }

    public String getStory() {
        return story;
    }

    public List<QuestChoice> getChoices() {
        return choices;
    }

    public QuestStatus getStatus() {
        return status;
    }

    public static QuestScreen finished(String story, QuestStatus status) {
        return new QuestScreen(story, Collections.emptyList(), status);
    }
}
