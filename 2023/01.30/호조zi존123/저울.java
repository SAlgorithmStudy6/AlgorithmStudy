package baekjoon;

import java.util.*;
import java.io.*;

public class 저울_2437 {
	static int n, ans;
	static int[] weight;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        weight = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weight);

        int sum = 0;
        for (int i=0; i<n; i++) {
            if (sum + 1 < weight[i]) {
                break;
            }

            sum += weight[i];
        }
        
        ans = sum +1;

        System.out.println(ans);
    }
}
