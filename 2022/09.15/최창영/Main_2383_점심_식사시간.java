import java.io.*;
import java.util.*;

public class Main_2383_점심_식사시간 {
    static int N, result;
    static int[][] arr;
    static int[] ans;
    static List<Person> personList;
    static Stair[] stairArr;
    static int finalTime; // 가장 늦게 도착한 사람의 시간.

    static class Person {
        int num;
        int x;
        int y;
        int minMin;

        public Person(int num, int x, int y, int minMin) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.minMin = minMin;
        }
    } // End of Person class

    static class Stair {
        int x;
        int y;
        int time;
        List<Integer> waitingList;

        public Stair(int x, int y, int time, List<Integer> waitingList) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.waitingList = waitingList;
        }
    } // End of Stair class

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/2383.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append('#').append(t).append(' ');

            N = Integer.parseInt(br.readLine());
            init();

            int stairIndex = 0;
            int manCount = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1) {
                        personList.add(new Person(manCount, i, j, Integer.MAX_VALUE));
                        manCount++;
                    } else if (arr[i][j] > 1) {
                        stairArr[stairIndex] = new Stair(i, j, arr[i][j], new LinkedList<>());
                        stairIndex++;
                    }
                }
            }

            ans = new int[manCount];
            DFS(0, manCount);

            sb.append(result).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
    } // End of main

    private static void DFS(int depth, int depthLimit) {

        if (depth == depthLimit) {
            int finalTime1 = Integer.MIN_VALUE;
            int finalTime2 = Integer.MIN_VALUE;

            for (Stair s : stairArr) {
                s.waitingList = new ArrayList<>();
            }

            for (int i = 0; i < depthLimit; i++) {
                int stairIndex = ans[i] - 1;
                int manNum = i; // 사람 번호
                int distTime = minCalc(personList.get(manNum).x, stairArr[stairIndex].x, personList.get(manNum).y, stairArr[stairIndex].y);

                stairArr[stairIndex].waitingList.add(distTime);
                // index에 해당하는 계단으로 이동,
            }


            // 가장 마지막에 빠져나온 사람을 기준으로 result 값을 최솟값으로 갱신
            for (int i = 0; i < 2; i++) {
                Stair s = stairArr[i];
                int stairTime = s.time;

                // 대기열이 비었으면 진행 X
                if (s.waitingList.isEmpty()) continue;

                // 대기시간 오름차순으로 정렬
                Collections.sort(s.waitingList);

                // 대기열이 3이하까지는 상관없음
                if (s.waitingList.size() <= 3) {
                    finalTime1 = Math.max(finalTime1, (s.waitingList.get(s.waitingList.size() - 1) + stairTime));
                } else {
                    Deque<Integer> deque = new LinkedList<>();
                    int time = 0;

                    // 먼저 3개를 일단 덱에 탈출시간으로 지정해서 집어넣음.
                    for (int j = 0; j < 3; j++) {
                        deque.offerLast(s.waitingList.remove(0) + s.time);
                    }

                    // 조건에 만족해서 탈출할 때 까지 무한반복
                    for (; ; ) {
                        while (!deque.isEmpty() && deque.peekFirst() == time) {
                            deque.pollFirst();
                        }

                        if (deque.size() < 3) {
                            int dif = 3 - deque.size();
                            int removeCount = 0;

                            for (int j = 0; j < dif; j++) {
                                if (s.waitingList.isEmpty()) {
                                    break;
                                }

                                if (time < s.waitingList.get(0)) {
                                    break;
                                } else if (time + s.time == s.waitingList.get(0)) {
                                    deque.offerLast(s.waitingList.get(0) + s.time);
                                    removeCount++;
                                } else if (time + s.time > s.waitingList.get(0)) {
                                    deque.offerLast(time + s.time);
                                    removeCount++;
                                }

                                s.waitingList.remove(0);
                            }
                        }

                        if (deque.size() <= 3 && s.waitingList.isEmpty()) {
                            time = deque.pollLast();
                            break;
                        }

                        time++;
                    }

                    finalTime2 = Math.max(finalTime2, time);
                } // End of else of if
            } // End of for(i);

            // 모든 값이 계산되고 나면 종료
            finalTime = Math.max(finalTime1, finalTime2);
            result = Math.min(finalTime, result);
            return;
        }

        for (int i = 1; i <= 2; i++) {
            ans[depth] = i;
            DFS(depth + 1, depthLimit);
        }
    } // End of DFS

    private static int minCalc(int pr, int sr, int pc, int sc) {
        int time = Math.abs(pr - sr) + Math.abs(pc - sc);
        return time + 1; // 계단을 내려가는 시간이 기준이므로 + 1을 해줌.
    } // End of minCalc

    private static void init() {
        result = Integer.MAX_VALUE;
        arr = new int[N][N];
        stairArr = new Stair[2];
        personList = new ArrayList<>();
        finalTime = Integer.MIN_VALUE;
    } // End of init
} // End of Main class