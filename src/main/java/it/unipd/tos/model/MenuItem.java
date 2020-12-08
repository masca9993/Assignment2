////////////////////////////////////////////////////////////////////
// [ANDREA] [MASCARI] [1187132]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class MenuItem {

    private itemType item;
    private String name;
    private double price;


    public MenuItem(itemType i, String n, double p) {
        item = i;
        name = n;
        price = p;
    }

    public itemType getItemType() {
        return item;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}