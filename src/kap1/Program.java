package kap1;
import hjelpeklasser.*;
import eksempelklasser.*;

public class Program {

    public static void main(String[] args) {
        Tid[] tider = new Tid[4];

        tider[0] = new Tid(24,12, 2014, "19:15");
        tider[1] = new Tid(24,12,2014,"12:00");
        tider[2] = new Tid(23,12,2014,"12:00");
        tider[3] = new Tid(23,12,2014,"09:00");

        Tabell.innsettingssortering(tider);
        for (Tid x : tider) System.out.print(x + "\n");

        // Utskrift: 00:00 07:00 09:09 22:56 23:59
    }
}
