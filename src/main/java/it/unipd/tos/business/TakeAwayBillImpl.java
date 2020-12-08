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

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime time) throws RestaurantBillException {
        double price=0;
        for (int i = 0; i < itemsOrdered.size(); i++) {
            price += itemsOrdered.get(i).getPrice();
        }
        price -= IceCreamDiscount(itemsOrdered);
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

}
