import java.util.*;
import java.io.*;

public class Main_2382_미생물_격리 {
    static int N, M, K, result;
    static LinkedList<Microorganism> list;

    static class Microorganism implements Comparable<Microorganism> {
        int count; // 미생물 숫자
        int direction; // 이동 방향
        int x;
        int y;

        public Microorganism(int x, int y, int count, int direction) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.direction = direction;
        }

        @Override
        public int compareTo(Microorganism o) {
            if (this.x == o.x) {
                if (this.y == o.y) {
                    return o.count - this.count;
                }

                return this.y - o.y;
            }

            return this.x - o.x;
        }
    } // End of microorganism class

    // 미생물 이동방향 1 : 상 , 2 : 하, 3 : 좌 , 4 : 우
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/2382.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append('#').append(t).append(' ');

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 가로 세로 크기
            M = Integer.parseInt(st.nextToken()); // 미생물이 격리되는 시간.
            K = Integer.parseInt(st.nextToken()); // 미생물 군집의 숫자
            init();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                list.add(new Microorganism(x, y, count, direction));
            }
            Collections.sort(list);

            while (M-- > 0) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    Microorganism micro = list.get(i);
                    int x = micro.x;
                    int y = micro.y;
                    int count = micro.count;
                    int direction = micro.direction;

                    if (direction == 1) { // 상
                        x--;
                        if (x == 0) {
                            count = count / 2;
                            direction = 2;
                        }
                    } else if (direction == 2) { // 하
                        x++;
                        if (x == N - 1) {
                            count = count / 2;
                            direction = 1;
                        }
                    } else if (direction == 3) { // 좌
                        y--;
                        if (y == 0) {
                            count = count / 2;
                            direction = 4;
                        }
                    } else if (direction == 4) { // 우
                        y++;
                        if (y == N - 1) {
                            count = count / 2;
                            direction = 3;
                        }
                    }

                    list.set(i, new Microorganism(x, y, count, direction));
                }

                // 리스트 정렬, x를 기준으로 정렬, x가 같으면, y값, y도 같으면 count를 기준으로 내림차순 정렬
                Collections.sort(list);

                // 한바퀴를 돌고 난 후 겹치는 값이 있는지 확인.
                // x와 y를 기준으로 정렬 후 그래도 값이 같으면 count를 기준으로 정렬.

                List<Microorganism> list2 = new LinkedList<>();
                int preX = -1;
                int preY = -1;
                for (int i = 0; i < list.size(); i++) {
                    Microorganism micro = list.get(i);
                    int x = micro.x;
                    int y = micro.y;
                    int count = micro.count;
                    int direction = micro.direction;
                    if (preX == x && preY == y) continue;

                    for (int j = i + 1; j < list.size(); j++) {
                        Microorganism micro2 = list.get(j);
                        int x2 = micro2.x;
                        int y2 = micro2.y;
                        double count2 = micro2.count;

                        if (x != x2) {
                            break;
                        }

                        if (y == y2) {
                            count += count2;
                        }
                    }

                    preX = x;
                    preY = y;
                    list2.add(new Microorganism(x, y, count, direction));
                }

                list.clear();
                list.addAll(list2);
            }

            // 최종 결과
            for (Microorganism micro : list) {
                result += micro.count;
            }

            sb.append(result).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
    } // End of main


    private static void init() {
        result = 0;
        list = new LinkedList<>();
    } // End of init
} // End of Main class