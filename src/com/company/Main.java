package com.company;

import constants.Constants;

import javax.swing.*;

public class Main {
    protected static int status = 1;

    public static void main(String[] args) {

        // write your code here
        MyFrame frame = new MyFrame();
        Myobject myobject2 = new Myobject(frame);

        frame.setContentPane(myobject2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Move");
        frame.setSize(Constants.WIDTH + 18, Constants.HEIGHT + 40);

        frame.setVisible(true);

        while (status==1) {
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myobject2.repaint();
        }

        if(status==2){
            JOptionPane.showMessageDialog(frame, "ПОРАЖЕНИЕ");
        } else if (status==3){
            JOptionPane.showMessageDialog(frame, "ПОБЕДА");
        }
    }
}
