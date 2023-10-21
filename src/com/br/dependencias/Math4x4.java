package com.br.dependencias;

public record Math4x4(float[][] m) {
    
    public static Math4x4 getMatRotY(float fTheta){
        Math4x4 matrix = new Math4x4(new float[][]{
            new float[]{1,0,0,0},
            new float[]{0,1,0,0},
            new float[]{0,0,1,0},
            new float[]{0,0,0,1},
        });

        matrix.m[0][0] = (float) Math.cos(fTheta);
		matrix.m[0][2] = (float) Math.sin(fTheta);
		matrix.m[2][0] = -(float) Math.sin(fTheta);
		matrix.m[1][1] = 1.0f;
		matrix.m[2][2] = (float) Math.cos(fTheta);
		matrix.m[3][3] = 1.0f;
		
        return matrix;
    }

    public static Math4x4 getMatRotX(float fTheta){
        return new Math4x4(new float[][]{
            {1, 0, 0, 0},
            {0, (float) Math.cos(fTheta * 0.5), (float) Math.sin(fTheta * 0.5), 0},
            {0, (float) -Math.sin(fTheta * 0.5), (float) Math.cos(fTheta * 0.5), 0},
            {0, 0, 0, 1}
            });
    }

    public static Math4x4 getMatRotZ(float fTheta){
        return new Math4x4(new float[][]{
            {(float) Math.cos(fTheta), (float) Math.sin(fTheta), 0, 0},
            {(float) -Math.sin(fTheta), (float) Math.cos(fTheta), 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        });

    }

    public static Math4x4 getMatProjection(float width, float height,float fFovDegrees, float fFar, float fNear){
        float fFovRad = (float) (1.0f / Math.tan(fFovDegrees * 0.5f / 180.0f * Math.PI));
        float fAspectRatio = width / height;
        
        return new Math4x4(new float[][] {
            {(fAspectRatio * fFovRad), 0 , 0 , 0     },
            { 0 , fFovRad, 0, 0                          },
            { 0, 0, (fFar / (fFar - fNear)), 1           },
            { 0, 0,(-fFar * fNear) / (fFar - fNear), 0}
        });
    }

    public static Math4x4 getMathIdentity(){
        return new Math4x4(new float[][]{
            new float[]{1,0,0,0},
            new float[]{0,1,0,0},
            new float[]{0,0,1,0},
            new float[]{0,0,0,1},
        });
    }

    public static Math4x4 getMathTranslation(float x, float y, float z){
        return new Math4x4(new float[][]{
            new float[]{1,0,0,0},
            new float[]{0,1,0,0},
            new float[]{0,0,1,0},
            new float[]{x,y,z,1},
        });
    }
    
    public void matrixMultiply(Math4x4 m){
        for (int x =0; x < 4; x++){
            for (int y = 0; y <4;y++){
                this.m[y][x] = this.m[y][0] * m.m[0][x] + this.m[y][1] * m.m[1][x] + this.m[y][2] * m.m[2][x] + this.m[y][3] * m.m[3][x];
            }
        }
    }
}
