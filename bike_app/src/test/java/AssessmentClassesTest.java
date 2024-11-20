import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AssessmentClassesTest {
    AssessmentClass assessmentClass = new AssessmentClass();
    AssessmentClass assessmentClass1 = new AssessmentClass("className", "percent");
    AssessmentClasses assessmentClasses = new AssessmentClasses();

    @Test
    void testAddClass() {
        assessmentClasses.addClass(assessmentClass);
        assessmentClasses.addClass(assessmentClass1);
        assertEquals(2, assessmentClasses.getRecordAmt());

    }

    @Test
    void testGetClass() {
        assessmentClasses.addClass(assessmentClass);
        assessmentClasses.addClass(assessmentClass1);
        assertEquals(assessmentClass1, assessmentClasses.getClass(1));

    }

    @Test
    void testGetRecordAmt() {
        assessmentClasses.addClass(assessmentClass);
        assessmentClasses.addClass(assessmentClass1);
        assertEquals(2, assessmentClasses.getRecordAmt());

    }

    @Test
    void testToString() {
        assessmentClasses.addClass(assessmentClass);
        assessmentClasses.addClass(assessmentClass1);
        assertEquals("[ " + assessmentClass.toString() + assessmentClass1.toString() + "]", assessmentClasses.toString());

    }
}
