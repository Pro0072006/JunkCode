package redes.modelo_de_redes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class Vista extends JFrame {
    private JTextArea areaEntrada;
    private JTextField campoFuente;
    private JTextField campoSumidero;
    private JButton botonCalcular;
    private JTextArea areaResultado;
    private JPanel panelGrafico;
    private int[][] matrizFlujo;

    public Vista() {
        setTitle("Flujo Máximo en una Red de Datos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        areaEntrada = new JTextArea(5, 40);
        campoFuente = new JTextField(5);
        campoSumidero = new JTextField(5);
        botonCalcular = new JButton("Calcular Flujo Máximo");
        areaResultado = new JTextArea(5, 40);
        areaResultado.setEditable(false);

        panelGrafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarGrafo(g);
            }
        };

        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoEntrada = areaEntrada.getText();
                int fuente = Integer.parseInt(campoFuente.getText());
                int sumidero = Integer.parseInt(campoSumidero.getText());
                int[][] grafo = parsearEntrada(textoEntrada);
                if (grafo != null) {
                    int flujoMaximo = fordFulkerson(grafo, fuente, sumidero);
                    areaResultado.setText("El flujo máximo de " + fuente + " a " + sumidero + " es: " + flujoMaximo);
                    panelGrafico.repaint();
                } else {
                    areaResultado.setText("Entrada inválida. Por favor, introduce una matriz de adyacencia válida.");
                }
            }
        });

        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new FlowLayout());
        panelEntrada.add(new JLabel("Fuente:"));
        panelEntrada.add(campoFuente);
        panelEntrada.add(new JLabel("Sumidero:"));
        panelEntrada.add(campoSumidero);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(areaEntrada), BorderLayout.NORTH);
        panel.add(panelEntrada, BorderLayout.CENTER);
        panel.add(botonCalcular, BorderLayout.EAST);
        panel.add(new JScrollPane(areaResultado), BorderLayout.SOUTH);

        add(panel, BorderLayout.NORTH);
        add(panelGrafico, BorderLayout.CENTER);
    }

    private int[][] matrizGrafo;

    private int[][] parsearEntrada(String textoEntrada) {
        try {
            String[] filas = textoEntrada.split("\n");
            int n = filas.length;
            int[][] grafo = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] columnas = filas[i].trim().split("\\s+");
                for (int j = 0; j < n; j++) {
                    grafo[i][j] = Integer.parseInt(columnas[j]);
                }
            }
            matrizGrafo = grafo;
            matrizFlujo = new int[n][n]; // Inicializar la matriz de flujo
            return grafo;
        } catch (Exception e) {
            return null;
        }
    }

    private void dibujarGrafo(Graphics g) {
        if (matrizGrafo == null) {
            return;
        }

        int cantidadNodos = matrizGrafo.length;
        int anchoPanel = panelGrafico.getWidth();
        int altoPanel = panelGrafico.getHeight();
        int radio = 20;
        int centroX = anchoPanel / 2;
        int centroY = altoPanel / 2;
        int radioCirculo = Math.min(anchoPanel, altoPanel) / 2 - radio - 20;

        Point[] puntos = new Point[cantidadNodos];
        for (int i = 0; i < cantidadNodos; i++) {
            double angulo = 2 * Math.PI * i / cantidadNodos;
            int x = centroX + (int) (radioCirculo * Math.cos(angulo));
            int y = centroY + (int) (radioCirculo * Math.sin(angulo));
            puntos[i] = new Point(x, y);
        }

        g.setColor(Color.BLACK);
        for (int i = 0; i < cantidadNodos; i++) {
            for (int j = 0; j < cantidadNodos; j++) {
                if (matrizGrafo[i][j] > 0) {
                    g.drawLine(puntos[i].x, puntos[i].y, puntos[j].x, puntos[j].y);
                    String etiqueta = matrizFlujo[i][j] + "/" + matrizGrafo[i][j];
                    int etiquetaX = (puntos[i].x + puntos[j].x) / 2;
                    int etiquetaY = (puntos[i].y + puntos[j].y) / 2;
                    g.drawString(etiqueta, etiquetaX, etiquetaY);
                }
            }
        }

        for (int i = 0; i < cantidadNodos; i++) {
            g.setColor(Color.RED);
            g.fillOval(puntos[i].x - radio, puntos[i].y - radio, 2 * radio, 2 * radio);
            g.setColor(Color.BLACK);
            g.drawOval(puntos[i].x - radio, puntos[i].y - radio, 2 * radio, 2 * radio);
            g.drawString(String.valueOf(i), puntos[i].x - 5, puntos[i].y + 5);
        }
    }

    private boolean bfs(int[][] grafoResidual, int s, int t, int[] padre) {
        boolean[] visitado = new boolean[grafoResidual.length];
        Queue<Integer> cola = new LinkedList<>();
        cola.add(s);
        visitado[s] = true;
        padre[s] = -1;

        while (!cola.isEmpty()) {
            int u = cola.poll();

            for (int v = 0; v < grafoResidual.length; v++) {
                if (!visitado[v] && grafoResidual[u][v] > 0) {
                    cola.add(v);
                    padre[v] = u;
                    visitado[v] = true;
                }
            }
        }

        return visitado[t];
    }

    private int fordFulkerson(int[][] grafo, int s, int t) {
        int u, v;
        int[][] grafoResidual = new int[grafo.length][grafo.length];

        for (u = 0; u < grafo.length; u++) {
            for (v = 0; v < grafo.length; v++) {
                grafoResidual[u][v] = grafo[u][v];
            }
        }

        int[] padre = new int[grafo.length];
        int flujoMaximo = 0;

        while (bfs(grafoResidual, s, t, padre)) {
            int flujoCamino = Integer.MAX_VALUE;
            for (v = t; v != s; v = padre[v]) {
                u = padre[v];
                flujoCamino = Math.min(flujoCamino, grafoResidual[u][v]);
            }

            for (v = t; v != s; v = padre[v]) {
                u = padre[v];
                grafoResidual[u][v] -= flujoCamino;
                grafoResidual[v][u] += flujoCamino;
                matrizFlujo[u][v] += flujoCamino; // Registrar el flujo
                matrizFlujo[v][u] -= flujoCamino; // Ajustar el flujo inverso
            }

            flujoMaximo += flujoCamino;
        }

        return flujoMaximo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }
}
