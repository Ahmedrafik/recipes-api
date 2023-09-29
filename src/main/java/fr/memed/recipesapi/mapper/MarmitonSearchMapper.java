package fr.memed.recipesapi.mapper;

import fr.memed.recipesapi.model.SearchElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MarmitonSearchMapper {

    public SearchElement fromMarmiton(Node node) {
        String title = ((TextNode) node.childNode(0).childNode(1).childNode(0)).text();
        String recipeUrl = node.attr("href");
        String pictureUrl = node.childNode(0).childNode(0).childNode(0).attr("src");
        return new SearchElement(title, recipeUrl, pictureUrl);
    }
}
