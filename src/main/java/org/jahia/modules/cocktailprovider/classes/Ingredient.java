package org.jahia.modules.cocktailprovider.classes;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private String id;
    private String name;
    private String description;
    private String type;
    private String alcohol;
    private String ingredientThumb;

    public Ingredient(String id, String name, String description, String type, String alcohol) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.alcohol = alcohol;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getIngredientThumb() {
        return "https://www.thecocktaildb.com/images/ingredients/"+name+"-Medium.png";
    }

    public void setIngredientThumb(String name) {
        this.ingredientThumb = "https://www.thecocktaildb.com/images/ingredients/"+name+"-Medium.png";
    }
}
