import java.util.Scanner;

public class Main {
	static int D, K;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
	
		D=scan.nextInt();
		K=scan.nextInt();
		for(int i=1;i<=K;i++) {
			for(int j=i;j<=K;j++) {
				int[] arr=sol(i,j);
				if(arr[0]==K) {
					System.out.println(i);
					System.out.println(j);
					System.exit(0);
				}
			}
		}
		

	}
	static int[] sol(int a,int b) {
		int nexta=a,nextb=b;
		for(int i=0;i<D-1;i++) {
			int aa=nexta;
			int bb=nextb;
			nexta=bb;
			nextb=aa+bb;
		}
//	System.out.println(a+" "+b+" ->"+nexta+" "+nextb);
		int[] arr= {nexta,nextb};
		return arr;
	}
	

}
