package com.alex.tank;

public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIGHT / 2 - Bullet.WIDTH;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT;
        Bullet bullet = new Bullet(bx, by, tank.getDir(), tank.getGroup(), tank.getTankFrame());
        tank.getTankFrame().bullets.add(bullet);
    }
}
