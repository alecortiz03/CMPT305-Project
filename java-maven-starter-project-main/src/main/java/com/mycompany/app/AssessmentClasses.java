import java.util.List;
import java.util.ArrayList;

public class AssessmentClasses {
    private List<AssessmentClass> classes = new ArrayList<AssessmentClass>();

    public AssessmentClasses(){
        this.classes = new ArrayList<AssessmentClass>();
    }
    public AssessmentClass getClass(int index){
        AssessmentClass assessment = new AssessmentClass();
        assessment = classes.get(index);
        return assessment;
    }
    public void addClass(AssessmentClass assessment){
        classes.add(assessment);
    }
    public int getRecordAmt(){
        return classes.size();
    }
    public String toString(){
        String output = "[ ";
        for (AssessmentClass assessment : this.classes){
            output += assessment.toString();
        }
        output += "]";
        return output;
    }
}
