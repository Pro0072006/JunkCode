package programa;

public class Domino {
    private int EsquinaA;
    private int EsquinaB;

    public Domino(int ladoA, int ladoB) {
        setEsquinaA(ladoA);
        setEsquinaB(ladoB);
    }

    public int getEsquinaA() {
        return EsquinaA;
    }

    public int getEsquinaB() {
        return EsquinaB;
    }

    public boolean setEsquinaA(int esquinaA) {
        if (esquinaA < 0 || esquinaA > 6)
            return false;

        EsquinaA = esquinaA;
        return true;
    }

    public boolean setEsquinaB(int esquinaB) {
        if (esquinaB < 0 || esquinaB > 6)
            return false;

        EsquinaB = esquinaB;
        return true;
    }

    public boolean Emparejar(Domino otraFicha) {
        return (this.EsquinaA == otraFicha.getEsquinaA() || this.EsquinaA == otraFicha.getEsquinaB()
                || this.EsquinaB == otraFicha.getEsquinaA() || this.EsquinaB == otraFicha.getEsquinaB());
    }

}
