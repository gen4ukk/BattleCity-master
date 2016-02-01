package com.company;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by gen4ukk on 25.01.2016.
 */
public class Tank implements Texture, Runnable {
    private Image image = new ImageIcon("tank1.png").getImage();
    private Graphics2D graphics2D;
    private int delta_X = 0;
    private int delta_Y = -Constants.BULLET_SPEED;
    private boolean dead = false;
    private int random_X;
    private int random_Y;
    private int newDirection;
    private int direction;
    private Thread thread;
    private Bullet bullet = new Bullet();
    private boolean swap = false;
    AffineTransform atRandom = new AffineTransform();

    public void move() {
        graphics2D.drawImage(image, atRandom, null);
    }

    public void initTank(int _random_X, int _random_Y) {
        newDirection = 1;
        random_X = _random_X;
        random_Y = _random_Y;
        delta_X = 0;
        delta_Y = Constants.BULLET_SPEED;
        atRandom.translate(random_X + Constants.TANK_SIZE, random_Y + Constants.TANK_SIZE);
        atRandom.rotate(Math.toRadians(180));
        bullet.setCheck(false);
        dead = false;
        Myobject.arrayBullet.add(bullet);
    }

    public boolean setDead() {
        this.random_X = -50;
        this.random_Y = -50;
        this.delta_X = 0;
        this.delta_Y = 0;
        return this.dead=true;
    }

    public boolean isDead() {
        return dead;
    }

    public void moveRandom() {

        if (swap) {
            newDirection = (int) (Math.random() * 4);
            swap = false;
        }



        switch (newDirection) {
            case 0: {
                // A
                atRandom = moveLeft(random_X, random_Y);
                if (!bullet.isCheck()) {
                    bullet.initBullet(this.random_X+Constants.BULLET_SIZE, this.random_Y + Constants.TANK_SIZE / 2 - Constants.BULLET_SIZE / 2, delta_X, delta_Y, this.random_X, this.random_Y);
                }
                break;
            }
            case 1: {
                // S
                atRandom = moveDown(random_X, random_Y);
                if (!bullet.isCheck()) {
                    bullet.initBullet(this.random_X + Constants.TANK_SIZE / 2 - Constants.BULLET_SIZE / 2, this.random_Y + Constants.TANK_SIZE - 2*Constants.BULLET_SIZE, delta_X, delta_Y, this.random_X, this.random_Y);
                }
                break;
            }
            case 2: {
                //W
                atRandom = moveUp(random_X, random_Y);
                if (!bullet.isCheck()) {
                    bullet.initBullet(this.random_X + Constants.TANK_SIZE / 2 - Constants.BULLET_SIZE / 2, this.random_Y + Constants.BULLET_SIZE, delta_X, delta_Y, this.random_X, this.random_Y);
                }
                break;
            }
            case 3: {
                //D
                atRandom = moveRight(random_X, random_Y);
                if (!bullet.isCheck()) {
                    bullet.initBullet(this.random_X + Constants.TANK_SIZE - 2*Constants.BULLET_SIZE, this.random_Y + Constants.TANK_SIZE / 2 - Constants.BULLET_SIZE / 2, delta_X, delta_Y, this.random_X, this.random_Y);
                }
                    break;
            }
        }


        if (!bullet.isCheck()) {
           if (thread==null){
               thread = new Thread(this);
               thread.start();
           }
         //   bullet.initBullet();
        } else{
            thread=null;
            bullet.print(graphics2D);
            bullet.move();
        }

        graphics2D.drawImage(image, atRandom, null);
    }

    public AffineTransform moveDown(int _random_X, int _random_Y) {
        atRandom = new AffineTransform();
        random_X = _random_X;
        random_Y = _random_Y;
        delta_X = 0;
        delta_Y = Constants.BULLET_SPEED;
        boolean status = false;
        int tempY = 0;

        for (BrickTexture brickTexture : Myobject.arrayTexture) {
            swap = false;
            if (Myobject.checkClash(brickTexture.getY(), this.random_Y + Constants.TANK_SIZE, brickTexture.getX(), this.random_X, this.random_X + Constants.TANK_SIZE, brickTexture.getX() + Constants.TEXTURE_SIZE, Constants.TANK_STEP)) {
                tempY = brickTexture.getY();
                status = true;
            }
        }

        if (status) {
            atRandom.translate(this.random_X + Constants.TANK_SIZE, tempY);
            atRandom.rotate(Math.toRadians(180));
            swap = true;
        } else if (this.random_Y > Constants.HEIGHT - Constants.TANK_SIZE - Constants.TANK_STEP) {
            atRandom.translate(this.random_X + Constants.TANK_SIZE, Constants.HEIGHT);
            atRandom.rotate(Math.toRadians(180));
            swap = true;
        } else if(direction==newDirection) {
            this.random_Y += Constants.TANK_STEP;
            atRandom.translate(Constants.TANK_SIZE + this.random_X, Constants.TANK_SIZE + this.random_Y);
            atRandom.rotate(Math.toRadians(180));
        } else{
            atRandom.translate(Constants.TANK_SIZE + this.random_X, Constants.TANK_SIZE + this.random_Y);
            atRandom.rotate(Math.toRadians(180));
            direction=newDirection;
        }
        return atRandom;
    }

