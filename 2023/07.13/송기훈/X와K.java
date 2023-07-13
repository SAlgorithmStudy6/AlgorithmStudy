package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// X 이진수 중 1인 곳은 0으로, 나머지는 K 이진수로 표현한 것을 넣어준다.
public class X와K {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String sX = Integer.toBinaryString(x);
        String sK = Integer.toBinaryString(k);


        StringBuilder sb = new StringBuilder();
        int kIndex = sK.length() - 1;

        // sX의 끝에서 시작으로
        for (int i = sX.length()-1; i >= 0; i--) {
            char c = sX.charAt(i);

            // c가 1이면 결과값에 0을 추가
            if (c == '1') {
                sb.insert(0, 0);
            }
            // c가 0이면 kIndex를 하나 줄이고 0 추가
            else {
                if (kIndex == -1) {
                    continue;
                };
                sb.insert(0, sK.charAt(kIndex));
                kIndex--;
            }
        }

        // sX가 끝났는데도 kIndex가 남아있다면 sK를 넣어준다
        while (kIndex >= 0) {
            sb.insert(0, sK.charAt(kIndex));
            kIndex--;
        }

        System.out.println(Long.parseLong(sb.toString(), 2));

    }

}
