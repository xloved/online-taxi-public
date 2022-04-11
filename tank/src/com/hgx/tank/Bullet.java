package com.hgx.tank;

import java.awt.*;
import java.util.Random;

/**
 * 炮弹类
 */
public class Bullet {

    private int x,y;
    private Dir dir;
    private static final int SPEED = 8;
    //private static final int WIDTH = 20,HEIGHT = 20;
    //获取炮弹起始位置
    public static int WIDTH = ResouceMgr.bulletD.getWidth();
    public static int HEIGHT =ResouceMgr.bulletD.getHeight();
    //子弹是否存活
    private boolean living = true;
    //引入tankFrame属性
    private TankFrame tf = null;
    //封装坦克阵营类
    private Group group = Group.BAO;

    //定义坦克相交的位置
    Rectangle rect = new Rectangle();

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

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }
    //设置炮弹坐标和颜色

    public void paint(Graphics graphics){
        if(!living){
            tf.bulletList.remove(this);
        }
         /*Color color = graphics.getColor();//获取默认颜色
         graphics.setColor(Color.red);//设置炮弹颜色为红色
         graphics.fillOval(x,y,WIDTH,HEIGHT);//设置炮弹坐标
         graphics.setColor(color);//回到默认颜色*/
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
    //碰撞检测方法
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;
//        Rectangle rectangle1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
//        Rectangle rectangle2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        //判断子弹和坦克是否相交   intersects是判断一个方块与另一个方块相交
//        if(rectangle1.intersects(rectangle2)){
//               tank.die();//坦克消失
//               this.die();//子弹消失
//            //让爆照在坦克中心爆照
//            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH;
//            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
//               tf.explodes.add(new Explode(eX,eY,tf));//坦克和子弹交互时发生爆炸
//        }
        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(new Explode(eX,eY,tf));//坦克和子弹交互时发生爆炸
        }
    }

    private void die() {
          this.living = false;
    }
}
