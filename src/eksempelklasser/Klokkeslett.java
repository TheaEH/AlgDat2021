package eksempelklasser;

import java.util.Objects;

public class Klokkeslett implements Comparable<Klokkeslett> {
    private int timer;
    private int minutter;

    public Klokkeslett(String s) {
        if (s.length()!=5 || s.charAt(2) != ':') {
            throw new IllegalArgumentException("Klokkeslett må ha former tt:mm");
        }

        timer = Integer.parseInt(s.substring(0,2));

        if (timer > 23 || timer < 0) {
            throw new IllegalArgumentException("Timetallet " + timer + " er ulovlig!");
        }
        minutter = Integer.parseInt(s.substring(3,5));

        if (minutter > 59 || minutter < 0) {
            throw new IllegalArgumentException("Minutt-tallet " + minutter + " er ulovlig!");
        }
    }

    public int compareTo(Klokkeslett k) {
        if (timer < k.timer) return -1;
        else if (timer > k.timer) return 1;
        else return minutter - k.minutter;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Klokkeslett)) return false;
        Klokkeslett k = (Klokkeslett) o;
        return timer == k.timer && minutter == k.minutter;
    }

    public boolean equals(Klokkeslett k) {
        return timer == k.timer && minutter == k.minutter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timer, minutter);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (timer < 10) s.append("0");
        s.append(timer);
        s.append(":");
        if (minutter < 10) s.append("0");
        s.append(minutter);
        return s.toString();
    }
}
