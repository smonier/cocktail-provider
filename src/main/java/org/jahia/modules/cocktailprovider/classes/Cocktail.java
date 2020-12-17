package org.jahia.modules.cocktailprovider.classes;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Cocktail implements Serializable {

    private String id;
    private String name;
    private String instructions;
    private String[] tags;
    private String[] categories;
    private String cocktailGlass;
    private String cocktailThumb;
    private String[][] ingredients;
    private String dateModified;

    public Cocktail(String id,String name,String instructions,String[] tags,String[] categories,String cocktailGlass,String cocktailThumb,String[][] ingredients,String dateModified) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.tags = tags;
        this.categories = categories;
        this.cocktailGlass = cocktailGlass;
        this.cocktailThumb = cocktailThumb;
        this.ingredients = ingredients;
        this.dateModified = dateModified;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getCocktailGlass() {
        return cocktailGlass;
    }

    public void setCocktailGlass(String cocktailGlass) {
        this.cocktailGlass = cocktailGlass;
    }

    public String getCocktailThumb() {
        return cocktailThumb;
    }

    public void setCocktailThumb(String cocktailThumb) {
        this.cocktailThumb = cocktailThumb;
    }

    public String[][] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[][] ingredients) {
        this.ingredients = ingredients;
    }

    public String getDateModified() throws ParseException {
        if (dateModified != "null"){
            String ds1 = dateModified.substring(0, 10);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("E, dd MMM yyyy");
            String formatedDate = sdf2.format(sdf1.parse(ds1));
            return formatedDate;
        } else {
            return null;
        }
    }
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
