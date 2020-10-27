package com.alex.tank;

public class FourBulletFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIGHT / 2 - Bullet.WIDTH;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT ;

        Bullet bullet = new Bullet(bx, by,Dir.DOWN, tank.getGroup(), tank.getTankFrame());
        Bullet bullet1 = new Bullet(bx, by,Dir.UP, tank.getGroup(), tank.getTankFrame());
        Bullet bullet2 = new Bullet(bx, by,Dir.LEFT, tank.getGroup(), tank.getTankFrame());
        Bullet bullet3 = new Bullet(bx, by,Dir.RIGHT, tank.getGroup(), tank.getTankFrame());

        tank.getTankFrame().bullets.add(bullet);
        tank.getTankFrame().bullets.add(bullet1);
        tank.getTankFrame().bullets.add(bullet2);
        tank.getTankFrame().bullets.add(bullet3);
    }

}
