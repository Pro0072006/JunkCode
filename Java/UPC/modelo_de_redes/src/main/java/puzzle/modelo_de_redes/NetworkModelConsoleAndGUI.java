package puzzle.modelo_de_redes;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

class Edge {
    int weight;
    int capacity;

    Edge(int weight, int capacity) {
        this.weight = weight;
        this.capacity = capacity;
    }
}

class Graph {
    private Edge[][] adjacencyMatrix;
    private int vertices;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyMatrix = new Edge[vertices][vertices];
    }

    public void addEdge(int src, int dest, int weight, int capacity) {
        adjacencyMatrix[src][dest] = new Edge(weight, capacity);
    }

    public int[] dijkstra(int startVertex) {
        int[] distances = new int[vertices];
        boolean[] shortestPathTreeSet = new boolean[vertices];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        for (int count = 0; count < vertices - 1; count++) {
            int u = minDistance(distances, shortestPathTreeSet);
            shortestPathTreeSet[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (!shortestPathTreeSet[v] && adjacencyMatrix[u][v] != null
                        && distances[u] != Integer.MAX_VALUE
                        && distances[u] + adjacencyMatrix[u][v].weight < distances[v]) {
                    distances[v] = distances[u] + adjacencyMatrix[u][v].weight;
                }
            }
        }

        return distances;
    }

    private int minDistance(int[] distances, boolean[] shortestPathTreeSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!shortestPathTreeSet[v] && distances[v] <= min) {
                min = distances[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public Edge[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getVertices() {
        return vertices;
    }
}

class GraphPanel extends JPanel {
    private Graph graph;
    private int[] distances;
    private int startVertex;

    public GraphPanel(Graph graph) {
        this.graph = graph;
        this.distances = null;
        this.startVertex = -1;
    }

    public void setDistances(int startVertex, int[] distances) {
        this.startVertex = startVertex;
        this.distances = distances;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        int vertexCount = graph.getVertices();
        int radius = 200;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int vertexRadius = 20;

        Point[] points = new Point[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            double angle = 2 * Math.PI * i / vertexCount;
            int x = centerX + (int) (radius * Math.cos(angle));
            int y = centerY + (int) (radius * Math.sin(angle));
            points[i] = new Point(x, y);
        }

        Edge[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (adjacencyMatrix[i][j] != null) {
                    g.drawLine(points[i].x, points[i].y, points[j].x, points[j].y);
                    int midX = (points[i].x + points[j].x) / 2;
                    int midY = (points[i].y + points[j].y) / 2;
                    g.drawString(adjacencyMatrix[i][j].weight + "," + adjacencyMatrix[i][j].capacity, midX, midY);
                }
            }
        }

        for (int i = 0; i < vertexCount; i++) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(points[i].x - vertexRadius, points[i].y - vertexRadius, 2 * vertexRadius, 2 * vertexRadius);
            g.setColor(Color.BLACK);
            g.drawOval(points[i].x - vertexRadius, points[i].y - vertexRadius, 2 * vertexRadius, 2 * vertexRadius);
            g.drawString("V" + i, points[i].x - vertexRadius / 2, points[i].y + vertexRadius / 2);
            if (distances != null && i != startVertex) {
                g.drawString(String.valueOf(distances[i]), points[i].x - vertexRadius / 2,
                        points[i].y + vertexRadius / 2 + 15);
            }
        }
    }
}

public class NetworkModelConsoleAndGUI extends JFrame {
    private Graph graph;
    private GraphPanel graphPanel;

    public NetworkModelConsoleAndGUI(Graph graph) {
        this.graph = graph;
        setTitle("Modelo de Red");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        graphPanel = new GraphPanel(graph);
        add(graphPanel, BorderLayout.CENTER);

        JButton showResultsButton = new JButton("Mostrar Rutas Más Cortas");
        showResultsButton.addActionListener(e -> displayShortestPaths());
        add(showResultsButton, BorderLayout.SOUTH);
    }

    private void displayShortestPaths() {
        String startVertexStr = JOptionPane.showInputDialog(this, "Ingrese el vértice de inicio:");
        int startVertex = Integer.parseInt(startVertexStr);
        int[] distances = graph.dijkstra(startVertex);
        graphPanel.setDistances(startVertex, distances);

        StringBuilder result = new StringBuilder("Distancias desde el vértice " + startVertex + ":\n");
        for (int i = 0; i < distances.length; i++) {
            result.append("Hasta el vértice ").append(i).append(": ").append(distances[i]).append("\n");
        }

        JOptionPane.showMessageDialog(this, result.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el número de vértices:");
        int vertices = Integer.parseInt(scanner.nextLine());

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
            lines[i] = scanner.nextLine();
        }

        Graph graph = new Graph(vertices);

        for (int i = 0; i < vertices; i++) {
            String[] edges = lines[i].split(", ");
            for (int j = 0; j < vertices; j++) {
                String[] edgeData = edges[j].split(" ");
                int weight = Integer.parseInt(edgeData[0]);
                int capacity = Integer.parseInt(edgeData[1]);
                if (weight != -1 && capacity != -1) {
                    graph.addEdge(i, j, weight, capacity);
                }
            }
        }

        scanner.close();

        SwingUtilities.invokeLater(() -> {
            new NetworkModelConsoleAndGUI(graph).setVisible(true);
        });
    }
}
