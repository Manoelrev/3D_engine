package com.br.Engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

import com.br.dependencias.Math4x4;
import com.br.dependencias.Triangle;
import com.br.dependencias.Vec3D;

public class Engine3d {
    
    private int width, height;
    private float fNear = 0.1f, fFar = 1000, fFov = 120;
    private Math4x4 matProj;
    
    public Engine3d(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void setDimension(int height, int width) {
        this.width = width;
        this.height = height;
    }

    public void draw3DObject(Graphics2D gd2, Vector<Triangle> mesh, float fTheta, Vec3D vCamera){
        
        Math4x4 matWorld = Math4x4.getMatRotZ(fTheta * 0.5f );
        matWorld.matrixMultiply(Math4x4.getMatRotX(fTheta));
        matWorld.matrixMultiply(Math4x4.getMathTranslation(0, 0, 20));

        matProj = Math4x4.getMatProjection(width, height, fFov, fFar, fNear);

        Vector<Triangle> vecTriangletoRaster = new Vector<Triangle>();

        for (Triangle v : mesh) {
            Triangle triProjected = new Triangle();

            // Rotaciona o objeto 3D
            triProjected.multiplayMatrixVector(v, matWorld);
            
            // Calculo feito para não renderizar vertices que não estão sendo observados.
            Vec3D normal = new Vec3D(), line1 = Vec3D.GetSufarce(triProjected.v2, triProjected.v1), line2 = Vec3D.GetSufarce(triProjected.v3, triProjected.v1);
            normal.crossProduct(line1, line2);
            normal.normalize();

            if( normal.getX() * (triProjected.v1.getX() - vCamera.getX()) +
                normal.getY() * (triProjected.v1.getY() - vCamera.getY()) +
                normal.getZ() * (triProjected.v1.getZ() - vCamera.getZ()) < 0){
                
                // Gera e calcula as luzes no objeto
                Vec3D ligh = new Vec3D(0, 0, -1);
                ligh.normalize();
                triProjected.lighValue = Math.max(0.1f, ligh.dotProduct(normal));

                // Projeta o objeto 3D em 2D e aumenta a escala do objeto para o tamanho da tela
                triProjected.multiplayMatrixVector(triProjected, matProj);
                triProjected.addXPoint(1f);
                triProjected.addYPoint(1f);
                triProjected.multiplayXPoint(0.5f * width);
                triProjected.multiplayYPoint(0.5f * height);

                // Guarda os valores dos triangulos em um vetor
                vecTriangletoRaster.add(triProjected);
            }

            // Organiza a lista dos triangulos para que os objetos sejam desenhado na ordem correta
            vecTriangletoRaster.sort((Triangle o1, Triangle o2) -> (o1.v1.getZ() + o1.v2.getZ() + o1.v3.getZ()) / 3 > (o2.v1.getZ() + o2.v2.getZ() + o2.v3.getZ()) / 3 ? -1 : 1);
                
            // Função para desenhar o Objeto
            for (Triangle vec3d : vecTriangletoRaster) {
                gd2.setColor(CreateColorLight(230,230,230,vec3d.lighValue)); 
                gd2.fillPolygon(vec3d.returnXPoints(),vec3d.returnYPoints(), 3);
            }
        }
    }

    private Color CreateColorLight(float r, float g, float b, float light){
        return new Color((int) (r * light), (int) (g * light), (int) (b * light));
    }
}

