package kap1;
import hjelpeklasser.*;
import eksempelklasser.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;

public class Program {

    public static void main(String[] args) {
        int[] a = {7, 6, 5, 4, 3, 2, 1};  // verdiene i Figur 5.3.2 a)
        PrioritetsKø<Integer> kø = HeapPrioritetsKø.naturligOrden();
        for (int k : a) kø.leggInn(k);

        kø.taUt();
        kø.taUt();
        kø.taUt();
        kø.taUt();

        System.out.println(kø);
    }
}
