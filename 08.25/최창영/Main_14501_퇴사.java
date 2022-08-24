import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14501
public class Main_14501_퇴사 {
    static int[][] arr;
    static int N;
    static int result = -1;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/14501.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        quit(0, 0);
        System.out.println(result);
    } // End of main

    private static void quit(int day, int totalPrice) {
        if (day == N) {
            result = Math.max(result, totalPrice);
            return;
        } else if (day > N) {
            return;
        }

        quit(day + 1, totalPrice); // depth는 날짜를 의미하는데, 해당 날짜에 상담을 하지 않고, 넘기는 경우의 수
        quit(day + arr[day][0], totalPrice + arr[day][1]); // 해당 날짜에 상담을 진행하는 경우의 수
    } // End of DP
} // End of Main class