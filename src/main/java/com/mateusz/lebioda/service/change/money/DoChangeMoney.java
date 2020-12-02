package com.mateusz.lebioda.service.change.money;

import java.util.List;

public interface DoChangeMoney {
    void doChangeMoney(int moneyToChange, List<Integer> denominations);
}
