package lib.bookloan.model.dto.bookprop;

public enum BookGenre {
    ACTION("Action"),
    CRIME("Crime"),
    SCIFI("Scifi"),
    DRAMA("Drama"),
    MANGA("Manga"),
    ;

    public String getValueOfGenre() {
        return switch(this) {
            case ACTION -> "action";
            case CRIME -> "crime";
            case SCIFI -> "scifi";
            case DRAMA -> "drama";
            case MANGA -> "manga";
        };
    }

    private String genre;

    BookGenre (String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
