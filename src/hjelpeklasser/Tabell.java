package hjelpeklasser;

import java.util.*;

@SuppressWarnings("DuplicatedCode")
public class Tabell {   // Samleklasse for tabellmetoder

    private Tabell() {} //privat standardkonstruktør - hindrer instansiering

    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static int[] randPerm(int n)  // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall

        Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

        for (int k = n - 1; k > 0; k--)  // løkke som går n - 1 ganger
        {
            int i = r.nextInt(k+1);        // en tilfeldig tall fra 0 til k
            bytt(a,k,i);                   // bytter om
        }

        return a;                        // permutasjonen returneres
    }

    public static void randPerm(int[] a)  // stokker om a
    {
        Random r = new Random();     // en randomgenerator

        for (int k = a.length - 1; k > 0; k--)
        {
            int i = r.nextInt(k + 1);  // tilfeldig tall fra [0,k]
            bytt(a,k,i);
        }
    }

    public static int maks(int[] a, int fra, int til)
    {
        if (a == null)
            throw new NullPointerException("tom tabell");

        fratilKontroll(a.length, fra, til);
        if (fra == til)
            throw new NoSuchElementException
                    ("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

        int m = fra;              // indeks til største verdi i a[fra:til>
        int maksverdi = a[fra];   // største verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++)
        {
            if (a[i] > maksverdi)
            {
                m = i;                // indeks til største verdi oppdateres
                maksverdi = a[m];     // største verdi oppdateres
            }
        }

        return m;  // posisjonen til største verdi i a[fra:til>
    }

    public static int maks(int[] a)  // bruker hele tabellen
    {
        return maks(a,0,a.length);     // kaller metoden over
    }


    public static int min(int[] a, int fra, int til)
    {
        if (a == null)
            throw new NullPointerException("tom tabell");

        fratilKontroll(a.length, fra, til);
        if (fra == til)
            throw new NoSuchElementException
                    ("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");


        int m = fra;              // indeks til minste verdi i a[fra:til>
        int minverdi = a[fra];   // minste verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++)
        {
            if (a[i] < minverdi)
            {
                m = i;                // indeks til største verdi oppdateres
                minverdi = a[m];     // største verdi oppdateres
            }
        }
        return m;  // posisjonen til største verdi i a[fra:til>
    }

    public static int min(int[] a)  // bruker hele tabellen
    {
        return min(a,0,a.length);     // kaller metoden over
    }

    public static void bytt(char[] c, int i, int j) {
        char temp = c[i]; c[i] = c[j]; c[j] = temp;
    }

    public static void skriv(int[] a, int fra, int til) {
        fratilKontroll(a.length, fra, til);
        if (til-fra > 0) {
            System.out.print(a[fra]);

            for (int i = fra+1; i<til; i++) {
                System.out.print(" " + a[i]);
            }
        }
    }

    public static void skriv(int[] a) {
        skriv(a, 0, a.length);
    }

    public static void skrivln(int[] a, int fra, int til) {
        skriv(a, fra , til);
        System.out.println();
    }

    public static void skrivln(int[] a) {
        skrivln(a, 0, a.length);
    }

    public static void skriv(char[] c, int fra, int til) {
        fratilKontroll(c.length, fra, til);
        if (til-fra > 0) {
            System.out.print(c[fra]);

            for (int i = fra+1; i<til; i++) {
                System.out.print(" " + c[i]);
            }
        }
    }

    public static void skriv(char[] c) {
        skriv(c, 0, c.length);
    }

    public static void skrivln(char[] c, int fra, int til) {
        skriv(c, fra , til);
        System.out.println();
    }

    public static void skrivln(char[] c) {
        skrivln(c, 0, c.length);
    }

    public static void fratilKontroll(int tablengde, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new ArrayIndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public static void vhKontroll(int tablengde, int v, int h)
    {
        if (v < 0)
            throw new ArrayIndexOutOfBoundsException("v(" + v + ") < 0");

        if (h >= tablengde)
            throw new ArrayIndexOutOfBoundsException
                    ("h(" + h + ") >= tablengde(" + tablengde + ")");

        if (v > h + 1)
            throw new IllegalArgumentException
                    ("v = " + v + ", h = " + h);
    }

    public static int[] nestMaks(int[] a) // ny versjon
    {
        int n = a.length;     // tabellens lengde
        if (n < 2) throw      // må ha minst to verdier
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = 0;      // m er posisjonen til største verdi
        int nm = 1;     // nm er posisjonen til nest største verdi

        // bytter om m og nm hvis a[1] er større enn a[0]
        if (a[1] > a[0]) { m = 1; nm = 0; }

        int maksverdi = a[m];                // største verdi
        int nestmaksverdi = a[nm];           // nest største verdi

        for (int i = 2; i < n; i++)
        {
            if (a[i] > nestmaksverdi)
            {
                if (a[i] > maksverdi)
                {
                    nm = m;
                    nestmaksverdi = maksverdi;     // ny nest størst

                    m = i;
                    maksverdi = a[m];              // ny størst
                }
                else
                {
                    nm = i;
                    nestmaksverdi = a[nm];         // ny nest størst
                }
            }
        } // for

        return new int[] {m,nm};    // n i posisjon 0, nm i posisjon 1

    } // nestMaks

    public static void sortering(int[] a)  // legges i class Tabell
    {
        for (int i = a.length; i>1; i--) {
            int m = maks(a, 0, i);
            bytt(a,m,i-1);
        }

    } // Sortering

    public static int[] nestMaksTournament(int[] a)   // en turnering
    {
        int n = a.length;                // for å forenkle notasjonen

        if (n < 2) // må ha minst to verdier!
            throw new IllegalArgumentException("a.length(" + n + ") < 2!");

        int[] b = new int[2*n];          // turneringstreet
        System.arraycopy(a,0,b,n,n);     // legger a bakerst i b

        for (int k = 2*n-2; k > 1; k -= 2)   // lager turneringstreet
            b[k/2] = Math.min(b[k],b[k+1]);

        int maksverdi = b[1], nestmaksverdi = Integer.MIN_VALUE;

        for (int m = 2*n - 1, k = 2; k < m; k *= 2)
        {
            int tempverdi = b[k+1];  // ok hvis maksverdi er b[k]
            if (maksverdi != b[k]) { tempverdi = b[k]; k++; }
            if (tempverdi > nestmaksverdi) nestmaksverdi = tempverdi;
        }

        return new int[] {maksverdi,nestmaksverdi}; // størst og nest størst

    } // nestMaks

    public static void kopier(int[] a, int i, int[] b, int j, int ant) {
        for (int k = i; k < ant+i;) {
            b[j++] = a[k++];
        }
    }

    public static void snu(int[] a, int v, int h)  // snur intervallet a[v:h]
    {
        while (v < h) bytt(a, v++, h--);
    }

    public static void snu(int[] a, int v)  // snur fra og med v og ut tabellen
    {
        snu(a, v, a.length - 1);
    }

    public static void snu(int[] a)  // snur hele tabellen
    {
        snu(a, 0, a.length - 1);
    }

    public static boolean nestePermutasjon(int[] a)
    {
        int i = a.length - 2;                    // i starter nest bakerst
        while (i >= 0 && a[i] > a[i + 1]) i--;   // går mot venstre
        if (i < 0) return false;                 // a = {n, n-1, . . . , 2, 1}

        int j = a.length - 1;                    // j starter bakerst
        while (a[j] < a[i]) j--;                 // stopper når a[j] > a[i]
        bytt(a,i,j); snu(a,i + 1);               // bytter og snur

        return true;                             // en ny permutasjon
    }

    public static int inversjoner(int[] a)
    {
        int antall = 0;  // antall inversjoner
        for (int i = 0; i < a.length - 1; i++)
        {
            for (int j = i + 1; j < a.length; j++)
            {
                if (a[i] > a[j]) antall++;  // en inversjon siden i < j
            }
        }
        return antall;
    }

    public static boolean erSortert(int[] a)  // legges i samleklassen Tabell
    {
        for (int i = 1; i < a.length; i++)      // starter med i = 1
            if (a[i-1] > a[i]) return false;      // en inversjon

        return true;
    }

    public static void utvalgssortering(int[] a)
    {
        for (int i = 0; i < a.length - 1; i++) {
            bytt(a, i, min(a, i, a.length));  // to hjelpemetoder
        }
    }

    public static void utvalgssortering(int[] a, int fra, int til)
    {
        fratilKontroll(a.length, fra, til);
        for (int i = fra; i < til-1; i++) {
            bytt(a, i, min(a, i, til));  // to hjelpemetoder
        }
    }

    public static void selectionSort(int[] a) {
        for (int i= 0; i<a.length-1; i++) { // Tallet som skal sorteres
            int min = a[i];
            int minIndeks = i;
            for (int j = i+1; j<a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    minIndeks = j;
                }
            }
            a[minIndeks] = a[i]; a[i] = min;    // bytter plass på a[i] og den minste vi fant
        }
    }

    public static int linearsok(int[] a, int verdi) // legges i class Tabell
    {
        if (a.length == 0 || verdi > a[a.length-1])
            return -(a.length + 1);  // verdi er større enn den største

        int i = 0; for( ; a[i] < verdi; i++);  // siste verdi er vaktpost

        return verdi == a[i] ? i : -(i + 1);   // sjekker innholdet i a[i]
    }

    public static int linearsok(int[] a, int k, int verdi)
    {
        if (k < 1)
            throw new IllegalArgumentException("Må ha k > 0!");

        int j = k - 1;
        for (; j < a.length && verdi > a[j]; j += k);

        int i = j - k + 1;  // søker i a[j-k+1:j]
        for (; i < a.length && verdi > a[i]; i++);

        if (i < a.length && a[i] == verdi) return i;  // funnet
        else return -(i + 1);
    }

    public static int kvadratrotsok(int[] a, int verdi)
    {
        return linearsok(a, (int) Math.sqrt(a.length), verdi);
    }

    // 3. versjon av binærsøk - returverdier som for Programkode 1.3.6 a)
    public static int binarysearch(int[] a, int fra, int til, int verdi)
    {
        Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;  // v og h er intervallets endepunkter

        while (v < h)  // obs. må ha v < h her og ikke v <= h
        {
            int m = (v + h)/2;  // heltallsdivisjon - finner midten

            if (verdi > a[m]) v = m + 1;   // verdi må ligge i a[m+1:h]
            else  h = m;                   // verdi må ligge i a[v:m]
        }
        if (h < v || verdi < a[v]) return -(v + 1);  // ikke funnet
        else if (verdi == a[v]) return v;            // funnet
        else  return -(v + 2);                       // ikke funnet
    }

    public static void innsettingssortering(int[] a, int fra, int til)
    {
        fratilKontroll(a.length, fra, til);
        for (int i = fra+1; i < til; i++)  // starter med i = 1
        {
            int temp = a[i];  // hjelpevariabel
            for (int j = i - 1; j >= fra && temp < a[j]; j--) Tabell.bytt(a, j, j + 1);
        }
    }

    public static void innsettingssortering(int[] a)
    {
        innsettingssortering(a, 0, a.length);
    }

    public static int binarysearch(int[] a, int verdi)  // søker i hele a
    {
        return binarysearch(a,0,a.length,verdi);  // bruker metoden over
    }

    public static void shell(int[] a, int k)
    {
        for (int i = k; i < a.length; i++)
        {
            int temp = a[i], j = i - k;
            for ( ; j >= 0 && temp < a[j]; j -= k) a[j + k] = a[j];
            a[j + k] = temp;
        }
    }

}
