package modelo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

        if (!SePuedeResolver()) {
            MezclarTablero();
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

        int deltaFila = pocisionVacia[0] - filaFicha;
        int deltaColumna = pocisionVacia[1] - columnaFicha;
        int valorAux = -1;

        if (deltaFila == 0 && deltaColumna == 0) {
            return false;
        }

        if (deltaFila > 0 && deltaColumna > 0) {
            return false;
        }

        if (deltaFila > 0 && deltaColumna == 0) {
            for (int i = 0; i < deltaFila; i++) {
                valorAux = tablero[pocisionVacia[0] - i][columnaFicha];
                tablero[pocisionVacia[0] - i][columnaFicha] = tablero[pocisionVacia[0] - i - 1][columnaFicha];
                tablero[pocisionVacia[0] - i - 1][columnaFicha] = valorAux;
                movimientos++;
            }
            return true;
        }

        if (deltaFila < 0 && deltaColumna == 0) {
            for (int i = 0; i < Math.abs(deltaFila); i++) {
                valorAux = tablero[pocisionVacia[0] + i][columnaFicha];
                tablero[pocisionVacia[0] + i][columnaFicha] = tablero[pocisionVacia[0] + i + 1][columnaFicha];
                tablero[pocisionVacia[0] + i + 1][columnaFicha] = valorAux;
                movimientos++;
            }
            return true;
        }

        if (deltaFila == 0 && deltaColumna > 0) {
            for (int i = 0; i < deltaColumna; i++) {
                valorAux = tablero[filaFicha][pocisionVacia[1] - i];
                tablero[filaFicha][pocisionVacia[1] - i] = tablero[filaFicha][pocisionVacia[1] - i - 1];
                tablero[filaFicha][pocisionVacia[1] - i - 1] = valorAux;
                movimientos++;
            }
            return true;
        }

        if (deltaFila == 0 && deltaColumna < 0) {
            for (int i = 0; i < Math.abs(deltaColumna); i++) {
                valorAux = tablero[filaFicha][pocisionVacia[1] + i];
                tablero[filaFicha][pocisionVacia[1] + i] = tablero[filaFicha][pocisionVacia[1] + i + 1];
                tablero[filaFicha][pocisionVacia[1] + i + 1] = valorAux;
                movimientos++;
            }
            return true;
        }

        return false;
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

    private boolean SePuedeResolver() {
        int inversiones = 0;
        int filaFichaVacia = EncontrarFichaVacia()[0] + 1;
        int[] tableroUni = new int[16];

        int cuenta = 0;
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                tableroUni[cuenta++] = tablero[i][j];
            }
        }

        for (int i = 0; i < 16; i++) {
            if (tableroUni[i] == 16) {
                continue;
            }
            for (int j = i + 1; j < 16; j++) {
                if (tableroUni[j] == 16) {
                    continue;
                }
                if (tableroUni[i] > tableroUni[j]) {
                    inversiones++;
                }
            }
        }

        if (filaFichaVacia % 2 == 0) {
            return inversiones % 2 == 0; // Es par
        } else {
            return inversiones % 2 != 0; // Es impar
        }
    }
}
