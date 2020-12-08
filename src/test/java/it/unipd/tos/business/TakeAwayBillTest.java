////////////////////////////////////////////////////////////////////
// [ANDREA] [MASCARI] [1187132]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;


import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.unipd.tos.business.TakeAwayBillImpl;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.itemType;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;


import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class TakeAwayBillTest {

    @Test
    public void testOrderPrice() {
        TakeAwayBillImpl bill=new TakeAwayBillImpl();
        User u= new User("Damiano", LocalDate.of(1998, 7, 29));
        List<MenuItem> ord=new ArrayList<MenuItem>();
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 5));
        ord.add(new MenuItem(itemType.Budini, "Biancaneve", 5));    
        try
        {
             double price=bill.getOrderPrice(ord, u, LocalTime.of(18,23));
             assertEquals(price, 19, 0.0001);
        }
        catch (RestaurantBillException e) {
    fail();
        }   
    }

}
