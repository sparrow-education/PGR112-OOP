package lib.bookloan.model.dto.bookprop;

public enum Subscription {
    INACTIVE,
    ACTIVE;


    public int getValueOfSub() {
        return switch(this) {
            case INACTIVE -> 0;
            case ACTIVE -> 1;
        };
    }
}
