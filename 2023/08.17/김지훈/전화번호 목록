

import java.io.*;
import java.util.*;


class Main{


    static String[] str;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            boolean flag = true;
            n = Integer.parseInt(br.readLine());
            str = new String[n];
            for (int i = 0; i < n; i++) {
                str[i] = br.readLine().trim();
            }
            Arrays.sort(str);
            for (int i = 0; i < n - 1; i++) {
                if (str[i + 1].startsWith(str[i])) {
                    flag = !flag;
                    break;
                }
            }
            System.out.println(flag ? "YES" : "NO");

        }
    }

}
