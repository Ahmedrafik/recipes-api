package fr.memed.recipesapi.dto;

import java.util.List;

public record Recipe(String title, Long prepaTime, Long restTime, Long bakingTime, Difficulty difficulty,
                     List<Ingredient> ingredientForSingle, List<String> steps) {
}
