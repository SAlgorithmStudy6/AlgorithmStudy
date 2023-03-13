import java.io.*;
import java.util.*;

public class Main {

    static int N, X, Y, D, G;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static ArrayList<P> set = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken()) + 1;
            G = Integer.parseInt(st.nextToken());

            for (int k = 0; k <= G; k++) {
                list.add(new ArrayList<>());
            }

            draw();
        }

        int ans = 0;
        for (P p : set) {
            if ((rangeCheck(p.x, p.y) && rangeCheck(p.x, p.y + 1) && rangeCheck(p.x + 1, p.y) && rangeCheck(p.x + 1, p.y + 1))) {
                if (set.contains(new P(p.x, p.y + 1)) && 
                    set.contains(new P(p.x + 1, p.y)) && 
                    set.contains(new P(p.x + 1, p.y + 1))) {
                    ++ans;
                }
            }
        }

        System.out.println(ans);
    }

    static void draw() {
        // 1.list에 방향을 넣고
        // 2. 그 방향대로 이동한 후 끝점을 X, Y에 넣고
        // 3. set에 방문한 점을 넣어준다.
        if (!set.contains(new P(X, Y)))
            set.add(new P(X, Y)); 
        list.get(0).add(D); // 세대 방향
        moveXY(D); // XY이동
        if (!set.contains(new P(X, Y)))
            set.add(new P(X, Y)); // 방문 체크
        
        if (1 <= G) {
            list.get(1).add(D % 4 + 1); // 1세대 방향
            moveXY(D % 4 + 1); // XY이동
            if (!set.contains(new P(X, Y)))
                set.add(new P(X, Y)); // 방문 체크
        }

        for (int i = 2; i <= G; i++) {
            // 2세대 뒤에서부터
            for (int a = i - 1; a >= 0; a--) { // 1, 0
                for (int b = list.get(a).size() - 1; b >= 0; b--) {
                    int d = list.get(a).get(b) % 4 + 1;
                    list.get(i).add(d);
                    moveXY(d);
                    if (!set.contains(new P(X, Y)))
                        set.add(new P(X, Y)); // 방문 체크
                }
            }
        }
    }

    static void moveXY(int d) {
        if (d == 1) {
            X += 1;
        } else if (d == 2) {
            Y -= 1;
        } else if (d == 3) {
            X -= 1;
        } else if (d == 4) {
            Y += 1;
        }
    }

    static boolean rangeCheck(int x, int y) {
        return 0 <= x && x <= 100 && 0 <= y && y <= 100;
    }

    static class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        
        // 이거 없으면 객체 값 비교 안 됨 = contains 안됨
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            P p = (P) o;
            return x == p.x && y == p.y;
        }
    }
}
