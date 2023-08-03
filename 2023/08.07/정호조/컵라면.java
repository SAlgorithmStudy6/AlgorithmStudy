import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 컵라면_1781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Homework> homework = new PriorityQueue<>((o1, o2) ->{
           if(o1.deadLine == o2.deadLine){
               return o2.ramen - o1.ramen;
           } else {
               return o1.deadLine - o2.deadLine;
           }
        });
        PriorityQueue<Integer> result = new PriorityQueue<>();


        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());

            homework.add(new Homework(deadLine, ramen));
        }

        int sum =0;
        for(int i=0; i<N; i++){
            Homework hwTemp = homework.poll();
            result.add(hwTemp.ramen);

            if(hwTemp.deadLine < result.size()){
//                System.out.println("poll " + result.peek() + " deadline : "+hwTemp.deadLine + " size : "+result.size());
                result.poll();
            }
        }

        while (!result.isEmpty()){
            sum += result.poll();
        }

        System.out.println(sum);
    }

    static class Homework {
        int deadLine, ramen;

        public Homework(int deadLine, int ramen){
            this.deadLine = deadLine;
            this.ramen = ramen;
        }
    }
}
