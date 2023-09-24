package programmers.lv2;

import java.util.Arrays;

class Test {
    public static void main(String[] args) {
//        int cap = 2;
//        int n = 7;
//        int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
//        int[] pickups = {0, 2, 0, 1, 0, 2, 0};

        int cap = 4;
        int n = 5;
        int[] deliveries = {1, 0, 3, 1, 2};
        int[] pickups = {0, 3, 0, 4, 0};

        new 택배_배달과_수거하기().solution(cap, n, deliveries, pickups);
    }
}


public class 택배_배달과_수거하기 {

//    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
//        long answer = 0;
//        int d = 0;
//        int p = 0;
//
//        for (int i = n-1; i >= 0; i--) {
//            if (deliveries[i] > 0 || pickups[i] > 0) {
//                int count = 0;
//                // 몇 번 와야됨?
//                while (d < deliveries[i] || p < pickups[i]) {
//                    count += 1;
//                    d += cap;
//                    p += cap;
//                }
//                d -= deliveries[i];
//                p -= pickups[i];
//                answer += (long) (i + 1) * count * 2;
//            }
//        }
//
//        return answer;
//    }


    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int dIndex = n - 1;
        int pIndex = n - 1;

        while (dIndex >= 0 || pIndex >= 0) {

            while (dIndex >= 0 && deliveries[dIndex] == 0) {
                dIndex--;
            }
            while (pIndex >= 0 && pickups[pIndex] == 0) {
                pIndex--;
            }

            int carry = 0;

            answer += (Math.max(dIndex, pIndex) + 1) * 2L;

            // 배달
            while (dIndex >= 0 && carry < cap) {
                carry += deliveries[dIndex];
                deliveries[dIndex] = 0;
                dIndex -= 1;
            }
            System.out.println("c: " + carry);
            System.out.println("d1: " + Arrays.toString(deliveries));
            if (carry > cap) {
                dIndex += 1;
                deliveries[dIndex] = carry - cap;
            }
            System.out.println("dI: " + dIndex);
            System.out.println("d2: " + Arrays.toString(deliveries));

            carry = 0;
            // 수거
            while (pIndex >= 0 && carry < cap) {
                carry += pickups[pIndex];
                pickups[pIndex] = 0;
                pIndex -= 1;
            }
            System.out.println("c: " + carry);
            System.out.println("p1: " + Arrays.toString(pickups));
            if (carry > cap) {
                pIndex += 1;
                pickups[pIndex] = carry - cap;
            }
            System.out.println("pI: " + pIndex);
            System.out.println("p2: " + Arrays.toString(pickups));
        }


        return answer;
    }


}




