package fr.memed.recipesapi.mapper;

import fr.memed.recipesapi.dto.Difficulty;
import fr.memed.recipesapi.dto.Ingredient;
import fr.memed.recipesapi.dto.Recipe;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class RecipeMapper {
    public Recipe fromMarmiton(Document document) {
        String title = ((TextNode) document.getElementsByClass("SHRD__sc-10plygc-0 itJBWW").first().childNode(0)).text();
        Element timesElement = document.getElementsByClass("RCP__sc-180phmr-3 eFXDQE").first();
        Long prepaTime = parseTime(0, Objects.requireNonNull(timesElement));
        Long restTime = parseTime(1, timesElement);
        Long bakingTime = parseTime(2, timesElement);
        String difficultyString = ((TextNode) document.getElementsByClass("RCP__sc-1qnswg8-1 iDYkZP").get(1).childNode(0)).text();
        return new Recipe(title, prepaTime, restTime, bakingTime,
                Difficulty.getDifficulty(difficultyString), new HashMap<>(), new ArrayList<>());
    }

    private Long parseTime(int index, Element timesElement) {
        try {
            return Long.parseLong(((TextNode) timesElement.childNode(index).childNode(2).childNode(0)).text().split(" ")[0]);
        } catch (NumberFormatException exp) {
            return -1L;
        }
    }

    private Map<Ingredient, Double> ingredientList(Document document) {
        Long nbPeople = Long.parseLong(((TextNode) document.getElementsByClass("SHRD__sc-w4kph7-4 knYsyq").first().childNode(0)).text());
        return null;
    }

}
