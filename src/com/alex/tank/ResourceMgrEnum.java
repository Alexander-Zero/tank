package com.alex.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


//枚举单例,感觉很吊的样子
public enum ResourceMgrEnum {
    INSTANCE;
    private BufferedImage goodTankL, goodTankR, goodTankU, goodTankD;
    private BufferedImage badTankL, badTankR, badTankU, badTankD;
    private BufferedImage bulletL, bulletU, bulletR, bulletD;
    private BufferedImage[] explodes = new BufferedImage[16];

    ResourceMgrEnum() {
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/goodTank1.png"));
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/badTank1.png"));
            badTankD = ImageUtil.rotateImage(badTankU, 180);
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);

            //子弹
            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public BufferedImage getGoodTankL() {
        return goodTankL;
    }

    public BufferedImage getGoodTankR() {
        return goodTankR;
    }

    public BufferedImage getGoodTankU() {
        return goodTankU;
    }

    public BufferedImage getGoodTankD() {
        return goodTankD;
    }

    public BufferedImage getBadTankL() {
        return badTankL;
    }

    public BufferedImage getBadTankR() {
        return badTankR;
    }

    public BufferedImage getBadTankU() {
        return badTankU;
    }

    public BufferedImage getBadTankD() {
        return badTankD;
    }

    public BufferedImage getBulletL() {
        return bulletL;
    }

    public BufferedImage getBulletU() {
        return bulletU;
    }

    public BufferedImage getBulletR() {
        return bulletR;
    }

    public BufferedImage getBulletD() {
        return bulletD;
    }

    public BufferedImage[] getExplodes() {
        return explodes;
    }
}
