package redes.modelo_de_redes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class PanelGrafo extends JPanel {
    private Grafo grafo;
    private int[] distancias;
    private int verticeInicial;

    public PanelGrafo(Grafo grafo) {
        this.grafo = grafo;
        this.distancias = null;
        this.verticeInicial = -1;
    }

    public void setDistancias(int verticeInicial, int[] distancias) {
        this.verticeInicial = verticeInicial;
        this.distancias = distancias;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        int vertexCount = grafo.getVertices();
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

        Vertice[][] adjacencyMatrix = grafo.getMatrizAdyacencia();
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (adjacencyMatrix[i][j] != null) {
                    g.drawLine(points[i].x, points[i].y, points[j].x, points[j].y);
                    int midX = (points[i].x + points[j].x) / 2;
                    int midY = (points[i].y + points[j].y) / 2;
                    g.drawString(adjacencyMatrix[i][j].peso + "," + adjacencyMatrix[i][j].capacidad, midX, midY);
                }
            }
        }

        for (int i = 0; i < vertexCount; i++) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(points[i].x - vertexRadius, points[i].y - vertexRadius, 2 * vertexRadius, 2 * vertexRadius);
            g.setColor(Color.BLACK);
            g.drawOval(points[i].x - vertexRadius, points[i].y - vertexRadius, 2 * vertexRadius, 2 * vertexRadius);
            g.drawString("V" + i, points[i].x - vertexRadius / 2, points[i].y + vertexRadius / 2);
            if (distancias != null && i != this.verticeInicial) {
                g.drawString(String.valueOf(distancias[i]), points[i].x - vertexRadius / 2,
                        points[i].y + vertexRadius / 2 + 15);
            }
        }
    }
}
