import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AddressTest {
    Address address = new Address("123", "Main St");
    Address address2 = new Address();

    @Test
    void testGetHouseNum() {
        assertEquals("123", address.getHouseNum());

    }

    @Test
    void testGetStreet() {
        assertEquals("Main St", address.getStreet());

    }

    @Test
    void testToString() {
        assertEquals("123 Main St", address.toString());

    }
}
