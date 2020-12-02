package com.mateusz.lebioda;

import com.mateusz.lebioda.service.change.money.ChangeMoney;
import com.mateusz.lebioda.service.change.money.ChangeMoneyGraphic;
import com.mateusz.lebioda.service.change.money.DoChangeMoney;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Logger logger = LoggerFactory.getLogger("ChangeMoneyBuilder");

    private static int moneyToChange = 0;
    private static List<Integer> denomination = new ArrayList<>();
    private static DoChangeMoney generator;

    public static void main(String[] args) {
        init(args);

        long start = System.currentTimeMillis();

        generator.doChangeMoney(moneyToChange, denomination);

        long generationTime = System.currentTimeMillis() - start;
        LocalTime time = LocalTime.ofSecondOfDay(generationTime / 1000);

        logger.info("Generation time: {}s", time);
    }

    private static void init(String[] arg) {
        generator = new ChangeMoney();
        for (int i = 0; i < arg.length; i++) {
            switch (arg[i].toLowerCase()) {
                case "-m":
                    moneyToChange = Integer.parseInt(arg[i + 1]);
                    break;
                case "-d":
                    denomination = decodeDenominateList(arg[i + 1]);
                    break;
                case "-g":
                    generator = new ChangeMoneyGraphic(false);
                    break;
                case "-t":
                    generator = new ChangeMoneyGraphic(true);
                    break;
                case "-h":
                    logger.info("\n\n" +
                            " -m -> Money to change. For example: -m 500 \n\n" +
                            " -d -> Denomination list. For example: -d [1,2,5] \n\n" +
                            " -g -> ON graphic mode. \n\n" +
                            " -t -> On text mode. Application will save result in txt file. \n\n" +
                            " WARRING! To both of above mode generation time will be longer and to big data you can get StackOverFlow Exception\n\n");
                    System.exit(-1);
            }
        }

        if (moneyToChange <= 0 || denomination.isEmpty()) {
            logger.error("\nInvalid input.\n\n Use \"-h\" to get more information");
            System.exit(-1);
        }
    }

    private static List<Integer> decodeDenominateList(String arg) {
        String stringList = arg.substring(arg.indexOf('[') + 1, arg.indexOf(']'));
        String[] valueList = stringList.split(",");
        List<Integer> denominate = new ArrayList<>();
        try {
            for (String value : valueList) {
                denominate.add(Integer.valueOf(value));
            }
        } catch (NumberFormatException e) {
            logger.error("Invalid list format");
            System.exit(-1);
        }
        return denominate;
    }
}
