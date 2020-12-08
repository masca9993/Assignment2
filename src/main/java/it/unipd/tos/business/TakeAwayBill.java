////////////////////////////////////////////////////////////////////
// [ANDREA] [MASCARI] [1187132]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import java.time.LocalTime;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem;


public interface TakeAwayBill {
  double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime time) throws
RestaurantBillException;
}