package kap1;
import hjelpeklasser.*;

import java.time.chrono.ThaiBuddhistChronology;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) {

        int[] a = Tabell.randPerm(200_000);

        long tid2 = System.currentTimeMillis();
        Tabell.innsettingssortering(a);
        tid2 = System.currentTimeMillis() - tid2;

        System.out.println("Innsettingssortering: " + tid2);
        System.out.println(Tabell.erSortert(a));
    }
}
