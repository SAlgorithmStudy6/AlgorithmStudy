import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 꼬인전깃줄_1365 {
    static int[] wire;
    static ArrayList<Integer> list;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        wire = new int[N];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            wire[i] = Integer.parseInt(st.nextToken());
        }
        list.add(wire[0]);

        for (int i = 1; i < N; i++) {
            int next = wire[i];
            if (list.get(list.size() - 1) < next) {
                list.add(next);
            } else {
                int temp = Collections.binarySearch(list, next);
                int index = temp >= 0 ? temp : -temp - 1;
                list.set(index, next);
            }
        }

        System.out.println(N - list.size());
    }
}
