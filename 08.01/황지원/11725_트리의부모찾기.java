import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> pc = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 노드 개수
        
        int result[] = new int[n + 1];
        boolean visited[][] = new boolean[n + 1][n + 1];
        
        for(int i=0; i<n+1; i++) {
            pc.add(new ArrayList<>());
        }
        
        // 입력
        for (int i = 1; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            pc.get(a).add(b);
            pc.get(b).add(a);
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        q.add(1);
        
        while(q.size() > 0) {
            int parent = q.poll();
            
            for(int num : pc.get(parent)) {
                if(result[num] == 0) {
                    result[num] = parent;
                    q.offer(num);
                }
            }
        }
        for (int i = 2; i <= n; i++) { // 자식
            System.out.println(result[i]);
        }
    }
}
