package com.hgx.tank.ResCollider;

import com.hgx.tank.Bullet;
import com.hgx.tank.FMResponsibility.GameObject;
import com.hgx.tank.Tank;
import com.hgx.tank.Wall;

/**
 * 坦克与墙碰撞检测
 */
public class TankWallCollider implements Collider {


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
         //判断o1,o2是否属于各自的实例,如果是则o1=t,o2=w
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;

             //判断t与w的位置是否相交，相交则t调用back()回退前一次位置;
            if (t.rect.intersects(w.rect)) {
                t.back();

            }
        }else if(o1 instanceof Wall && o2 instanceof Tank){
             return collide(o2,o1);
        }
        return true; //条件都满足往下继续执行
    }
}