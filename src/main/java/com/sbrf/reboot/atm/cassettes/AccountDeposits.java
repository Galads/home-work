package com.sbrf.reboot.atm.cassettes;

import lombok.NonNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public class AccountDeposits<T extends Deposit> {
    private List<T> arrDeposits;

    AccountDeposits(ArrayList<T> accountDeposit) {
        this.arrDeposits = accountDeposit;
    }

    public double getApy(@NonNull T deposit) {
        return deposit.getApy();
    }

    public ArrayList<T> getAmountAfter() {
        ArrayList<T> apyResAmount = new ArrayList<>();

        arrDeposits.forEach(e -> {
                    BigDecimal befVal = e.getValue();
                        BigDecimal resVal = befVal.multiply(
                                BigDecimal.valueOf(e.getApy()),
                                MathContext.DECIMAL64);
                        resVal = resVal.divide(BigDecimal.valueOf(100.0), MathContext.DECIMAL64);
                        e.setValue(befVal.add(resVal));
                    apyResAmount.add(e);
                }
        );
        return apyResAmount;
    }
}