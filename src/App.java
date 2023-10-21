import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;


import com.br.dependencias.Triangle;
import com.br.dependencias.Vec3D;
import com.br.Engine.Engine3d;

import com.br.Engine.Screen;

public class App extends Screen {

    private static int width = 800;
    private static int height = 600;
    
    private static float fTheta = 0;
    private static Engine3d engine = new Engine3d(width, height);
    
    private static Vec3D camera = new Vec3D();
    private static Vector<Triangle> cubeMesh;
  
    public App(int width, int height) throws InterruptedException{
        super(width, height);  

    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2D = this.getBufferGraphics();
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillRect(0, 0, getWidth(), getHeight());

        fTheta += 0.03;       

        engine.setDimension(getHeight(), getWidth());
        engine.draw3DObject(g2D, cubeMesh, fTheta, camera);
        
        ((Graphics2D) g ).drawImage(getBufferedImage(), null, 0, 0); 
    }


    public static void main(String[] args) throws Exception {
        cubeMesh = Triangle.getFileObj(App.class.getResource("resources/axis.obj"));
        new App(width, height);
    }

}
