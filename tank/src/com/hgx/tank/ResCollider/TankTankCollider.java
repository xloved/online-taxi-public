package com.hgx.tank.ResCollider;

import com.hgx.tank.Bullet;
import com.hgx.tank.Explode;
import com.hgx.tank.FMResponsibility.GameObject;
import com.hgx.tank.Tank;

/**
 * 坦克坦克碰撞检测
 */
public class TankTankCollider implements Collider {


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
         //判断o1,o2是否属于各自的tank,如果是则o1=t1,o2=t2
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
             //判断t1与t2的位置是否相交，相交则t1与t2调用back()回退相交时刻的上一次坦克移动的位置;
            if (t1.rect.intersects(t2.rect)) {
                t1.back();
                t2.back();
            }
        }
        return true; //条件都满足往下继续执行
    }
}