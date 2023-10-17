package fr.memed.recipesapi.mapper;

import fr.memed.recipesapi.dto.Ingredient;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class IngredientMapper {

    public Ingredient fromMarmiton(Element element, Long nbPeople) {
        String name = ((TextNode) element.getElementsByClass("RCP__sc-8cqrvd-3").get(0).childNode(0)).text();
        String pictureUrl = element.getElementsByClass("SHRD__sc-dy77ha-0").get(0).attr("data-src");
        Double quantity = Double.valueOf(((TextNode) element.getElementsByClass("SHRD__sc-10plygc-0").get(0).childNode(0)).text()) / nbPeople;
        return new Ingredient(name, quantity, pictureUrl);
    }
}
