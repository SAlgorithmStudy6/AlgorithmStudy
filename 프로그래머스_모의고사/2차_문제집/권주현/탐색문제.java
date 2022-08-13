import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> list;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
         //source : 시작점
        // destination : 도착점
        int[] answer = new int[sources.length];
         list=new ArrayList<>(); 
        for(int i=0;i<n+1;i++){
            list.add(new ArrayList<Integer>());
        }
        for(int i=0;i<roads.length;i++){
                list.get(roads[i][0]).add(roads[i][1]);
                list.get(roads[i][1]).add(roads[i][0]);  
        }
        for(int i=0;i<sources.length;i++){
            if(sources[i]==destination){
                answer[i]=0;
            }
            else answer[i]=bfs(sources[i],n,destination);
        }

        return answer;
    }
    public static int bfs(int start,int n,int des){
        int[] visited=new int[n+1];
        Queue<Integer> q=new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty()){
            int now=q.poll();
            
            for(int i:list.get(now)){
                if(visited[i]==0){
                    if(i==des){
                        return visited[now]+1;
                    }
                    else {
                        visited[i]=visited[now]+1;
                        q.offer(i);
                    }
                }
            }
            
        }
        return -1;

    }
}
