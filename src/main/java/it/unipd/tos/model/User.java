////////////////////////////////////////////////////////////////////
// [ANDREA] [MASCARI] [1187132]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.time.LocalDate;

public class User {
    private String ID;
    private LocalDate nascita;

    public User(String i, LocalDate n) {
        ID = i;
        nascita = n;

    }

    public String getID() {
        return ID;
    }

    public LocalDate getNascita() {
        return nascita;
    }

}
