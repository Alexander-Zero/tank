package com.alex.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();
    public static final int WIGHT = ResourceMgr.tankD.getWidth();

    private final static int SPEED = 1;
    private int x;
    private int y;
    private Dir dir;
    private boolean moving = true;
    private boolean living = true;
    private Group group = Group.BAD;
    private TankFrame tankFrame;

    public static final Random RANDOM = new Random();


    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.tanks.remove(this);
            return;
        }

        switch (this.dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }
//        g.setColor(Color.ORANGE);
//        g.fillRect(x, y, 50, 50);
        move();
    }

    private void move() {
        if (!moving) {
            return;
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        if (this.group == Group.BAD && RANDOM.nextInt(100) > 95) {
            fire();
        }
        if (this.group == Group.BAD) {
            randomDir();
        }
    }

    public void fire() {
        int bx = this.x + Tank.WIGHT / 2 - Bullet.WIDTH;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT;
        Bullet bullet = new Bullet(bx, by, this.dir, this.group, this.tankFrame);
        tankFrame.bullets.add(bullet);
    }

    public void die() {
        this.living = false;
    }

    private void randomDir() {
        this.dir = Dir.values()[RANDOM.nextInt(4)];


    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


}
