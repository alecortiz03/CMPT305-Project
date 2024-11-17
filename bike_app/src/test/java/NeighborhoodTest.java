import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class NeighborhoodTest {
    Neighborhood neighborhood = new Neighborhood("Downtown", "Ward 1");
    Neighborhood neighborhood2 = new Neighborhood();
    @Test
    void testGetNeighborhood() {
        assertEquals("Downtown", neighborhood.getNeighborhood());

    }

    @Test
    void testGetWard() {
        assertEquals("Ward 1", neighborhood.getWard());

    }

    @Test
    void testToString() {
        assertEquals("Downtown (Ward 1)", neighborhood.toString());

    }
}
