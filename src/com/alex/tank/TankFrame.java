package com.alex.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

//版本2 初始化来实现
public class TankFrame extends Frame {

    Tank tank = new Tank(200, 200, Dir.DOWN, Group.GOOD, this);
    //    Bullet bullet = new Bullet(300, 300, Dir.DOWN);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
//    Explode explode = new Explode(100, 100, this);


    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    public TankFrame() {
        setVisible(true);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new keyListener());
    }

    //绘图,Graphics 画笔
    @Override
    public void paint(Graphics g) {
        //我方坦克
        tank.paint(g);

        //敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        //我方子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        //碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

        for(int i = 0;i<explodes.size();i++){
            explodes.get(i).paint(g);
        }
//        explode.paint(g);
    }


    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }

        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);

    }

    class keyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
            }

            setTankDir();
        }

        private void setTankDir() {
            if (!bL && !bR && !bU && !bD) {
                tank.setMoving(false);
            } else {
                tank.setMoving(true);
                if (bL) {
                    tank.setDir(Dir.LEFT);
                }
                if (bR) {
                    tank.setDir(Dir.RIGHT);
                }
                if (bU) {
                    tank.setDir(Dir.UP);
                }
                if (bD) {
                    tank.setDir(Dir.DOWN);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;

            }

            setTankDir();
        }
    }

}
