public class ThreeSum {
    public static int count(int[] a) {
        int N = a.length;
        int count = 0;

        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                        System.out.println(count + " A[i] = " + a[i] + ", A[j] = " + a[j] + ", A[k] = " + a[k] +
                                " (Sum =  " + (a[i] + a[j] + a[k]) + ")");
                    }

        return count;
    }

    public static void main(String[] args) {
        int[] a = { -1, -2, 3, 4, -3, 1, 2, 0, -1 };
        int totalCount = count(a);
    }
}
