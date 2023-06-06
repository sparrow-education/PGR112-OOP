package lib.bookloan.model.dto.user;


// I am your father
public abstract class T<T> {
    private int rowId;
    private int id;
    private String name;

    public T() {
    }

    public T(int id) {
        this.id = id;
    }

    public T(String name) {
        this.name = name;
    }


    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
