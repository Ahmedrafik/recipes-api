package fr.memed.recipesapi.controller;

import fr.memed.recipesapi.model.SearchElement;
import fr.memed.recipesapi.service.MarmitonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        return marmitonService.searchRecipes("tomate");
    }
}
