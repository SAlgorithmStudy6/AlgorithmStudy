import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


class Solution {

    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static boolean visited[];
    static int[] answer = {};

    public int[] solution(int N, int[][] roads, int[] sources, int dest) {
        visited = new boolean[N+1];
        answer = new int [sources.length];
        for(int i=0; i<=N; i++){
            map.add(new ArrayList<>());
        }
        // roads -> map 값 이동
        for(int i=0; i<roads.length; i++) {
            int one = roads[i][0];
            int two = roads[i][1];
            map.get(one).add(two);
            map.get(two).add(one);
        }
        for(int i=0; i<sources.length; i++) {
            int now = sources[i];
            
            answer[i] = BFS(now, dest);
            Arrays.fill(visited, false);
        }
        return answer;
    }
    static int BFS(int now, int dest) {
        
        Queue<P> q= new LinkedList<>();
        visited[now]= true;
        q.add(new P(now, 0));
        
        while(!q.isEmpty()) {
            P poll = q.poll();
            if (poll.now == dest) {
                return poll.val;
            }
            for(int i=0; i<map.get(poll.now).size(); i++) {
                int next = map.get(poll.now).get(i);
                if (visited[next] == false) {
                    q.add(new P(next, poll.val+1));
                    visited[next] = true;
                }
            }
        }
        return -1;
    }
}
class P{
    int now;
    int val;
    P(int now, int val){
        this.now = now;
        this.val = val;
    }
}
