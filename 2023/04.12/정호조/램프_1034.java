import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class 램프_1034 {
    static int N, M, K, ans;
    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        ans = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if(map.containsKey(str)){
                map.replace(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }
        K = Integer.parseInt(br.readLine());

        for (String row: map.keySet()){
            int cnt = 0;
            for(int i=0; i<M; i++){
                if(row.charAt(i) == '0') ++cnt;
            }

            if(K >= cnt && (K-cnt) %2 == 0){
                ans = Math.max(ans, map.get(row));
            }
        }

        System.out.println(ans);
    }
}
