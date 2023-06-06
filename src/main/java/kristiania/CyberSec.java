package kristiania;

import java.util.Collection;

public class CyberSec extends KristianiaStudent implements IStudent {
    private int penTestExp;

    /**
     * KristianiaStudent's constructor
     * @param fName first name
     * @param lName last name
     * @param id student id
     */
    public CyberSec(String fName, String lName, int id, int pte) {
        super(fName, lName, id);
        this.penTestExp = pte;
    }

    @Override
    public String hasComputer() {
        return null;
    }

    @Override
    protected int getStudentId() {
        return super.getStudentId();
    }

}
