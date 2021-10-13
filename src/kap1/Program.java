package kap1;
import hjelpeklasser.*;
import eksempelklasser.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class Program {

    public static void main(String[] args) {
        Integer[] a = Tabell.randPermInteger(10);

        Kø<Integer> kø = new EnkeltLenketListe<>();
        for (Integer i : a) kø.leggInn(i);

        System.out.println(kø);    // usortert

        Stakk<Integer> stakk = new TabellStakk<>();

        EnkeltLenketListe.sorter(kø, stakk, Comparator.naturalOrder());

        System.out.println(kø);    // sortert

    }
}
