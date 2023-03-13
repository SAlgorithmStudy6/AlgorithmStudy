import java.util.*;

// w, h가 공약수가 있다면 문제를 공약수를 나눈 w' 과 h'으로 축소시킬 수 있다.
// w'과 h'이 서로소라 가정했을 때 대각선은 반대쪽 코너에 도달하기 전 w'-1 세로선과 h'-1 가로선을 지나고
// 지날 때 마다 새로운 정사각형이 추가됩니다.
// 그래서 첫 정사각형을 포함 1 + (w'-1) + (h'-1) = w' + h' - 1개의 정사각형을 지나게 되므로,
// 공약수를 다시 곱해주면 w + h - gcd(w, h)개의 정사각형을 지나는 것을 찾을 수 있다.

public class Main_PRG_멀쩡한_사각형 {
    public static void main(String[] args) throws Exception {
        Main_PRG_멀쩡한_사각형 m = new Main_PRG_멀쩡한_사각형();

        int w = 8;
        int h = 12;

        System.out.println(m.solution(w, h));
    } // End of main

    public static long solution(int w, int h) {
        int gcd = 0;
        if (w < h) {
            gcd = GCD(h, w);
        } else {
            gcd = GCD(w, h);
        }

        long total = (long) w * h;
        return total - (w + h - gcd);
    } // End of solution

    // 유클리드 호제법
    private static int GCD(int x, int y) {
        if (y == 0) {
            return x;
        }

        return GCD(y, x % y);
    } // End of GCD

} // End of Main class
