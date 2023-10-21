package com.br.Engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public abstract class Screen extends JFrame{

    private int width;
    private int height;
    private BufferedImage bufferedImage;

    public Screen(int width, int height) throws InterruptedException{

        this.width = width;
        this.height = height; 
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        setTitle("3D ENGINE");
        setBounds(0, 0, this.width, this.height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        while(true){
        ((Graphics2D) bufferedImage.getGraphics()).scale(800, 600);
        repaint();
        Thread.sleep(10);
        }
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public Graphics2D getBufferGraphics() {
        return (Graphics2D) bufferedImage.getGraphics();
    }
}
