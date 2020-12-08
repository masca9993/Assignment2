////////////////////////////////////////////////////////////////////
// [ANDREA] [MASCARI] [1187132]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.time.LocalDate;;

public class UserTest {

    @Test
    public void user_costructor_test() {
        String i = "Antonio";
        LocalDate n = LocalDate.of(2002, 11, 5);
        User u = new User(i, n);
        assertEquals(i, u.getID());
        assertEquals(n, u.getNascita());
    }

}