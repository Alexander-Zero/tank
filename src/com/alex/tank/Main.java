package com.alex.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int initTankCount = Integer.parseInt(PropertiesMgr.get("tankCount"));

        TankFrame tankFrame = new TankFrame();

        //敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            Tank tank = new Tank(i * 50 + 100, 100, Dir.DOWN, Group.BAD, tankFrame);
            tankFrame.tanks.add(tank);
        }

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
