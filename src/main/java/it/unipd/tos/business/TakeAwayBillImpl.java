////////////////////////////////////////////////////////////////////
// [ANDREA] [MASCARI] [1187132]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.time.LocalDate;
import java.time.Period;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.itemType;

public class TakeAwayBillImpl implements TakeAwayBill {
    
    private List<User> free_ords;

    public TakeAwayBillImpl() {
        free_ords=new ArrayList<User>();
    }

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime time) throws RestaurantBillException {
       
        double price=0;
        
        if (itemsOrdered.size() > 30 || itemsOrdered.size()==0) {
            throw new RestaurantBillException();
        }
        
        for (int i = 0; i < itemsOrdered.size(); i++) {
            price += itemsOrdered.get(i).getPrice();
        }
        
        price -= IceCreamDiscount(itemsOrdered);
        
        if (price - DrinksPrice(itemsOrdered) > 50) {
            price -= 0.1 * price;
        }
        
        if (price < 10) {
            price += 0.5;
        }

        if (FreeOrder(free_ords, time, user)) {
            price = 0;
        }
        
        return price;
    }
    
    private double IceCreamDiscount(List<MenuItem> itemsOrdered) {
        int n_gelati = 0;
        double min_price_gelati = 0;
        boolean first_gel = false;
        for (int i = 0; i < itemsOrdered.size(); i++) {
            if (itemsOrdered.get(i).getItemType() == itemType.Gelati) {
                if (!first_gel) {
                    min_price_gelati = itemsOrdered.get(i).getPrice();
                    first_gel = true;
                } else if (itemsOrdered.get(i).getPrice() < min_price_gelati) {
                    min_price_gelati = itemsOrdered.get(i).getPrice();
                }
                n_gelati++;
            }
        }
        if (n_gelati > 5) {
            return min_price_gelati / 2;
        }
        else {
            return 0;
        }
    }
    
    private double DrinksPrice(List<MenuItem> itemsOrdered) {

        double tot_bev = 0;

        for (int i = 0; i < itemsOrdered.size(); i++) {
            if (itemsOrdered.get(i).getItemType() == itemType.Bevande) {
                tot_bev += itemsOrdered.get(i).getPrice();
            }
        }
        return tot_bev;

    }
    
    private boolean FreeOrder(List<User> free_ords, LocalTime time, User user) {
        Period under = Period.between(user.getNascita(), LocalDate.now());
        if (under.getYears() < 18 && time.isAfter(LocalTime.of(18, 0, 0)) && time.isBefore(LocalTime.of(19, 0, 0))
                && free_ords.size() < 10) {
            boolean check = false;
            for (int i = 0; i < free_ords.size(); i++) {
                if (free_ords.get(i).getID() == user.getID()) {
                    check = true;
                }
            }
            if (time.getMinute() % 2 == 0 && !check) {
                free_ords.add(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
