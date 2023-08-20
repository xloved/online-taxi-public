package com.hgx.tank.tank;

import com.hgx.tank.FMResponsibility.GameModel;
import com.hgx.tank.FMResponsibility.GameObject;

import java.awt.*;

/**
 * 坦克爆炸类
 */
public class Explode extends GameObject {
       public static int WIDTH = ResouceMgr.explodes[0].getWidth();
       public static int HEIGHT = ResouceMgr.explodes[0].getHeight();

       private int step = 0;

    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setWIDTH(int WIDTH) {
        Explode.WIDTH = WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        Explode.HEIGHT = HEIGHT;
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        GameModel.getInstance().add(this);

        //new Thread(()->new Audio("audio/explode.wav").play()).start();
    }


    public void paint(Graphics graphics){
        graphics.drawImage(ResouceMgr.explodes[step++], x, y, null);
        if(step >= ResouceMgr.explodes.length){
            //清除爆炸
              GameModel.getInstance().remove(this);
        }
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

}
