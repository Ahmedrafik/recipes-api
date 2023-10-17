package fr.memed.recipesapi.mapper;

import fr.memed.recipesapi.dto.Difficulty;
import fr.memed.recipesapi.dto.Ingredient;
import fr.memed.recipesapi.dto.Recipe;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class RecipeMapper {

    @Autowired
    private IngredientMapper ingredientMapper;

    public Recipe fromMarmiton(Document document) {
        String title = ((TextNode) document.getElementsByClass("SHRD__sc-10plygc-0 itJBWW").first().childNode(0)).text();
        Element timesElement = document.getElementsByClass("RCP__sc-180phmr-3 eFXDQE").first();
        Long prepaTime = parseTime(0, Objects.requireNonNull(timesElement));
        Long restTime = parseTime(1, timesElement);
        Long bakingTime = parseTime(2, timesElement);
        String difficultyString = ((TextNode) document.getElementsByClass("RCP__sc-1qnswg8-1 iDYkZP").get(1).childNode(0)).text();
        return new Recipe(title, prepaTime, restTime, bakingTime,
                Difficulty.getDifficulty(difficultyString), ingredientList(document), stepsList(document));
    }

    private Long parseTime(int index, Element timesElement) {
        try {
            return Long.parseLong(((TextNode) timesElement.childNode(index).childNode(2).childNode(0)).text().split(" ")[0]);
        } catch (NumberFormatException exp) {
            return -1L;
        }
    }

    private List<Ingredient> ingredientList(Document document) {
        Long nbPeople = Long.parseLong(((TextNode) document.getElementsByClass("SHRD__sc-w4kph7-4 knYsyq").first().childNode(0)).text());
        return document.getElementsByClass("MuiGrid-root MuiGrid-item MuiGrid-grid-xs-3 MuiGrid-grid-sm-3")
                .stream()
                .map(element -> ingredientMapper.fromMarmiton(element, nbPeople))
                .toList();
    }

    private List<String> stepsList(Document document) {
        return document.getElementsByClass("RCP__sc-1wtzf9a-3")
                .stream()
                .map(element -> ((TextNode) element.childNode(0)).text())
                .toList();
    }

}
