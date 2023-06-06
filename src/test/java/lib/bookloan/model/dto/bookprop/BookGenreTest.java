package lib.bookloan.model.dto.bookprop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookGenreTest {

    @Test
    void isGenreOfManga() {
        assertThat("manga").isEqualTo(BookGenre.MANGA.getValueOfGenre());
    }
}