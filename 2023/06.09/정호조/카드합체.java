import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 카드합체놀이_15903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long ans = 0;

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> prQueue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            long num = Integer.parseInt(st.nextToken());
            prQueue.add(num);
            ans += num;
        }

        for (int i = 0; i < m; i++) {
            long x = prQueue.poll();    //숫자가 int 범위를 넘어서는지 잘 확인해야됨
            long y = prQueue.poll();
            long sum = x + y;

            prQueue.add(sum);
            prQueue.add(sum);
        }

        if(m != 0){
            ans = 0;
            while (!prQueue.isEmpty()){
                ans += prQueue.poll();
            }
        }

        System.out.println(ans);
    }
}
