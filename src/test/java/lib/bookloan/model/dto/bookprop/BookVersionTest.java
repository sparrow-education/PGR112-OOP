package lib.bookloan.model.dto.bookprop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BookVersionTest {



    @Test
    void isHardCopy() {
        assertEquals(0,BookVersion.HARDCOPY.getValueOfBookVersionAsInt());
    }

    @Test
    void isAcoustic() {
        assertEquals(1,BookVersion.ACOUSTIC.getValueOfBookVersionAsInt());
    }


}