import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LocationTest {
    Location location = new Location(1.0, 2.0);
    Location location2 = new Location(1.0, 2.0);
    Location location3 = new Location(3.0, 4.0);
    Location location4 = new Location();
    

    @Test
    void testCompareTo() {
        assertEquals(0, location.compareTo(location2));
        assertEquals(-1, location.compareTo(location3));
        assertEquals(1, location.compareTo(location4));

    }

    @Test
    void testEquals() {
        assertEquals(true, location.equals(location2));
        assertEquals(false, location.equals(location3));
        assertEquals(false, location.equals(location4));
        

    }

    @Test
    void testGetX() {
        assertEquals(1.0, location.getX());

    }

    @Test
    void testGetY() {
        assertEquals(2.0, location.getY());

    }

    @Test
    void testHashCode() {
        assertEquals(location.hashCode(), location.hashCode());

    }

    @Test
    void testToString() {
        assertEquals("( 1.0, 2.0 )", location.toString());

    }
}
