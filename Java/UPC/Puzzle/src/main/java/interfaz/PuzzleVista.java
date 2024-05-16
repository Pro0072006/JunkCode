package interfaz;

import javax.swing.*;

import controlador.PuzzleControlador;

import java.awt.*;

public class PuzzleVista extends JFrame {
    private PuzzleControlador controlador;
    private JButton[][] botones;
    private JLabel tiempoLabel;
    private JLabel movimientosLabel;

    public PuzzleVista() {

    }

    public void InicializarVista(PuzzleControlador controlador) {
        this.controlador = controlador;
        setTitle("Puzzle 15");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel(new GridLayout(4, 4));
        botones = new JButton[4][4];
        int cuenta = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                final int fila = i;
                final int columna = j;
                botones[i][j] = new JButton();
                botones[i][j].setPreferredSize(new Dimension(60, 60));
                botones[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                botones[i][j].addActionListener(e -> controlador.BotonFicha(botones[fila][columna].getText()));
                botones[i][j].setText(cuenta++ + "");
                gamePanel.add(botones[i][j]);
            }
        }
        botones[3][3].setText("");
        add(gamePanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new FlowLayout());
        movimientosLabel = new JLabel("Movimientos: 0");
        infoPanel.add(movimientosLabel);
        add(infoPanel, BorderLayout.NORTH);

        JButton newGameButton = new JButton("Nuevo juego");
        newGameButton.addActionListener(e -> controlador.NuevoJuegoBoton());
        add(newGameButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void ActualizarTablero(int[][] tablero) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                botones[i][j].setText(String.valueOf(tablero[i][j]));
                if (!botones[i][j].getText().equalsIgnoreCase("16")) {
                    if (Integer.parseInt(botones[i][j].getText()) == (i * 4 + j + 1)) {
                        botones[i][j].setBackground(new Color(255, 182, 193)); // Color rosado pastel
                    } else {
                        botones[i][j].setBackground(null); // Restablecer el color predeterminado
                    }
                } else {
                    botones[i][j].setText("");
                }
            }
        }
    }

    public void ActualizarMovimientosLabel(int movimientos) {
        movimientosLabel.setText("Movimientos: " + movimientos);
    }

    public void MostrarMensajeGanador() {
        JOptionPane.showMessageDialog(this,
                "Felicitaciones! Resolviste el puzzle en:  " + movimientosLabel.getText());
    }
}
