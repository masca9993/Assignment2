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
    
    @Test
    public void testOrderPrice_IceCreamDiscount() {
        TakeAwayBillImpl bill=new TakeAwayBillImpl();
        User u= new User("Damiano", LocalDate.of(1998, 7, 29));
        List<MenuItem> ord=new ArrayList<MenuItem>();
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 2));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 5));
        ord.add(new MenuItem(itemType.Budini, "Biancaneve", 5));    
        try
        {
             double price=bill.getOrderPrice(ord, u, LocalTime.of(18,23));
             assertEquals(price, 23, 0.0001);
        }
        catch (RestaurantBillException e) {
    fail();
        }   
    }
    
    @Test 
    public void testOrderPrice_MoreThan50Discount() {
        TakeAwayBillImpl bill=new TakeAwayBillImpl();
        User u= new User("Damiano", LocalDate.of(1998, 7, 29));
        List<MenuItem> ord=new ArrayList<MenuItem>();
        ord.add(new MenuItem(itemType.Gelati, "Banana", 10));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Budini, "Biancaneve", 22));
        ord.add(new MenuItem(itemType.Bevande, "Fanta", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 5));
        ord.add(new MenuItem(itemType.Budini, "Biancaneve", 15));   
        try
        {
             double price=bill.getOrderPrice(ord, u, LocalTime.of(18,23));
             assertEquals(price, 54.9, 0.0001);
        }
        catch (RestaurantBillException e) {
    fail();
        }   
    }   
    
    //Prima applico sconto 50% sul gelato meno costoso, poi sul nuovo totale applico sconto del 10% se ci sono ancora le condizioni
    @Test 
    public void testOrderPrice_BothDiscounts1() {
        TakeAwayBillImpl bill=new TakeAwayBillImpl();
        User u= new User("Damiano", LocalDate.of(1998, 7, 29));
        List<MenuItem> ord=new ArrayList<MenuItem>();
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 2));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 50));
        ord.add(new MenuItem(itemType.Budini, "Biancaneve", 5));
            
        try
        {
             double price=bill.getOrderPrice(ord, u, LocalTime.of(18,23));
             assertEquals(price, 61.2, 0.0001);
        }
        catch (RestaurantBillException e) {
    fail();
        }   
    }   
    
    @Test 
    public void testOrderPrice_BothDiscouts2() {
        TakeAwayBillImpl bill=new TakeAwayBillImpl();
        User u= new User("Damiano", LocalDate.of(1998, 7, 29));
        List<MenuItem> ord=new ArrayList<MenuItem>();
        ord.add(new MenuItem(itemType.Gelati, "Banana", 10));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 10));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 5));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 5));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 10));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 10));
        ord.add(new MenuItem(itemType.Budini, "Biancaneve", 1));
            
        try
        {
             double price=bill.getOrderPrice(ord, u, LocalTime.of(18,23));
             assertEquals(price, 48.5, 0.0001);
        }
        catch (RestaurantBillException e) {
    fail();
        }   
    }
    
    @Test(expected = RestaurantBillException.class)
    public void testOrderPrice_MoreThan30Orders() throws RestaurantBillException{
        TakeAwayBillImpl bill=new TakeAwayBillImpl();
        User u= new User("Damiano", LocalDate.of(1998, 7, 29));
        List<MenuItem> ord=new ArrayList<MenuItem>();
        for (int i=0; i<31; i++)
            ord.add(new MenuItem(itemType.Gelati, "Banana", 10));
        bill.getOrderPrice(ord, u, LocalTime.of(18,23));    
    }
    
    @Test(expected = RestaurantBillException.class)
    public void testOrderPrice_0Orders() throws RestaurantBillException{
        TakeAwayBillImpl bill=new TakeAwayBillImpl();
        User u= new User("Damiano", LocalDate.of(1998, 7, 29));
        List<MenuItem> ord=new ArrayList<MenuItem>();
        bill.getOrderPrice(ord, u, LocalTime.of(18,23));    
    }
    
    @Test 
    public void testOrderPrice_LessThan10Euros() {
        TakeAwayBillImpl bill=new TakeAwayBillImpl();
        User u= new User("Damiano", LocalDate.of(1998, 7, 29));
        List<MenuItem> ord=new ArrayList<MenuItem>();
        ord.add(new MenuItem(itemType.Gelati, "Banana", 3));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 1));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 2));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 2));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 1));
        ord.add(new MenuItem(itemType.Gelati, "Banana", 1));
            
        try
        {
             double price=bill.getOrderPrice(ord, u, LocalTime.of(18,23));
             assertEquals(price, 10, 0.0001);
        }
        catch (RestaurantBillException e) {
    fail();
        }   
    }

}
