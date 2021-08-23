package kap1;

import java.text.DecimalFormat;
import java.util.*;

public class Kap1 {
    public static void main(String[] args){
        /*
        //int[] tabell = {8,3,5,7,9,6,10,2,10,4};
        int[] t2 = new int[10];
        /*
        int[] minmaks = minmaks(tabell);
        System.out.println("minste har indeks " + minmaks[0] + " og største tall har indeks " + minmaks[1]);

        //System.out.println(maks2(tabell));
        //System.out.println(harmonisk(3));
        //DecimalFormat toSifre = new DecimalFormat( "#.#");
        //System.out.println(toSifre.format(antOkning(1000)));
        //makstest();
        int[] a = {8,3,5,7,9,6,10,2,1,4}, b = {};  // to tabeller

        OptionalInt m = maks3(a);
        OptionalInt m2 = maks3(b);

        if (m.isPresent()) System.out.println(m.getAsInt());
        if (m2.isPresent()) System.out.println(m2.getAsInt());

        //System.out.println(Arrays.toString(randPerm(10)));
        //randPerm(10);
        Arrays.setAll(t2, i ->i+1);
        */

        /*
        int[] a = {6,8,1,4,2,10,7,9,3,5};
        int[] b = randPerm(10);
        randPerm(a);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
         */

        /*
        int n = 10000;
        long tid = System.currentTimeMillis();
        int[] a = randPerm(n);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid);
         */

        /*
        System.out.println(Arrays.toString(randPermV(10)));
        System.out.println(Arrays.toString(randPermH(10)));
        */

        /*
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i+1;
        }
        randPerm(a,1,8);
        System.out.println(Arrays.toString(a));
        */

        System.out.println(Arrays.toString(heltall(10)));
    }

    /*
    public static int[] minmaks(int[] a) {
        // 2(n-1) sammenlikninger
        int m1 = 0;
        int m2 = 0;
        int min = a[0];
        int maks = a[0];

        for (int i = 1; i<a.length; i++){
            if (a[i] > maks) {
                maks = a[i];
                m2 = i;
            }
            if (a[i] < min) {
                min = a[i];
                m1 = i;
            }
        }

        return new int[]{m1, m2};
    }

    public static long fak(int n) {
        int ut = 1;
        while (n > 1) {
            ut *= n;
            n--;
        }
        return ut;
    }

    public static int maks(int[] a)  // a er en heltallstabell
    {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("Tabellen a er tom!");

        int m = 0;  // indeks til foreløpig største verdi (m for maks)

        for (int i = 1; i < a.length; i++) // obs: starter med i = 1
        {
            if (a[i] > a[m]) m = i;  // indeksen oppdateres
        }

        return m;  // returnerer indeksen/posisjonen til største verdi

    } // maks

    public static int maks1(int[] a)   // versjon 2 av maks-metoden
    {
        if (a.length < 1) {
            throw new java.util.NoSuchElementException("Tabellen a er tom!");
        }
        int m = 0;               // indeks til største verdi
        int maksverdi = a[0];    // største verdi

        for (int i = 1; i < a.length; i++) if (a[i] > maksverdi)
        {
            maksverdi = a[i];     // største verdi oppdateres
            m = i;                // indeks til største verdi oppdateres
        }
        return m;   // returnerer indeks/posisjonen til største verdi

    } // maks

    public static int maks2(int[] a)  // versjon 3 av maks-metoden
    {
        if (a.length < 1) {
            throw new java.util.NoSuchElementException("Tabellen a er tom!");
        }
        int sist = a.length - 1;       // siste posisjon i tabellen
        int m = 0;                     // indeks til største verdi
        int maksverdi = a[0];          // største verdi
        int temp = a[sist];            // tar vare på siste verdi
        a[sist] = 0x7fffffff;          // legger tallet 2147483647 sist

        for (int i = 0; ; i++)         // i starter med 0
            if (a[i] > maksverdi)       // denne blir sann til slutt
            {
                if (i == sist)             // sjekker om vi er ferdige
                {
                    a[sist] = temp;          // legger siste verdi tilbake
                    return temp > maksverdi ? sist : m;   // er siste størst?
                }
                else
                {
                    maksverdi = a[i];        // maksverdi oppdateres
                    m = i;                   // m oppdateres
                }
            }
    } // maks

    public static OptionalInt maks3(int[] a)           // indeks til største verdi
    {
        if (a.length < 1) return OptionalInt.empty();   // en konstruksjonsmetode

        int m = 0, maksverdi = a[0];                    // startindeks og verdi

        for (int i = 1; i < a.length; i++)              // starter med i = 1
        {
            if (a[i] > maksverdi)
            {
                m = i; maksverdi = a[i];                    // oppdaterer
            }
        }

        return OptionalInt.of(m);                       // en konstruksjonsmetode
    }

    public static void makstest()
    {
        int[] a = {8,3,5,7,9,6,10,2,10,4};  // maksverdien 10 er i posisjon 6

        if (maks2(a) != 6)  // kaller maks-metoden
            System.out.println("Kodefeil: Gir feil posisjon for maksverdien!");

        a = new int[0];  // en tom tabell, lengde lik 0
        boolean unntak = false;

        try
        {
            maks2(a);  // kaller maks-metoden
        }
        catch (Exception e)
        {
            unntak = true;
            if (!(e instanceof java.util.NoSuchElementException))
                System.out.println("Kodefeil: Feil unntak for en tom tabell!");
        }

        if (!unntak)
            System.out.println("Kodefeil: Mangler unntak for en tom tabell!");
    }

    public static double harmonisk(int n) {
        double teller = 1;
        double hn = 0;
        while (teller <= n) {
            hn += 1/teller;
            teller++;
        }
        return hn;
    }

    public static double antOkning(int n) {
        double ut = Math.log(n);
        ut-=0.423;
        return ut;
    }

     */

    public static int[] randPerm1(int n)  // virker, men er ineffektiv
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall

        for (int i = 0; i < n; )         // vi skal legge inn n tall
        {
            int k = r.nextInt(n);          // trekker en indeks k
            if (a[k] == 0) {
                a[k] = i+1;
                i++;
            }
        }
        return a;                        // tabellen returneres
    }


    public static void bytt(int[] a, int i, int j)
    {
        int temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static int[] randPermH(int n)  // en effektiv versjon
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

    public static int[] randPermV(int n)  // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall

        Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

        for (int k = 0; k < n-1; k++)  // løkke som går n - 1 ganger
        {
            int i = (n-1)-r.nextInt(n-k);        // et tilfeldig tall mellom n-1 og k
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

    // Stokker om tallene i et array a mellom indeks v og h
    public static void randPerm(int[] a, int v, int h){
        Random r = new Random();

        for (int k = v; k < h; k++){
            int i = h-r.nextInt(h-v+1);
            bytt(a,k,i);
        }
    }

    public static int[] heltall(int n){
        ArrayList<Integer> a = new ArrayList<Integer>();
        int[] a2 = new int[n];
        for (int i = 1; i <= n; i++){
            a.add(i);
        }
        Collections.shuffle(a);
        for (int i = 0; i<a.size(); i++){
            a2[i] = a.get(i);
        }
        return a2;
    }


}
