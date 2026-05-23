package ru.yuurgu.textquest.quest;

import ru.yuurgu.textquest.model.QuestScreen;
import ru.yuurgu.textquest.model.QuestStatus;

/**
 * Движок квеста: здесь будет граф сцен и проверка цели.
 * Сейчас делегирует демо-данным для проверки макета.
 */
public class QuestEngine {

    private QuestScreen current = DemoQuestData.initialScreen();

    public QuestScreen currentScreen() {
        return current;
    }

    public QuestScreen applyChoice(String choiceId) {
        if (current.getStatus() != QuestStatus.ACTIVE) {
            return current;
        }
        current = DemoQuestData.nextScreen(choiceId);
        return current;
    }

    public void reset() {
        current = DemoQuestData.initialScreen();
    }
}
