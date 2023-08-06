import java.util.*;
import java.io.*;


class Main {

    static class Problem implements Comparable<Problem>{
        int deadline;
        int ramen;

        public  Problem(int deadline,int ramen){
            this.deadline = deadline;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Problem o) {
            if(this.deadline==o.deadline) return this.ramen-this.ramen;
            else return this.deadline-o.deadline;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Problem> pq = new PriorityQueue<>();
        PriorityQueue<Integer> mins = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            pq.add(new Problem(n1,n2));
        }

        long answer = 0;
        int time = 0;
        while(!pq.isEmpty()){
            Problem now = pq.poll();
            //바로 풀수 있을때
            if(time<now.deadline){
                answer+=now.ramen;
                mins.add(now.ramen);
                time++;
            }
            //타임라인이 지났을때
            else{
                //자기보다 작은거 있으면 그거 중지시킴
                if(!mins.isEmpty()&&mins.peek()<now.ramen){
                    int min = mins.poll();
                    answer=answer-min+now.ramen;
                    mins.add(now.ramen);
                }
            }
        }
        System.out.println(answer);
    }
}
