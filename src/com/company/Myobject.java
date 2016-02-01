package com.company;

import constants.Constants;
import constants.MapCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by gen4ukk on 07.01.2016.
 */
public class Myobject extends JPanel implements KeyListener {

    private int newDirection;
    private int direction;
    private int countOfTanks = 0;
    private int countOfLifes = Constants.PLAYERLIFES;

    protected static Graphics2D graphics2D;
    private Tank tank;
    private Tank tank2;
    private Tank tank3;
    private Tank tank4;
    private Crash crash;
    private MyFrame frame;
    private Bullet myBullet;
    private BrickTexture hq;

    AffineTransform at;
    public static ArrayList<BrickTexture> arrayTexture = new ArrayList<BrickTexture>();
    public static ArrayList<Bullet> arrayBullet = new ArrayList<Bullet>();
    public static ArrayList<Tank> arrayTank = new ArrayList<Tank>();

    Myobject(MyFrame _frame) {
        frame = _frame;
        frame.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) throws ConcurrentModificationException {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;
        graphics2D.drawRect(0, 0, Constants.WIDTH, Constants.HEIGHT);


        if (arrayTexture.isEmpty()) {

            MapCreator.nullTexture();
            MapCreator.column(50, 50);
            MapCreator.column(150, 50);
            MapCreator.column(250, 50);
            MapCreator.column(250, 150);
            MapCreator.column(350, 50);
            MapCreator.column(450, 50);
            MapCreator.pole(0, 200);
            MapCreator.pole(100, 200);
            MapCreator.pole(100, 250);
            MapCreator.pole(0, 250);
            MapCreator.pole(200, 250);
            MapCreator.pole(Constants.WIDTH - 100, 250);
            MapCreator.pole(Constants.WIDTH - 200, 250);
            MapCreator.pole(Constants.WIDTH - 300, 250);
            MapCreator.pole(Constants.WIDTH - 100, 200);
            MapCreator.pole(Constants.WIDTH - 200, 200);

            hq = MapCreator.headQuarters(Constants.WIDTH / 2 - 45, Constants.HEIGHT - 70);
        }

        if (crash == null) {
            crash = new Crash();
        } else {
            crash.setGraphics2D(graphics2D);
            crash.printTexture();
        }


        if (tank == null || tank.isDead()) {
            at = new AffineTransform();
            tank = new Tank();
            tank.initTank(Constants.WIDTH / 2 - Constants.TANK_SIZE - 45, Constants.HEIGHT - Constants.TANK_SIZE);
            at.translate(Constants.WIDTH / 2 - Constants.TANK_SIZE - 45, Constants.HEIGHT - Constants.TANK_SIZE);
//                tank.initTank(200,200);
//                at.translate(200,200);
            tank.setAtRandom(at);
//            myBullet = tank.getBullet();
            tank.setDelta_Y(-Constants.BULLET_SPEED);
            tank.print(graphics2D);
            tank.move();
            countOfLifes--;
        } else if (tank != null) {
            tank.print(graphics2D);
            tank.move();
        }


        if (myBullet==null) {
            myBullet = new Bullet();
        } else if (myBullet.isCheck()){
            myBullet.print(graphics2D);
            myBullet.move();
            myBullet.checkEndOfPoint();
        }

        if (tank2 != null) {
            tank2.print(graphics2D);
            tank2.moveRandom();
        } else if (countOfTanks != Constants.NUMBEROFTANKS) {
            countOfTanks++;
            tank2 = new Tank();
            tank2.initTank(Constants.HEIGHT - Constants.TANK_SIZE, 0);
            tank2.print(graphics2D);
            tank2.moveRandom();
            arrayTank.add(tank2);
        }

        if (tank3 != null) {
            tank3.print(graphics2D);
            tank3.moveRandom();
        } else if (countOfTanks != Constants.NUMBEROFTANKS) {
            countOfTanks++;
            tank3 = new Tank();
            tank3.initTank(Constants.HEIGHT / 2 - Constants.TANK_SIZE / 2, 0);
            tank3.print(graphics2D);
            tank3.moveRandom();
            arrayTank.add(tank3);
        }

        if (tank4 != null) {
            tank4.print(graphics2D);
            tank4.moveRandom();
        } else if (countOfTanks != Constants.NUMBEROFTANKS) {
            countOfTanks++;
            tank4 = new Tank();
            tank4.initTank(0, 0);
            tank4.print(graphics2D);
            tank4.moveRandom();
            arrayTank.add(tank4);
        }

        try {
            for (Bullet bullet : arrayBullet) {

                boolean checkCrash = crash.crashBulletTank(bullet, tank);

                if (checkCrash) {
                    bullet.setDead();
                    tank.setDead();
                }

                bullet.checkEndOfPoint();

                int i = 0;
                checkCrash = false;
                while (i < arrayTexture.size()) {
                    checkCrash = crash.crashBulletTexture(bullet, arrayTexture.get(i));
                    if (!checkCrash) {
                        i++;
                    }
                    if (checkCrash) {
                        bullet.setDead();
                    }
                }
            }



            for (Bullet bullet : arrayBullet) {
                boolean checkCrash = false;
                if (bullet.getDelta_X() < 0) {
                    int i = 0;
                    while (i < arrayTexture.size()) {
                        BrickTexture brickTexture2 = arrayTexture.get(i);
                        if (checkClash(bullet.getX(), brickTexture2.getX() + Constants.TEXTURE_SIZE, brickTexture2.getY(), bullet.getyTank(), bullet.getyTank() + Constants.TANK_SIZE, brickTexture2.getY() + Constants.TEXTURE_SIZE, Constants.BULLET_SPEED)) {
                            arrayTexture.remove(brickTexture2);
                            checkCrash = true;
                        } else {
                            i++;
                        }
                    }
                } else if (bullet.getDelta_Y() < 0) {
                    int i = 0;
                    while (i < arrayTexture.size()) {
                        BrickTexture brickTexture2 = arrayTexture.get(i);
                        if (checkClash(bullet.getY(), brickTexture2.getY() + Constants.TEXTURE_SIZE, brickTexture2.getX(), bullet.getxTank(), bullet.getxTank() + Constants.TANK_SIZE, brickTexture2.getX() + Constants.TEXTURE_SIZE, Constants.BULLET_SPEED)) {
                            arrayTexture.remove(brickTexture2);
                            checkCrash = true;
                        } else {
                            i++;
                        }
                    }
                } else if (bullet.getDelta_X() > 0) {
                    int i = 0;
                    while (i < arrayTexture.size()) {
                        BrickTexture brickTexture2 = arrayTexture.get(i);
                        if (checkClash(brickTexture2.getX(), bullet.getX() + Constants.BULLET_SIZE, brickTexture2.getY(), bullet.getyTank(), bullet.getyTank() + Constants.TANK_SIZE, brickTexture2.getY() + Constants.TEXTURE_SIZE, Constants.BULLET_SPEED)) {
                            arrayTexture.remove(brickTexture2);
                            checkCrash = true;
                        } else {
                            i++;
                        }
                    }
                } else if (bullet.getDelta_Y() > 0) {
                    int i = 0;
                    while (i < arrayTexture.size()) {
                        BrickTexture brickTexture2 = arrayTexture.get(i);
                        if (checkClash(bullet.getY() + Constants.BULLET_SIZE, brickTexture2.getY(), brickTexture2.getX(), bullet.getxTank(), bullet.getxTank() + Constants.TANK_SIZE, brickTexture2.getX() + Constants.TEXTURE_SIZE, Constants.BULLET_SPEED)) {
                            arrayTexture.remove(brickTexture2);
                            checkCrash = true;
                        } else {
                            i++;
                        }
                    }
                }

                if (checkCrash) {
                    bullet.setDead();
                }
            }


            if (myBullet.isCheck()) {
                boolean checkCrash = false;
                int i = 0;
                while (i < arrayTexture.size()) {
                    if (crash.crashBulletTexture(myBullet, arrayTexture.get(i))) {
                        checkCrash = true;
                        i--;
                    }
                    i++;
                }

                if (checkCrash) {
                    myBullet.setDead();
                }
            }





            if (myBullet != null) {
                boolean checkCrash = false;

                int i = 0;
                while (i < arrayTank.size()) {
                    Tank tanks = arrayTank.get(i);
                    if (myBullet.getDelta_X() < 0) {
                        if (checkClash(myBullet.getX()
                                , tanks.getX() + Constants.TANK_SIZE
                                , myBullet.getY()
                                , tanks.getY()
                                , tanks.getY() + Constants.TANK_SIZE
                                , myBullet.getY() + Constants.BULLET_SIZE
                                , Constants.BULLET_SPEED)) {
                            checkCrash = true;
                        }
                    } else if (myBullet.getDelta_X() > 0) {
                        if (checkClash(tanks.getX()
                                , myBullet.getX() + Constants.BULLET_SIZE
                                , myBullet.getY()
                                , tanks.getY()
                                , tanks.getY() + Constants.TANK_SIZE
                                , myBullet.getY() + Constants.BULLET_SIZE
                                , Constants.BULLET_SPEED)) {
                            checkCrash = true;
                        }
                    } else if (myBullet.getDelta_Y() > 0) {
                        if (checkClash(tanks.getY()
                                , myBullet.getY() + Constants.BULLET_SIZE
                                , myBullet.getX()
                                , tanks.getX()
                                , tanks.getX() + Constants.TANK_SIZE
                                , myBullet.getX() + Constants.BULLET_SIZE
                                , Constants.BULLET_SPEED)) {
                            checkCrash = true;
                        }
                    } else if (myBullet.getDelta_Y() < 0) {
                        if (checkClash(myBullet.getY()
                                , tanks.getY() + Constants.TANK_SIZE
                                , myBullet.getX()
                                , tanks.getX()
                                , tanks.getX() + Constants.TANK_SIZE
                                , myBullet.getX() + Constants.BULLET_SIZE
                                , Constants.BULLET_SPEED)) {
                            checkCrash = true;
                        }
                    }

                    if (checkCrash) {
                        arrayTank.remove(tanks);
                        myBullet = null;

                        if (tanks == tank2) {
                            tank2 = null;
                        } else if (tanks == tank3) {
                            tank3 = null;
                        } else if (tanks == tank4) {
                            tank4 = null;
                        }
                        break;
                    }
                    i++;
                }
            }

        } catch (ConcurrentModificationException e) {
            //              System.out.println("deadBullet");
        }


        if (!arrayTexture.contains(hq) || countOfLifes==-1){
            Main.status=2;
        }

        if (arrayTank.isEmpty()){
            Main.status=3;
        }


//        System.out.println("*****************");
//        System.out.println("x=" + at.getTranslateX() + "y=" + at.getTranslateY());
//        System.out.println("*****************");
    }

