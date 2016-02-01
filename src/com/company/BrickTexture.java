package com.company;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by gen4ukk on 20.01.2016.
 */
public class BrickTexture{ //implements Texture {
    private int x;
    private int y;
    private Graphics2D graphics2D;
    private Image image;
    private int textureSize;

    public BrickTexture(Image _image, int _x, int _y) {
        image = _image;
        x = _x;
        y = _y;
        textureSize = Constants.TEXTURE_SIZE;
    }

    public void setTextureSize(int textureSize) {
        this.textureSize = textureSize;
    }

    public void print(Graphics2D _graphics2D) {
        graphics2D = _graphics2D;
        graphics2D.drawImage(image, x, y, textureSize, textureSize, null);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

   // @Override
    public int getDelta_X() {
        return 0;
    }

  //  @Override
    public int getDelta_Y() {
        return 0;
    }
}
