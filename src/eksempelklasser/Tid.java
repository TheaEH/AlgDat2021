package eksempelklasser;

import java.util.Objects;

public class Tid implements Comparable<Tid> {
    private Klokkeslett klokkeslett;
    private Dato dato;

    public Tid(Klokkeslett k, Dato d) {
        this.klokkeslett = k;
        this.dato = d;
    }

    public Tid(int dag, int maaned, int aar, String klokkeslett) {
        this.dato = new Dato(dag, maaned, aar);
        this.klokkeslett = new Klokkeslett(klokkeslett);
    }

    public int compareTo(Tid t) {
        int cmp = dato.compareTo(t.dato);
        if (cmp != 0) return cmp;
        return klokkeslett.compareTo(t.klokkeslett);
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (! (o instanceof Tid)) return false;
        Tid t = (Tid) o;
        return equals(t);
    }

    public boolean equals(Tid t) {
        return dato.equals(t.dato) && klokkeslett.equals(t.klokkeslett);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klokkeslett, dato);
    }

    @Override
    public String toString() {
        return dato + " kl. " + klokkeslett;
    }
}
