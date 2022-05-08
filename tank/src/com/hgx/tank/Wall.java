package com.hgx.tank;

import com.hgx.tank.FMResponsibility.GameObject;

import java.awt.*;

/**
 * 墙类
 */
public class Wall extends GameObject {

     int w, h;
     public Rectangle rect;

    public Wall(int x,int y,int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.rect = new Rectangle(x,y,w,h);
    }

    @Override
    public void paint(Graphics graphics) {
            Color color = graphics.getColor();
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(x,y,w,h);
            graphics.setColor(color);
    }

    @Override
    public int getWidth() {
        return w;
    }

    @Override
    public int getHeight() {
        return h;
    }
}
