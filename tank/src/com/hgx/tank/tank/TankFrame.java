package com.hgx.tank.tank;

import com.hgx.tank.FMResponsibility.GameModel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 *  frame.setVisible(true);设置显示窗口
 *  frame.setTitle("Thank War");  设置窗口标题
 *  frame.setSize(800,600); 设置窗口大小
 *  frame.setResizable(false); 设置窗口不可改变大小
 *  frame.addWindowListener 设置窗口可点击关闭
 */
public class TankFrame extends Frame {

    GameModel gm = GameModel.getInstance();
    static final int GAME_WIDTH = 1080,GAME_HEIGHT = 960;


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
        gm.paint(graphics);
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
                case KeyEvent.VK_S:
                    gm.save();
                    break;
                case KeyEvent.VK_L:
                    gm.load();
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
                        gm.getMainTank().fire();//开火
                        break;
                    default:

                        break;
            }

            setMainTankDir();
        }

       private void setMainTankDir(){
           Tank  tank = gm.getMainTank();
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
