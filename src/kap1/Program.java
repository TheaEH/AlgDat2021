package kap1;
import hjelpeklasser.*;
import eksempelklasser.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;

public class Program {

    public static void main(String[] args) {
        int[] posisjon = {1,2,3,4,5,6,7,10,11,13,14,22,23,28,29};
        Integer[] verdi = {1,3,5,7,6,8,11,12,10,10,15,14,18,15,20};

        BinTre<Integer> tre = new BinTre<>(posisjon, verdi);  // Bruker en konstruktør

        Comparator<Integer> c = Comparator.naturalOrder();
        System.out.println(tre.erMintre(c));  // Utskrift: true
        System.out.println(tre.minimumsgren(Comparator.naturalOrder()));
    }
}
