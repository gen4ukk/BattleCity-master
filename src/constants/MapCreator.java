package constants;

import com.company.BrickTexture;
import com.company.Myobject;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gen4ukk on 29.01.2016.
 */
public class MapCreator {

    private static Image texture = new ImageIcon("texture.png").getImage();
    private static Image headQuarters = new ImageIcon("headquarters.png").getImage();

    public static void nullTexture(){
        Myobject.arrayTexture.add(new BrickTexture(texture, -50,-50 ));
    }

    public static void texture(int x, int y){
        Myobject.arrayTexture.add(new BrickTexture(texture, x,y ));
   }

    public static void square(int _x, int _y){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Myobject.arrayTexture.add(new BrickTexture(texture,_x+j*Constants.TEXTURE_SIZE,_y+i*Constants.TEXTURE_SIZE));
            }
        }
    }
    public static void column(int _x, int _y){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                Myobject.arrayTexture.add(new BrickTexture(texture,_x+j*Constants.TEXTURE_SIZE,_y+i*Constants.TEXTURE_SIZE));
            }
        }
    }

    public static void pole(int _x, int _y){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                Myobject.arrayTexture.add(new BrickTexture(texture,_x+j*Constants.TEXTURE_SIZE,_y+i*Constants.TEXTURE_SIZE));
            }
        }
    }

    public static BrickTexture headQuarters(int _x, int _y){
        BrickTexture hq = new BrickTexture(headQuarters,_x+20,_y+20);
        hq.setTextureSize(Constants.HEADQUARTERS);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                Myobject.arrayTexture.add(new BrickTexture(texture,_x+j*Constants.TEXTURE_SIZE,_y+i*Constants.TEXTURE_SIZE));
            }
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                Myobject.arrayTexture.add(new BrickTexture(texture,_x+j*Constants.TEXTURE_SIZE,_y+i*Constants.TEXTURE_SIZE));
                Myobject.arrayTexture.add(new BrickTexture(texture,_x+70+j*Constants.TEXTURE_SIZE,_y+i*Constants.TEXTURE_SIZE));
            }
        }
        Myobject.arrayTexture.add(hq);
        return hq;
    }

}
