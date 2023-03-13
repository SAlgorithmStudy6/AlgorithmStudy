import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class swea5656 {
    static class pos4 {
        int x, y, size;

        public pos4(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    static int N, W, H, ans;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int o = 1; o <= T; o++) {
            ans=Integer.MAX_VALUE;
            N = scan.nextInt(); // 구슬을 쏠 수 있는 횟수
            W = scan.nextInt();
            H = scan.nextInt();

            int[][] arr = new int[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    arr[i][j] = scan.nextInt();
                }
            }
         choice(0,arr);
         System.out.println("#" + o + " " + ans);

        }
    }

    public static void choice(int cnt, int[][] arr) {
        if (cnt == N) { // basecase
            int block=getRemain(arr);
           ans= Math.min(ans, block);
            return ;
        }
        int[][] newarr = new int[H][W];

        for (int j = 0; j < W; j++) { // y축 선택
            int i = 0;
            while (i < H && arr[i][j] == 0) { // x축 선택
                ++i;
            }
            if (i == H) { // 모두 빈칸인 경우 예외처리
                choice(cnt + 1, arr);
            } else {
                copy(arr, newarr);
                broke(newarr, i, j);
                rebuild(newarr);
                choice(cnt+1,newarr);
            }
        }
    }

    public static void broke(int[][] arr, int r, int c) {
        Queue<pos4> q = new LinkedList<>();
        if(arr[r][c]>1) {
            q.offer(new pos4(r, c, arr[r][c]));
        }
        arr[r][c] = 0; //제거처리(방문처리)
        
        while(!q.isEmpty()) {
            pos4 p = q.poll();
            
            for(int d=0; d<4; d++) {
                int nx = p.x;
                int ny = p.y;
                for(int k=1; k<p.size; k++) {
                    nx += dx[d];
                    ny += dy[d];
                    if(nx>=0 && nx<H && ny>=0 && ny<W && arr[nx][ny] != 0) {
                        if(arr[nx][ny] > 1) {
                            q.offer(new pos4(nx, ny, arr[nx][ny]));
                        }
                        arr[nx][ny] = 0;
                    }
                }
            }
        }
    }

    public static void copy(int[][] arr, int[][] newarr) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                newarr[i][j] = arr[i][j];
            }
        }
    }
    public static void rebuild(int [][]arr) {
        for(int j=0;j<W;j++) {
            Queue<Integer> q3=new LinkedList<>();
            int i=H-1;  //밑에서 부터 올라가면서
            while(i>=0) {
                if(arr[i][j]>0) {//블록이 존재하면 q에 넣어주기
                    q3.offer(arr[i][j]);
                    arr[i][j]=0;
                }
                i--;
            }
            i=H-1;
            while(!q3.isEmpty()) {   //밑에서부터 채우기
                arr[i][j]=q3.poll();
                i--;
            }
        }
    }
    private static int getRemain(int[][] map) {
        int count =0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(map[i][j] > 0) count++;
            }
        }
        return count;
    }

}
