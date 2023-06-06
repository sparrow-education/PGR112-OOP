package kristiania;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        KristianiaStudent student1 = new CyberSec("John","Wick",1,5);
        System.out.println(student1);

        KristianiaStudent student2 = new FrontEnd("Neo", "TheOne",2, 10);
        student2.setStudentId(10);
        System.out.println(student2);

        // Records is immutable, therefor we don't have setters
        Dummy dummy = new Dummy("Test","Test",0);
        Dummy betterDummy = new Dummy(dummy.fName().concat(" 1"), dummy.lName().concat(" 1"), dummy.id() + 1);
        System.out.println(betterDummy);




    }
}
