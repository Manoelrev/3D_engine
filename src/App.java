import java.awt.Graphics;
import java.awt.Graphics2D;

import com.br.graficos.Engine;
import com.br.graficos.Vec3D;
import com.br.janela.Engine3d;

public class App extends Engine3d {

    private static int width = 800;
    private static int height = 600;
    private static float fTheta = 0;
    private static Engine engine = new Engine(width, height);

    private static Vec3D[][] cubeMesh = {
    new Vec3D[]{new Vec3D(0, 0, 0),new Vec3D(0, 1, 0),new Vec3D(1, 1, 0)},
    new Vec3D[]{new Vec3D(0, 0, 0),new Vec3D(1, 1, 0),new Vec3D(1, 0, 0)},

    new Vec3D[]{new Vec3D(1, 0, 0),new Vec3D(1, 1, 0),new Vec3D(1, 1, 1)},
    new Vec3D[]{new Vec3D(1, 0, 0),new Vec3D(1, 1, 1),new Vec3D(1, 0, 1)},

    new Vec3D[]{new Vec3D(1, 0, 1),new Vec3D(1, 1, 1),new Vec3D(0, 1, 1)},
    new Vec3D[]{new Vec3D(1, 0, 1),new Vec3D(0, 1, 1),new Vec3D(0, 0, 1)},

    new Vec3D[]{new Vec3D(0, 0, 1),new Vec3D(0, 1, 1),new Vec3D(0, 1, 0)},
    new Vec3D[]{new Vec3D(0, 0, 1),new Vec3D(0, 1, 0),new Vec3D(0, 0, 0)},

    new Vec3D[]{new Vec3D(0, 1, 0),new Vec3D(0, 1, 1),new Vec3D(1, 1, 1)},
    new Vec3D[]{new Vec3D(0, 1, 0),new Vec3D(1, 1, 1),new Vec3D(1, 1, 0)},

    new Vec3D[]{new Vec3D(1, 0, 1),new Vec3D(0, 0, 1),new Vec3D(0, 0, 0)},
    new Vec3D[]{new Vec3D(1, 0, 1),new Vec3D(0, 0, 0),new Vec3D(1, 0, 0)}
    };

    public App(int width, int height) throws InterruptedException{
        super(width, height);
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;

        fTheta += 0.014;
        engine.draw3DObject(g2D, cubeMesh, fTheta);
        
    }

    public static void main(String[] args) throws Exception {
        new App(width, height);
    }
}
