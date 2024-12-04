package use_case;

import data_access.GeminiAIforRecipe;
import entities.DietPreference;
import entities.Ingredient;
import interfaces.FileStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.IngredientRepository;
import use_case.diet_preference.RecipeUseCase;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RecipeUseCaseTest {

    private RecipeUseCase recipeUseCase;
    private FileStorage mockFileStorage;
    private IngredientRepository mockIngredientRepository;
    private GeminiAIforRecipe mockGeminiService;

    @BeforeEach
    public void setUp() {
        // Mock dependencies
        mockFileStorage = mock(FileStorage.class);
        mockIngredientRepository = mock(IngredientRepository.class);
        mockGeminiService = mock(GeminiAIforRecipe.class);

        // Create the RecipeUseCase instance with a mocked FileStorage
        recipeUseCase = new RecipeUseCase(mockFileStorage);
        recipeUseCase.setGeminiService(mockGeminiService);
    }

    @Test
    public void testProcessRecipesSuccessful() throws Exception {
        // Mock data
        List<String> preference = new ArrayList<>();
        preference.add("Vegetarian");
        preference.add("Gluten-Free");
        DietPreference dietPreference = new DietPreference(preference);
        List<Ingredient> mockIngredients = new ArrayList<>();
        mockIngredients.add(new Ingredient("Tomato", 2));
        mockIngredients.add(new Ingredient("Cheese", 10));

        when(mockIngredientRepository.getAllIngredients()).thenReturn(mockIngredients);

        List<String> expectedStringifiedIngredients = new ArrayList<>();
        expectedStringifiedIngredients.add("Tomato  2.0");
        expectedStringifiedIngredients.add("Cheese  10.0");


        // Mock GeminiAIforRecipe behavior
        String mockJsonResponse = "{  \"hits\": [    {      \"recipe\": {        \"label\": \"Tomato Salad with Feta and Chickpeas\",        " +
                "\"image\": \"https://www.google.com/search?q=tomato+salad+with+feta+and+chickpeas&tbm=isch&ved=2ahUKEwi_49T4wN3AAxV-gQIHHc-" +
                "2A6kQ2-cCegQIABAA&oq=tomato+salad+with+feta+and+chickpeas&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=72G-Yp6wE62q9" +
                "AOKj7yIBw&bih=657&biw=1366&hl=en#imgrc=96B7o_e6fK79JM\",        \"calories\": 250,        \"totalTime\": 20,        \"url\": " +
                "\"https://www.google.com/search?q=tomato+salad+with+feta+and+chickpeas&tbm=isch&ved=2ahUKEwi_49T4wN3AAxV-gQIHHc-2A6kQ2-cCegQIAB" +
                "AA&oq=tomato+salad+with+feta+and+chickpeas&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=72G-Yp6wE62q9AOKj7yIBw&bih=657&" +
                "biw=1366&hl=en#imgrc=96B7o_e6fK79JM\"      }    },    {      \"recipe\": {        \"label\": \"Tomato and Lentil Soup\",        " +
                "\"image\": \"https://www.google.com/search?q=tomato+and+lentil+soup&tbm=isch&ved=2ahUKEwiV5-a1wN3AAxW-gQIHHc-1B-kQ2-cCegQIABAA&oq" +
                "=tomato+and+lentil+soup&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=u2G-YpfjH6_k9AOd6o2oBQ&bih=657&biw=1366&hl=en#imgrc=d" +
                "253C5lU5B8R-M\",        \"calories\": 200,        \"totalTime\": 30,        \"url\": \"https://www.google.com/search?q=tomato+and+le" +
                "ntil+soup&tbm=isch&ved=2ahUKEwiV5-a1wN3AAxW-gQIHHc-1B-kQ2-cCegQIABAA&oq=tomato+and+lentil+soup&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABA" +
                "Q&sclient=img&ei=u2G-YpfjH6_k9AOd6o2oBQ&bih=657&biw=1366&hl=en#imgrc=d253C5lU5B8R-M\"      }    },    {      \"recipe\": {        \"l" +
                "abel\": \"Tomato and Chickpea Curry\",        \"image\": \"https://www.google.com/search?q=tomato+and+chickpea+curry&tbm=isch&ved=2ahU" +
                "KEwi_1Jq1wN3AAxW_gQIHHc30A_kQ2-cCegQIABAA&oq=tomato+and+chickpea+curry&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=u2G-YpfjH" +
                "6_k9AOd6o2oBQ&bih=657&biw=1366&hl=en#imgrc=F07w-8m0x7R08M\",        \"calories\": 300,        \"totalTime\": 45,        \"url\": \"http" +
                "s://www.google.com/search?q=tomato+and+chickpea+curry&tbm=isch&ved=2ahUKEwi_1Jq1wN3AAxW_gQIHHc30A_kQ2-cCegQIABAA&oq=tomato+and+chickpea" +
                "+curry&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=u2G-YpfjH6_k9AOd6o2oBQ&bih=657&biw=1366&hl=en#imgrc=F07w-8m0x7R08M\"      }" +
                "    },    {      \"recipe\": {        \"label\": \"Tomato Quinoa Salad\",        \"image\": \"https://www.google.com/search?q=tomato+qui" +
                "noa+salad&tbm=isch&ved=2ahUKEwj67t-zwN3AAxV-gQIHHc-1B-kQ2-cCegQIABAA&oq=tomato+quinoa+salad&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&s" +
                "client=img&ei=72G-Yp6wE62q9AOKj7yIBw&bih=657&biw=1366&hl=en#imgrc=59r_wV6n325QmM\",        \"calories\": 200,        \"totalTime\": " +
                "30,        \"url\": \"https://www.google.com/search?q=tomato+quinoa+salad&tbm=isch&ved=2ahUKEwj67t-zwN3AAxV-gQIHHc-1B-kQ2-cCegQIABA" +
                "A&oq=tomato+quinoa+salad&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=72G-Yp6wE62q9AOKj7yIBw&bih=657&biw=1366&hl=en#imgrc=5" +
                "9r_wV6n325QmM\"      }    },    {      \"recipe\": {        \"label\": \"Tomato and Black Bean Salad\",        \"image\": \"https://" +
                "www.google.com/search?q=tomato+and+black+bean+salad&tbm=isch&ved=2ahUKEwiQ7b7pwN3AAxVeggQIHRzTBGQQ2-cCegQIABAA&oq=tomato+and+black+be" +
                "an+salad&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=u2G-YpfjH6_k9AOd6o2oBQ&bih=657&biw=1366&hl=en#imgrc=4V_M8b0W4g3FMM\"," +
                "        \"calories\": 250,        \"totalTime\": 20,        \"url\": \"https://www.google.com/search?q=tomato+and+black+bean+salad&t" +
                "bm=isch&ved=2ahUKEwiQ7b7pwN3AAxVeggQIHRzTBGQQ2-cCegQIABAA&oq=tomato+and+black+bean+salad&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclie" +
                "nt=img&ei=u2G-YpfjH6_k9AOd6o2oBQ&bih=657&biw=1366&hl=en#imgrc=4V_M8b0W4g3FMM\"      }    },    {      \"recipe\": {        \"label\":" +
                " \"Tomato and Chickpea Pasta Salad\",        \"image\": \"https://www.google.com/search?q=tomato+and+chickpea+pasta+salad&tbm=isch&ve" +
                "d=2ahUKEwiO6NqpwN3AAxV8ggQIHf60A28Q2-cCegQIABAA&oq=tomato+and+chickpea+pasta+salad&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&" +
                "ei=u2G-YpfjH6_k9AOd6o2oBQ&bih=657&biw=1366&hl=en#imgrc=53hU472R5Q5tUM\",        \"calories\": 350,        \"totalTime\": 30,        \"u" +
                "rl\": \"https://www.google.com/search?q=tomato+and+chickpea+pasta+salad&tbm=isch&ved=2ahUKEwiO6NqpwN3AAxV8ggQIHf60A28Q2-cCegQIABAA&oq=to" +
                "mato+and+chickpea+pasta+salad&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=u2G-YpfjH6_k9AOd6o2oBQ&bih=657&biw=1366&hl=en#imgrc=5" +
                "3hU472R5Q5tUM\"      }    },    {      \"recipe\": {        \"label\": \"Tomato and Avocado Toast\",        \"image\": \"https://www.googl" +
                "e.com/search?q=tomato+and+avocado+toast&tbm=isch&ved=2ahUKEwiB-a-pwN3AAxW_ggQIHR2SAwQ2-cCegQIABAA&oq=tomato+and+avocado+toast&gs_lcp=CgNpbWcQ" +
                "A1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=72G-Yp6wE62q9AOKj7yIBw&bih=657&biw=1366&hl=en#imgrc=i-W4zY3_1iY-OM\",        \"calories\": 200,     " +
                "   \"totalTime\": 10,        \"url\": \"https://www.google.com/search?q=tomato+and+avocado+toast&tbm=isch&ved=2ahUKEwiB-a-pwN3AAxW_ggQIHR2SA" +
                "wQ2-cCegQIABAA&oq=tomato+and+avocado+toast&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=72G-Yp6wE62q9AOKj7yIBw&bih=657&biw=1366&hl" +
                "=en#imgrc=i-W4zY3_1iY-OM\"      }    },    {      \"recipe\": {        \"label\": \"Tomato and Egg Breakfast Burrito\",        \"image\": \"" +
                "https://www.google.com/search?q=tomato+and+egg+breakfast+burrito&tbm=isch&ved=2ahUKEwiP6N6pwN3AAxV1ggQIHTm1A-0Q2-cCegQIABAA&oq=tomato+and+" +
                "egg+breakfast+burrito&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=72G-Yp6wE62q9AOKj7yIBw&bih=657&biw=1366&hl=en#imgrc=D7O_7y85I" +
                "1yUYM\",        \"calories\": 350,        \"totalTime\": 20,        \"url\": \"https://www.google.com/search?q=tomato+and+egg+breakfast+bu" +
                "rrito&tbm=isch&ved=2ahUKEwiP6N6pwN3AAxV1ggQIHTm1A-0Q2-cCegQIABAA&oq=tomato+and+egg+breakfast+burrito&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABA" +
                "Q&sclient=img&ei=72G-Yp6wE62q9AOKj7yIBw&bih=657&biw=1366&hl=en#imgrc=D7O_7y85I1yUYM\"      }    },    {      \"recipe\": {        \"label\"" +
                ": \"Tomato and Chicken Skewers\",        \"image\": \"https://www.google.com/search?q=tomato+and+chicken+skewers&tbm=isch&ved=2ahUKEwj3j-Cp" +
                "wN3AAxW-gQIHHc-5A-0Q2-cCegQIABAA&oq=tomato+and+chicken+skewers&gs_lcp=CgNpbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=u2G-YpfjH6_k9AOd6o2oB" +
                "Q&bih=657&biw=1366&hl=en#imgrc=W2D5z_vI64-K3M\",        \"calories\": 250,        \"totalTime\": 30,        \"url\": \"https://www.google." +
                "com/search?q=tomato+and+chicken+skewers&tbm=isch&ved=2ahUKEwj3j-CpwN3AAxW-gQIHHc-5A-0Q2-cCegQIABAA&oq=tomato+and+chicken+skewers&gs_lcp=CgN" +
                "pbWcQA1C5-4CY_YTY_YTYgB8ABAQ&sclient=img&ei=u2G-YpfjH6_k9AOd6o2oBQ&bih=657&biw=1366&hl=en#imgrc=W2D5z_vI64-K3M\"      }    }  ]\n" +
                "}";
        when(mockGeminiService.getRecipe(dietPreference.getPreferences(), expectedStringifiedIngredients))
                .thenReturn(mockJsonResponse);

        // Call the method under test
        recipeUseCase.processRecipes(dietPreference, mockIngredientRepository);

        // Verify interactions
        verify(mockIngredientRepository, times(1)).getAllIngredients();
        verify(mockGeminiService, times(1)).getRecipe(dietPreference.getPreferences(), expectedStringifiedIngredients);
        verify(mockFileStorage, times(1)).saveToFile(mockJsonResponse);
    }

}
