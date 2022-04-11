package com.hgx.tank;

import java.awt.*;

/**
 * 坦克爆炸类
 */
public class Explode {
       public static int WIDTH = ResouceMgr.explodes[0].getWidth();
       public static int HEIGHT = ResouceMgr.explodes[0].getHeight();
       private int x, y;
       private boolean living = true;
       TankFrame tf = null;
       private int step = 0;

    public Explode(int x, int y,  TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        //new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics graphics){
        graphics.drawImage(ResouceMgr.explodes[step++], x, y, null);
        if(step >= ResouceMgr.explodes.length){
//            step = 0;
            //清除爆炸
              tf.explodes.remove(this);
        }
    }

}
