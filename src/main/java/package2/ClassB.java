package package2;

public class ClassB {
    public String name = "B";
    protected int id = 3;
    private String MAC = "00-B0-D0-63-C2-26";
    int classifiedId = 1337;


    public void classPublicMethod() {
        System.out.println("This is ClassB public method");
    }
    protected void classProtectedMethod() {
        System.out.println("This is ClassB protected method");
    }
    void packageProtectedMethod() {
        System.out.println("This is ClassB package method");
    }
    private void classPrivateMethod() {
        System.out.println("I can never be accessed");
    }
}
