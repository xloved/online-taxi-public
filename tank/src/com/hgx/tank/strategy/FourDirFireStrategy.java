package com.hgx.tank.strategy;

import com.hgx.tank.tank.Bullet;
import com.hgx.tank.tank.Dir;
import com.hgx.tank.tank.Tank;

public class FourDirFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank tank) {
        Dir[] dirs =Dir.values();
        int bX = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        for (Dir dir:dirs) {
            new Bullet(bX,bY,dir,tank.getGroup());
        }

    }
}
