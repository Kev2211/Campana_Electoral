/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Estructuras;

/**
 *
 * @author Yfmay
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JFrame;


public class CaminoHamiltoniano {
  private int[][] grafo;
  private int numVertices;
  private LinkedList<Integer> camino;
  private LinkedList<Integer>[] adyacencia;
  private boolean[] visitado;
  private int pesoMin = Integer.MAX_VALUE;
  private int[] solucion;
  private int[][] matrixAux;
  private boolean[][] lluvia;
  private boolean[][] derrumbe;

  public CaminoHamiltoniano(int[][] grafo) {
    this.grafo = grafo;
    this.numVertices = grafo.length;
    this.camino = new LinkedList<>();
    this.matrixAux = new int[numVertices][numVertices];
    this.lluvia = new boolean[numVertices][numVertices];
    this.derrumbe = new boolean[numVertices][numVertices];
  }
  
    private void llenarAdyacencia(){
      adyacencia = new LinkedList[numVertices];
      for (int i = 0; i < numVertices; i++) {
        adyacencia[i] = new LinkedList<>();
      for(int j = 0; j < numVertices; j++){
          if(grafo[i][j] != 0){
              adyacencia[i].add(j);
              matrixAux[i][j] = grafo[i][j];
          }
      }
    }
  }
  
  public boolean encontrarCaminoHamiltoniano(int verticeInicial, String vector[]){
      
      llenarAdyacencia();
      visitado = new boolean[numVertices];
      visitado[verticeInicial] = true;
      camino.add(verticeInicial);
      buscarCamino(verticeInicial);
      
      if(solucion == null){
          return false;
      }else{
          return true;
      }
      //imprimirCamino(vector);
  }
  

  
  private void buscarCamino(int vertice){
      if(camino.size() == numVertices){
          int pesoCamino = 0;
          for(int i=0; i<numVertices - 1; i++){
              pesoCamino += matrixAux[camino.get(i)][camino.get(i+1)];
              
          }
          
          if(pesoCamino < pesoMin){
              pesoMin = pesoCamino;
              solucion = new int[numVertices];
              for(int i = 0; i<numVertices; i++){
                  solucion[i] = camino.get(i);
                  //lluviafinal[i] = lluvia[i];
              }
          }
          return;
      }
      
      for(int v : adyacencia[vertice]){
          if(!visitado[v]){
              if(!lluvia[vertice][v]){
                boolean bandera = lluvia(40); 
                if(!derrumbe[vertice][v]){
                  if(bandera){                 
                    if(!derrumbe(bandera,30)){
                      cambiarPeso(vertice,v);
                      camino.add(v);
                      visitado[v] = true;
                      buscarCamino(v);
                      //lluvia[vertice][v] = false;
                      //matrixAux[vertice][v]= grafo[vertice][v];
                      camino.removeLast();
                      visitado[v] = false;
                      //derrumbe[v] = false;

                    }else{
                          derrumbe[vertice][v] = true;
                  }
                  }else{
                    camino.add(v);
                    visitado[v] = true;
                    buscarCamino(v);
                    //lluvia[vertice] = false;
                    //matrixAux[vertice][v]= grafo[vertice][v];
                    camino.removeLast();
                    visitado[v] = false;
                  }
                }
              }else{
                    camino.add(v);
                    visitado[v] = true;
                    buscarCamino(v);
                    //lluvia[vertice] = false;
                    //matrixAux[vertice][v]= grafo[vertice][v];
                    camino.removeLast();
                    visitado[v] = false;
              }
          }
      }
      
  }
  
    public String toString(String vector[]) {
    String str = "La ruta más corta es la siguiente:\n\n";
    for (int i=0; i<solucion.length; i++) {
        str += vector[solucion[i]] + "  ";
    }
    str += "\n\nTiempo empleado: " + pesoMin + " horas." + "\nEquivalente a: " + pesoMin/24 + " dias y " + pesoMin%24 + " horas.";
    return str;
  }
  
  public boolean lluvia(int probabilidad){
      final boolean[] porcentaje = new boolean[100];  
        boolean p = false;
        
        for(int i = 0; i < porcentaje.length - 1; i++)
        {
            porcentaje[i] = false;  
        }
        
        for(int j = 0; j < probabilidad; j++)
        {
            porcentaje[j] = true;   
        }
        
        int aux = porcentaje.length - 1; 
        
        int Porcentaje = (int) (Math.random() * aux); 

        return p = porcentaje[Porcentaje];
  }
  
    public boolean derrumbe(boolean bandera, int probabilidad){
        final boolean[] porcentaje = new boolean[100];  
        boolean p = false;
        
        for(int i = 0; i < porcentaje.length - 1; i++)
        {
            porcentaje[i] = false;  
        }
        
        for(int j = 0; j < probabilidad; j++)
        {
            porcentaje[j] = true;   
        }
        
        int aux = porcentaje.length - 1; 
        
        int Porcentaje = (int) (Math.random() * aux); 

        return p = porcentaje[Porcentaje];
    }
  
  public void cambiarPeso( int vertice, int j){
        lluvia[vertice][j] = true;
        matrixAux[vertice][j] = grafo[vertice][j] * 2 ;

  }
      
  
  public String conLluvia(String[] vector){
    String str = "";
    for (int i=0; i<solucion.length-1;i++){
        str += vector[solucion[i]] + " - " + vector[solucion[i+1]] + " : " + pasarPalabra(lluvia[solucion[i]][solucion[i+1]]) + "\n";
    }   
    return str;
  }
  
  private String pasarPalabra(boolean bandera){
      String str;
      if(bandera){
          return str = "Llovió";
      }else{
          return str = "No llovió";
      }
  }

    public int getNumVertices() {
        return numVertices;
    }

    public int[] getSolucion() {
        return solucion;
    }

    public int[][] getMatrixAux() {
        return matrixAux;
    }

    public boolean[][] getLluvia() {
        return lluvia;
    }

    
  public static void main(String[] args) {
      
      int[][] matriz = {
          {0, 9, 2, 8, 8, 9, 15, 19, 14, 17, 13, 12, 13, 13},
          {9, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 3, 11, 0},
          {2, 6, 0, 8, 9, 13, 15, 18, 12, 0, 15, 0, 0, 0},
            {8, 0, 8, 0, 5, 8, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {8, 0, 9, 5, 0, 0, 13, 0, 0, 0, 0, 0, 0, 0}, 
            {9, 0, 13, 8, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0}, 
            {15, 0, 15, 0, 13, 0, 0, 4, 0, 0, 0, 0, 3, 0}, 
            {19, 0, 18, 0, 0, 0, 4, 0, 5, 2, 0, 0, 7, 8}, 
            {14, 0, 12, 0, 0, 0, 0, 5, 0, 0, 0, 0, 8, 1}, 
            {17, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 8, 6}, 
            {13, 0, 15, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {12, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 5}, 
            {13, 11, 0, 0, 0, 0, 3, 7, 8, 8, 0, 11, 0, 11}, 
            {13, 0, 0, 0, 0, 0, 0, 8, 1, 6, 0, 5, 11, 0}
      };
             
    String[] nombres = {"Bogotá","Bucaramanga","Tunja","Medellin","Manizales","Cali","Monteria","Cartagena","Bosconia","Barranquilla","Villa Garzón","Agua Chica","Tarazá","Valledupar"};
    
    
    View frame = new View(nombres,matriz);
    frame.setVisible(true);
    frame.setSize(700, 550);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    
  }
  
  
}

    

