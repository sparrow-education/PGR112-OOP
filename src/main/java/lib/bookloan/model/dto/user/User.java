package lib.bookloan.model.dto.user;



public class User extends T {
    private int rowId;
    private int id;
    private String name;


    public User(String name) {
        this.name = name;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {

    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.substring(0, 1).toUpperCase().concat(name.substring(1)).strip();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("ID: %d -- NAME: %s%n",getId(),getName());
    }
}
