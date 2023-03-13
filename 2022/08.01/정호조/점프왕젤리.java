package pro16173;

import java.util.*;

public class 점프왕젤리 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = sc.nextInt();
               
            }
        }

        bfs(arr, visited);

        sc.close();
    }

    static void bfs(int[][] board, boolean[][] visited){
        int len = board.length;
        boolean answer = false;
        Queue<int[]> que = new LinkedList<>();
        int q[] = {0,0};
        que.add(q);

        while (!que.isEmpty()) {
            int[] temp = que.poll();
            int r = temp[0], c = temp[1];
            visited[r][c] = true;

            if(board[r][c] == -1){
                answer = true;
                break;
            }
            
            int bottom = r + board[r][c];
            int right = c + board[r][c];


            if (bottom < len && !visited[bottom][c]){
            	int[] m = {r + board[r][c], c};
                que.add(m);
                visited[bottom][c] = true;
            }
            if (right < len && !visited[r][right]){
            	int[] m = {r + board[r][c], c};
                que.add(m);
                visited[r][right] = true;
            }
        }

        if(answer){
            System.out.println("HaruHaru");
        }else{
            System.out.println("Hing");
        }
    }
}