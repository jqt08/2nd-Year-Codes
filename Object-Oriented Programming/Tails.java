
public class Tails {
    public static void main(String[] args) {
        tails(5);
    }

    public static void tails(int N) {
        if (N == 1) {
            return;
        }
        System.out.println(N);
        tails(N - 1);
    }
}