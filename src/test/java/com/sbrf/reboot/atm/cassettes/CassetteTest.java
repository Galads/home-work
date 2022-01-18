package com.sbrf.reboot.atm.cassettes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

class CassetteTest {

    class OneHundred extends Banknote {
    }

    class OneThousand extends Banknote {
    }

    class UsdDeposit extends Deposit {
    }

    class EurDeposit extends Deposit {
    }

    @Test
    void getCountBanknotes() {
        OneHundred oneHundred = new OneHundred();

        Cassette<OneHundred> cassette = new Cassette<>(new ArrayList<OneHundred>() {{
            add(oneHundred);
//            add(new OneThousand()); //it will not compile
//            add(new Banknote()); //it will not compile
        }});

        Assertions.assertEquals(1, cassette.getCountBanknotes());
    }

    @Test
    void getAmountAfter() {
        EurDeposit eurDeposit = new EurDeposit();
        eurDeposit.setValues(new BigDecimal("432.78"), 3.6, 7, true);

        AccountDeposits<Deposit> ad = new AccountDeposits<>(
                new ArrayList<Deposit>() {{
                    add(eurDeposit);
                    add(new UsdDeposit());
                }});

        Assertions.assertEquals(new BigDecimal("448.36008"),
                ad.getAmountAfter().get(0).getValue());
    }

    @Test
    void getApy() {
        EurDeposit eurDeposit = new EurDeposit();
        eurDeposit.setValues(new BigDecimal("111.0"), 5.4, 7, true);

        AccountDeposits<Deposit> ad = new AccountDeposits<>(
                new ArrayList<Deposit>() {{
                    add(eurDeposit);
                }});
        Assertions.assertEquals(5.4, ad.getApy(eurDeposit));
    }
}