package com.hgx.tank.ResCollider;

import com.hgx.tank.FMResponsibility.GameObject;

/**
 * 碰撞检测接口
 */
public interface Collider {

    boolean collide(GameObject o1,GameObject o2);
}
