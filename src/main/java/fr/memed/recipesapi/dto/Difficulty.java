package fr.memed.recipesapi.dto;

import java.util.Arrays;

public enum Difficulty {

    VERY_EASY("très facile"),
    EASY("facile"),
    NORMAL("moyen"),
    HARD("difficile"),
    VERY_HARD("très difficile");

    private final String difficulte;

    Difficulty(String difficulte) {
        this.difficulte = difficulte;
    }

    public static Difficulty getDifficulty(String difficulte) {
        return Arrays.stream(Difficulty.values())
                .filter(difficulty -> difficulty.getDifficulte().equals(difficulte))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("No difficulty corresponding to %s", difficulte)));

    }

    public String getDifficulte() {
        return difficulte;
    }

    @Override
    public String toString() {
        return "Difficulty{" +
                "difficulte='" + difficulte + '\'' +
                '}';
    }
}
