package com.br.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Engine {

    private int width;
    private int height;
    private float fNear = 0.1f;
    private float fFar = 1000;
    private float fFov = 90;
    private float fAspectionRation = 800 / 600;
    private float fFovRad = (float) (1 / Math.tan(fFov * 0.5 / 180 * Math.PI));

    private Math4x4 matProj = new Math4x4(new float[][] {
        {(fAspectionRation * fFovRad), 0 , 0 , 0     },
        { 0 , fFovRad, 0, 0                          },
        { 0, 0, (fFar / (fFar - fNear)), 1           },
        { 0, 0,(-fFar * fNear) / (fFar - fNear), 0}
    });
    
    public Engine(int width, int height){
        this.width = width;
        this.height = height;
        fAspectionRation = width / height;
    }

    public void draw3DObject(Graphics2D gd2, Vec3D[][] mesh, float fTheta){
        gd2.clearRect(0, 0, width, height);
        Vec3D[] triProjected = new Vec3D[]{new Vec3D(0,0,0),new Vec3D(0,0,0),new Vec3D(0,0,0)};
        Vec3D[] triRotatedZ = new Vec3D[]{new Vec3D(0,0,0),new Vec3D(0,0,0),new Vec3D(0,0,0)};
        Vec3D[] triRotatedZX = new Vec3D[]{new Vec3D(0,0,0),new Vec3D(0,0,0),new Vec3D(0,0,0)};
        Vec3D[] triTranslated = new Vec3D[]{new Vec3D(0,0,0),new Vec3D(0,0,0),new Vec3D(0,0,0)};
        
        Math4x4 matRotZ = new Math4x4(new float[][]{
            {(float) Math.cos(fTheta), (float) Math.sin(fTheta), 0, 0},
            {(float) -Math.sin(fTheta), (float) Math.cos(fTheta), 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        });

        Math4x4 matRotX = new Math4x4(new float[][]{
        {1, 0, 0, 0},
        {0, (float) Math.cos(fTheta * 0.5), (float) Math.sin(fTheta * 0.5), 0},
        {0, (float) -Math.sin(fTheta * 0.5), (float) Math.cos(fTheta * 0.5), 0},
        {0, 0, 0, 1}
        });

        for (Vec3D[] v : mesh) {


            triRotatedZ[0] = multiplayMatrixVector(v[0], matRotZ);
            triRotatedZ[1] = multiplayMatrixVector(v[1], matRotZ);
            triRotatedZ[2] = multiplayMatrixVector(v[2], matRotZ);

            triRotatedZX[0] = multiplayMatrixVector(triRotatedZ[0], matRotX);
            triRotatedZX[1] = multiplayMatrixVector(triRotatedZ[1], matRotX);
            triRotatedZX[2] = multiplayMatrixVector(triRotatedZ[2], matRotX);

            // Offset into the screen
            triTranslated[0] = triRotatedZX[0];
            triTranslated[1] = triRotatedZX[1];
            triTranslated[2] = triRotatedZX[2];
            
			triTranslated[0].z = triRotatedZX[0].z + 3.0f;
			triTranslated[1].z = triRotatedZX[1].z + 3.0f;
			triTranslated[2].z = triRotatedZX[2].z + 3.0f;

            // Project triangles from 3D --> 2D

            triProjected[0] = multiplayMatrixVector(triTranslated[0], matProj);
            triProjected[1] = multiplayMatrixVector(triTranslated[1], matProj);
            triProjected[2] = multiplayMatrixVector(triTranslated[2], matProj);

            triProjected[0].x += 1.0; 
            triProjected[0].y += 1.0;
			triProjected[1].x += 1.0; 
            triProjected[1].y += 1.0;
			triProjected[2].x += 1.0; 
            triProjected[2].y += 1.0;
			triProjected[0].x *= 0.5 * width;
			triProjected[0].y *= 0.5 * height;
			triProjected[1].x *= 0.5 * width;
			triProjected[1].y *= 0.5 * height;
			triProjected[2].x *= 0.5 * width;
			triProjected[2].y *= 0.5 * height;

            Path2D myPath = new Path2D.Double();


            myPath.moveTo(triProjected[0].x, triProjected[0].y);
            myPath.lineTo(triProjected[1].x, triProjected[1].y);
            myPath.lineTo(triProjected[2].x, triProjected[2].y);
            myPath.closePath();
            gd2.fill(myPath);
        }
    }

    private Vec3D multiplayMatrixVector(Vec3D o, Math4x4 m){
        float x = o.x * m.m()[0][0] + o.y * m.m()[1][0] + o.z * m.m()[2][0] + m.m()[3][0];
        float y = o.x * m.m()[0][1] + o.y * m.m()[1][1] + o.z * m.m()[2][1] + m.m()[3][1];
        float z = o.x * m.m()[0][2] + o.y * m.m()[1][2] + o.z * m.m()[2][2] + m.m()[3][2];
        float w = o.x * m.m()[0][3] + o.y * m.m()[1][3] + o.z * m.m()[2][3] + m.m()[3][3];

        if (w != 0){
            x /= w; y /= w; z /= w;
        }

        return new Vec3D(x, y, z);
    }
}
