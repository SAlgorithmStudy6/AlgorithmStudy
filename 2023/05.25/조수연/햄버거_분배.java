import java.util.*;
import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer token = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(token.nextToken());
        int K = Integer.parseInt(token.nextToken());

        String info = br.readLine();
        boolean[] ate = new boolean[N];
        int answer = 0;
        boolean isFind;

        for (int i = info.length() - 1; i >= 0; i--) {
            if (info.charAt(i) == 'P') {

                isFind = false;

                if (i + K > info.length() - 1) {
                    for (int j = info.length() - 1; j >= i + 1; j--) {
                        if (info.charAt(j) == 'H' && !ate[j]) {
                            ate[j] = true;
                            answer++;
                            isFind = true;
                            break;
                        }
                    }
                } else {
                    for (int j = i + K; j >= i + 1; j--) {
                        if (info.charAt(j) == 'H' && !ate[j]) {
                            ate[j] = true;
                            answer++;
                            isFind = true;
                            break;
                        }
                    }
                }

                if (isFind) continue;

                if (i - K < 0) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (info.charAt(j) == 'H' && !ate[j]) {
                            ate[j] = true;
                            answer++;
                            break;
                        }
                    }
                } else {
                    for (int j = i - 1; j >= i - K; j--) {
                        if (info.charAt(j) == 'H' && !ate[j]) {
                            ate[j] = true;
                            answer++;
                            break;
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
    }
}