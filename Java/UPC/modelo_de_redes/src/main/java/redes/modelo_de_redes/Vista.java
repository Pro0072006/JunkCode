package redes.modelo_de_redes;

import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private Grafo grafo;
    private PanelGrafo panelGrafo;

    public Vista(Grafo grafo) {
        this.grafo = grafo;
        setTitle("Modelo de Red");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelGrafo = new PanelGrafo(grafo);
        add(panelGrafo, BorderLayout.CENTER);

        JButton showResultsButton = new JButton("Mostrar Rutas Más Cortas");
        showResultsButton.addActionListener(e -> MostrarRutasMasCortas());
        add(showResultsButton, BorderLayout.SOUTH);
    }

    private void MostrarRutasMasCortas() {
        String verticeInicialString = JOptionPane.showInputDialog(this, "Ingrese el vértice de inicio:");
        int VerticeInicial = Integer.parseInt(verticeInicialString);
        int[] distances = grafo.dijkstra(VerticeInicial);
        panelGrafo.setDistancias(VerticeInicial, distances);

        StringBuilder resultado = new StringBuilder("Distancias desde el vértice " + VerticeInicial + ":\n");
        for (int i = 0; i < distances.length; i++) {
            resultado.append("Hasta el vértice ").append(i).append(": ").append(distances[i]).append("\n");
        }

        JOptionPane.showMessageDialog(this, resultado.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el número de vértices:");
        int vertices = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese la matriz de adyacencia en el siguiente formato:\n" +
                "peso capacidad, peso capacidad, ...\n" +
                "Si no hay arista, use -1 -1. Ejemplo para una matriz de 5x5:\n" +
                "0 0, 10 15, -1 -1, 30 5, -1 -1\n" +
                "10 15, 0 0, 20 25, -1 -1, -1 -1\n" +
                "-1 -1, 20 25, 0 0, -1 -1, 40 10\n" +
                "30 5, -1 -1, -1 -1, 0 0, 50 20\n" +
                "-1 -1, -1 -1, 40 10, 50 20, 0 0");
        String[] lines = new String[vertices];
        for (int i = 0; i < vertices; i++) {
            lines[i] = sc.nextLine();
        }

        Grafo grafo = new Grafo(vertices);

        for (int i = 0; i < vertices; i++) {
            String[] edges = lines[i].split(", ");
            for (int j = 0; j < vertices; j++) {
                String[] edgeData = edges[j].split(" ");
                int peso = Integer.parseInt(edgeData[0]);
                int capacidad = Integer.parseInt(edgeData[1]);
                if (peso != -1 && capacidad != -1) {
                    grafo.AñadirVertice(i, j, peso, capacidad);
                }
            }
        }

        sc.close();

        SwingUtilities.invokeLater(() -> {
            new Vista(grafo).setVisible(true);
        });
    }
}
