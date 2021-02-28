package cn.cs.edu.bit.jcarstore.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Welcome extends MyFrame {

	MyPanel mp=null;
    public Welcome(){
    	super("Welcome", 700, 450);
        mp=new MyPanel();
        this.add(mp);
        //this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}



