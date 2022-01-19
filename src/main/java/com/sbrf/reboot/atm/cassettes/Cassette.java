package com.sbrf.reboot.atm.cassettes;

import java.util.ArrayList;
import java.util.List;

public class Cassette<T extends Banknote> {
    private List<T> arrBanknotes;

    public Cassette(ArrayList<T> collection) {
        this.arrBanknotes = collection;
    }

    public int getCountBanknotes() {
        return this.arrBanknotes.size();
    }
}
