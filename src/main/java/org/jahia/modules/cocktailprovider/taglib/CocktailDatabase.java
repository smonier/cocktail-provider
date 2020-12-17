package org.jahia.modules.cocktailprovider.taglib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.jahia.modules.cocktailprovider.cache.CrunchifyInMemoryCache;
import org.jahia.modules.cocktailprovider.classes.Cocktail;
import org.jahia.modules.cocktailprovider.classes.Ingredient;


public class CocktailDatabase {
    private static final String COCKTAILDB_API_KEY_ATTR = "apiKeyValue";

    private static final Logger logger = LoggerFactory.getLogger(CocktailDatabase.class);
    private static String apiKeyValue;
    private static CrunchifyInMemoryCache<String, String> cache = new CrunchifyInMemoryCache<String, String>(1200, 500, 200);

    public void setApiKeyValue(String apiKeyValue) {
        if (apiKeyValue.startsWith("'")) {
            this.apiKeyValue = apiKeyValue.replaceAll("'", "");
        } else {
            this.apiKeyValue = apiKeyValue;
        }
    }

    private static String API_URL = "https://www.thecocktaildb.com/api/json/v1/";
    private static String API_COCKTAIL_BYNAME = "/search.php?s=";
    private static String API_COCKTAIL_BYLETTER = "/search.php?f=";
    private static String API_INGREDIENT_BYNAME = "/search.php?i=";
    private static String API_COCKTAIL_BYID = "/lookup.php?i=";
    private static String API_INGREDIENT_BYID = "/lookup.php?iid=";
    private static String API_COCKTAIL_BYINGREDIENT = "/filter.php?i=";
    private static String API_INGREDIENT_LIST = "/list.php?i=list";
    private static String API_CATEGORIES_LIST = "/list.php?c=list";

    public static List<Cocktail> getCocktailByName(String Name) throws RepositoryException, IOException, JSONException, URISyntaxException {
        String cdbUrl = API_URL + apiKeyValue + API_COCKTAIL_BYNAME + URLEncoder.encode(Name, StandardCharsets.UTF_8);
        String jsonString = null;
        logger.info("**getCocktailByName** URL: " + cdbUrl);

        logger.info("Checking if Response already in cache ...");
        logger.info("Cache Size: " + cache.size());
        if (cache.get(cdbUrl) != null) {
            jsonString = cache.get(cdbUrl);
            logger.info("Get Response from Cache ...");

        } else {
            jsonString = readJsonFromUrl(cdbUrl);
            cache.put(cdbUrl, jsonString);
            logger.info("Put Response in Cache ...");
            logger.info("Call API: " + cdbUrl);
        }
        try {
            JSONObject cocktailApiJsonObject = new JSONObject(jsonString);
            JSONArray cocktailArray = new JSONArray(cocktailApiJsonObject.getString("drinks"));
            return getCocktails(cocktailArray);
        } catch (JSONException e) {
            logger.error("Error parsing JSONObject in JSONArray: " + API_COCKTAIL_BYNAME);
            e.printStackTrace();
            return null;
        }
    }

    public static List<Cocktail> getCocktailByLetter(String Letter) throws
            RepositoryException, IOException, JSONException {
        String cdbUrl = API_URL + apiKeyValue + API_COCKTAIL_BYLETTER + Letter.charAt(0);
        String jsonString = null;
        logger.info("**getCocktailByLetter** URL: " + cdbUrl);

        logger.info("Checking if Response already in cache ...");
        logger.info("Cache Size: " + cache.size());
        if (cache.get(cdbUrl) != null) {
            jsonString = cache.get(cdbUrl);
            logger.info("Get Response from Cache ...");

        } else {
            jsonString = readJsonFromUrl(cdbUrl);
            cache.put(cdbUrl, jsonString);
            logger.info("Put Response in Cache ...");
            logger.info("Call API: " + cdbUrl);
        }
        try {
            JSONObject cocktailApiJsonObject = new JSONObject(jsonString);
            JSONArray cocktailArray = new JSONArray(cocktailApiJsonObject.getString("drinks"));
            return getCocktails(cocktailArray);
        } catch (JSONException e) {
            logger.error("Error parsing JSONObject in JSONArray: " + API_COCKTAIL_BYLETTER);
            e.printStackTrace();
            return null;
        }
    }

