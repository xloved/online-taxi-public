package com.hgx.tank.FMResponsibility;

import com.hgx.tank.*;
import com.hgx.tank.ResCollider.ColliderChain;

import java.awt.*;
import java.util.ArrayList;

import java.util.List;

public class GameModel {
    /**
     * 类加载的时候先加载INSTANCE，然后加载静态语句块调用init()创建new Tank
     * 这样的话初始化tank和GameModel就不会形成闭环，其中一个为NULL
     */
    private static final GameModel INSTANCE = new GameModel();

    static{
        INSTANCE.init();//使用静态块先加载init方法
    }

    private final List<GameObject> objects = new ArrayList<>();

    ColliderChain colliderChain = new ColliderChain();

    Tank myTank;


    public static GameModel getInstance(){
        return INSTANCE;
    }


    private GameModel(){}//私有构造

    //初始化坦克
    private void init(){
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);//初始化主站坦克
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

        for(int i = 0; i < initTankCount; i++){
            //初始化敌方Tank new Tank
            new Tank(50 + i * 80, 200, Dir.DOWN,  Group.BAO);
        }

        //初始化墙壁
        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        //add(new Wall(480,480,50,200));
        add(new Wall(300,300,50,200));
        //add(new Wall(680,680,200,50));
        add(new Wall(550,300,50,200));
    }


    public void paint(Graphics graphics){
        Color color = graphics.getColor();
        graphics.setColor(Color.CYAN);
        //graphics.drawString("子弹的数量为："+bulletList.size(),10,50);
        //graphics.drawString("坦克的数量为："+tanks.size(),10,70);
        //graphics.drawString("爆炸的数量为："+explodes.size(),10,90);
        graphics.setColor(color);
        myTank.paint(graphics);

        //画出敌军的坦克数量
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).paint(graphics);
        }

        //碰撞检测
        for(int i = 0; i < objects.size(); i++){
            for(int j = i + 1; j < objects.size(); j++){
                //bulletList.get(i).collideWith(tanks.get(j));
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                colliderChain.collide(o1,o2);
            }
        }

    }

    public Tank getMainTank(){
        return myTank;
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go){
           this.objects.remove(go);
    }

}
