import java.util.*;

public class Assignment_4 {
    public static int[][] buildDP(int[] arr, int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            int currentNum = arr[i - 1];
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= currentNum && dp[i - 1][j - currentNum] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - currentNum] + 1);
                }
            }
        }
        return dp;
    }

    public static List<Integer> getSubset(int[][] dp, int[] arr, int n, int k) {
        List<Integer> result = new ArrayList<>();
        if (dp[n][k] == -1) return result;

        int i = n, j = k;
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                result.add(arr[i - 1]);
                j -= arr[i - 1];
            }
            i--;
        }
        Collections.reverse(result);
        return result;
    }

    public static void solve(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = buildDP(arr, n, k);

        if (dp[n][k] == -1) {
            System.out.println("-1");
            return;
        }

        List<Integer> result = getSubset(dp, arr, n, k);

        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        solve(arr, k);
    }
}
