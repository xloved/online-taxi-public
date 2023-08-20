package com.hgx.tank.strategy;

import com.hgx.tank.tank.Bullet;
import com.hgx.tank.tank.Tank;

/**
 * 定义默认的开火方式
 */
public class DefaultFireStrategy implements FireStrategy{

    private DefaultFireStrategy(){};
    private static DefaultFireStrategy defaultFireStrategy = new DefaultFireStrategy();

    public static DefaultFireStrategy getDefaultFireStrategy(){
        return defaultFireStrategy;
    }
    //调用默认的开火方式
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        new Bullet(bX,bY,tank.getDir(),tank.getGroup());
    }
}
