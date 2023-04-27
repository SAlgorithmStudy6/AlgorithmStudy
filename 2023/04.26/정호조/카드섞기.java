import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 카드섞기_1091 {
    static int[] initCard, curCard, beforeCard, p, s;
    static ArrayList[] resultCard;
    static int N, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ans = 0;
        initCard = new int[N];
        curCard = new int[N];
        beforeCard = new int[N];
        p = new int[N];
        s = new int[N];
        resultCard = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            resultCard[i] = new ArrayList<Integer>();
        }

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st1.nextToken());
            s[i] = Integer.parseInt(st2.nextToken());
            initCard[i] = i;
            curCard[i] = i;
            beforeCard[i] = i;
        }

        for (int i = 0; i < N; i++) {   //정답 카드 -> 각 번호의 사람들이 가질 수 있는 카드를 list로 넣어줌

            resultCard[p[i]].add(i);
        }

        while (true) {
            if (isDone()) break;
            if (ans > 0 && Arrays.equals(curCard, initCard)) {  //종료 조건 : 섞은 현재 카드가 다시 맨 처음 카드로 돌아왔을 경우
                ans = -1;
                break;
            }

            ans++;
            for (int i = 0; i < N; i++) {   //카드 섞기
                curCard[s[i]] = beforeCard[i];
            }

            for (int i = 0; i < N; i++) {   //다음 턴에서 섞을 때를 위해 현재 카드를 beforeCard에 저장
                beforeCard[i] = curCard[i];
            }
        }

        System.out.println(ans);
    }

    static boolean isDone() {   //각 번호의 사람이 알맞게 카드를 갖고있는지 체크
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (resultCard[i % 3].contains(curCard[i])) {
                cnt++;
            }
        }

        if (cnt == N) return true;
        else return false;
    }
}
