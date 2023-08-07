package baekjoon.gold.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 컵라면 {

    static class Homework implements Comparable {
        int deadline;
        int cupRamen;

        public Homework(int deadline, int cupRamen) {
            this.deadline = deadline;
            this.cupRamen = cupRamen;
        }

        @Override
        public int compareTo(Object o) {
            Homework h = (Homework) o;
            if (this.deadline == h.deadline) {
                return h.cupRamen - this.cupRamen;
            }

            return this.deadline - h.deadline;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", this.deadline, this.cupRamen);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Homework> homeworkList = new ArrayList<>();

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cupRamen = Integer.parseInt(st.nextToken());
            homeworkList.add(new Homework(deadline, cupRamen));
        }

        homeworkList.sort(Homework::compareTo);

        for (Homework h : homeworkList) {
            System.out.println(h);
        }

        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            Homework hw = homeworkList.get(i);
            pq.add(hw.cupRamen);

            if (hw.deadline < pq.size()) {
                pq.poll();
            }
            // ex) hw.deadline이 2인데 pq.size가 5다?
            // hw.deadline을 오름차순 정렬했으므로 현재는 2초라고 생각해야 됨
            // 따라서 최대 2문제까지만 소화 가능하므로 poll해서 맞춰줘야 함
        }
        System.out.println(pq);

        int sum = 0;
        while (!pq.isEmpty()) {
            int cupRamen = pq.poll();
            sum += cupRamen;
        }
        System.out.println(sum);
    }

}

//public class 컵라면 {
//
//    static class Homework implements Comparable<Homework>{
//        int deadline;
//        int cupRamen;
//
//        public Homework(int deadline, int cupRamen) {
//            this.deadline = deadline;
//            this.cupRamen = cupRamen;
//        }
//
//        @Override
//        public int compareTo(Homework o) {
//            if (this.cupRamen == o.cupRamen) {
//                return this.deadline - o.deadline;
//            }
//
//            return o.cupRamen - this.cupRamen;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("(%d,%d)", this.deadline, this.cupRamen);
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        // maxDeadline의 수만큼 고를 수 있음
//        // Deadline이 5인 원소는 5개 고를 수 있고 2인 원소는 2개 고를 수 있음
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int N = Integer.parseInt(br.readLine());
//
//        List<Homework> homeworkList = new ArrayList<>();
//
//        StringTokenizer st;
//
//        int maxDeadline = -1;
//        TreeMap<Integer, Integer> countMap = new TreeMap<>();
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            int deadline = Integer.parseInt(st.nextToken());
//            int cupRamen = Integer.parseInt(st.nextToken());
//            homeworkList.add(new Homework(deadline, cupRamen));
//            maxDeadline = Math.max(maxDeadline, deadline);
//            if (!countMap.containsKey(deadline)) {
//                countMap.put(deadline, 0);
//            }
//        }
//
//        // 단, maxDeadline은 주어진 deadline N보다 작거나 같아야 함
//        maxDeadline = Math.min(maxDeadline, N);
//
//        homeworkList.sort(Homework::compareTo);
//
//        int count = 0;
//        int sum = 0;
//        for (Homework h : homeworkList) {
//            // 이미 maxDeadline만큼 골랐으면 끝
//            if (count == maxDeadline) {
//                break;
//            }
//
//            // maxDeadline보다 크거나 count 보다 작아도 안 됨
//            // count초만큼 흘렀으니까 이미 deadline이 지난 문제
//            if (h.deadline > maxDeadline) {
//                continue;
//            }
//
//            // 해당 deadline을 이미 deadline 수만큼 골랐으면 패스
//            if (countMap.get(h.deadline) == h.deadline) {
//                continue;
//            }
//
//            count += 1;
//            sum += h.cupRamen;
//            countMap.put(h.deadline, countMap.get(h.deadline) + 1);
//
//            System.out.println(countMap);
//            System.out.println(count);
//            System.out.println(h);
//        }
//
//        System.out.println(sum);
//
//    }
//
//}