    public AffineTransform moveUp(int _random_X, int _random_Y) {
        random_X = _random_X;
        random_Y = _random_Y;
        delta_X = 0;
        delta_Y = -Constants.BULLET_SPEED;
        atRandom = new AffineTransform();
        boolean status = false;
        int tempY = 0;

        for (BrickTexture brickTexture : Myobject.arrayTexture) {
            swap = false;
            if (Myobject.checkClash(this.random_Y, brickTexture.getY() + Constants.TEXTURE_SIZE, brickTexture.getX(), this.random_X, this.random_X + Constants.TANK_SIZE, brickTexture.getX() + Constants.TEXTURE_SIZE, Constants.TANK_STEP)) {
                tempY = brickTexture.getY() + Constants.TEXTURE_SIZE;
                status = true;
            }
        }

        if (status) {
            atRandom.translate(this.random_X, tempY);
            swap = true;
        } else if (this.random_Y < 3) {
            atRandom.translate(this.random_X, 0);
            swap = true;
        } else if(direction==newDirection) {
            this.random_Y -= Constants.TANK_STEP;
            atRandom.translate(0 + this.random_X, 0 + this.random_Y);
            direction=newDirection;
        } else{
            atRandom.translate(0 + this.random_X, 0 + this.random_Y);
            direction=newDirection;
        }

        return atRandom;
    }

    public AffineTransform moveRight(int _random_X, int _random_Y) {
        atRandom = new AffineTransform();
        random_X = _random_X;
        random_Y = _random_Y;
        delta_X = Constants.BULLET_SPEED;
        delta_Y = 0;
        boolean status = false;

        int tempX = 0;
        for (BrickTexture brickTexture : Myobject.arrayTexture) {
            swap = false;
            if (Myobject.checkClash(brickTexture.getX(), this.random_X + Constants.TANK_SIZE, brickTexture.getY(), this.random_Y, this.random_Y + Constants.TANK_SIZE, brickTexture.getY() + Constants.TEXTURE_SIZE, Constants.TANK_STEP)) {
                tempX = brickTexture.getX();
                status = true;
            }
        }

        if (status) {
            atRandom.translate(tempX, this.random_Y);
            swap = true;
        } else if (this.random_X > Constants.WIDTH - Constants.TANK_SIZE - Constants.TANK_STEP) {
            atRandom.translate(Constants.WIDTH, this.random_Y);
            swap = true;
        } else if(direction==newDirection) {
            this.random_X += Constants.TANK_STEP;
            atRandom.translate(Constants.TANK_SIZE + this.random_X, 0 + this.random_Y);
        } else{
            atRandom.translate(Constants.TANK_SIZE + this.random_X, 0 + this.random_Y);
            direction=newDirection;
        }

        atRandom.rotate(Math.toRadians(90));
        return atRandom;
    }

    public AffineTransform moveLeft(int _random_X, int _random_Y) {
        atRandom = new AffineTransform();
        random_X = _random_X;
        random_Y = _random_Y;
        delta_X = -Constants.BULLET_SPEED;
        delta_Y = 0;

        boolean status = false;
        int tempX = 0;

        for (BrickTexture brickTexture : Myobject.arrayTexture) {
            swap = false;
            if (Myobject.checkClash(random_X, brickTexture.getX() + Constants.TEXTURE_SIZE, brickTexture.getY(), random_Y, random_Y + Constants.TANK_SIZE, brickTexture.getY() + Constants.TEXTURE_SIZE, Constants.TANK_STEP)) {
                tempX = brickTexture.getX();
                status = true;
            }
        }

        if (status) {
            atRandom.translate(tempX + Constants.TEXTURE_SIZE, random_Y + Constants.TANK_SIZE);
            swap = true;
        } else if (this.random_X < 3) {
            atRandom.translate(0, random_Y + Constants.TANK_SIZE);
            swap = true;
        } else if(direction==newDirection) {
            random_X -= Constants.TANK_STEP;
            atRandom.translate(0 + random_X, Constants.TANK_SIZE + random_Y);
        } else{
            atRandom.translate(0 + random_X, Constants.TANK_SIZE + random_Y);
            direction=newDirection;
        }

        atRandom.rotate(Math.toRadians(270));
        return atRandom;
    }

    public void swapDirection() {
        swap = true;
    }

    @Override
    public void print(Graphics2D _graphics2D) {
        graphics2D = _graphics2D;
    }


    @Override
    public int getX() {
        return random_X;
    }

    @Override
    public int getY() {
        return random_Y;
    }

    @Override
    public int getDelta_X() {
        return this.delta_X;
    }

    @Override
    public int getDelta_Y() {
        return this.delta_Y;
    }

    public boolean isSwap() {
        return swap;
    }

    public void setNewDirection(int _directionR) {
        newDirection = _directionR;
    }

    public void setDelta_X(int delta_X) {
        this.delta_X = delta_X;
    }

    public void setDelta_Y(int delta_Y) {
        this.delta_Y = delta_Y;
    }

    public void setAtRandom(AffineTransform atRandom) {
        this.atRandom = atRandom;
    }

    public Bullet getBullet() {
        return bullet;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            bullet.setCheck(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
