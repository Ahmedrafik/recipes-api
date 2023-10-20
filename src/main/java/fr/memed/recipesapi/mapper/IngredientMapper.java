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
        Double quantity = mapQuantity(element.getElementsByClass("SHRD__sc-10plygc-0").get(0)) / nbPeople;
        String unit = mapUnit(element.getElementsByClass("SHRD__sc-10plygc-0").get(0));
        return new Ingredient(name, quantity, unit, pictureUrl);
    }

    private Double mapQuantity(Element element) {
        double quantity;
        try {
            quantity = element.childNodeSize() == 0 ? 0 : Double.parseDouble(((TextNode) element.childNode(0)).text());
        } catch (ClassCastException e) {
            quantity = Double.parseDouble(((TextNode) element.childNode(0).childNode(0)).text())
                    / Double.parseDouble(((TextNode) element.childNode(2).childNode(0)).text());
        }
        return quantity;
    }

    private String mapUnit(Element element) {
        if (element.childNodeSize() == 0 || element.childNodeSize() == 1) {
            return "";
        } else {
            try {
                return ((TextNode) element.childNode(element.childNodeSize() - 1)).text();
            } catch (ClassCastException e) {
                return ((TextNode) element.childNode(element.childNodeSize() - 1).childNode(0)).text();
            }
        }
    }
}
