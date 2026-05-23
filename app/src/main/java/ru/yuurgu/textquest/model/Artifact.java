package ru.yuurgu.textquest.model;

import java.util.ArrayList;
import java.util.List;

public final class Artifact {

    private final String name;
    private final String description;
    private final int bonusDamage;
    private final int bonusMagic;
    private final int bonusDefense;

    public Artifact(String name, String description) {
        this(name, description, 0, 0, 0);
    }

    public Artifact(String name, String description, int bonusDamage, int bonusMagic, int bonusDefense) {
        this.name = name;
        this.description = description;
        this.bonusDamage = bonusDamage;
        this.bonusMagic = bonusMagic;
        this.bonusDefense = bonusDefense;
    }

    public String displayLine() {
        StringBuilder line = new StringBuilder(name);
        if (description != null && !description.trim().isEmpty()) {
            line.append(" — ").append(description);
        }

        List<String> bonuses = new ArrayList<>();
        if (bonusDamage != 0) {
            bonuses.add("урон " + formatBonus(bonusDamage));
        }
        if (bonusMagic != 0) {
            bonuses.add("магия " + formatBonus(bonusMagic));
        }
        if (bonusDefense != 0) {
            bonuses.add("защита " + formatBonus(bonusDefense));
        }

        if (!bonuses.isEmpty()) {
            line.append(" (");
            for (int i = 0; i < bonuses.size(); i++) {
                if (i > 0) {
                    line.append(", ");
                }
                line.append(bonuses.get(i));
            }
            line.append(')');
        }
        return line.toString();
    }

    private static String formatBonus(int value) {
        return value > 0 ? "+" + value : String.valueOf(value);
    }
}
