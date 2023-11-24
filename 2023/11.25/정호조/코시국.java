import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//bfs로 풀었지만 거리가 2이하일 때 X가 안나오면 무조건 answer는 0이므로 dfs 코드가 더 깔끔함
public class 거리두기확인하기 {
    public static void main(String[] args) throws IOException {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {
                "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {
                "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        Solution s = new Solution();

        System.out.println(Arrays.toString(s.solution(places)));
    }

    static class Solution {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        String[] curRoom;
        Queue<Point> queue;
        boolean[][] vis;

        public int[] solution(String[][] places) {
            int[] answer = new int[places.length];
            queue = new LinkedList<>();

            for (int i = 0; i < places.length; i++) {
                curRoom = places[i];
                boolean checkResult = true;

                Loop:
                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 5; c++) {
                        if (curRoom[r].charAt(c) == 'P') {
                            queue.clear();
                            vis = new boolean[5][5];
                            if (!checkAround(r, c)) {
                                checkResult = false;
                                break Loop;
                            }
                        }
                    }
                }
                answer[i] = checkResult ? 1 : 0;
            }
            return answer;
        }

        //사람 기준으로 거리 2인 곳만 BFS 탐색
        boolean checkAround(int r, int c) {
            vis[r][c] = true;
            queue.add(new Point(r, c, 0));
            while (!queue.isEmpty()) {
                Point cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nextR = cur.r + dr[d];
                    int nextC = cur.c + dc[d];
                    int distance = cur.distance + 1;

                    if (distance > 2) break;

                    if (0 <= nextR && nextR < 5 && 0 <= nextC && nextC < 5 && !vis[nextR][nextC]) {
                        char nextSeat = curRoom[nextR].charAt(nextC);
                        if (distance <= 1) {    //인접했는데 사람이면 종료
                            if (nextSeat == 'P') return false;
                        } else {
                            if (nextSeat == 'P' && !checkPartition(r, c, nextR, nextC)) return false;
                        }

                        vis[nextR][nextC] = true;
                        queue.add(new Point(nextR, nextC, distance));
                    }
                }
            }

            return true;
        }

        boolean checkPartition(int personR, int personC, int nextR, int nextC) {
            if (personC == nextC) { //세로로 마주한 경우
                return curRoom[Math.min(personR, nextR) + 1].charAt(personC) == 'X';
            } else if (personR == nextR) {  //가로로 마주한 경우
                return curRoom[personR].charAt(Math.min(personC, nextC) + 1) == 'X';
            } else {    //대각으로 마주한 경우 -> 원래 사람 좌표(bfs의 시작점 == personR, personC)에서 부터 새로 발견한 사람의 좌표(nextR, nextC)까지 탐색했을 때 이미 사람이 2명이므로 파티션도 두 번 나와야함
                int startR = Math.min(personR, nextR);
                int endR = Math.max(personR, nextR);
                int startC = Math.min(personC, nextC);
                int endC = Math.max(personC, nextC);
                int partitionCnt = 0;

                for (int i = startR; i <= endR; i++) {
                    for (int j = startC; j <= endC; j++) {
                        if (curRoom[i].charAt(j) == 'X') partitionCnt++;
                    }
                }

                return partitionCnt >= 2;
            }
        }

        class Point {
            int r, c, distance;

            public Point(int r, int c, int distance) {
                this.r = r;
                this.c = c;
                this.distance = distance;
            }
        }
    }
}
