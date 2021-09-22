package kap1;
import hjelpeklasser.*;
import eksempelklasser.*;

import java.util.Arrays;
import java.util.Comparator;

public class Program {

    public static void main(String[] args) {
        int[] a = Tabell.randPerm(10);

        Tabell.kvikksortering(a, 0, a.length);

        System.out.println(Arrays.toString(a));
    }
}
