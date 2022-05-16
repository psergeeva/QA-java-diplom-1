package praktikum;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest extends TestCase {

    private Burger burger;
    String bunName = "testBun";
    float bunPrice = 1.11f;
    String ingredientName = "testIngredient";
    float ingredientPrice = 2.22f;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger ();
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient (ingredient);
        assertEquals ("Ingredient is not added.", 1, burger.ingredients.size ());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient (ingredient);
        burger.removeIngredient (0);
        assertTrue ("There shouldn't be ingredient after removal.", burger.ingredients.isEmpty ());
    }


    @Test
    public void testMoveIngredient() {
        Mockito.when (ingredient.getType ()).thenReturn (IngredientType.FILLING);
        burger.addIngredient (ingredient);
        Mockito.when (ingredient.getType ()).thenReturn (IngredientType.SAUCE);
        burger.addIngredient (ingredient);
        burger.moveIngredient (1, 0);
        assertEquals (IngredientType.SAUCE, burger.ingredients.get (0).getType ());
    }

    @Test
    public void testGetPriceWithNoIngredients() {
        burger.setBuns (bun);
        when (bun.getPrice ()).thenReturn (2.5f);
        assertEquals ("Price is incorrect.", 5, burger.getPrice (), 0.0);
    }

    @Test
    public void shouldGetReceipt() {
        Mockito.when (bun.getName ()).thenReturn (bunName);
        Mockito.when (bun.getPrice ()).thenReturn (bunPrice);
        burger.setBuns (bun);
        Mockito.when (ingredient.getType ()).thenReturn (IngredientType.FILLING);
        Mockito.when (ingredient.getName ()).thenReturn (ingredientName);
        Mockito.when (ingredient.getPrice ()).thenReturn (ingredientPrice);
        burger.addIngredient (ingredient);
        assertEquals ("Receipt format is different from expected one.", "(==== testBun ====)\r\n" + "= filling testIngredient =\r\n" + "(==== testBun ====)\r\n\r\n" + "Price: 4.440000\r\n", burger.getReceipt ());
    }
}