package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 지름길 {

    static class Shortcut {
        int start;
        int end;
        int distance;
        public Shortcut(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Shortcut> shortcuts = new ArrayList<Shortcut>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            Shortcut temp = new Shortcut(start, end, distance);
            shortcuts.add(temp);
        }

        // start 오름차순으로 정렬, 같으면 end 오름차순
        Collections.sort(shortcuts, new Comparator<Shortcut>() {
            @Override
            public int compare(Shortcut s1, Shortcut s2) {
                if (s1.start == s2.start) {
                    return s1.end - s2.end;
                } else {
                    return s1.start - s2.start;
                }
            }
        });

        int[] road = new int[10001];
        Arrays.fill(road, Integer.MAX_VALUE);
        road[0] = 0;

        int index = 0;
        int now = 0;
        while (now < D) {
            if (index < shortcuts.size()) {
                Shortcut temp = shortcuts.get(index);
                if (now == temp.start) {
                    road[temp.end] = Math.min(road[now] + temp.distance, road[temp.end]);
                    index += 1;
                } else {
                    road[now + 1] = Math.min(road[now + 1], road[now] + 1);
                    now += 1;
                }
            } else {
                road[now + 1] = Math.min(road[now + 1], road[now] + 1);
                now += 1;
            }
        }
        System.out.println(road[D]);
    }
}
