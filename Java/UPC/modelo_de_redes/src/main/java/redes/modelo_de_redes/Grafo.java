package redes.modelo_de_redes;

import java.util.Arrays;

class Grafo {
    private Vertice[][] matrizAdyacencia;
    private int vertices;

    public Grafo(int vertices) {
        this.vertices = vertices;
        matrizAdyacencia = new Vertice[vertices][vertices];
    }

    public void AñadirVertice(int origen, int destino, int peso, int capacidad) {
        matrizAdyacencia[origen][destino] = new Vertice(peso, capacidad);
    }

    public int[] dijkstra(int verticeInicial) {
        int[] distancias = new int[vertices];
        int[] capacidades = new int[vertices];
        boolean[] rutaMasCorta = new boolean[vertices];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(capacidades, Integer.MIN_VALUE);
        distancias[verticeInicial] = 0;
        capacidades[verticeInicial] = Integer.MAX_VALUE; // Máxima capacidad posible desde el vértice inicial

        for (int i = 0; i < vertices - 1; i++) {
            int u = minDistancia(distancias, capacidades, rutaMasCorta);
            rutaMasCorta[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (!rutaMasCorta[v] && matrizAdyacencia[u][v] != null
                        && distancias[u] != Integer.MAX_VALUE) {
                    int nuevaDistancia = distancias[u] + matrizAdyacencia[u][v].peso;
                    int nuevaCapacidad = Math.min(capacidades[u], matrizAdyacencia[u][v].capacidad);

                    if (nuevaDistancia < distancias[v] ||
                            (nuevaDistancia == distancias[v] && nuevaCapacidad > capacidades[v])) {
                        distancias[v] = nuevaDistancia;
                        capacidades[v] = nuevaCapacidad;
                    }
                }
            }
        }

        return distancias;
    }

    private int minDistancia(int[] distancias, int[] capacidades, boolean[] rutaMasCorta) {
        int minDistancia = Integer.MAX_VALUE;
        int maxCapacidad = Integer.MIN_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!rutaMasCorta[v] &&
                    (distancias[v] < minDistancia ||
                            (distancias[v] == minDistancia && capacidades[v] > maxCapacidad))) {
                minDistancia = distancias[v];
                maxCapacidad = capacidades[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public Vertice[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public int getVertices() {
        return vertices;
    }
}
