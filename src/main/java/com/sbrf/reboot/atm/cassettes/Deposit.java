package com.sbrf.reboot.atm.cassettes;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

public class Deposit {
    private boolean floatingRate = false;
    @Getter
    private double apy;
    @Setter
    @Getter
    @NonNull
    private BigDecimal value = new BigDecimal("0.0");
    public int days;

    public void setValues(BigDecimal value, double apy, int days, boolean fr) {
        this.value = value;
        this.apy = apy;
        this.days = days;
        this.floatingRate = fr;
    }
}
