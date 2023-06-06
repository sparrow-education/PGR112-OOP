package kristiania;

/**
 * Records are final which means when constructors are set it's not mutable
 */
public record Dummy(String fName, String lName, int id) implements IStudent {

    @Override
    public String hasComputer() {
        return null;
    }
}
