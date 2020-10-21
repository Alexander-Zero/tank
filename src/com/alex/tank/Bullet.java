package com.alex.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 3;
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();

    private TankFrame tankFrame;
    private boolean living = true;
    private Dir dir;
    private int x;
    private int y;
    private Group group;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.bullets.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

//        Color oldColor = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x, y, HEIGHT, WIDTH);
//        g.setColor(oldColor);
        move();
    }

    private void move() {
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
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }


    /**
     * 碰撞检测
     * @param tank
     */
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }

        Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle tankRect = new Rectangle(tank.getX(), tank.getY(), Tank.WIGHT, Tank.HEIGHT);
        if (bulletRect.intersects(tankRect)) {
            tank.die();
            die();
            Explode explode = new Explode(this.x, this.y, this.tankFrame);
            tankFrame.explodes.add(explode);
        }
    }

    private void die() {
        this.living = false;
    }
}
