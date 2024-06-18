

public class Heads {
    public static void main(String[] args) {
        head(5);
    }

    public static void head(int N) {
        if (N == 1) {
            return;
        }
        head(N - 1);
        System.out.println(N);
    }
}