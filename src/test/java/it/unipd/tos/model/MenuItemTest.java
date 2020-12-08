////////////////////////////////////////////////////////////////////
// [ANDREA] [MASCARI] [1187132]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MenuItemTest {

    @Test
    public void ConstructorTest() {
        itemType tipo= itemType.Gelati;
        String nome= "biancaneve";
        double price= 3.5;
        MenuItem item= new MenuItem(tipo, nome, price);
        assertEquals(tipo, item.getItemType());
        assertEquals(nome, item.getName());
        assertEquals(price, item.getPrice(), 0.00001);
    }

}