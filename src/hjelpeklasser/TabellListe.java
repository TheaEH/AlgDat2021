package hjelpeklasser;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TabellListe<T> implements Liste<T>
{
    private T[] a;
    private int antall;
    private int endringer;

    @SuppressWarnings("unchecked")          // pga. konverteringen: Object[] -> T[]
    public TabellListe(int storrelse)       // konstruktør
    {
        a = (T[])new Object[storrelse];       // oppretter tabellen
        antall = 0;                           // foreløpig ingen verdier
    }

    public TabellListe()                    // standardkonstruktør
    {
        this(10);                             // startstørrelse på 10
    }

    public TabellListe(T[] b)                    // en T-tabell som parameter
    {
        this(b.length);                            // kaller den andre konstruktøren

        for (T verdi : b)
        {
            if (verdi != null) a[antall++] = verdi;  // hopper over null-verdier
        }
    }

    @Override
    public int antall()
    {
        return antall;          // returnerer antallet
    }

    @Override
    public boolean tom()
    {
        return antall == 0;     // listen er tom hvis antall er 0
    }

    @Override
    public void nullstill() {
        if (antall > 10) {
            a = (T[]) new Object[10];
        }
        else {
            for (T t : a) {
                t = null;
            }
        }
        antall = 0;
        endringer++;
    }


    // Skal ligge som en indre klasse i class TabellListe
    private class TabellListeIterator implements Iterator<T>
    {
        private int denne = 0;       // instansvariabel
        private boolean fjernOK = false;
        private int iteratorendringer = endringer;

        @Override
        public boolean hasNext()     // sjekker om det er flere igjen
        {
            return denne < antall;     // sjekker verdien til denne
        }

        @Override
        public T next()              // returnerer aktuell verdi
        {
            if (iteratorendringer != endringer)
            {
                throw new ConcurrentModificationException("Listen er endret!");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Tomt eller ingen verdier igjen!");
            }

            T denneVerdi = a[denne];
            denne++;
            fjernOK = true;
            return denneVerdi;
        }

        public void forEach(Consumer<? super T> action) {
            for (int i = 0; i < antall; i++) {
                action.accept(a[i]);
            }
        }

        public void forEachRemaining(Consumer<? super T> action) {
            while (denne < antall) {
                action.accept(a[denne++]);
            }
        }

        @Override
        public void remove()         // ny versjon
        {
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Listen er endret!");
            }

            if (!fjernOK) {
                throw new IllegalStateException("Ulovlig tilstand!");
            }

            fjernOK = false;           // remove() kan ikke kalles på nytt

            // verdien i denne - 1 skal fjernes da den ble returnert i siste kall
            // på next(), verdiene fra og med denne flyttes derfor en mot venstre
            antall--;           // en verdi vil bli fjernet
            denne--;            // denne må flyttes til venstre

            System.arraycopy(a, denne + 1, a, denne, antall - denne);  // tetter igjen
            a[antall] = null;   // verdien som lå lengst til høyre nulles

            endringer++;
            iteratorendringer++;
        }
    }  // TabellListeIterator

    @Override
    public Iterator<T> iterator() {
        return new TabellListeIterator();
    }

    @Override
    public T hent(int indeks)
    {
        indeksKontroll(indeks, false);   // false: indeks = antall er ulovlig
        return a[indeks];                // returnerer er tabellelement
    }

    @Override
    public int indeksTil(T verdi)
    {
        for (int i = 0; i < antall; i++)
        {
            if (a[i].equals(verdi)) return i;   // funnet!
        }
        return -1;   // ikke funnet!
    }

    @Override
    public T oppdater(int indeks, T verdi)
    {
        Objects.requireNonNull(verdi, "null er ulovlig!");
        indeksKontroll(indeks, false);  // false: indeks = antall er ulovlig

        T gammelverdi = a[indeks];      // tar vare på den gamle verdien
        a[indeks] = verdi;              // oppdaterer
        endringer++;
        return gammelverdi;             // returnerer den gamle verdien
    }

    @Override
    public boolean fjern(T verdi)
    {
        Objects.requireNonNull(verdi, "null er ulovlig!");

        for (int i = 0; i < antall; i++)
        {
            if (a[i].equals(verdi))
            {
                antall--;
                System.arraycopy(a, i + 1, a, i, antall - i);

                a[antall] = null;
                endringer++;

                return true;
            }
        }
        return false;
    }

    @Override
    public T fjern(int indeks)
    {
        indeksKontroll(indeks, false);  // false: indeks = antall er ulovlig
        T verdi = a[indeks];

        antall--; // sletter ved å flytte verdier mot venstre
        System.arraycopy(a, indeks + 1, a, indeks, antall - indeks);
        a[antall] = null;   // tilrettelegger for "søppeltømming"
        endringer++;

        return verdi;
    }

    @Override
    public boolean fjernHvis(Predicate<? super T> p) {  // betingelsesfjerning
        Objects.requireNonNull(p);                      // kaster unntak

        int nyttAntall = antall;

        for (int i = 0, j = 0; j<antall; j++) {
            if (p.test(a[j])) {         // a[j] skal fjernes
                nyttAntall--;
            }
            else {
                a[i++] = a[j];          // forskyver
            }
        }

        for (int i = nyttAntall; i < antall; i++) {
            a[i] = null;        // tilrettelegger for søppeltømming
        }

        boolean fjernet = nyttAntall < antall;

        if (fjernet) {
                endringer++;
        }

        antall = nyttAntall;

        return fjernet;
    }


    @Override
    public boolean leggInn(T verdi)  // inn bakerst
    {
        Objects.requireNonNull(verdi, "null er ulovlig!");

        if (antall == a.length)  // En full tabell utvides med 50%
        {
            a = Arrays.copyOf(a,(3*antall)/2 + 1);
        }

        a[antall++] = verdi;    // setter inn ny verdi
        endringer++;
        return true;            // vellykket innlegging
    }

    @Override
    public void leggInn(int indeks, T verdi)
    {
        Objects.requireNonNull(verdi, "null er ulovlig!");

        indeksKontroll(indeks, true);  // true: indeks = antall er lovlig

        // En full tabell utvides med 50%
        if (antall == a.length) a = Arrays.copyOf(a,(3*antall)/2 + 1);

        // rydder plass til den nye verdien
        System.arraycopy(a, indeks, a, indeks + 1, antall - indeks);

        a[indeks] = verdi;     // setter inn ny verdi

        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi)
    {
        return indeksTil(verdi) != -1;
    }

    @Override
    public String toString() {
        StringBuilder ut = new StringBuilder();

        ut.append("[");
        for (int i = 0; i<antall-1; i++) {
            ut.append(a[i]);
            ut.append(", ");
        }
        ut.append(a[antall-1]);
        ut.append("]");
        return ut.toString();
    }

}
