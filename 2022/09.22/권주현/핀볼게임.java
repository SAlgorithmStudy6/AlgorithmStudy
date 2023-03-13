package tmp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class swea5650 {
    static int N, ans;
    static int[][] arr;
    static ArrayList<ArrayList<pos3>> hole;
    static int dx[] = { -1, 1, 0, 0 }; // 상하좌우 순서
    static int dy[] = { 0, 0, -1, 1 };

    static class pos3 {
        int x, y;
        int dir, cnt;

        public pos3(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int o = 1; o <= T; o++) {
            ans = 0;
            N = scan.nextInt();
            arr = new int[N][N];
            hole = new ArrayList<>();
            for (int i = 0; i < 11; i++) {
                hole.add(new ArrayList<>());
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = scan.nextInt();
                    // 웜홀의 짝꿍찾기
                    if (arr[i][j] >= 6) {// 웜홀일때
                        hole.get(arr[i][j]).add(new pos3(i, j,0,0));
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 0) { // 빈공간에서만 시작할 수 있음
                        ans = Math.max(ans, sol(hole,i, j));
                    }
                }
            }
            System.out.println("#" + o + " " + ans);

        }

    }

    public static int sol(ArrayList<ArrayList<pos3>> hole,int x, int y) {
        Queue<pos3> q = new LinkedList<pos3>();

        int cnt = 0; // 부딪힌 횟수

        q.offer(new pos3(x, y, 0, 0));
        q.offer(new pos3(x, y, 1, 0));
        q.offer(new pos3(x, y, 2, 0));
        q.offer(new pos3(x, y, 3, 0));

        while (!q.isEmpty()) {

            pos3 cur = q.poll();
            int curx = cur.x;
            int cury = cur.y;
            int curd = cur.dir;
            int curc = cur.cnt;

            if (curx == x && cury == y && curc > 0) {
                if (cnt < curc) {
                    cnt = curc;
                }
                continue;
            }

            // dir 방향으로 한칸 전진
            int nx = curx + dx[curd];
            int ny = cury + dy[curd];
            int nd = curd;
            
            if (nx == x && ny == y) {
                if (cnt < curc) {
                    cnt = curc;
                }
                continue;
            }
            // 벽에 부딪혔을때
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {

                // 방향 반대로
                if (curd == 0) {
                    nd = 1;
                } else if (curd == 1) {
                    nd = 0;
                } else if (curd == 2) {
                    nd = 3;
                } else if (curd == 3) {
                    nd = 2;
                }
                //0 1
                int val = arr[curx][cury];
                if (val == 0) {// 아무것도 없을 때
                    q.offer(new pos3(curx, cury, nd, curc+1));
                } else if (val > 0 && val < 6) {// 블록이 있을때
                    nd = chk_dir(val, nd);
                        q.offer(new pos3(curx, cury, nd, curc + 2));
                    
                } else if (val >= 6 && val <= 10) {// 웜홀이 있을 때
                    pos3 tmp = hole.get(val).get(0);
                    if (tmp.x == curx && tmp.y == cury) { // 첫번째로 저장된 좌표와 같을때
                        q.offer(new pos3(hole.get(val).get(1).x, hole.get(val).get(1).y, nd, curc));

                    } else {
                        q.offer(new pos3(tmp.x, tmp.y, nd, curc+1));

                    }
                }
                
            } else {
                int val = arr[nx][ny];
                if (val == 0) {// 아무것도 없을 때
                    q.offer(new pos3(nx, ny, nd, curc));
                } else if (val > 0 && val < 6) {// 블록이 있을때
                    nd = chk_dir(val, nd);
                    if(nx>=0&&ny>=0&&nx<N&&ny<N) {
                        q.offer(new pos3(nx, ny, nd, curc + 1));
                    }
                } else if (val >= 6 && val <= 10) {// 웜홀이 있을 때
                    pos3 tmp = hole.get(val).get(0);
                    if (tmp.x == nx && tmp.y == ny) { // 첫번째로 저장된 좌표와 같을때
                        q.offer(new pos3(hole.get(val).get(1).x, hole.get(val).get(1).y, nd, curc));

                    } else {
                        q.offer(new pos3(tmp.x, tmp.y, nd, curc));

                    }
                } else if (val == -1 || (nx == x && ny == y)) {// 블랙홀이 있을 때, 시작점으로 돌아왔을 때
                    if (cnt < curc) {
                        cnt = curc;
                    }
                    continue;
                }
            }

        }

        return cnt;
    }

    public static int chk_dir(int block, int dir) {
        int ndir = 0;
        // 0:상 , 1:하, 2:좌, 3:우
        if (block == 1) {
            if (dir == 0) {
                ndir = 1;
            } else if (dir == 1) {
                ndir = 3;
            } else if (dir == 2) {
                ndir = 0;
            } else if (dir == 3) {
                ndir = 2;
            }
        } else if (block == 2) {
            if (dir == 0) {
                ndir = 3;
            } else if (dir == 1) {
                ndir = 0;
            } else if (dir == 2) {
                ndir = 1;
            } else if (dir == 3) {
                ndir = 2;
            }

        } else if (block == 3) {
            if (dir == 0) {
                ndir = 2;
            } else if (dir == 1) {
                ndir = 0;
            } else if (dir == 2) {
                ndir = 3;
            } else if (dir == 3) {
                ndir = 1;
            }

        } else if (block == 4) {
            if (dir == 0) {
                ndir = 1;
            } else if (dir == 1) {
                ndir = 2;
            } else if (dir == 2) {
                ndir = 3;
            } else if (dir == 3) {
                ndir = 0;
            }

        } else if (block == 5) {
            if (dir == 0) {
                ndir = 1;
            } else if (dir == 1) {
                ndir = 0;
            } else if (dir == 2) {
                ndir = 3;
            } else if (dir == 3) {
                ndir = 2;
            }
        }
        return ndir;
    }

}
