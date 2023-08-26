

import java.io.*;
import java.util.*;


class Main{


    static int[][] map;
    static int[][] dp;
    static int[] dx={-1,0,1,0};
    static int[] dy={0,1,0,-1};
    static int M,N;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];
        result = 0;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0,0));
    }

    static int dfs(int x,int y){
    if(x==M-1&y==N-1) return 1;
    //방문했던 경우
    if(dp[x][y]!=-1) return dp[x][y];
    else{
        //방문처리
        dp[x][y] = 0;
        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(isRange(nx,ny)&&map[x][y]>map[nx][ny]){
                dp[x][y]+=dfs(nx,ny);
            }
        }
    }
        return dp[x][y];
    }

    static boolean isRange(int x,int y){
        return x>=0&&y>=0&&x<M&&y<N;
    }

}
