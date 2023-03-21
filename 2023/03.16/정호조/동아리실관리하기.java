import java.io.*;
import java.util.*;

public class 동아리실관리하기_3316 {
    static String str;
    static int answer;
    static int[][] cases;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            str = br.readLine();
            cases = new int[str.length()][16];
            answer = 0;
            getResult();
            System.out.printf("#%d %d\n", tc, answer);
        }
    }

    static void getResult() {
        for (int day = 0; day < cases.length; day++) {
            int master = 1 << str.charAt(day) - 'A';
            for (int people = 1; people <= 15; people++) {
                if (day == 0) {
                    if ((people & 1) != 0 && (people & master) != 0) {  //첫째날 키잡이(A)와 담당자가 속해있는 경우만 1 입력
                        cases[day][people] = 1;
                    }
                } else {
                    if (cases[day - 1][people] != 0) {   // 전날까지 가능했던 경우의 수 중
                        for (int curcase = 1; curcase <= 15; curcase++) {
                            if ((curcase & master) != 0 && ((people & curcase) != 0)) { // 담당자가 참석하고 전날 참석했던 인원 중 일부라도 참석한 경우의 수
                                cases[day][curcase] = (cases[day][curcase] + cases[day - 1][people]) % 1000000007;   //나누면서 더하지 않으면 음수 값이 나옴
                            }
                        }
                    }
                }
            }

        }

        for (int i = 1; i < 16; i++) {
            answer = (answer + cases[str.length() - 1][i]) % 1000000007;
        }
    }
}
