package com.hgx.tank.ResCollider;

import com.hgx.tank.FMResponsibility.GameObject;

import java.io.Serializable;

/**
 * 碰撞检测接口
 */
public interface Collider extends Serializable {

    boolean collide(GameObject o1,GameObject o2);
}
