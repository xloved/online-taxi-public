package com.hgx.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 *  frame.setVisible(true);设置显示窗口
 *  frame.setTitle("Thank War");  设置窗口标题
 *  frame.setSize(800,600); 设置窗口大小
 *  frame.setResizable(false); 设置窗口不可改变大小
 *  frame.addWindowListener 设置窗口可点击关闭
 */
public class TankFrame extends Frame {
    //设置初始化坦克坐标和移动方向
    Tank tank = new Tank(200,400,Dir.DOWN,this,Group.GOOD);
    //Tank tank1 = new Tank(200,400,Dir.DOWN,this);
    List<Tank>   tanks = new ArrayList<>();
    List<Bullet> bulletList = new ArrayList<Bullet>();//把子弹放进一个容器中
    //Bullet bullet = new Bullet(300,300,Dir.DOWN);
    static final int GAME_WIDTH = 1080,GAME_HEIGHT = 960;
    //Explode explode = new Explode(110,100,this);
    List<Explode> explodes = new ArrayList<>();//把爆炸类放进容器中

    public TankFrame() throws HeadlessException {
        setTitle("TANK WAR");
        setVisible(true);
        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());//键盘监听
    }


    Image offScreenImage = null;//设置一张图片

    /**
     * 解决坦克显示闪烁，解决双缓冲问题
     *
     */
    @Override
    public void update(Graphics graphics){
        if(offScreenImage == null){
             offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);//如果没有图片创建一张
        }else{
            Graphics goffScreen = offScreenImage.getGraphics();//获取画笔
            Color color = goffScreen.getColor();//获取默认颜色
            goffScreen.setColor(Color.black);//设置当前图片颜色
            goffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);//设置图片填充大小
            goffScreen.setColor(color);//返回默认颜色
            paint(goffScreen);//调用paint方法,把画笔传递给paint
            graphics.drawImage(offScreenImage,0,0,null);//把整张图片画到屏幕上
        }

    }
    /**
     * paint方法是设置绘图
     * Graphics 是画笔
     */
    @Override
    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.CYAN);
        graphics.drawString("子弹的数量为："+bulletList.size(),10,50);
        graphics.drawString("坦克的数量为："+tanks.size(),10,70);
        graphics.drawString("爆炸的数量为："+explodes.size(),10,90);
        graphics.setColor(color);
        tank.paint(graphics);
       //画出坦克可以连续发射子弹
//      for(int i = 0; i<bulletList.size();i++){
//                   bulletList.get(i).paint(graphics);
//        }
         //画出敌军的坦克数量
        for(int i = 0; i < tanks.size(); i++){
            tanks.get(i).paint(graphics);
        }
         //画出爆炸的数量
        for(int i = 0; i < explodes.size(); i++){
            explodes.get(i).paint(graphics);
        }
        //碰撞检测
        for(int i = 0; i < bulletList.size(); i++){
              for(int j = 0; j < tanks.size(); j++){
                 bulletList.get(i).collideWith(tanks.get(j));
              }
        }
        /*for (Bullet b: bulletList) {
            b.paint(graphics);
            bulletList.remove(b);
        }*/
       /* for(int i = 0 ; i < bulletList.size(); i++){
            bulletList.get(i).paint(graphics);
        }*/
          //使用迭代器进行清除
        for(Iterator<Bullet> iterator = bulletList.iterator(); iterator.hasNext();){
                 Bullet b = iterator.next();
                 if(!b.isLive()){
                    iterator.remove();
                 }
                 b.paint(graphics);
        }

        //explode.paint(graphics);
        //bullet.paint(graphics);单颗子弹
    }

    //设置键盘监听事件
    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        //键盘按压监听
        @Override
        public void keyPressed(KeyEvent e) {
            //获取当前键盘按下的值
            int key = e.getKeyCode();
            //循环当前的key是哪一个键
            switch (key) {
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
                default:
                    break;
            }
            setMainTankDir();
            //new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }

        //键盘抬起监听
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
                switch (key) {
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
                    default:

                        break;
            }

            setMainTankDir();
        }

       private void setMainTankDir(){

           if(!bL && !bU && !bR && !bD) {
               tank.setMoving(false);
           } else{
               tank.setMoving(true);//tank开始移动
               if(bL && !bU && !bR && !bD) tank.setDir(Dir.LEFT);
               if(!bL && bU && !bR && !bD) tank.setDir(Dir.UP);
               if(!bL && !bU && bR && !bD) tank.setDir(Dir.RIGHT);
               if(!bL && !bU && !bR && bD) tank.setDir(Dir.DOWN);
               if(bL && bU && !bR && !bD) tank.setDir(Dir.LU);
               if(!bL && bU && bR && !bD) tank.setDir(Dir.UR);
               if(!bL && !bU && bR && bD) tank.setDir(Dir.RD);
               if(bL && !bU && !bR && bD) tank.setDir(Dir.DL);
           }
       }

    }


}
