package com.mateusz.lebioda.service.change.money;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ChangeMoneyTable implements DoChangeMoney {

    private static Logger logger = LoggerFactory.getLogger("ChangeMoneyRecursionTable");

    private int money;
    private List<Integer> denominations;


    public long getValue(){
        List<Long> previousList = createFirsTable();

        for(int i=1; i<denominations.size(); i++){
            previousList = doIteration(previousList, denominations.get(i));
        }

        return getLastValue(previousList);
    }

    private List<Long> createFirsTable(){
        List<Long> actList = new ArrayList<>();
        long denomination = this.denominations.get(0);

        for(int i=0; i<=money; i++){
            if(i % denomination == 0){
                actList.add(1L);
            }else {
                actList.add(0L);
            }
        }
        return actList;
    }


    private List<Long> doIteration(List<Long> prewList, int value){
        List<Long> actList = new ArrayList<>();
        for (int i = 0; i<=money; i++) {
            long prewValue = i-value < 0 ? 0 : actList.get(i-value);
            actList.add(prewList.remove(0) + prewValue);
        }
        return actList;
    }

    private long getLastValue(List<Long> list) {
        return list.get(money);
    }

    @Override
    public void doChangeMoney(int moneyToChange, List<Integer> denominations) {
        this.money = moneyToChange;
        denominations.sort(Integer::compareTo);
        this.denominations = denominations;
        logger.info("" + getValue());
    }
}