    public static Cocktail getCocktailById(String Id) throws RepositoryException, JSONException, IOException {
        String cdbUrl = API_URL + apiKeyValue + API_COCKTAIL_BYID + Id;
        String jsonString = null;
        logger.info("**getCocktailById** URL: " + cdbUrl);

        logger.info("Checking if Response already in cache ...");
        logger.info("Cache Size: " + cache.size());
        if (cache.get(cdbUrl) != null) {
            jsonString = cache.get(cdbUrl);
            logger.info("Get Response from Cache ...");

        } else {
            jsonString = readJsonFromUrl(cdbUrl);
            cache.put(cdbUrl, jsonString);
            logger.info("Put Response in Cache ...");
            logger.info("Call API: " + cdbUrl);
        }
        try {
            JSONObject cocktailApiJsonObject = new JSONObject(jsonString);
            JSONArray cocktailArray = new JSONArray(cocktailApiJsonObject.getString("drinks"));
            List<Cocktail> cocktails = getCocktails(cocktailArray);
            return cocktails.isEmpty() ? null : cocktails.get(0);
        } catch (JSONException e) {
            logger.error("Error parsing JSONObject in JSONArray: " + API_COCKTAIL_BYID);
            e.printStackTrace();
            return null;
        }
    }

    public static List<Cocktail> getCocktailByIngredient(String Ingredient) throws
            RepositoryException, IOException, JSONException {
        String cdbUrl = API_URL + apiKeyValue + API_COCKTAIL_BYINGREDIENT + URLEncoder.encode(Ingredient, StandardCharsets.UTF_8);
        String jsonString = null;
        logger.info("**getCocktailByIngredient** URL: " + cdbUrl);

        logger.info("Checking if Response already in cache ...");
        logger.info("Cache Size: " + cache.size());
        if (cache.get(cdbUrl) != null) {
            jsonString = cache.get(cdbUrl);
            logger.info("Get Response from Cache ...");

        } else {
            jsonString = readJsonFromUrl(cdbUrl);
            cache.put(cdbUrl, jsonString);
            logger.info("Put Response in Cache ...");
            logger.info("Call API: " + cdbUrl);
        }
        try {
            JSONObject cocktailApiJsonObject = new JSONObject(jsonString);
            JSONArray cocktailArray = new JSONArray(cocktailApiJsonObject.getString("drinks"));
            return getCocktailsByIngredient(cocktailArray);
        } catch (JSONException e) {
            logger.error("Error parsing JSONObject in JSONArray: " + API_COCKTAIL_BYINGREDIENT);
            e.printStackTrace();
            return null;
        }
    }

    public static List<Ingredient> getIngredientByName(String Name) throws
            RepositoryException, IOException, JSONException {
        String cdbUrl = API_URL + apiKeyValue + API_INGREDIENT_BYNAME + URLEncoder.encode(Name, StandardCharsets.UTF_8);
        String jsonString = null;
        logger.info("**getIngredientByName** URL: " + cdbUrl);

        logger.info("Checking if Response already in cache ...");
        logger.info("Cache Size: " + cache.size());
        if (cache.get(cdbUrl) != null) {
            jsonString = cache.get(cdbUrl);
            logger.info("Get Response from Cache ...");

        } else {
            jsonString = readJsonFromUrl(cdbUrl);
            cache.put(cdbUrl, jsonString);
            logger.info("Put Response in Cache ...");
            logger.info("Call API: " + cdbUrl);
        }

        try {
            JSONObject ingrdientApiJsonObject = new JSONObject(jsonString);
            JSONArray ingredientArray = new JSONArray(ingrdientApiJsonObject.getString("ingredients"));
            return getIngredient(ingredientArray);
        } catch (JSONException e) {
            logger.error("Error parsing JSONObject in JSONArray: " + API_INGREDIENT_BYNAME);
            e.printStackTrace();
            return null;
        }
    }

