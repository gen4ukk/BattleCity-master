package com.company;

import constants.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gen4ukk on 25.01.2016.
 */
public class Bullet implements Texture{
    private Image bulletI = new ImageIcon("bullet2.png").getImage();
    private int xB;
    private int yB;
    private int tempxB;
    private int tempyB;

    private int xTank;
    private int yTank;
    private Graphics2D graphics2D;
    private int deltaX;
    private int deltaY;
    private int height = 10;
    private int weight = 10;
    private boolean check = false;

    void initBullet(int _x, int _y, int _deltaX, int _deltaY, int _xTank, int _yTank) {
        xB = _x;
        yB = _y;
        xTank = _xTank;
        yTank = _yTank;
        deltaX = _deltaX;
        deltaY = _deltaY;
    }

    public void setDead() {
        check = false;
    }

    public void checkEndOfPoint() {
        if (this.getX() >= Constants.WIDTH || this.getX() <= 0 || this.getY() >= Constants.HEIGHT || this.getY() <= 0) {
            check = false;
        }
    }


    public void setCheck(boolean check) {
        this.check = check;
    }

    void move() {

        if (!check) {
            graphics2D.drawImage(bulletI, tempxB, tempyB, height, weight, null);
            tempxB = xB + deltaX;
            tempyB = yB + deltaY;
            xB = Constants.WIDTH;
            yB = Constants.HEIGHT;
        } else {
            graphics2D.drawImage(bulletI, xB, yB, height, weight, null);
            xB = xB + deltaX;
            yB = yB + deltaY;
        }
    }

    @Override
    public void print(Graphics2D _graphics2D) {
        graphics2D = _graphics2D;
    }

    public int getX() {
        return xB;
    }

    public boolean isCheck() {
        return check;
    }

    public int getY() {
        return yB;
    }


    public int getyTank() {
        return yTank;
    }

    public int getxTank() {
        return xTank;
    }

    @Override
    public int getDelta_X() {
        return this.deltaX;
    }

    @Override
    public int getDelta_Y() {
        return this.deltaY;
    }


}

