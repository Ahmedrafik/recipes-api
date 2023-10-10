package fr.memed.recipesapi.dto;

import java.util.List;
import java.util.Map;

public record Recipe(String title, Long prepaTime, Long restTime, Long bakingTime, Difficulty difficulty,
                     Map<Ingredient, Double> ingredientForSingle, List<String> steps) {
}
