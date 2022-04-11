package com.hgx.tank;



public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

        for(int i = 0; i < initTankCount; i++){
            tf.tanks.add(new Tank(80 +i*80, 200, Dir.DOWN, tf, Group.BAO));
        }
        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
