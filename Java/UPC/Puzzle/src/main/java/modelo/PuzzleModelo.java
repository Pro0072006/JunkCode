package modelo;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

public class PuzzleModelo {
    private int[][] tablero;
    private int tamaño = 4;
    private int fichaVacia = 16;
    private int movimientos = 0;
    private boolean juegoEmpezado = false;

    public PuzzleModelo() {
        tablero = new int[tamaño][tamaño];
        InicializarTablero();
    }

    public void InicializarTablero() {
        movimientos = 0;
        int count = 1;
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                tablero[i][j] = count++;
            }
        }
    }

    public int getMovimientos() {
        return movimientos;
    }

    public boolean getJuegoEmpezado() {
        return juegoEmpezado;
    }

    public void IniciarJuego() {
        juegoEmpezado = true;
    }

    public int[][] getTablero() {
        return tablero.clone();
    }

    public void MezclarTablero() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Collections.shuffle(list);
        int index = 0;
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                tablero[i][j] = list.get(index++);
            }
        }
    }

    public boolean Mover(int ficha) {
        int[] pocisionVacia = EncontrarFichaVacia();
        int filaFicha = -1, columnaFicha = -1;
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (tablero[i][j] == ficha) {
                    filaFicha = i;
                    columnaFicha = j;
                    break;
                }
            }
        }
        if ((Math.abs(pocisionVacia[0] - filaFicha) == 1 && pocisionVacia[1] == columnaFicha) ||
                (Math.abs(pocisionVacia[1] - columnaFicha) == 1 && pocisionVacia[0] == filaFicha)) {
            tablero[pocisionVacia[0]][pocisionVacia[1]] = ficha;
            tablero[filaFicha][columnaFicha] = fichaVacia;
            movimientos++;
            return true;
        } else {
            return false;
        }
    }

    public boolean EstaResuelto() {
        int cuenta = 1;
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (tablero[i][j] != cuenta++ && cuenta != fichaVacia) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] EncontrarFichaVacia() {
        int[] pos = new int[2];
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (tablero[i][j] == fichaVacia) {
                    pos[0] = i;
                    pos[1] = j;
                    break;
                }
            }
        }
        return pos;
    }
}
