import java.util.*;
public class Main {

	public static Scanner scan =new Scanner(System.in);
	public static StringBuilder sb=new StringBuilder();
	public static int[][] arr;
	public static boolean [] visited;
	public static int [] choice;
	static int N,T,num=0,ans=0,min=101;
	static boolean flag;
    public static void main(String[] args) {
    	//입력
    	N=scan.nextInt();
    	arr= new int[N+1][N+1];
    	visited=new boolean[N+1];
    	for(int i=1;i<N+1;i++) {
    		for(int j=1;j<N+1;j++) {
    			arr[i][j]=scan.nextInt();
    		}
    	}
    	solve(0,1);
    	System.out.print(min);
    }
    //팀 나누기 (조합)
    public static void solve(int cnt,int pos) {
    	//basecase
    	if(cnt==N/2) {		//팀의 구성원이 다 모집되었음
    		//최소값 구하기
    		min=Math.min(min, cal(visited));
    		return;
    	}
    	for(int i=pos;i<N+1;i++) {
    		if(!visited[i]) {
    			visited[i]=true;
    			solve(cnt+1,i+1);
    			visited[i]=false;
    		}
    	}
    }
    //나눠진 팀에서 점수 차 계산하기 
    public static int cal(boolean[] visited) {
    	int first=0;
    	int sec=0;
    	//모든 조합으로 검사
    	for(int i=1;i<N;i++) {		
    		for(int j=i+1;j<N+1;j++) {
    			if(visited[i]&&visited[j]) {	//첫번째 팀인경우
    				first+=arr[i][j]+arr[j][i];
    			}
    			else if(visited[i]==false&&visited[j]==false){//두번째 팀인 경우
    				sec+=arr[i][j]+arr[j][i];
    			}
    		}
    	}
    	int ret=Math.abs(first-sec);
    	return ret;
    }

 }
