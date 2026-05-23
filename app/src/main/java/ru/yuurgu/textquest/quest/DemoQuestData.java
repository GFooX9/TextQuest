package ru.yuurgu.textquest.quest;

import java.util.Arrays;
import java.util.List;

import ru.yuurgu.textquest.model.Artifact;
import ru.yuurgu.textquest.model.CharacterStats;
import ru.yuurgu.textquest.model.QuestChoice;
import ru.yuurgu.textquest.model.QuestScreen;
import ru.yuurgu.textquest.model.QuestStatus;

/**
 * Заглушка сюжета в духе «Космических рейнджеров».
 * Позже замените на полноценный граф квеста (QuestEngine).
 */
public final class DemoQuestData {

    public static final String GOAL =
            "Ограбить малокский банк на планете Малок-7 и добыть бриллиант Софиты для президента.";

    public static final CharacterStats CHARACTER = new CharacterStats(
            "Рейнджер Кейн",
            85,
            12,
            8,
            5,
            6
    );

    public static final List<Artifact> ARTIFACTS = Arrays.asList(
            new Artifact("Взломщик сейфов", "набор отмычек и декодер", 0, 3, 0),
            new Artifact("Плазменный резак", "режет бронированные двери", 2, 0, 0)
    );

    private static final QuestScreen INTRO = new QuestScreen(
            "Наш президент — страстный коллекционер. Он желает заполучить знаменитый бриллиант Софиты.\n"
                    + "Единственный способ — ограбить малокский банк на планете Малок-7. Команда в сборе,\n"
                    + "не хватает только взломщика сейфов — вас. Прибыть нужно до полуночи, иначе наймут другого.\n\n"
                    + "Вы стоите у служебного входа. Охранник курит у шлюза, внутри слышен гул генераторов.",
            Arrays.asList(
                    new QuestChoice("stealth", "Проскользнуть мимо охранника в тени"),
                    new QuestChoice("talk", "Отвлечь охранника разговором"),
                    new QuestChoice("force", "Взять штурмом — рискованно, но быстро")
            )
    );

    private static final QuestScreen AFTER_STEALTH = new QuestScreen(
            "Вы бесшумно проходите в хранилище. Сейф Софиты за энергетическим полем.\n"
                    + "На панели мигает индикатор — до смены охраны двадцать минут.",
            Arrays.asList(
                    new QuestChoice("hack", "Взломать поле взломщиком сейфов"),
                    new QuestChoice("overload", "Перегрузить генератор — шумно, но поле упадёт")
            )
    );

    private static final QuestScreen SUCCESS = QuestScreen.finished(
            "Поле падает. Сейф открывается — бриллиант Софиты сияет холодным светом.\n"
                    + "Команда ждёт у шаттла. Президент будет доволен, а вам обещали щедрую долю из хранилища.",
            QuestStatus.COMPLETE
    );

    private static final QuestScreen FAIL = QuestScreen.finished(
            "Тревога! Охрана банка блокирует все выходы. Грабители рассеиваются,\n"
                    + "а вас объявляют в розыск по всей системе. Бриллиант остаётся в сейфе.",
            QuestStatus.FAILED
    );

    private DemoQuestData() {
    }

    public static QuestScreen initialScreen() {
        return INTRO;
    }

    public static QuestScreen nextScreen(String choiceId) {
        switch (choiceId) {
            case "stealth":
                return AFTER_STEALTH;
            case "talk":
            case "hack":
                return SUCCESS;
            case "force":
            case "overload":
                return FAIL;
            default:
                return INTRO;
        }
    }
}
