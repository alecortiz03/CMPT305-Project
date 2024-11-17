import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PropertyAssessmentTest {
    Location location1 = new Location();
    Neighborhood neighborhood1 = new Neighborhood();
    Address address1 = new Address();
    AssessmentClass assessmentClass1 = new AssessmentClass();
    AssessmentClasses assessmentClasses1 = new AssessmentClasses();

    Location location2 = new Location(1.0, 2.0);
    Neighborhood neighborhood2 = new Neighborhood("neighborhood", "ward");
    Address address2 = new Address("123", "street");
    AssessmentClass assessmentClass2 = new AssessmentClass("className", "percent");
    AssessmentClasses assessmentClasses2 = new AssessmentClasses();

    Location location3 = new Location(7.0, 8.0);
    Neighborhood neighborhood3 = new Neighborhood("1234242", "ward12131");
    Address address3 = new Address("789", "road");
    AssessmentClass assessmentClass3 = new AssessmentClass("className2", "percent2");
    AssessmentClasses assessmentClasses3 = new AssessmentClasses();

    Location location4 = new Location();
    Neighborhood neighborhood4 = new Neighborhood();
    Address address4 = new Address();
    AssessmentClass assessmentClass4 = new AssessmentClass();
    AssessmentClasses assessmentClasses4 = new AssessmentClasses();



    


    @Test
    void testCompareTo() {
        assessmentClasses1.addClass(assessmentClass1);
        assessmentClasses2.addClass(assessmentClass2);
        assessmentClasses3.addClass(assessmentClass3);
        assessmentClasses4.addClass(assessmentClass4);

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(1, address1, 100, assessmentClasses1, neighborhood1, location1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(2, address2, 200, assessmentClasses2, neighborhood2, location2);
        PropertyAssessment propertyAssessment3 = new PropertyAssessment(3, address3, 300, assessmentClasses3, neighborhood3, location3);
        PropertyAssessment propertyAssessment4 = new PropertyAssessment(4, address4, 400, assessmentClasses4, neighborhood4, location4);

        assertEquals(0, propertyAssessment1.compareTo(propertyAssessment1));
        assertEquals(-1, propertyAssessment1.compareTo(propertyAssessment2));
        assertEquals(1, propertyAssessment2.compareTo(propertyAssessment1));


    }

    @Test
    void testEquals() {
        assessmentClasses1.addClass(assessmentClass1);
        assessmentClasses2.addClass(assessmentClass2);
        assessmentClasses3.addClass(assessmentClass3);
        assessmentClasses4.addClass(assessmentClass4);

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(1, address1, 100, assessmentClasses1, neighborhood1, location1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(2, address2, 200, assessmentClasses2, neighborhood2, location2);
        PropertyAssessment propertyAssessment3 = new PropertyAssessment(3, address3, 300, assessmentClasses3, neighborhood3, location3);
        PropertyAssessment propertyAssessment4 = new PropertyAssessment(4, address4, 400, assessmentClasses4, neighborhood4, location4);

        assertEquals(true, propertyAssessment1.equals(propertyAssessment1));
        assertEquals(false, propertyAssessment1.equals(propertyAssessment2));

    }

    @Test
    void testGetAccountNum() {
        assessmentClasses1.addClass(assessmentClass1);
        assessmentClasses2.addClass(assessmentClass2);
        assessmentClasses3.addClass(assessmentClass3);
        assessmentClasses4.addClass(assessmentClass4);

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(1, address1, 100, assessmentClasses1, neighborhood1, location1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(2, address2, 200, assessmentClasses2, neighborhood2, location2);
        PropertyAssessment propertyAssessment3 = new PropertyAssessment(3, address3, 300, assessmentClasses3, neighborhood3, location3);
        PropertyAssessment propertyAssessment4 = new PropertyAssessment(4, address4, 400, assessmentClasses4, neighborhood4, location4);

        assertEquals(1, propertyAssessment1.getAccountNum());
        assertEquals(2, propertyAssessment2.getAccountNum());
        assertEquals(3, propertyAssessment3.getAccountNum());
        assertEquals(4, propertyAssessment4.getAccountNum());

    }

    @Test
    void testGetAddress() {
        assessmentClasses1.addClass(assessmentClass1);
        assessmentClasses2.addClass(assessmentClass2);
        assessmentClasses3.addClass(assessmentClass3);
        assessmentClasses4.addClass(assessmentClass4);

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(1, address1, 100, assessmentClasses1, neighborhood1, location1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(2, address2, 200, assessmentClasses2, neighborhood2, location2);
        PropertyAssessment propertyAssessment3 = new PropertyAssessment(3, address3, 300, assessmentClasses3, neighborhood3, location3);
        PropertyAssessment propertyAssessment4 = new PropertyAssessment(4, address4, 400, assessmentClasses4, neighborhood4, location4);

        
       
    }

    @Test
    void testGetAssessedValue() {
        assessmentClasses1.addClass(assessmentClass1);
        assessmentClasses2.addClass(assessmentClass2);
        assessmentClasses3.addClass(assessmentClass3);
        assessmentClasses4.addClass(assessmentClass4);

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(1, address1, 100, assessmentClasses1, neighborhood1, location1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(2, address2, 200, assessmentClasses2, neighborhood2, location2);
        PropertyAssessment propertyAssessment3 = new PropertyAssessment(3, address3, 300, assessmentClasses3, neighborhood3, location3);
        PropertyAssessment propertyAssessment4 = new PropertyAssessment(4, address4, 400, assessmentClasses4, neighborhood4, location4);

        assertEquals(100, propertyAssessment1.getAssessedValue());
        assertEquals(200, propertyAssessment2.getAssessedValue());
        assertEquals(300, propertyAssessment3.getAssessedValue());
        assertEquals(400, propertyAssessment4.getAssessedValue());

    }

    @Test
    void testGetLocation() {
        assessmentClasses1.addClass(assessmentClass1);
        assessmentClasses2.addClass(assessmentClass2);
        assessmentClasses3.addClass(assessmentClass3);
        assessmentClasses4.addClass(assessmentClass4);

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(1, address1, 100, assessmentClasses1, neighborhood1, location1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(2, address2, 200, assessmentClasses2, neighborhood2, location2);
        PropertyAssessment propertyAssessment3 = new PropertyAssessment(3, address3, 300, assessmentClasses3, neighborhood3, location3);
        PropertyAssessment propertyAssessment4 = new PropertyAssessment(4, address4, 400, assessmentClasses4, neighborhood4, location4);

        assertEquals(location1, propertyAssessment1.getLocation());
        assertEquals(location2, propertyAssessment2.getLocation());
        assertEquals(location3, propertyAssessment3.getLocation());
        assertEquals(location4, propertyAssessment4.getLocation());

    }

    @Test
    void testGetNeighborhood() {
        assessmentClasses1.addClass(assessmentClass1);
        assessmentClasses2.addClass(assessmentClass2);
        assessmentClasses3.addClass(assessmentClass3);
        assessmentClasses4.addClass(assessmentClass4);

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(1, address1, 100, assessmentClasses1, neighborhood1, location1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(2, address2, 200, assessmentClasses2, neighborhood2, location2);
        PropertyAssessment propertyAssessment3 = new PropertyAssessment(3, address3, 300, assessmentClasses3, neighborhood3, location3);
        PropertyAssessment propertyAssessment4 = new PropertyAssessment(4, address4, 400, assessmentClasses4, neighborhood4, location4);

        

    }

    @Test
    void testGetWardClass() {
        assessmentClasses1.addClass(assessmentClass1);
        assessmentClasses2.addClass(assessmentClass2);
        assessmentClasses3.addClass(assessmentClass3);
        assessmentClasses4.addClass(assessmentClass4);

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(1, address1, 100, assessmentClasses1, neighborhood1, location1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(2, address2, 200, assessmentClasses2, neighborhood2, location2);
        PropertyAssessment propertyAssessment3 = new PropertyAssessment(3, address3, 300, assessmentClasses3, neighborhood3, location3);
        PropertyAssessment propertyAssessment4 = new PropertyAssessment(4, address4, 400, assessmentClasses4, neighborhood4, location4);

        assertEquals(assessmentClasses1, propertyAssessment1.getWardClass());
        assertEquals(assessmentClasses2, propertyAssessment2.getWardClass());
        assertEquals(assessmentClasses3, propertyAssessment3.getWardClass());
        assertEquals(assessmentClasses4, propertyAssessment4.getWardClass());

    }

    @Test
    void testHashCode() {
        assessmentClasses1.addClass(assessmentClass1);
        assessmentClasses2.addClass(assessmentClass2);
        assessmentClasses3.addClass(assessmentClass3);
        assessmentClasses4.addClass(assessmentClass4);

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(1, address1, 100, assessmentClasses1, neighborhood1, location1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(2, address2, 200, assessmentClasses2, neighborhood2, location2);
        PropertyAssessment propertyAssessment3 = new PropertyAssessment(3, address3, 300, assessmentClasses3, neighborhood3, location3);
        PropertyAssessment propertyAssessment4 = new PropertyAssessment(4, address4, 400, assessmentClasses4, neighborhood4, location4);

        assertEquals(propertyAssessment1.hashCode(), propertyAssessment1.hashCode());

    }

    @Test
    void testToString() {
        assessmentClasses1.addClass(assessmentClass1);
        assessmentClasses2.addClass(assessmentClass2);
        assessmentClasses3.addClass(assessmentClass3);
        assessmentClasses4.addClass(assessmentClass4);

        PropertyAssessment propertyAssessment1 = new PropertyAssessment(1, address1, 100, assessmentClasses1, neighborhood1, location1);
        PropertyAssessment propertyAssessment2 = new PropertyAssessment(2, address2, 200, assessmentClasses2, neighborhood2, location2);
        PropertyAssessment propertyAssessment3 = new PropertyAssessment(3, address3, 300, assessmentClasses3, neighborhood3, location3);
        PropertyAssessment propertyAssessment4 = new PropertyAssessment(4, address4, 400, assessmentClasses4, neighborhood4, location4);

        

    }
}
