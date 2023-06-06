package kristiania;

import java.util.ArrayList;

/**
 * All Kristiania students has some sort of first name, last name and a student id.
 * KristianiaStudent is a super class where we pull up the common fields that exists in both front end & cyber sec students
 * This class can't be instantiated because of abstraction
 * Use superclass with abstraction if classes share the same "implementation" (name, id ...)
 */
public abstract class KristianiaStudent {
    protected String firstName;
    protected String lastName;
    protected int studentId;

    /**
     * Creating a new student at Kristiania it must contain 3 parameters
     * For constructor reuse in FrontEnd & CyberSec, we just call for super
     * @param firstName
     * @param lastName
     * @param studentId
     */
    protected KristianiaStudent(String firstName, String lastName, int studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
    }

    protected int getStudentId() {
        return studentId;
    }
    protected void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    protected ArrayList<KristianiaStudent> studentArrayList = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("%s %s ID: %d ",firstName, lastName, getStudentId());
    }
}
