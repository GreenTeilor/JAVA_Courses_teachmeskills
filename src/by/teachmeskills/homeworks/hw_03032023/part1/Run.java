package by.teachmeskills.homeworks.hw_03032023.part1;

import by.teachmeskills.homeworks.hw_03032023.part1.hands.*;
import by.teachmeskills.homeworks.hw_03032023.part1.heads.*;
import by.teachmeskills.homeworks.hw_03032023.part1.legs.*;

public class Run {
    public static IRobot findMostExpensiveRobot(IRobot[] robots) {
        if (robots != null && robots.length != 0) {
            IRobot maxPriceRobot = robots[0];
            for (IRobot robot : robots) {
                maxPriceRobot = robot.getPrice() > maxPriceRobot.getPrice() ? robot : maxPriceRobot;
            }
            return maxPriceRobot;
        }
        return null;
    }

    public static void main(String[] args) {
        IRobot robot1 = new Robot(new HeadSamsung(4), new HandToshiba(7), new LegSony(11));
        IRobot robot2 = new Robot(new HeadSony(3), new HandSamsung(14), new LegToshiba(2));
        IRobot robot3 = new Robot(new HeadToshiba(15), new HandSony(5), new LegSamsung(7));
        IRobot[] robots = {robot1, robot2, robot3};
        for (IRobot robot : robots) {
            robot.action();
            System.out.println("\n");
        }
        System.out.println("Most expensive robot price: " + findMostExpensiveRobot(robots).getPrice());
    }
}
