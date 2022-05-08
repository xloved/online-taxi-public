package com.hgx.tank.strategy;

import com.hgx.tank.Tank;

/**
 *定义坦克开火的策略模式
 */
public interface FireStrategy {

    void fire(Tank tank);
}
