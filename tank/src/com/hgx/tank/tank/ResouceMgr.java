package com.hgx.tank.tank;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * 资源管理类，放入图片等
 */
public class ResouceMgr {
    //public static BufferedImage tankL, tankU, tankR, tankD;
    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    public static BufferedImage bulletL, bulletU,bulletR,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static{
        try {
            goodTankU = ImageIO.read(ResouceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

            badTankU = ImageIO.read(ResouceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            bulletU = ImageIO.read(Objects.requireNonNull(ResouceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png")));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            for(int i = 0; i < 16; i++){
                explodes[i] = ImageIO.read(Objects.requireNonNull(ResouceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif")));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
