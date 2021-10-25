package hjelpeklasser;

import java.util.*;

public class BinTre<T> implements Iterable<T>           // et generisk binærtre
{
    private static final class Node<T>  // en indre nodeklasse
    {
        private T verdi;            // nodens verdi
        private Node<T> venstre;    // referanse til venstre barn/subtre
        private Node<T> høyre;      // referanse til høyre barn/subtre

        private Node(T verdi, Node<T> v, Node<T> h)    // konstruktør
        {
            this.verdi = verdi; venstre = v; høyre = h;
        }

        private Node(T verdi) { this.verdi = verdi; }  // konstruktør

    } // class Node<T>

    private Node<T> rot;      // referanse til rotnoden
    private int antall;       // antall noder i treet

    public BinTre() { rot = null; antall = 0; }          // konstruktør

    public BinTre(int[] posisjon, T[] verdi)  // konstruktør
    {
        if (posisjon.length > verdi.length) throw new
                IllegalArgumentException("Verditabellen har for få elementer!");

        for (int i = 0; i < posisjon.length; i++) leggInn(posisjon[i],verdi[i]);
    }

    public final void leggInn(int posisjon, T verdi)  // final: kan ikke overstyres
    {
        if (posisjon < 1) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") < 1!");

        Node<T> p = rot, q = null;    // nodereferanser

        int filter = Integer.highestOneBit(posisjon) >> 1;   // filter = 100...00

        while (p != null && filter > 0)
        {
            q = p;
            p = (posisjon & filter) == 0 ? p.venstre : p.høyre;
            filter >>= 1;  // bitforskyver filter
        }

