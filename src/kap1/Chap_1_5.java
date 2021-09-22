package kap1;

public class Chap_1_5 {
    public static void main(String[] args) {
        int n = 20666;
        System.out.println(sum(1,n));  // metoden fra Programkode 1.5.1 g)
    }

    // Finner an i en rekke, der a(n) = 2a(n-1) + 3a(n-2)
    // a0 = 1 og a1 = 2
    public static int a(int n) {
        int l1 = 0;
        int l2 = 1;
        int ut = 1;
        for (int i = 0; i < n; i++) {
            ut = 2*l2 + 3*l1;
            l1 = l2;
            l2 = ut;
        }
        return ut;
    }

    public static int tverrsum(int n)              // n må være >= 0
    {
        int sum = 0;

        while (n > 0) {
            sum += n%10;
            n = n/10;
        }
        return sum;
    }

    public static int sifferrot(int n) {
        while (n>9) {
            n = tverrsum(n);
        }
        return n;
    }

    public static int kvadratsum(int n) {
        if (n == 1) {
            return 1;
        }
        else return n*n + kvadratsum(n-1);
    }

    public static int sum(int k, int i) {
        if (k == i) {
            return k;
        }
        int midt = (k+i)/2;
        return sum(k,midt) + sum(midt+1, i);
    }

    public static int maks(int[] a, int left, int right) {
        if (left == right) {
            return left;
        }

        int midt = (left+right)/2;
        int maxleft = maks(a, left, midt);
        int maxright = maks(a, midt+1, right);

        return a[maxleft] > a[maxright] ? maxleft : maxright;
    }

    public static int maks(int[] a, int n) {
        return maks(a, 0, n-1);
    }

    public static int fakultet(int n) {
        if (n == 1) {
            return 1;
        }
        return n*fakultet(n-1);
    }

    public static int fib(int n)         // det n-te Fibonacci-tallet
    {
        if (n <= 1) return n;              // fib(0) = 0, fib(1) = 1
        else return fib(n-1) + fib(n-2);   // summen av de to foregående
    }

    public static int euklid(int a, int b)
    {
        System.out.println("euklid "+ a + "," + b +" starter");
        if (b == 0) {
            System.out.println("euklid "+ a + "," + b +" er ferdig");
            return a;
        }
        int r = a % b;            // r er resten
        int k = euklid(b,r);
        System.out.println("euklid "+ a + "," + b +" er ferdig");
        return k;       // rekursivt kall
    }
}
