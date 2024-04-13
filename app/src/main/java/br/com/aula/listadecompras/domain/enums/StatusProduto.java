package br.com.aula.listadecompras.domain.enums;

public enum StatusProduto {
    COMPRADO,
    NAO_COMPRADO;

    public static StatusProduto fromInt(int value) {
        switch (value) {
            case 0:
                return COMPRADO;
            case 1:
                return NAO_COMPRADO;
            default:
                return null;
        }
    }

    public int toInt() {
        switch (this) {
            case COMPRADO:
                return 0;
            case NAO_COMPRADO:
                return 1;
            default:
                return -1;
        }
    }
}