        if (filter > 0) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") mangler forelder!");
        else if (p != null) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") finnes fra før!");

        p = new Node<>(verdi);          // ny node

        if (q == null) rot = p;         // tomt tre - ny rot
        else if ((posisjon & 1) == 0)   // sjekker siste siffer i posisjon
            q.venstre = p;                // venstre barn til q
        else
            q.høyre = p;                  // høyre barn til q

        antall++;                       // en ny verdi i treet
    }

    private Node<T> finnNode(int posisjon)  // finner noden med gitt posisjon
    {
        if (posisjon < 1) return null;

        Node<T> p = rot;   // nodereferanse
        int filter = Integer.highestOneBit(posisjon >> 1);   // filter = 100...00

        for (; p != null && filter > 0; filter >>= 1)
            p = (posisjon & filter) == 0 ? p.venstre : p.høyre;

        return p;   // p blir null hvis posisjon ikke er i treet
    }

    public boolean finnes(int posisjon)
    {
        return finnNode(posisjon) != null;
    }

    public T hent(int posisjon)
    {
        Node<T> p = finnNode(posisjon);

        if (p == null) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") finnes ikke i treet!");

        return p.verdi;
    }

    public T oppdater(int posisjon, T nyverdi)
    {
        Node<T> p = finnNode(posisjon);

        if (p == null) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") finnes ikke i treet!");

        T gammelverdi = p.verdi;
        p.verdi = nyverdi;

        return gammelverdi;
    }

    public void nivåorden(Oppgave<? super T> oppgave)    // ny versjon
    {
        if (tom()) return;                   // tomt tre
        Kø<Node<T>> kø = new TabellKø<>();   // Se Avsnitt 4.2.3
        kø.leggInn(rot);                     // legger inn roten

        while (!kø.tom())                    // så lenge køen ikke er tom
        {
            Node<T> p = kø.taUt();             // tar ut fra køen
            oppgave.utførOppgave(p.verdi);     // den generiske oppgaven

            if (p.venstre != null) kø.leggInn(p.venstre);
            if (p.høyre != null) kø.leggInn(p.høyre);
        }
    }

    private static <T> void preorden(Node<T> p, Oppgave<? super T> oppgave)
    {
        while (true) {
            oppgave.utførOppgave(p.verdi);                       // utfører oppgaven
            if (p.venstre != null) preorden(p.venstre,oppgave);  // til venstre barn
            if (p.høyre == null) return;      // metodekallet er ferdig
            p = p.høyre;
        }
    }

    public void preorden(Oppgave<? super T> oppgave)
    {
        if (!tom()) preorden(rot,oppgave);  // sjekker om treet er tomt
    }

    private static <T> void inorden(Node<T> p, Oppgave<? super T> oppgave)
    {
        while (true) {
            if (p.venstre != null) inorden(p.venstre,oppgave);
            oppgave.utførOppgave(p.verdi);
            if (p.høyre == null) return;
            p = p.høyre;
        }
    }

    public void inorden(Oppgave <? super T> oppgave)
    {
        if (!tom()) inorden(rot,oppgave);
    }

    private static <T> void postorden(Node <T> p, Oppgave <? super T> oppgave) {

        if (p.venstre != null) postorden(p.venstre, oppgave);
        if (p.høyre != null) postorden(p.høyre, oppgave);
        oppgave.utførOppgave(p.verdi);
    }

    public void postorden(Oppgave<? super T> oppgave) {
        if (rot != null) postorden(rot, oppgave);
    }

    public void iterativPreorden(Oppgave<? super T> oppgave)   // ny versjon
    {
        if (tom()) return;

        Stakk<Node<T>> stakk = new TabellStakk<>();
        Node<T> p = rot;    // starter i roten

        while (true)
        {
            oppgave.utførOppgave(p.verdi);

            if (p.venstre != null)
            {
                if (p.høyre != null) stakk.leggInn(p.høyre);
                p = p.venstre;
            }
            else if (p.høyre != null)  // her er p.venstre lik null
            {
                p = p.høyre;
            }
            else if (!stakk.tom())     // her er p en bladnode
            {
                p = stakk.taUt();
            }
            else                       // p er en bladnode og stakken er tom
                break;                   // traverseringen er ferdig
        }
    }

    public void iterativInorden(Oppgave<? super T> oppgave)  // iterativ inorden
    {
        if (tom()) return;            // tomt tre

        Stakk<Node<T>> stakk = new TabellStakk<>();
        Node<T> p = rot;   // starter i roten og går til venstre
        for ( ; p.venstre != null; p = p.venstre) stakk.leggInn(p);

        while (true)
        {
            oppgave.utførOppgave(p.verdi);      // oppgaven utføres

            if (p.høyre != null)          // til venstre i høyre subtre
            {
                for (p = p.høyre; p.venstre != null; p = p.venstre)
                {
                    stakk.leggInn(p);
                }
            }
            else if (!stakk.tom())
            {
                p = stakk.taUt();   // p.høyre == null, henter fra stakken
            }
            else break;          // stakken er tom - vi er ferdig

        } // while
    }

    private static <T> Node<T> random(int n, Random r)
    {
        if (n == 0) return null;                      // et tomt tre
        else if (n == 1) return new Node<>(null);     // tre med kun en node

        int k = r.nextInt(n);    // k velges tilfeldig fra [0,n>

        Node<T> venstre = random(k,r);     // tilfeldig tre med k noder
        Node<T> høyre = random(n-k-1,r);   // tilfeldig tre med n-k-1 noder

        return new Node<>(null,venstre,høyre);
    }

    public static <T> BinTre<T> random(int n)
    {
        if (n < 0) throw new IllegalArgumentException("Må ha n >= 0!");

        BinTre<T> tre = new BinTre<>();
        tre.antall = n;

        tre.rot = random(n,new Random());   // kaller den private metoden

        return tre;
    }

    public int antall() { return antall; }               // returnerer antallet

    public boolean tom() { return antall == 0; }         // tomt tre?

    private static int antallBladnoder(Node<?> p)
    {
        if (p.venstre == null && p.høyre == null) return 1;

        return (p.venstre == null ? 0 : antallBladnoder(p.venstre))
                + (p.høyre == null ? 0 : antallBladnoder(p.høyre));
    }

    public int antallBladnoder()
    {
        return rot == null ? 0 : antallBladnoder(rot);
    }

    public boolean erMintre(Comparator<? super T> c) // legges i BinTre
    {
        if (rot == null) return true;    // et tomt tre er et minimumstre
        else return erMintre(rot,c);     // kaller den private hjelpemetoden
    }

    private static <T> boolean erMintre(Node<T> p, Comparator<? super T> c)
    {
        if (p.venstre != null)
        {
            if (c.compare(p.venstre.verdi,p.verdi) < 0) return false;
            if (!erMintre(p.venstre,c)) return false;
        }
        if (p.høyre != null)
        {
            if (c.compare(p.høyre.verdi,p.verdi) < 0) return false;
            if (!erMintre(p.høyre,c)) return false;
        }
        return true;
    }

    public String minimumsgren(Comparator<? super T> c) {
        Node<T> p = rot;
        StringJoiner s = new StringJoiner(", ", "[", "]");

        while (p != null) {
            s.add(p.verdi.toString());

            if (p.høyre == null) {
                p = p.venstre;
            }
            else if (p.venstre == null) {
                p = p.høyre;
            }
            else {
                int cmp = c.compare(p.venstre.verdi, p.høyre.verdi);
                p = cmp <=0 ? p.venstre : p.høyre;
            }
        }
        return s.toString();
    }

    public Iterator<T> iterator()     // skal ligge i class BinTre
    {
        return new InordenIterator();
    }

    ////////////////// class InordenIterator //////////////////////////////

    private class InordenIterator implements Iterator<T>
    {
        private Stakk<Node<T>> s = new TabellStakk<>();
        private Node<T> p = null;

        private Node<T> først(Node<T> q)   // en hjelpemetode
        {
            while (q.venstre != null)        // starter i q
            {
                s.leggInn(q);                  // legger q på stakken
                q = q.venstre;                 // går videre mot venstre
            }
            return q;                        // q er lengst ned til venstre
        }

        private InordenIterator()          // konstruktør
        {
            if (tom()) return;               // treet er tomt
            p = først(rot);                  // bruker hjelpemetoden
        }

        @Override
        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");

            T verdi = p.verdi;                        // tar vare på verdien

            if (p.høyre != null) p = først(p.høyre);  // p har høyre subtre
            else if (s.tom()) p = null;               // stakken er tom
            else p = s.taUt();                        // tar fra stakken

            return verdi;                             // returnerer verdien
        }

        @Override
        public boolean hasNext()
        {
            return p != null;
        }

    } // InordenIterator

    private class OmvendtInordenIterator implements Iterator<T>
    {
        private final Stakk<Node<T>> s;    // hjelpestakk
        private Node<T> p;                 // hjelpepeker

        private Node<T> sist(Node<T> q)    // en hjelpemetode
        {
            while (q.høyre != null)          // starter i q
            {
                s.leggInn(q);                  // legger q på stakken
                q = q.høyre;                   // går videre mot høyre
            }
            return q;                        // q er lengst ned til høyre
        }

        public OmvendtInordenIterator()    // konstruktør
        {
            s = new TabellStakk<>();
            if (tom()) return;               // treet er tomt
            p = sist(rot);                   // bruker hjelpemetoden
        }

        @Override
        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException();

            T verdi = p.verdi;               // tar vare på verdien i noden p

            if (p.venstre != null)           // p har venstre subtre
                p = sist(p.venstre);
            else if (s.tom()) p = null;      // stakken er tom
            else  p = s.taUt();              // tar fra stakken

            return verdi;                    // returnerer verdien
        }

        @Override
        public boolean hasNext()
        {
            return p != null;
        }

    }  // OmvendtInordenIterator

    public Iterator<T> omvendtIterator()
    {
        return new OmvendtInordenIterator();
    }

    private class PreordenIterator implements Iterator<T>
    {
        private final Stakk<Node<T>> s;
        private Node<T> p;

        // konstruktør
        private PreordenIterator()
        {
            s = new TabellStakk<>();
            p = rot;
        }

        @Override
        public boolean hasNext()
        {
            return p != null;
        }

        @Override
        public T next()  // neste er med hensyn på preorden
        {
            if (!hasNext()) throw new NoSuchElementException();

            T verdi = p.verdi;

            if (p.venstre != null)                  // går til venstre
            {
                if (p.høyre != null) s.leggInn(p.høyre);
                p = p.venstre;
            }
            else if (p.høyre != null) p = p.høyre;  // går til høyre
            else if (s.tom()) p = null;             // ingen flere i treet
            else p = s.taUt();                      // tar fra satkken

            return verdi;
        }

    } // PreordenIterator

    public Iterator<T> preIterator()
    {
        return new PreordenIterator();
    }


} // class BinTre<T>
