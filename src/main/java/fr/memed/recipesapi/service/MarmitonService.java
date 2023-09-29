package fr.memed.recipesapi.service;

import fr.memed.recipesapi.mapper.MarmitonSearchMapper;
import fr.memed.recipesapi.model.SearchElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class MarmitonService {

    private final MarmitonSearchMapper marmitonSearchMapper;
    @Value("${marmiton.url}")
    private String marmitonEndpoint;

    public MarmitonService(MarmitonSearchMapper marmitonSearchMapper) {
        this.marmitonSearchMapper = marmitonSearchMapper;
    }

    public ResponseEntity<List<SearchElement>> searchRecipes(String filter) {
        try {
            Document document = Jsoup.connect(marmitonEndpoint + filter).get();
            List<SearchElement> searchElementList = Objects.requireNonNull(document
                            .getElementsByClass("MRTN__sc-1gofnyi-0 YLcEb")
                            .first())
                    .childNodes()
                    .stream()
                    .map(marmitonSearchMapper::fromMarmiton)
                    .toList();
            return new ResponseEntity<>(searchElementList, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
