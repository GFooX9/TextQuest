package ru.yuurgu.textquest.model;

public final class QuestChoice {

    private final String id;
    private final String text;

    public QuestChoice(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
