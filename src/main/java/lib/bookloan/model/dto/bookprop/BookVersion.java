package lib.bookloan.model.dto.bookprop;

// In resultSet we use BookVersion.valueOf(), the constant has to case match from database.
public enum BookVersion {
    HARDCOPY("Hardcopy"),
    ACOUSTIC("Acoustic");


    public int getValueOfBookVersionAsInt() {
        return switch(this) {
            case HARDCOPY -> 0;
            case ACOUSTIC -> 1;
        };
    }
    public String getValueOfBookVersionAsString() {
        return switch(this) {
            case HARDCOPY -> "hardcopy";
            case ACOUSTIC -> "acoustic";
        };
    }


    public String version;

    BookVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
