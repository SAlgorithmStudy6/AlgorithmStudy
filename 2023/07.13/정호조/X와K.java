import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * X + Y == X | Y 이기 위해서는 X를 이진수로 변환했을 때 X의 n번째 자리가 1이면 Y의 n번째 자리는 0이어야함
 * -> 둘 다 1이게 되면 덧셈 시 자릿수가 넘어가게 되어 OR 연산의 결과값과 같아질 수 없기 때문
 * 나머지 자리는 K를 이진수로 변환한 후 0번째 자리수 부터 차례로 넣어주면 됨
 * */

public class X와K_1322 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        String binaryX = Long.toBinaryString(x);
        String binaryK = Long.toBinaryString(k);
        StringBuilder result = new StringBuilder();
        int idx = binaryK.length() - 1;

        for (int i = binaryX.length() - 1; i >= 0; i--) {
            char cur = binaryX.charAt(i);

            if(cur == '0'){
                if(idx == -1) continue;

                result.insert(0, binaryK.charAt(idx));
                idx--;
            } else {
                result.insert(0, 0);
            }
        }

        while(idx >= 0){
            result.insert(0, binaryK.charAt(idx));
            idx--;
        }

        Long ans = Long.parseLong(result.toString(), 2);
        System.out.println(ans);
    }
}
