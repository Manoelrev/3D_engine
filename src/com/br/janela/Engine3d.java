package com.br.janela;
import javax.swing.JFrame;

public abstract class Engine3d extends JFrame{

    private int width;
    private int height;

    public Engine3d(int width, int height) throws InterruptedException{

        this.width = width;
        this.height = height;

        setTitle("3d engine");
        setBounds(0, 0, this.width, this.height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        while(true){
        repaint();
        Thread.sleep(10);
        }
    }
}
