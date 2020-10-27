package com.alex.tank;

import java.awt.*;

public class Explode {
    public static int WIDTH = ResourceMgr.getInstance().getExplodes()[0].getWidth();
    public static int HEIGH = ResourceMgr.getInstance().getExplodes()[0].getHeight();
    int x, y;
    private boolean living = true;
    TankFrame tankFrame;
    private int step = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.explodes.remove(this);
            return;
        }
        g.drawImage(ResourceMgr.getInstance2().getExplodes()[step++], x, y, null);
//        Audio audio = new Audio("audio/explode.wav");
//        audio.play();
        if (step >= ResourceMgr.getInstance2().getExplodes().length) {
            step = 0;
            this.living = false;
        }
    }
}
