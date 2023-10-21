package com.br.dependencias;

import java.io.File;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;

public class Triangle {
    
    public Vec3D v1;
    public Vec3D v2;
    public Vec3D v3;
    public float lighValue;

    public Triangle(Vec3D v1, Vec3D v2, Vec3D v3, int lighValue){
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.lighValue = lighValue;
    }

    
    public Triangle(){
        this.v1 = new Vec3D();
        this.v2 = new Vec3D();
        this.v3 = new Vec3D();
        this.lighValue = 0;
    }

    public void addXPoint(Float value){
        v1.addXPoint(value);
        v2.addXPoint(value);
        v3.addXPoint(value);
    }

    public void addYPoint(Float value){
        v1.addYPoint(value);
        v2.addYPoint(value);
        v3.addYPoint(value);
    }

    public void addZPoint(Float value){
        v1.addZPoint(value);
        v2.addZPoint(value);
        v3.addZPoint(value);
    }

    public void multiplayXPoint(Float value){
        v1.multiplayXPoint(value);
        v2.multiplayXPoint(value);
        v3.multiplayXPoint(value);
    }

    public void multiplayYPoint(Float value){
        v1.multiplayYPoint(value);
        v2.multiplayYPoint(value);
        v3.multiplayYPoint(value);
    }

    public void multiplayZPoint(Float value){
        v1.multiplayZPoint(value);
        v2.multiplayZPoint(value);
        v3.multiplayZPoint(value);
    }

    public void multiplayMatrixVector(Triangle o, Math4x4 m){
        v1.multiplayMatrixVector(o.v1, m);
        v2.multiplayMatrixVector(o.v2, m);
        v3.multiplayMatrixVector(o.v3, m);
    }

    public static Vector<Triangle> getFileObj(URL url) throws Exception {
        Scanner textScan = new Scanner(new File(url.toURI()));
        int index = 0;
        Vector<Vec3D> test = new Vector<Vec3D>();
        Vector<String> text = new Vector<String>();
        Vector<Triangle> resultado = new Vector<Triangle>();

        while(textScan.hasNextLine()){

        text.add(textScan.nextLine());
            switch(text.get(index).charAt(0)) {
                case 'v':
                    String[] tempText = text.get(index).replace("v ", "").split(" ");
                    test.add(new Vec3D(Float.valueOf(tempText[0]), Float.valueOf(tempText[1]), Float.valueOf(tempText[2])));
                    index++;
                    break;

                case 'f':
                    String[] samplet = text.get(index).replace("f ", "").split(" ");
                    resultado.add(new Triangle(test.get(Integer.valueOf(samplet[0])-1), test.get(Integer.valueOf(samplet[1])-1), test.get(Integer.valueOf(samplet[2])-1),0));
                    index++;
                    break;
                default:
                    index++;
                    break;
            }
        }
        return resultado;
    }

    public int[] returnXPoints(){
        return new int[]{(int) this.v1.getX(),(int)this.v2.getX(),(int)this.v3.getX()};
    }

    public int[] returnYPoints(){
        return new int[]{(int) this.v1.getY(),(int)this.v2.getY(),(int)this.v3.getY()};
    }
}
