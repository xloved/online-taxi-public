package com.hgx.tank;

import com.hgx.tank.FMResponsibility.GameModel;
import com.hgx.tank.FMResponsibility.GameObject;

import java.awt.*;
import java.util.Random;

/**
 * 炮弹类
 */
public class Bullet extends GameObject {

    private Dir dir;
    private static final int SPEED = 7;
    //获取炮弹起始位置
    public static int WIDTH = ResouceMgr.bulletD.getWidth();
    public static int HEIGHT =ResouceMgr.bulletD.getHeight();
    //子弹是否存活
    private boolean living = true;
    //封装坦克阵营类
    private Group group = Group.BAO;
    //定义坦克相交的位置
    public Rectangle rect = new Rectangle();



    public boolean isLive() {
        return living;
    }

    public void setLive(boolean living) {
        this.living = living;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        //设置tf
        GameModel.getInstance().add(this);
    }
    //设置炮弹坐标和颜色

    public void paint(Graphics graphics){
        if(!living){
            GameModel.getInstance().remove(this);
        }
        switch(dir){
            case LEFT:
                graphics.drawImage(ResouceMgr.bulletL, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResouceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResouceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResouceMgr.bulletD, x, y, null);
                break;
        }
         move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {

        //循环炮弹方向
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        //update rect
        rect.x = this.x;
        rect.y = this.y;
        //判断坦克打出的子弹是否超过游戏画面规定的长度，如果超过，则子弹不存活
        if(x <0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living =false;
        }
    }

    public void die() {
          this.living = false;
    }
}
