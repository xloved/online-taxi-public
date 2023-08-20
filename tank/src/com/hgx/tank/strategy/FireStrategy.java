package com.hgx.tank.strategy;

import com.hgx.tank.tank.Tank;

import java.io.Serializable;

/**
 *定义坦克开火的策略模式
 */
public interface FireStrategy extends Serializable {

    void fire(Tank tank);
}
