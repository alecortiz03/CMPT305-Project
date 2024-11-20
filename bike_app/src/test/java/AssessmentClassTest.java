import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AssessmentClassTest {
    AssessmentClass assessmentClass = new AssessmentClass();
    AssessmentClass assessmentClass1 = new AssessmentClass("className", "percent");

    @Test
    void testGetClassName() {
        assertEquals("className", assessmentClass1.getClassName()); 

    }

    @Test
    void testGetPercent() {
        assertEquals("percent", assessmentClass1.getPercent());

    }

    @Test
    void testToString() {
        assertEquals("className percent% ", assessmentClass1.toString());

    }
}
