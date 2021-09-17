package eksempelklasser;

import java.util.Objects;

public class Dato implements Comparable<Dato>
{
    private final int dag, mnd, aar;              // instansvariabler

    public Dato(int dag, int mnd, int aar)        // konstruktør
    {
        this.dag = dag; this.mnd = mnd; this.aar = aar;
    }

    public Dato(int dag, Maaned mnd, int aar) {
        this.dag = dag;
        this.mnd = mnd.mndnr();
        this.aar = aar;
    }

    public int compareTo(Dato d)                 // compareTo
    {
        if (aar < d.aar) return -1;
        else if (aar > d.aar) return 1;
        else if (mnd < d.mnd) return -1;
        else if (mnd > d.mnd) return 1;
        else return dag - d.dag;
    }

    public boolean equals(Object o)              // equals
    {
        if (o == this) return true;
        if (!(o instanceof Dato)) return false;
        Dato d = (Dato) o;
        return aar == d.aar && mnd == d.mnd && dag == d.dag;
    }

    public boolean equals(Dato d)                 // equals
    {
        return aar == d.aar && mnd == d.mnd && dag == d.dag;
    }

    public String toString()                     // toString
    {
        StringBuilder ut = new StringBuilder();
        ut.append(dag).append(". ").append(Maaned.toString(mnd)).
                append(" ").append(aar);
        return ut.toString();
    }

    @Override
    public int hashCode()                         // laget av Netbeans
    {
        int hash = 3;
        hash = 89 * hash + dag;
        hash = 89 * hash + mnd;
        hash = 89 * hash + aar;
        return hash;
    }

} // class Dato
