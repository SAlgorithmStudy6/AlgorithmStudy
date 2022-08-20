public class Solution1 {
    public static void main(String[] args) {
        Solution1 s = new Solution1();
        int a = 3; // 콜라를 받기 위해 마트에 주어야 하는 병 수
        int b = 1; // 빈병 a개를 가져다 주면 마늩가 주는 콜라 병 수
        int n = 20; // 가지고 있는 빈 병의 수

        System.out.println(s.solution(a, b, n));
    } // End of main

    public int solution(int a, int b, int n) {
        int totalCola = 0;

        if(n < a) {
            return totalCola;
        }

        while ( n >= a ) { // 보유중인 빈병과 콜라의 개수가 a개 미만이면, 멈춤

            int cola = (n / a) * b; // 빈병으로 콜라 생성
            totalCola += cola; // 전체 받은 콜라 합산
            n = cola + (n % a); // 교환한 콜라병의 수 + 교환하지 못하고 남은 콜라 병의 수
        }

        return totalCola;
    } // End of solution
} // End of Solution class