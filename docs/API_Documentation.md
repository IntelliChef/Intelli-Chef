# IntelliChef API Documentation

This document describes the APIs used in the IntelliChef project and provides setup instructions for integrating them.

---

## APIs Used

1. **Google Gemini API**: Used for image recognition to identify ingredients from a picture of the user's fridge.
2. **Edamam Recipe API**: Fetches recipe suggestions based on dietary preferences, ingredients, and cuisine types.

---

### Google Gemini API

**Purpose**: Detect ingredients in an image of the user’s refrigerator.

**Setup Instructions**:
1. Visit the [Google Gemini API Documentation](https://cloud.google.com/vertex-ai/generative-ai/docs/start/quickstarts/quickstart-multimodal).
2. Create a Google Cloud account and enable the Google Gemini API for your project.
3. Obtain an API key and save it as an environment variable:
   ```bash
   export GOOGLE_GEMINI_API_KEY="your_google_gemini_api_key"

#### Sample Usage:
The Gemini API takes an image as a byte array and responds with a list of identified items.

```java
// Pseudo-code for using Google Gemini API
ImageRecognizer recognizer = new ImageRecognizer("your_google_gemini_api_key");
List<String> ingredients = recognizer.identifyIngredientsFromImage(imageByteArray);
```

### Edamam Recipe API
Purpose: Suggests recipes based on dietary preferences, ingredients, and cuisine types.

**Setup Instructions**:

- Register for an Edamam account and subscribe to the Recipe API.
- Get your API keys and save them as environment variables:

```bash
export EDAMAM_RECIPE_API_KEY = "your_edamam_api_key"
export EDAMAM_RECIPE_APP_ID = "your_edamam_app_id"
```

#### Sample Usage:
The Edamam Recipe API allows you to filter recipes based on various parameters, such as ingredients, diet type, and calorie range.

```java
// Pseudo-code for using Edamam Recipe API
RecipeGenerator recipeGenerator = new RecipeGenerator("your_edamam_app_id", "your_edamam_api_key");
List<Recipe> recipes = recipeGenerator.getRecipes(ingredients, "vegan", 500);
```

### Error Handling
For each API, implement error handling to manage cases such as invalid API keys, quota limits, and network issues. Log error messages to help with debugging and maintenance.

#### Sample Error Handling:
```java
try {
    List<Recipe> recipes = recipeGenerator.getRecipes(ingredients, "vegan", 500);
} catch (ApiException e) {
    System.err.println("Error fetching recipes: " + e.getMessage());
}
```

### Best Practices
- Caching: Consider caching API responses to improve performance and minimize API calls.
- Environment Variables: Store API keys securely using environment variables.
- Rate Limits: Monitor API usage to avoid hitting rate limits.
Refer to each API's documentation for further customization and advanced features.

### Additional Resources
- [Google Gemini API Documentation](https://cloud.google.com/vertex-ai/generative-ai/docs/start/quickstarts/quickstart-multimodal)
- [Edamam Recipe API Documentation](https://developer.edamam.com/edamam-docs-recipe-api)

```
This documentation reflects the use of Maven, making it easier for users to set up and run the project accordingly. Let us know if there are further adjustments you'd like!
```
