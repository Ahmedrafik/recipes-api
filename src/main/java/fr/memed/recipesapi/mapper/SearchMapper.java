package fr.memed.recipesapi.mapper;

import fr.memed.recipesapi.dto.SearchElement;
import fr.memed.recipesapi.utils.Constants;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SearchMapper {

    public SearchElement fromMarmiton(Element element) {
        String title = element.select(Constants.TITLE_CLASS).text();
        String recipeUrl = element.select("a").attr("abs:href");
        String pictureUrl = element.select(".recipe-card__picture img").attr("data-src");
        return new SearchElement(title, recipeUrl, pictureUrl);
    }
}
