import java.util.*;
import java.io.*;

public class Main_16498_작은_벌점 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/16498.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int minPenaltyScore = (int) (2 * Math.pow(10, 9));

        int cardHavingCount[] = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        HashSet<Integer> aSet = new HashSet<>();
        HashSet<Integer> bSet = new HashSet<>();
        HashSet<Integer> cSet = new HashSet<>();

        cardHavingCount[0] = a;
        cardHavingCount[1] = b;
        cardHavingCount[2] = c;
        int total = a + b + c;
        int allCardArr[] = new int[total];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<a; i++) {
            aSet.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<b; i++) {
            bSet.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<c; i++) {
            cSet.add(Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(allCardArr);
        int minIdx = 0;
        int maxIdx = allCardArr.length-1;

        minPenaltyScore = Math.min(minPenaltyScore, binarySearch(allCardArr, minIdx, maxIdx));


    } // End of main

    private static int binarySearch(int allCardArr[], int minIdx, int maxIdx) {
        int penaltyScore = Integer.MAX_VALUE;
        int middleIdx = -1;
        int newPenalty = 0;

        while(minIdx < maxIdx) {
            middleIdx = (minIdx + maxIdx) / 2;
            newPenalty = Math.abs(allCardArr[maxIdx] - allCardArr[minIdx]);
            if(newPenalty < penaltyScore) {
                penaltyScore = newPenalty;
                maxIdx = middleIdx - 1;
            } else {
                minIdx = middleIdx + 1;
            }
        }

        return penaltyScore;
    } // End of binarySearch
} // End of Main class