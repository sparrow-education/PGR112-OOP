package kristiania;

public class FrontEnd extends KristianiaStudent implements IStudent {
    private int designExp;

    /**
     * Composition - Delegate method from Class Photographer and frontEnd student can also shoot photos
     */
    private Photographer photographer = new Photographer(5,true);
    public void shootPhotos() {
        photographer.shootPhotos();
    }

    public FrontEnd(String fName, String lName, int id, int designExp) {
        super(fName, lName, id);
        this.designExp = designExp;
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
