import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1781

public class 컵라면 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Question[] questionList = new Question[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cupNoodle = Integer.parseInt(st.nextToken());
            questionList[i] = new Question(deadLine, cupNoodle);
        }
        Arrays.parallelSort(questionList);
        for(Question q : questionList){
            int queueSize = pq.size();
            if(queueSize < q.deadLine){
                pq.add(q.cupNoodle);
            }else if(queueSize == q.deadLine){
                if(q.cupNoodle > pq.peek()){
                    pq.poll();
                    pq.add(q.cupNoodle);
                }
            }
        }
        int ans = 0;
        while(!pq.isEmpty()){
            ans += pq.poll();
        }
        System.out.println(ans);
    }
}

class Question implements Comparable<Question> {
    Question(int deadLine, int cupNoodle) {
        this.deadLine = deadLine;
        this.cupNoodle = cupNoodle;
    }

    int deadLine;
    int cupNoodle;

    @Override
    public int compareTo(Question o) {
        if (this.deadLine < o.deadLine) {
            return -1;
        } else if (this.deadLine == o.deadLine) {
            if (this.cupNoodle > o.cupNoodle) {
                return -1;
            } else if (this.cupNoodle == o.cupNoodle) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}
