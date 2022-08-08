import java.util.*;
public class Main {
    static int N, K;
    static int time[] = new int[100001];
    public static void main(String[] args) {
        // 1697번 숨바꼭질

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        
        bfs(N);
    }
    static void bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        time[n] = 1;
        while (!q.isEmpty()) {
            int nn = q.poll();
            if (nn == K) {
                System.out.println(time[nn] - 1);
                return;
            }
            if (nn - 1 >= 0 && time[nn - 1] == 0) {
                time[nn - 1] = time[nn] + 1;
                q.add(nn - 1);
            }
            if (nn + 1 <= 100000 && time[nn + 1] == 0) {
                time[nn + 1] = time[nn] + 1;
                q.add(nn + 1); 
                
            }
            if (2 * nn <= 100000 && time[2 * nn] == 0) {
                time[2 * nn] = time[nn] + 1;
                q.add(2 * nn); 
            }
        }
        System.out.println(-1);
    }
}
