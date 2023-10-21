package com.br.dependencias;

public class Vec3D{
    private float x;
    private float y;
    private float z;

    public Vec3D(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vec3D(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void addXPoint(float value){
        this.x += value;
    }

    public void addYPoint(float value){
        this.y += value;
    }

    public void addZPoint(float value){
        this.z += value;
    }

    public void multiplayXPoint(float value){
        this.x *= value;
    }

    public void multiplayYPoint(float value){
        this.y *= value;
    }

    public void multiplayZPoint(float value){
        this.z *= value;
    }

    public float dotProduct(Vec3D o){
        return o.x * this.x + o.y * this.y + o.z * this.z;
    }

    public void multiplayMatrixVector(Vec3D o, Math4x4 m){
        float x = o.x * m.m()[0][0] + o.y * m.m()[1][0] + o.z * m.m()[2][0] + m.m()[3][0];
        float y = o.x * m.m()[0][1] + o.y * m.m()[1][1] + o.z * m.m()[2][1] + m.m()[3][1];
        float z = o.x * m.m()[0][2] + o.y * m.m()[1][2] + o.z * m.m()[2][2] + m.m()[3][2];
        float w = o.x * m.m()[0][3] + o.y * m.m()[1][3] + o.z * m.m()[2][3] + m.m()[3][3];

        if (w != 0){
            x /= w; y /= w; z /= w;
        }

        this.x = x; this.y = y; this.z = z;
    }

    public void normalize(){
        float l = (float) Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
			this.x /= l; this.y /= l; this.z /= l;
    }

    public void crossProduct(Vec3D o, Vec3D l){     
        this.x = o.y * l.z - o.z * l.y;
        this.y = o.z * l.x - o.x * l.z;
        this.z = o.x * l.y - o.y * l.x;
    }

    public static Vec3D GetSufarce(Vec3D o, Vec3D m){
        return new Vec3D(o.x - m.x, o.y - m.y , o.z - m.z);
    }
}
