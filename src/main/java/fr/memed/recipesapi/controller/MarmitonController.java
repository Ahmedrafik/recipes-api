package fr.memed.recipesapi.controller;

import fr.memed.recipesapi.dto.Recipe;
import fr.memed.recipesapi.dto.SearchElement;
import fr.memed.recipesapi.service.MarmitonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marmiton")
public class MarmitonController {

    private final MarmitonService marmitonService;

    public MarmitonController(MarmitonService marmitonService) {
        this.marmitonService = marmitonService;
    }


    @GetMapping
    public ResponseEntity<List<SearchElement>> recipes() {
        return new ResponseEntity<>(marmitonService.searchRecipes("tomate"), HttpStatus.OK);
    }

    @GetMapping("/recipe")
    public ResponseEntity<Recipe> recipe(@RequestHeader String recipeName) {
        return new ResponseEntity<>(marmitonService.searchRecipe(recipeName), HttpStatus.OK);
    }

}
