package com.alex.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    public static final int HEIGHT = ResourceMgrEnum.INSTANCE.getGoodTankD().getHeight();
    public static final int WIGHT = ResourceMgrEnum.INSTANCE.getGoodTankD().getWidth();
    Rectangle rect = new Rectangle();

    private final static int SPEED = 1;
    private int x;
    private int y;
    private Dir dir;
    private boolean moving = true;
    private boolean living = true;
    private Group group = Group.BAD;
    private TankFrame tankFrame;
    private FireStrategy fireStrategy;

    public static final Random RANDOM = new Random();


    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIGHT;
        rect.height = HEIGHT;

        if (group == Group.BAD) {
            fireStrategy = new DefaultFireStrategy();
        } else {
            fireStrategy = new FourBulletFireStrategy();
        }
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

        if (group == Group.GOOD) {
            switch (this.dir) {
                case LEFT:
                    g.drawImage(ResourceMgrEnum.INSTANCE.getBadTankL(), x, y, null);
                    break;
                case UP:
                    g.drawImage(ResourceMgrEnum.INSTANCE.getGoodTankU(), x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgrEnum.INSTANCE.getGoodTankR(), x, y, null);
                    break;
                case DOWN:
                    g.drawImage(ResourceMgrEnum.INSTANCE.getGoodTankD(), x, y, null);
                    break;
            }
        } else {
            switch (this.dir) {
                case LEFT:
                    g.drawImage(ResourceMgrEnum.INSTANCE.getBadTankL(), x, y, null);
                    break;
                case UP:
                    g.drawImage(ResourceMgrEnum.INSTANCE.getGoodTankU(), x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgrEnum.INSTANCE.getGoodTankR(), x, y, null);
                    break;
                case DOWN:
                    g.drawImage(ResourceMgrEnum.INSTANCE.getGoodTankD(), x, y, null);
                    break;
            }
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
        if (this.group == Group.BAD && RANDOM.nextInt(100) > 95) {
            randomDir();
        }

        //边界检测
        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 0) {
            x = 0;
        }
        if (this.y < 0) {
            y = 0;
        }
        if (x > TankFrame.GAME_WIDTH - Tank.WIGHT) {
            x = TankFrame.GAME_WIDTH - Tank.WIGHT;
        }
        if (y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }

    }

    public void fire() {
        //修改为策略模式
        FireStrategy fireStrategy = getFireStrategy();
        fireStrategy.fire(this);
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

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public FireStrategy getFireStrategy() {
        if (this.fireStrategy == null) {
            return new FourBulletFireStrategy();
        }
        return fireStrategy;
    }

    public void setFireStrategy(FireStrategy fireStrategy) {
        this.fireStrategy = fireStrategy;
    }
}
