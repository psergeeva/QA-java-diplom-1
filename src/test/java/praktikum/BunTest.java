package praktikum;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BunTest {

    private final String EXPECTED_NAME = "bunTest";
    private final float EXPECTED_PRICE = 1f;
    Bun bun;

    @Before
    public void setUp() {
        bun = new Bun (EXPECTED_NAME,EXPECTED_PRICE);
    }

    @Test
    public void testGetName() {
        String expectedName = "bunName";
        assertEquals ("Bun's title doesn't match to expected one.", EXPECTED_NAME, bun.getName ());
    }

    @Test
    public void testGetPrice() {
        assertEquals ("Bun's price doesn't equal to expected one.", EXPECTED_PRICE, bun.getPrice (), 0.0);
    }
}