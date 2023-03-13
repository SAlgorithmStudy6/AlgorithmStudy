import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> coms = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); 
        int m = sc.nextInt();
        
        int result[] = new int[n + 1];
        boolean visited[] = new boolean[n + 1];
        
        for(int i=0; i<n+1; i++) {
            coms.add(new ArrayList<>());
        }
        
        // 입력
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            coms.get(a).add(b);
            coms.get(b).add(a);
        }
        
        
        Queue<Integer> q = new LinkedList<>();
        
        q.add(1);
        visited[1] = true;
        
        int count = 0;
        while(q.size() > 0) {
            int parent = q.poll();
            
            for(int num : coms.get(parent)) {
                if(visited[num] == false) {
                    visited[num] = true;
                    q.add(num);
                    count++;
                }
            }
        }
        System.out.println(count);

    }
}
