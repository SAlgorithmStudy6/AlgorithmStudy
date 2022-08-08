import java.util.*;

public class Main {

    static int N, M, A, B, X, Y;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static int family[];
    
    public static void main(String[] args) {
        // 2644 촌수계산
        Scanner sc = new Scanner(System.in);
        
        // 전체 사람의 수
        N = sc.nextInt(); sc.nextLine();
        
        family = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        String s[] = sc.nextLine().split(" ");
        A = Integer.parseInt(s[0]); // 구해야하는 사람 1
        B = Integer.parseInt(s[1]); // 구해야하는 사람 2

        // 부모관계 개수
        M = sc.nextInt(); sc.nextLine();
        
        for (int i = 0; i < M; i++) {
            s = sc.nextLine().split(" ");
            X = Integer.parseInt(s[0]); // 부모
            Y = Integer.parseInt(s[1]); // 자식
            map.get(X).add(Y);
            map.get(Y).add(X);
        }
        bfs();
    }

    static void bfs() {

        Queue<Integer> q = new LinkedList<>();

        q.add(A);
        family[A] = 1;

        while (!q.isEmpty()) {
            int n = q.poll();

            if (n == B) {
                System.out.println(family[B] - 1);
                return;
            }
            for (int i = 0; i < map.get(n).size(); i++) {
                int tmp = map.get(n).get(i);
          
                if(family[tmp] == 0) {
                    q.add(tmp);
                    family[tmp] = family[n] + 1;
                }
            }

        }
        System.out.println(-1);
    }
}