    static boolean checkClash(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int step) {
        if (arg1 - arg2 <= step && arg1 - arg2 >= 0 && ((arg3 > arg4 && arg3 < arg5) || (arg6 > arg4 && arg6 < arg5))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        newDirection = e.getKeyCode();

        if (tank != null) {
            tank.setNewDirection(newDirection);
            switch (newDirection) {
                case Constants.KEY_D: {
                    at = tank.moveRight(tank.getX(), tank.getY());
                    break;
                }
                case Constants.KEY_S: {
                    at = tank.moveDown(tank.getX(), tank.getY());
                    break;
                }
                case Constants.KEY_A: {
                    at = tank.moveLeft(tank.getX(), tank.getY());
                    break;
                }
                case Constants.KEY_W: {
                    at = tank.moveUp(tank.getX(), tank.getY());
                    break;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        newDirection = e.getKeyCode();
        switch (newDirection) {
            case Constants.KEY_SPACE: {
                if (!myBullet.isCheck()) {
                    //myBullet = new Bullet();
                    myBullet.setCheck(true);
                    myBullet.initBullet(tank.getX() + Constants.TANK_SIZE / 2 - Constants.BULLET_SIZE / 2, tank.getY() + Constants.TANK_SIZE / 2 - Constants.BULLET_SIZE / 2, tank.getDelta_X(), tank.getDelta_Y(), tank.getX(), tank.getY());
                    //       arrayBullet.add(myBullet);
                }
                break;
            }
        }
    }
}