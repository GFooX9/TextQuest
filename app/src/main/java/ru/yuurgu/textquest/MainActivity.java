package ru.yuurgu.textquest;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;

import ru.yuurgu.textquest.databinding.ActivityMainBinding;
import ru.yuurgu.textquest.databinding.ItemStatRowBinding;
import ru.yuurgu.textquest.model.Artifact;
import ru.yuurgu.textquest.model.CharacterStats;
import ru.yuurgu.textquest.model.QuestChoice;
import ru.yuurgu.textquest.model.QuestScreen;
import ru.yuurgu.textquest.model.QuestStatus;
import ru.yuurgu.textquest.quest.DemoQuestData;
import ru.yuurgu.textquest.quest.QuestEngine;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final QuestEngine questEngine = new QuestEngine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bindCharacter(DemoQuestData.CHARACTER);
        bindArtifacts(DemoQuestData.ARTIFACTS);
        binding.goalText.setText(DemoQuestData.GOAL);

        binding.btnConfirmChoice.setOnClickListener(v -> onConfirmChoice());

        renderScreen(questEngine.currentScreen());
    }

    private void bindCharacter(@NonNull CharacterStats stats) {
        binding.characterName.setText(stats.getName());
        bindStatRow(binding.rowHealth, R.string.label_health, stats.getHealth());
        bindStatRow(binding.rowStrength, R.string.label_strength, stats.getStrength());
        bindStatRow(binding.rowDamage, R.string.label_damage, stats.getDamage());
        bindStatRow(binding.rowMagic, R.string.label_magic, stats.getMagic());
        bindStatRow(binding.rowDefense, R.string.label_defense, stats.getDefense());
    }

    private void bindStatRow(@NonNull ItemStatRowBinding row, int labelRes, int value) {
        row.statLabel.setText(labelRes);
        row.statValue.setText(String.valueOf(value));
    }

    private void bindArtifacts(@NonNull List<Artifact> artifacts) {
        if (artifacts.isEmpty()) {
            binding.artifactsText.setText(R.string.no_artifacts);
            return;
        }

        StringBuilder text = new StringBuilder();
        for (int i = 0; i < artifacts.size(); i++) {
            if (i > 0) {
                text.append('\n');
            }
            text.append(artifacts.get(i).displayLine());
        }
        binding.artifactsText.setText(text.toString());
    }

    private void renderScreen(@NonNull QuestScreen screen) {
        binding.storyText.setText(screen.getStory());
        binding.storyScroll.scrollTo(0, 0);
        renderChoices(screen.getChoices());
        renderStatus(screen.getStatus());
    }

    private void renderChoices(@NonNull List<QuestChoice> choices) {
        binding.choicesGroup.removeAllViews();
        boolean active = !choices.isEmpty();
        int visibility = active ? View.VISIBLE : View.GONE;
        binding.choicesScroll.setVisibility(visibility);
        binding.choicesLabel.setVisibility(visibility);
        binding.btnConfirmChoice.setEnabled(active);

        for (int index = 0; index < choices.size(); index++) {
            QuestChoice choice = choices.get(index);
            RadioButton button = new RadioButton(this);
            button.setId(View.generateViewId());
            button.setText(choice.getText());
            button.setTag(choice.getId());
            button.setTextColor(ContextCompat.getColor(this, R.color.quest_text_primary));
            button.setPadding(0, 12, 0, 12);
            if (index == 0) {
                button.setChecked(true);
            }
            binding.choicesGroup.addView(button);
        }
    }

    private void renderStatus(@NonNull QuestStatus status) {
        int textRes;
        int colorRes;
        switch (status) {
            case COMPLETE:
                textRes = R.string.quest_status_complete;
                colorRes = R.color.quest_success;
                break;
            case FAILED:
                textRes = R.string.quest_status_failed;
                colorRes = R.color.quest_danger;
                break;
            case ACTIVE:
            default:
                textRes = R.string.quest_status_active;
                colorRes = R.color.quest_accent;
                break;
        }

        binding.questStatus.setText(textRes);
        binding.questStatus.setTextColor(ContextCompat.getColor(this, colorRes));
        binding.btnConfirmChoice.setVisibility(
                status == QuestStatus.ACTIVE ? View.VISIBLE : View.GONE
        );
    }

    private void onConfirmChoice() {
        int checkedId = binding.choicesGroup.getCheckedRadioButtonId();
        if (checkedId == -1) {
            return;
        }

        RadioButton button = binding.choicesGroup.findViewById(checkedId);
        Object tag = button.getTag();
        if (!(tag instanceof String)) {
            return;
        }

        QuestScreen next = questEngine.applyChoice((String) tag);
        renderScreen(next);
    }
}
