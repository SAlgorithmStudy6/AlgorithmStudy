import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class swea4012 {
    static int N;
    static int[][] arr;
    static boolean [] visited;
    static int ans_a,ans_b,ans;
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args)throws  IOException {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        long start=System.currentTimeMillis();
        int T = Integer.parseInt(br.readLine());
        for (int o = 1; o <= T; o++) {
            ans=Integer.MAX_VALUE;
            ans_a=0;
            ans_b=0;
            N=Integer.parseInt(br.readLine());
            arr=new int[N][N];
            visited=new boolean[N];
            
            for(int i=0;i<N;i++) {
                StringTokenizer st=new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++) {
                    arr[i][j]=Integer.parseInt(st.nextToken());
                }
            }
            choice(0, 0);
            
            sb.append("#"+o+" "+ans+"\n");
            
        }
        System.out.println(sb);
        System.out.println(System.currentTimeMillis()-start);
        br.close();
    }
    
    public static void choice(int cnt,int start) {
        
        if(cnt==N/2) { 
            ans_a=0;
            ans_b=0;
          //계산
          cal();
          int tmp=Math.abs(ans_a-ans_b);
          if(tmp<ans) {
              ans=tmp;
          }
          return;
        }
        for(int i=start;i<N;i++) {
            if(cnt==0&&i>=N/2) break;
            //Sij와 Sji를 더한값
            if(visited[i]==false) {
                visited[i]=true;
                choice(cnt+1, i+1);
                visited[i]=false;
            }
        }
        return;
    }
    
    public static void cal() {
        for(int i=0;i<N-1;i++) {
            for(int j=i+1;j<N;j++) {
               if(visited[i]&&visited[j]) {
                  ans_a+=arr[i][j]+arr[j][i];
                }
               else if(!visited[i]&&!visited[j]) {
                  ans_b+=arr[i][j]+arr[j][i];
                }
            }
        }
    }

}
