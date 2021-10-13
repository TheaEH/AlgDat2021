package hjelpeklasser;

import java.util.*;

public class TabellStakk<T> implements Stakk<T>
{
    private T[] a;                     // en T-tabell
    private int antall;                // antall verdier på stakken

    public TabellStakk()               // konstruktør - tabellengde 8
    {
        this(8);
    }

    @SuppressWarnings("unchecked")     // pga. konverteringen: Object[] -> T[]
    public TabellStakk(int lengde)     // valgfri tabellengde
    {
        if (lengde < 0)
            throw new IllegalArgumentException("Negativ tabellengde!");

        a = (T[])new Object[lengde];     // oppretter tabellen
        antall = 0;                      // stakken er tom
    }

    public void leggInn(T verdi)
    {
        if (antall == a.length)
            a = Arrays.copyOf(a, antall == 0 ? 1 : 2*antall);   // dobler

        a[antall++] = verdi;
    }

    public T kikk()
    {
        if (antall == 0)       // sjekker om stakken er tom
            throw new NoSuchElementException("Stakken er tom!");

        return a[antall-1];    // returnerer den øverste verdien
    }

    public T taUt()
    {
        if (antall == 0)       // sjekker om stakken er tom
            throw new NoSuchElementException("Stakken er tom!");

        antall--;             // reduserer antallet

        T temp = a[antall];   // tar var på det øverste objektet
        a[antall] = null;     // tilrettelegger for resirkulering

        return temp;          // returnerer den øverste verdien
    }

    public boolean tom() { return antall == 0; }

    public int antall() { return antall; }

    public void nullstill() {
        for (int i = 0; i<antall; i++) {
            a[i] = null;
        }
        antall = 0;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        if (antall > 0) {
            s.append(a[antall - 1]);

            for (int i = antall-2; i >= 0; i--) {
                s.append(", ").append(a[i]);
            }
        }
        s.append("]");
        return s.toString();
    }

    public static <T> void snu(Stakk<T> A) {
        Stakk<T> B = new TabellStakk<>(A.antall());
        Stakk<T> C = new TabellStakk<>(A.antall());

        while (!A.tom()) {
            B.leggInn(A.taUt());
        }
        while (!B.tom()) {
            C.leggInn(B.taUt());
        }
        while (!C.tom()) {
            A.leggInn(C.taUt());
        }
    }

    public static <T> void snu2(Stakk<T> A) {
        int n = A.antall()-1;
        Stakk<T> B = new TabellStakk<>();
        T temp;

        for (int i = n; i>0; i--) {
            temp = A.taUt();
            for (int j = 0; j<i; j++) {
                B.leggInn(A.taUt());
            }
            A.leggInn(temp);
            while (!B.tom()) {
                A.leggInn(B.taUt());
            }

        }
    }

    public static <T> void kopier(Stakk<T> A, Stakk<T> B) {
        Stakk<T> C = new TabellStakk<>(A.antall());
        T temp;

        while (!A.tom()) {
            C.leggInn(A.taUt());
        }
        while (!C.tom()) {
            temp = C.taUt();
            A.leggInn(temp);
            B.leggInn(temp);
        }
    }

    public static <T> void kopier2(Stakk<T> A, Stakk<T> B) {
        int n = A.antall();
        while (n>0) {
            for (int j = 0; j<n; j++) {
                B.leggInn(A.taUt());
            }
            T temp = B.kikk();

            for (int j = 0; j<n; j++) {
                A.leggInn(B.taUt());
            }
            B.leggInn(temp);
            n--;
        }
    }


    public static <T> void sorter(Stakk<T> A, Comparator< ? super T> c) {
        Stakk<T> B = new TabellStakk<>();
        T temp;
        while (!A.tom()) {
            temp = A.taUt();
            int n = 0;  // Antall tall som må (midlertidig) flyttes bort fra B før vi kan plassere temp foran dem

            while (!B.tom() && c.compare(temp, B.kikk()) < 0) { // løkka kjører så lenge temp er mindre enn bakerste tall i B
                n++;
                A.leggInn(B.taUt());    // flytter de midlertidig over i A
            }
            B.leggInn(temp);            // kan nå plassere det temp på sin rette plass
            for (int i = 0; i<n; i++) {
                B.leggInn(A.taUt());    // legger så tilbake de vi midlertidig flyttet over til A (n stk tall)
            }
        }
        while (!B.tom()) {              // etter at alle tallene har blitt sortert over til B
            A.leggInn(B.taUt());        // gjenstår kun å flytte alle over til A igjen
        }
    }

}  // class TabellStakk
