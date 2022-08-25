import java.util.*;
public class Main {

	public static Scanner scan =new Scanner(System.in);
	public static int n,m,t;
	public static boolean[] visited; //정점을 방문 했는지 true/false
	public static StringBuilder sb=new StringBuilder();
	public static int[] MAX,T,P;
	static int ans,blue,white,cnt1,cnt2;
    public static void main(String[] args) {
    	//입력
    	n=scan.nextInt();	//한변의 길이
     	MAX=new int[n+2];
     	T=new int[n+1];
     	P=new int[n+1];
     	for(int i=1;i<n+1;i++) {
     		T[i]=scan.nextInt();
     		P[i]=scan.nextInt();
     	}
     
     	for(int i=1;i<n+1;i++) {
     		int next=1;
     		next=i+T[i];
     		if(next<=n+1) {
     			MAX[next]=Math.max(findmax(i,MAX)+P[i], MAX[next]);
     		}
     	}
     	int max=0;
     	for(int i=1;i<n+2;i++) {  		
     		max=Math.max(MAX[i], max);
     	}
     	System.out.println(max);
     	
     	
    }
    static int findmax(int i, int[] max) {
    	int max1=0;
    	for(int j=1;j<=i;j++) {
    		max1=Math.max(max[j],max1);
    	}
    	return max1;
    }
 }
