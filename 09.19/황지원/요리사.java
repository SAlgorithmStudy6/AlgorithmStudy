import java.io.*;
import java.util.*;

public class 요리사 {
    static int arr[][], val[][], N, min;
    static ArrayList<Integer> teamA, teamB;
    static boolean visit[];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/4012.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            init();

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    val[j][i] = val[i][j] = arr[i][j] + arr[j][i];
                }
            }
            // 딱 반씩 나누는 조합 만들기
            makeHalf(1, 1, N / 2);
            System.out.println("#" + tc + " " + min);

        }

    }

    static void makeHalf(int index, int depth, int r) {
        if (r == 0) {
            teamA.clear();
            teamB.clear();
            for (int i = 1; i < visit.length; i++) {
                if (visit[i])
                    teamA.add(i);
                else
                    teamB.add(i);
            }

            long Aresult = 0, Bresult = 0;

            for (int i = 1; i <= teamA.size(); i++) {
                for (int j = i + 1; j <= teamA.size(); j++) {
                    Aresult += val[teamA.get(i-1)][teamA.get(j-1)];
                }
            }
            for (int i = 1; i <= teamB.size(); i++) {
                for (int j = i + 1; j <= teamB.size(); j++) {
                    Bresult += val[teamB.get(i-1)][teamB.get(j-1)];
                }
            }
            min = Math.min(min, (int) Math.abs(Aresult - Bresult));

            return;
        }
        if (depth == N) {
            return;
        }
        visit[index] = true;
        makeHalf(index + 1, depth + 1, r - 1);
        visit[index] = false;
        makeHalf(index + 1, depth + 1, r);
    }

    static void init() {
        arr = new int[N + 1][N + 1];
        val = new int[N + 1][N + 1];
        visit = new boolean[N + 1];
        teamA = new ArrayList<>();
        teamB = new ArrayList<>();
        min = Integer.MAX_VALUE;
    }
}
