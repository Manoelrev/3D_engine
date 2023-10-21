## 3D engine
  Esse é um projeto de renderização e criação de graficos 3d totalmente independente de apis externas como OpenGL.
  O programa usa um sistema de projeção de Matrizes para fazer o cálculo de cada objeto 3d afim de representar ele num plano 2d.

## Funcionalidades
  O programa continua nos seus estados inicial, então ainda é só possível a renderização de objetos 3d, mas ele ainda é capaz de;
  * Renderizar arquivos.obj;
  * Calcular e aplicar iluminação;
  * Dimensionar e rotacionar o objeto 3D;

  Nas próximas versões será implementada métodos para ser possível interagir com o mundo (câmera, movimentação, colisões);

## Como importar outros modelos 3D?
  Inicialmente você era precisar do blender, uma ferramenta de modelagem para fazer a exportação do modelo da maneira que o programa consegue carregar. [Link para baixar o blender](https://www.blender.org/)

  <img width="100%" src="https://github.com/Manoelrev/3D_engine/assets/92553052/8561ff96-c33d-4515-89b7-a0ca19bf4266"><br>
  
  * Após importar seu objeto 3d ao blender você vai para File -> Export -> Wafefront (.obj)

  <img width="600px" src="https://github.com/Manoelrev/3D_engine/assets/92553052/2c9f40b7-900c-4758-890f-f31643695252"><br>

  * Nessa tela você vai escolher o nome do objeto e é <b>IMPORTANTE</b> que você deixe as caixas de diálogo iguais à imagem acima
    
  Apos esse processo você ira salvar esse arquivo na pasta "resources" localizada em "src/resources"
  E para finalizar no arquivo App.java você ira editar o código para esse daqui:
  ```java
  // Não se esqueça de colocar o .obj no final.
  cubeMesh = Triangle.getFileObj(App.class.getResource("resources/" + NOME_DO_ARQUIVO));
  ```
  E pronto seu modelo já vai está sendo renderizado na próxima vez que o programa for executado.


## Finalização
  Se você leu até o final de uma ⭐ e contribua com sua opinião sobre o projeto.<br><br>
  <img width="300px" src="https://github.com/Manoelrev/3D_engine/assets/92553052/903b8d8f-193e-45ed-b2d3-cfa311ef336d"><br>

  
