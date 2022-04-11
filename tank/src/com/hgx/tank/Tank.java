package com.hgx.tank;

import com.sun.xml.internal.bind.v2.TODO;

import java.awt.*;
import java.util.Random;

/**
 * tank类，记载坦克的属性和方法
 */
public class Tank {

    private int x , y;//设置tank初始化坐标
    private Dir dir = Dir.DOWN;//tank初始方向
    private static final int XSPEED = 3, YSPEED = 3;//tank每次移动速度
    //TODO：设置坦克初始化移动
    private boolean moving = true;//坦克静止
    private boolean living = true;//坦克是否活着
    private TankFrame tf = null;
    public static int WIDTH = ResouceMgr.goodTankD.getWidth();
    public static int HEIGHT =ResouceMgr.goodTankD.getHeight();
    private Random random = new Random();//设置随机改变坦克方向
    private Group group = Group.BAO;//默认坦克是敌人

    Rectangle rect = new Rectangle();

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
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

    public void paint(Graphics graphics) {
        //设置坐标和图形大小
        /*Color color = graphics.getColor();
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x, y, 50, 50);
        graphics.setColor(color);*/
        if(!living) tf.tanks.remove(this) ; //坦克不存活,不画出坦克
        switch(dir){
            case LEFT:
                //graphics.drawImage(ResouceMgr.tankL, x, y, null);
                graphics.drawImage(this.group == Group.GOOD? ResouceMgr.goodTankL : ResouceMgr.badTankL, x, y, null);
                break;
            case UP:
                //graphics.drawImage(ResouceMgr.tankU, x, y, null);
                graphics.drawImage(this.group == Group.GOOD? ResouceMgr.goodTankU : ResouceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                //graphics.drawImage(ResouceMgr.tankR, x, y, null);
                graphics.drawImage(this.group == Group.GOOD? ResouceMgr.goodTankR : ResouceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                //graphics.drawImage(ResouceMgr.tankD, x, y, null);
                graphics.drawImage(this.group == Group.GOOD? ResouceMgr.goodTankD : ResouceMgr.badTankD, x, y, null);
                break;
        }
        move();

    }

    private void move() {
        if(!moving) return ;
        //循环tank方向
        switch (dir){
            case LEFT:
                x -= XSPEED;
                break;
            case LU:
                x -= XSPEED;
                y -= YSPEED;
                break;
            case UP:
                y -= YSPEED;
                break;
            case UR:
                x += XSPEED;
                y -= YSPEED;
                break;
            case RIGHT:
                x += XSPEED;
                break;
            case RD:
                x += XSPEED;
                y += YSPEED;
                break;
            case DOWN:
                y += YSPEED;
                break;
            case DL:
                x -= XSPEED;
                y += YSPEED;
                break;
            default:
                break;
        }
//        if(random.nextInt(10) > 8) this.fire();//坦克随机移动发射子弹
        if(this.group == Group.BAO && random.nextInt(100) > 95){
               this.fire();
        }
                 //判断坦克是否为地方坦克，然后进行移动
               if(this.group == Group.BAO && random.nextInt(10) > 6){
                   randomDir();//调用坦克随机移动方位方法
               }
               boundsCheck();
        //update rect
        rect.x = this.x;
        rect.y = this.y;
    }
     //边界检测，防止坦克出边界
    private void boundsCheck() {
        if(this.x < 2) x = 2;
        if(this.y < 28) y = 28;
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2)  x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT -Tank.HEIGHT - 2;
    }

    //坦克随机移动方向方法
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
           int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH;
           int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
            tf.bulletList.add(new Bullet(bX,bY,this.dir,this.group,this.tf));
            //进行坦克判断
        //if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
           this.living = false;
    }
}
