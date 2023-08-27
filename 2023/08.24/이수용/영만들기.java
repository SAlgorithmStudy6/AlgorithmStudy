import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/7490
public class 영만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] numList = new int[T];
        for(int t = 0; t < T; t++){
            numList[t] = Integer.parseInt(br.readLine());
        }
        for(int n : numList){
            backTracking(1, 0, 1, n, 1, "1");
            System.out.println();
        }

    }
    static void backTracking(int idx, int currentValue, int currentNum, int limit, int operator, String history){
        if(idx == limit){
            currentValue += (currentNum * operator);
            if(currentValue == 0){
                System.out.println(history);
            }
        }else {
            // 숫자를 이어붙이는 경우 지금 당장 계산하지 않고 숫자를 이어붙인 다음, 직전 연산이 +였는지 -였는지 다음 backTracking 에 넘겨줌
            backTracking(idx + 1, currentValue, (currentNum * 10) + (idx + 1), limit, operator, history + " " + (idx + 1));
            backTracking(idx + 1, currentValue + (currentNum * operator), idx + 1, limit, 1, history + "+" + (idx + 1));
            backTracking(idx + 1, currentValue + (currentNum * operator), idx + 1, limit, -1, history + "-" + (idx + 1));
        }
    }
}
