import java.io.*;
import java.util.*;

public class Swea_4013_특이한자석2 {

    static long N, orderMag[], changeMag, ans;
    static ArrayList<Integer> Mag[], Temp[];

    static boolean direction[], orderDirection[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            init();

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    Mag[i].add(Integer.parseInt(st.nextToken()));
                }
            }
            // 1. a, a2, b, b2 초기화
            for (int i = 0; i < 4; i++) {
                Temp[i] = Mag[i];
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                orderMag[i] = Integer.parseInt(st.nextToken()) - 1; // 바꿀 자석 번호 입력
                if (Integer.parseInt(st.nextToken()) == 1) // 자석의 방향 설정, true면 시계방향
                    orderDirection[i] = true;
                else
                    orderDirection[i] = false;
            }

            // 전체 자석 교환하는
            for (int aa = 0; aa < N; aa++) {

                // 3. changeMag에 어떤 자석 회전시킬지 적어주기
                changeMag = orderMag[aa];

                // 2. 방향을 미리 초기화해줌
                directionInit((int) changeMag, orderDirection[aa]);

                int prev = 0;
                int next = 0;
                // 회전 시킬 자석 번호 기준 앞 자석들 비교
                for (int i = (int) changeMag; i > 0; i--) {
                    if (Mag[i - 1].get(2) != Mag[i].get(6)) {
                        prev++;
                    } else {
                        break;
                    }
                }

                // 회전 시킬 자석 번호 기준 뒤의 자석들 비교
                for (int i = (int) changeMag; i < 3; i++) {
                    if (Mag[i].get(2) != Mag[i + 1].get(6)) {
                        next++;
                    } else {
                        break;
                    }
                }

                // 무조건 자기자신을 바꿔준다.
                if (direction[(int) changeMag] == true) { // 시계 방향 : 맨뒤맨앞
                    int temp = Temp[(int) changeMag].get(Temp[(int) changeMag].size() - 1);
                    Temp[(int) changeMag].add(0, temp);
                    Temp[(int) changeMag].remove(Temp[(int) changeMag].size() - 1);
                } else { // 반시계 방향 : 맨앞맨뒤
                    int temp = Temp[(int) changeMag].get(0);
                    Temp[(int) changeMag].add(temp);
                    Temp[(int) changeMag].remove(0);
                }

                // 4. for문으로 현재 changeMag 기준 왼쪽 자석들 비교해주기

                int i = (int) changeMag - 1;
                while (prev > 0 && i >= 0) {
                    if (direction[i]) { // 시계 방향 : 맨뒤맨앞
                        int temp = Temp[i].get(Temp[i].size() - 1);
                        Temp[i].add(0, temp);
                        Temp[i].remove(Temp[i].size() - 1);

                    } else { // 반시계 방향 : 맨앞 맨뒤
                        int temp = Temp[i].get(0);
                        Temp[i].add(temp);
                        Temp[i].remove(0);
                    }
                    prev--;
                    i--;
                }

                // 5. for문으로 현재 changeMag 기준 오른쪽 자석들 비교해주기

                i = (int) changeMag + 1;
                while (next > 0 && i < 4) {
                    if (direction[i]) { // 시계 방향 : 맨뒤꺼 빼서 맨앞에 넣기
                        int temp = Temp[i].get(Temp[i].size() - 1);
                        Temp[i].remove(Temp[i].size() - 1);
                        Temp[i].add(0, temp);

                    } else { // 반시계 방향 : 맨앞빼고 맨뒤에 넣기
                        int temp = Temp[i].get(0);
                        Temp[i].remove(0);
                        Temp[i].add(temp);
                    }
                    next--;
                    i++;
                }

                for (int a = 0; a < 4; a++) {
                    Mag[a] = Temp[a];
                }
            } // aa for end

            // 7. 마지막 점수 계산

            ans = Mag[0].get(0) * 1 + Mag[1].get(0) * 2 + Mag[2].get(0) * 4 + Mag[3].get(0) * 8;

            System.out.println("#" + tc + " " + ans);

        } // tc for end

    }

    // 방향 전체 초기화

    static void directionInit(int index, boolean standard) { // 0 ~ 3
        direction[(int) index] = standard;

        // 왼쪽
        for (long i = index - 1; i >= 0; i--) {
            if (Math.abs(i - index) % 2 == 0) { // 거리 2씩 떨어진 친구들 = 나랑 같게
                direction[(int) i] = standard;
            } else { // 거리 1씩 떨어진 친구들 = 나랑 다르게
                direction[(int) i] = !standard;
            }
        }
        for (long i = index + 1; i < 4; i++) {
            if (Math.abs(i - index) % 2 == 0) { // 거리 2씩 떨어진 친구들 = 나랑 같게
                direction[(int) i] = standard;
            } else { // 거리 1씩 떨어진 친구들 = 나랑 다르게
                direction[(int) i] = !standard;
            }
        }
    }

    static void init() {
        Mag = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            Mag[i] = new ArrayList<>();
        }
        Temp = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            Temp[i] = new ArrayList<>();
        }

        ans = 0;
        direction = new boolean[5];
        orderMag = new long[(int) N + 1];
        orderDirection = new boolean[(int) N + 1];

    }

}