    public static List<Ingredient> getIngredientById(String Id) throws
            RepositoryException, IOException, JSONException {
        String cdbUrl = API_URL + apiKeyValue + API_INGREDIENT_BYID + Id;
        String jsonString = null;
        logger.info("**getIngredientById** URL: " + cdbUrl);

        logger.info("Checking if Response already in cache ...");
        logger.info("Cache Size: " + cache.size());
        if (cache.get(cdbUrl) != null) {
            jsonString = cache.get(cdbUrl);
            logger.info("Get Response from Cache ...");

        } else {
            jsonString = readJsonFromUrl(cdbUrl);
            cache.put(cdbUrl, jsonString);
            logger.info("Put Response in Cache ...");
            logger.info("Call API: " + cdbUrl);
        }

        try {
            JSONObject ingrdientApiJsonObject = new JSONObject(jsonString);
            JSONArray ingredientArray = new JSONArray(ingrdientApiJsonObject.getString("ingredients"));
            return getIngredient(ingredientArray);
        } catch (JSONException e) {
            logger.error("Error parsing JSONObject in JSONArray: " + API_INGREDIENT_BYID);
            e.printStackTrace();
            return null;
        }
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            //  JSONObject json = new JSONObject(jsonText);
            return jsonText;
        } finally {
            is.close();
        }
    }

    public static List<Cocktail> getCocktails(JSONArray cocktailArray) throws IOException, JSONException {
        List<Cocktail> COCKTAIL_ARRAY_LIST = new ArrayList<>();
        try {
            for (int i = 0; i < cocktailArray.length(); i++) {
                JSONObject array1 = cocktailArray.getJSONObject(i);
                String tempId = array1.getString("idDrink");
                String tempName = array1.getString("strDrink");
                String tempInstructions = array1.getString("strInstructions");
                String[] tempTags = array1.getString("strTags").split("\\,", -1);
                String[] tempCategories = array1.getString("strCategory").split("\\,", -1);
                String tempCocktailGlass = array1.getString("strGlass");
                String tempCocktailThumb = array1.getString("strDrinkThumb");
                String[][] tempIngredients = new String[15][2];
                for (int j = 0; j < 15; j++) {
                    tempIngredients[j][0] = array1.getString("strIngredient" + (j + 1));
                    tempIngredients[j][1] = array1.getString("strMeasure" + (j + 1));
                }
                String tempDateModified = array1.getString("dateModified");
                COCKTAIL_ARRAY_LIST.add(new Cocktail(tempId, tempName, tempInstructions, tempTags, tempCategories, tempCocktailGlass, tempCocktailThumb, tempIngredients, tempDateModified));
            }

        } catch (JSONException e) {
            logger.error("Error parsing JSONObject in JSONArray: " + API_COCKTAIL_BYNAME);
            e.printStackTrace();
        }
        return COCKTAIL_ARRAY_LIST;
    }

    public static List<Cocktail> getCocktailsByIngredient(JSONArray cocktailArray) throws
            IOException, JSONException {
        List<Cocktail> COCKTAIL_ARRAY_LIST = new ArrayList<>();
        try {
            for (int i = 0; i < cocktailArray.length(); i++) {
                JSONObject array1 = cocktailArray.getJSONObject(i);
                String tempId = array1.getString("idDrink");

                COCKTAIL_ARRAY_LIST.add(getCocktailById(tempId));
            }

        } catch (JSONException | RepositoryException e) {
            logger.error("Error parsing JSONObject in JSONArray: " + API_COCKTAIL_BYINGREDIENT);
            e.printStackTrace();
        }
        return COCKTAIL_ARRAY_LIST;
    }

    public static List<Ingredient> getIngredient(JSONArray ingredientArray) throws IOException, JSONException {
        List<Ingredient> INGREDIENT_ARRAY_LIST = new ArrayList<>();
        try {
            for (int i = 0; i < ingredientArray.length(); i++) {
                JSONObject array1 = ingredientArray.getJSONObject(i);
                String tempId = array1.getString("idIngredient");
                String tempName = array1.getString("strIngredient");
                String tempDescription = array1.getString("strDescription");
                String tempType = array1.getString("strType");
                String tempAlcohol = array1.getString("strAlcohol");

                INGREDIENT_ARRAY_LIST.add(new Ingredient(tempId, tempName, tempDescription, tempType, tempAlcohol));
            }

        } catch (JSONException e) {
            logger.error("Error parsing JSONObject in JSONArray: " + INGREDIENT_ARRAY_LIST);
            e.printStackTrace();
        }
        return INGREDIENT_ARRAY_LIST;
    }


    static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}
