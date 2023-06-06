package lib.bookloan.model.dto.bookprop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {


    @Test
    void hasActiveSub() {
        assertEquals(1,Subscription.ACTIVE.getValueOfSub());
    }

    @Test
    void hasNotActiveSub() {
        assertEquals(0,Subscription.INACTIVE.getValueOfSub());
    }
}