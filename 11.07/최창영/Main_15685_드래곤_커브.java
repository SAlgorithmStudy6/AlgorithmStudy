import java.io.*;
import java.util.*;


public class Main_15685_드래곤_커브 {
    static int N;
    static boolean[][] map = new boolean[101][101];
    static int[] dirX = {1, 0, -1, 0};
    static int[] dirY = {0, -1, 0, 1};
    // 0 우 1 : 상 2 : 좌 3 : 하 : 4

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/15685.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int result = 0;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 커브의 시작 점 x
            int y = Integer.parseInt(st.nextToken()); // 커브의 시작 점 y
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            int g = Integer.parseInt(st.nextToken()); // 세대

            solution(x, y, d, g);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    result++;
                }
            }
        }

        System.out.println(result);
    } // End of main

    private static void solution(int x, int y, int d, int g) {
        List<Integer> list = new ArrayList<>();
        list.add(d);

        int size = list.size();
        for (int i = 1; i <= g; i++) {
            for (int j = size - 1; j >= 0; j--) {
                list.add((list.get(j) + 1) % 4);
            }
        }

        map[y][x] = true;
        for (int i : list) {
            x += dirX[i];
            y += dirY[i];
            map[y][x] = true;
        }

    } // End of solution
} // End of Main class
