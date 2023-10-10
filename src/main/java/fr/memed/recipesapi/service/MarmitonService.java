package fr.memed.recipesapi.service;

import fr.memed.recipesapi.dto.Recipe;
import fr.memed.recipesapi.dto.SearchElement;
import fr.memed.recipesapi.mapper.RecipeMapper;
import fr.memed.recipesapi.mapper.SearchMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class MarmitonService {

    private final SearchMapper searchMapper;
    private final RecipeMapper recipeMapper;
    @Value("${marmiton.url}")
    private String marmitonUrl;

    @Value("${marmiton.search-endpoint}")
    private String marmitonSearch;

    public MarmitonService(final SearchMapper searchMapper,
                           final RecipeMapper recipeMapper) {
        this.searchMapper = searchMapper;
        this.recipeMapper = recipeMapper;
    }

    public List<SearchElement> searchRecipes(String filter) {
        try {
            Document document = Jsoup.connect(marmitonUrl + marmitonSearch + filter).get();
            return Objects.requireNonNull(document
                            .getElementsByClass("MRTN__sc-1gofnyi-0 YLcEb")
                            .first())
                    .childNodes()
                    .stream()
                    .map(searchMapper::fromMarmiton)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Recipe searchRecipe(String recipeName) {
        try {
            Document document = Jsoup.connect(marmitonUrl + recipeName).get();
            return recipeMapper.fromMarmiton(document);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
