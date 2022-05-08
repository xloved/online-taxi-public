package com.hgx.tank.FMResponsibility;

import java.awt.*;
import java.io.Serializable;

/**
 * 游戏物体的父类
 */
public abstract class GameObject implements Serializable {

    public int x , y;

    public abstract void paint(Graphics graphics);

    public abstract int getWidth();
    public abstract int getHeight();

}
