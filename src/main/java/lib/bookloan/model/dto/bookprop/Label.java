package lib.bookloan.model.dto.bookprop;

import java.util.HashMap;
import java.util.Map;

// https://www.baeldung.com/java-enum-values
public enum Label {
    ACTION("Action"),
    NOVEL("Novel"),
    MANGA("Manga"),
    OTHER("Other");

    private String label;
    private static final Map<String, Label> BY_LABEL = new HashMap<>();
    static {
        for (Label label : values()) {
            BY_LABEL.put(label.label, label);
        }
    }

    private Label(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public static Label getValueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static Label valueOfLabel(String label) {
        for (Label l : values()) {
            if (l.label.equals(label)) {
                return l;
            }
        }
        return OTHER;
    }

    public static void main(String[] args) {
        System.out.println(ACTION.getLabel());
        Label l = Label.valueOfLabel("Action");
        System.out.println(l);

        Label manga = getValueOfLabel("Manga");
        System.out.println(manga);


        manga.setLabel("Action");
        System.out.println(getValueOfLabel(manga.label));
    }
}
