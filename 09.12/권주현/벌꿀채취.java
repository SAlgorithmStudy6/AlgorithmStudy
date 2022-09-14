import java.util.Scanner;

public class Solution {
    static int [][] arr;
    static boolean []visited;
    static int ans,N,M,C;
    static int max1,max2;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
        int T=scan.nextInt();
        for(int o=1;o<=T;o++) {
            N=scan.nextInt();
            M=scan.nextInt();
            C=scan.nextInt();
            
            ans=0;
            max1=Integer.MIN_VALUE;
            max2=Integer.MIN_VALUE;
            arr=new int[N][N];
            visited=new boolean[M];
            ans=0;
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    arr[i][j]=scan.nextInt();
                }
            }
            
            selecthoney();
                        System.out.println("#"+o+" "+ ans);     

        }
    }
    public static void selecthoney() {
        boolean[][] check = new boolean[N][N];
        boolean flag;
        // 1번 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - (M - 1); j++) {
                for (int k = j; k < j + M; k++) {   //방문처리
                    check[i][k] = true; 
                }

                // 2번
                for (int a = 0; a < N; a++) {
                    for (int b = 0; b < N - (M - 1); b++) {
                        flag = true;
                        for (int c = 0; c < M; c++) { //1번과 겹치는지 체크 
                            if (check[a][b + c]) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) continue; // 겹치면 다시 위치 찾기
                        //안겹치면 최댓값 계산     
                        check(i, j, a, b, 0); 
                        ans = Math.max(ans, max1 + max2);
                        max1 = 0;
                        max2 = 0;
                    }
                }

                for (int k = j; k < j + M; k++) {
                    check[i][k] = false;
                }
            }
        }

    }
    private static void check(int i, int j, int a, int b, int depth) {  
        if (depth == M) {       //M까지 다돌면
            int count1 = 0, count2 = 0, c1 = 0, c2 = 0;
            for (int k = 0; k < M; k++) {
                if (visited[k]) {
                    count1 += arr[i][j + k];        //꿀의 양
                    c1 += (int) Math.pow(arr[i][j + k], 2);     //이익
                    count2 += arr[a][b + k];
                    c2 += (int) Math.pow(arr[a][b + k], 2);
                }
            }
            if (count1 <= C) max1 = Math.max(max1, c1); // 채취할 수 있는 최댓값보다 작거나 같을 때 
            if (count2 <= C) max2 = Math.max(max2, c2);
            return;
        }
        
        visited[depth] = true;  
        check(i, j, a, b, depth + 1);
        visited[depth] = false;  
        check(i, j, a, b, depth + 1);
    }
}
