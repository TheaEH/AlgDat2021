package kap1;
import hjelpeklasser.*;

import java.util.Arrays;

public class Program {

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        int[] b = new int[a.length*2-1];
        /*
        int[] a = Tabell.randPerm(20);              // en tilfeldig tabell
        int[] b = Tabell.nestMaks(a);
        int m = b[0], nm = b[1];

        Tabell.skrivln(a);
        System.out.print("Størst(" + a[m] + ") har posisjon " + m);
        System.out.println(", nest størst(" + a[nm] + ") har posisjon " + nm);

        Tabell.sortering(a);
        Tabell.skrivln(a);
         */


        //int[] t = Tabell.nestMaksTournament(a);
        //System.out.print("Vinner: " + t[0] + ", 2.plass:  " + t[1]);


        Tabell.kopier(a,0,b,0,a.length);    // Setter a forrest i b
        System.out.println(Arrays.toString(b));

        Arrays.fill(b,0);   // "tømmer" b

        Tabell.kopier(a,0,b,b.length-a.length,a.length);    // Setter a bakerst i b
        System.out.println(Arrays.toString(b));

        Arrays.fill(b,0);   // "tømmer" b

        Tabell.kopier(a,0,b,(b.length-a.length)/2, a.length);
        System.out.println(Arrays.toString(b));

    }
}
