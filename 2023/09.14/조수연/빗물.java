import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] block = new int[w];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < w; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < w - 1; i++) {
            int left = 0;
            int right = 0;

            for (int j = 0; j < i; j++) {
                left = Math.max(left, block[j]);
            }

            for (int j = i + 1; j < w; j++) {
                right = Math.max(right, block[j]);
            }

            if (block[i] < left && block[i] < right) answer += Math.min(left, right) - block[i];
        }

        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
    }
}