package com.mateusz.lebioda.service.change.money;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.stream.Collectors;

public class ChangeMoney implements DoChangeMoney {
    private static Logger logger = LoggerFactory.getLogger("ChangeMoneyService");

    private int index = 0;

    public void doChangeMoney(int moneyToChange, List<Integer> denominations) {
        index = 0;
        denominations = denominations.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        newIteration(moneyToChange, denominations, denominations.get(0));
        print();
    }

    private void newIteration(int moneyToChange, List<Integer> denominations, int stopDenomination) {
        for (int denomination : denominations) {
            if (canISubtractAmount(moneyToChange, denomination) && denomination >= stopDenomination) {
                int localMoneyToChange = moneyToChange - denomination;
                if (localMoneyToChange == 0) {
                    index++;
                }
                newIteration(localMoneyToChange, denominations, denomination);
            }
        }
    }

    private boolean canISubtractAmount(int moneyToSubtract, int denomination) {
        return moneyToSubtract - denomination >= 0;
    }


    public void print() {
        logger.info("Numbers of solutions:  {}", getResultSize());
    }

    public int getResultSize() {
        return index;
    }
}
