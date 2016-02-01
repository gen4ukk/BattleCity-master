package com.company;

import constants.Constants;

import java.awt.*;

/**
 * Created by gen4ukk on 31.01.2016.
 */
public class Crash {

    private Graphics2D graphics2D;

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    public void printTexture(){
        for (BrickTexture brickTexture : Myobject.arrayTexture) {
            brickTexture.print(graphics2D);
        }
    }

    public boolean crashBulletTank(Bullet bullet, Tank tank){
        if (bullet.getDelta_X() < 0) {
            if (checkClash(bullet.getX(), tank.getX() + Constants.TANK_SIZE, bullet.getY(), tank.getY(), tank.getY() + Constants.TANK_SIZE, bullet.getY() + Constants.BULLET_SIZE, Constants.BULLET_SPEED)) {
                return  true;
            }
        } else if (bullet.getDelta_X() > 0) {
            if (checkClash(tank.getX(), bullet.getX() + Constants.BULLET_SIZE, bullet.getY(), tank.getY(), tank.getY() + Constants.TANK_SIZE, bullet.getY() + Constants.BULLET_SIZE, Constants.BULLET_SPEED)) {
                return  true;
            }
        } else if (bullet.getDelta_Y() > 0) {
            if (checkClash(tank.getY(), bullet.getY() + Constants.BULLET_SIZE, bullet.getX(), tank.getX(), tank.getX() + Constants.TANK_SIZE, bullet.getX() + Constants.BULLET_SIZE, Constants.BULLET_SPEED)) {
                return  true;
            }
        }else if (bullet.getDelta_Y() < 0) {
            if (checkClash(bullet.getY(), tank.getY()+Constants.TANK_SIZE, bullet.getX(), tank.getX(), tank.getX() + Constants.TANK_SIZE, bullet.getX() + Constants.BULLET_SIZE, Constants.BULLET_SPEED)) {
                return  true;
            }
        }
        return false;
    }

    public boolean crashBulletTexture(Bullet bullet, BrickTexture brickTexture2){
        boolean checkCrash = false;

            if (bullet.getDelta_X() < 0) {
//                BrickTexture brickTexture2 = Myobject.arrayTexture.get(i);
                if (checkClash(bullet.getX(), brickTexture2.getX() + Constants.TEXTURE_SIZE, brickTexture2.getY(), bullet.getyTank(), bullet.getyTank() + Constants.TANK_SIZE, brickTexture2.getY() + Constants.TEXTURE_SIZE, Constants.BULLET_SPEED)) {
                    Myobject.arrayTexture.remove(brickTexture2);
                    checkCrash = true;
                }
//                else {
//                    i++;
//                }
//            }
        } else if (bullet.getDelta_Y() < 0) {
//            int i = 0;
//            while (i < Myobject.arrayTexture.size()) {
//                BrickTexture brickTexture2 = Myobject.arrayTexture.get(i);
                if (checkClash(bullet.getY(), brickTexture2.getY() + Constants.TEXTURE_SIZE, brickTexture2.getX(), bullet.getxTank(), bullet.getxTank() + Constants.TANK_SIZE, brickTexture2.getX() + Constants.TEXTURE_SIZE, Constants.BULLET_SPEED)) {
                    Myobject.arrayTexture.remove(brickTexture2);
                    checkCrash = true;
                }
//                else {
//                    i++;
//                }
            //}
        } else if (bullet.getDelta_X() > 0) {
//            int i = 0;
//            while (i < Myobject.arrayTexture.size()) {
//                BrickTexture brickTexture2 = Myobject.arrayTexture.get(i);
                if (checkClash(brickTexture2.getX(), bullet.getX() + Constants.BULLET_SIZE, brickTexture2.getY(), bullet.getyTank(), bullet.getyTank() + Constants.TANK_SIZE, brickTexture2.getY() + Constants.TEXTURE_SIZE, Constants.BULLET_SPEED)) {
                    Myobject.arrayTexture.remove(brickTexture2);
                    checkCrash = true;
                }
//                else {
//                    i++;
//                }
//            }
        } else if (bullet.getDelta_Y() > 0) {
//            int i = 0;
//            while (i < Myobject.arrayTexture.size()) {
//                BrickTexture brickTexture2 = Myobject.arrayTexture.get(i);
                if (checkClash(bullet.getY() + Constants.BULLET_SIZE, brickTexture2.getY(), brickTexture2.getX(), bullet.getxTank(), bullet.getxTank() + Constants.TANK_SIZE, brickTexture2.getX() + Constants.TEXTURE_SIZE, Constants.BULLET_SPEED)) {
                    Myobject.arrayTexture.remove(brickTexture2);
                    checkCrash = true;
                }
//                else {
//                    i++;
//                }
            }
//        }

        return checkCrash;
    }



    static boolean checkClash(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int step) {
        if (arg1 - arg2 <= step && arg1 - arg2 >= 0 && ((arg3 > arg4 && arg3 < arg5) || (arg6 > arg4 && arg6 < arg5))) {
            return true;
        } else {
            return false;
        }
    }



}
