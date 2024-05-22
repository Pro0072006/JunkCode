package redes.modelo_de_redes;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

class Vertice {
    int peso;
    int capacidad;

    Vertice(int peso, int capacidad) {
        this.peso = peso;
        this.capacidad = capacidad;
    }
}

class Grafo {
    private Vertice[][] matrizAdayacencia;
    private int vertices;

    public Grafo(int vertices) {
        this.vertices = vertices;
        matrizAdayacencia = new Vertice[vertices][vertices];
    }

    public void AñadirVertice(int origen, int destino, int peso, int capacidad) {
        matrizAdayacencia[origen][destino] = new Vertice(peso, capacidad);
    }

    public int[] dijkstra(int verticeInicial) {
        int[] distancias = new int[vertices];
        boolean[] rutaMasCorta = new boolean[vertices];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[verticeInicial] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            int u = minDistancia(distancias, rutaMasCorta);
            rutaMasCorta[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (!rutaMasCorta[v] && matrizAdayacencia[u][v] != null
                        && distancias[u] != Integer.MAX_VALUE
                        && distancias[u] + matrizAdayacencia[u][v].peso < distancias[v]) {
                    distancias[v] = distancias[u] + matrizAdayacencia[u][v].peso;
                }
            }
        }

        return distancias;
    }

    private int minDistancia(int[] distancias, boolean[] rutaMasCorta) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!rutaMasCorta[v] && distancias[v] <= min) {
                min = distancias[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public Vertice[][] getMatrizAdyacencia() {
        return matrizAdayacencia;
    }

    public int getVertices() {
        return vertices;
    }
}
