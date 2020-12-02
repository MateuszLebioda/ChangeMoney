package com.mateusz.lebioda.service.change.money;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ChangeMoneyGraphic implements DoChangeMoney {

    public ChangeMoneyGraphic(boolean txtMode) {
        this.txtMode = txtMode;
    }

    private static Logger logger = LoggerFactory.getLogger("ChangeMoneyGraphicalService");
    private boolean txtMode;

    List<List<Integer>> results = new ArrayList<>();

    public void doChangeMoney(int moneyToChange, List<Integer> denominations) {
        denominations = denominations.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        newIteration(moneyToChange, denominations, new ArrayList<>(), denominations.get(0));
        if (txtMode) {
            printToFile();
        } else {
            print();
        }
    }

    private void newIteration(int moneyToChange, List<Integer> denominations, List<Integer> alreadyChanged, int stopDenomination) {
        for (int denomination : denominations) {
            if (canISubtractAmount(moneyToChange, denomination) && denomination >= stopDenomination) {
                int localMoneyToChange = moneyToChange - denomination;
                List<Integer> localAlreadyChanged = new ArrayList<>(alreadyChanged);
                localAlreadyChanged.add(denomination);
                if (localMoneyToChange == 0) {
                    results.add(localAlreadyChanged);
                }
                newIteration(localMoneyToChange, denominations, localAlreadyChanged, denomination);
            }
        }
    }

    private boolean canISubtractAmount(int moneyToSubtract, int denomination) {
        return moneyToSubtract - denomination >= 0;
    }

    public List<List<Integer>> getResults() {
        return results;
    }

    public void print() {
        for (List<Integer> list : getResults()) {
            logger.info(list.toString());
        }
        logger.info("Numbers of solutions:  {}", results.size());
    }

    private void printToFile() {
        try(FileWriter myWriter = new FileWriter("Output.txt")) {
            for (List<Integer> list : getResults()) {
                myWriter.write(list.toString() + "\n");
            }
        } catch (IOException e) {
            logger.error("Failde write to file.");
        }
        logger.info("Numbers of solution:  {}", results.size());
    }
}
