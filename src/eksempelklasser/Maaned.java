package eksempelklasser;

import java.util.Arrays;

public enum Maaned {
    JAN(1,"januar"),
    FEB(2,"februar"),
    MAR(3, "mars"),
    APR(4, "april"),
    MAI(5, "mai"),
    JUN(6, "juni"),
    JUL(7, "juli"),
    AUG(8, "august"),
    SEP(9, "september"),
    OKT(10, "oktober"),
    NOV(11, "november"),
    DES(12, "desember");


    private final String fulltnavn;
    private final int mndnr;

    private Maaned(int mndnr, String fulltnavn) {
        this.fulltnavn = fulltnavn;
        this.mndnr = mndnr;
    }

    public int mndnr() { return mndnr; }

    @Override
    public String toString() { return fulltnavn; }

    public static String toString(int mndnr) {
        if (mndnr < 1 || mndnr > 12) {
            throw new IllegalArgumentException("Ulovlig månedsnummer!");
        }
        return values()[mndnr-1].toString();
    }

    public static Maaned[] vaar() {
        return Arrays.copyOfRange(values(), 3, 5);
    }
    public static Maaned[] sommer() {
        return Arrays.copyOfRange(values(), 5, 8);
    }
    public static Maaned[] host() {
        return Arrays.copyOfRange(values(), 8, 10);
    }
    public static Maaned[] vinter() {
        return new Maaned[] {NOV, DES, JAN, FEB, MAR};
    }
}
