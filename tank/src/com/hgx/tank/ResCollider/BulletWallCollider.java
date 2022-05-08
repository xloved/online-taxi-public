package com.hgx.tank.ResCollider;

import com.hgx.tank.Bullet;
import com.hgx.tank.FMResponsibility.GameObject;
import com.hgx.tank.Tank;
import com.hgx.tank.Wall;

/**
 * 坦克坦克碰撞检测
 */
public class BulletWallCollider implements Collider {


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
         //判断o1,o2是否属于各自的实例,如果是则o1=b,o2=w
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;

             //判断b与w的位置是否相交，相交则b调用die()消失;
            if (b.rect.intersects(w.rect)) {
                b.die();

            }
        }else if(o1 instanceof Wall && o2 instanceof Bullet){
             return collide(o2,o1);
        }
        return true; //条件都满足往下继续执行
    }
}