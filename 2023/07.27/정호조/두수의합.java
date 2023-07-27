import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 두수의합_9024 {
    static int N, K, ans;
    static int[] num;
    static Map<Integer, Integer> check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ans = 0;
            num = new int[N];
            check = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(num);

            int start = 0;
            int end = N - 1;
            int dif = Integer.MAX_VALUE;
            while (start < end){
                int sum = num[start] + num[end];
                int curDif = Math.abs(K - sum);
                if(dif >= curDif){
                    if(dif > curDif) ans = 0;
                    dif = curDif;
                    ans++;
                }
                if(sum >= K){
                    end--;
                } else {
                    start++;
                }
            }

            System.out.println(ans);

            /**시간 초과*/
//            int dif = Integer.MAX_VALUE;
//
//            for (int i = 0; i < num.length; i++) {
////                System.out.println(Arrays.toString(Arrays.stream(num).toArray()));
////                System.out.println("i : " + i);
//                int idx = Arrays.binarySearch(num, K - num[i]);
//                if (idx < 0) {
//                    idx = -idx - 1;
//                }
//                if (idx >= num.length) {
//                    idx = num.length - 1;
//                }
//                if (idx > 0 && Math.abs(K - (num[idx] + num[i])) > Math.abs(K - (num[idx - 1] + num[i]))) {
//                    idx = idx - 1;
//                }
//
//                if (i == idx) {
////                    System.out.println("i == idx");
//                    continue;
//                }
//                if (check.containsKey(num[idx]) && check.containsValue(num[i])) continue;
//
////                System.out.println("num[i] " + num[i] + " idx : " + idx + ", num[idx] : " + num[idx] + " , 합 : " + (num[i] + num[idx]));
//                int curDif = Math.abs(K - (num[i] + num[idx]));
////                System.out.println("curDif : " + curDif);
//
//
//                if (dif > curDif) {
////                    System.out.println("dif 초기화, ans == 1");
//                    dif = curDif;
//                    ans = 0;
//                    ans++;
//                    check.put(num[i], num[idx]);
//                } else if (dif == curDif) {
//                    ans++;
////                    System.out.println("ans ++ : " + ans);
//                    check.put(num[i], num[idx]);
//                }
////                System.out.println();
//            }
//
//            System.out.println(ans);
        }
    }
}
