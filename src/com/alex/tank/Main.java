package com.alex.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {

/*        //版本1
        Frame frame = new Frame();
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setTitle("坦克大战");
        //window监听器
        frame.addWindowListener(new WindowAdapter() {
            //关闭动作
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });*/


        TankFrame tankFrame = new TankFrame();

        //敌方坦克
        for (int i = 0; i < 5; i++) {
            Tank tank = new Tank(i * 50+100, 100, Dir.DOWN, Group.BAD, tankFrame);
            tankFrame.tanks.add(tank);
        }

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
