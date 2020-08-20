package com.company;
import javax.swing.JFrame;
public class Frame {
    public Frame(){
        JFrame frame = new JFrame("FlipCard");
        frame.setSize(600, 475);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Panel());
        frame.setVisible(true);
    }

}
