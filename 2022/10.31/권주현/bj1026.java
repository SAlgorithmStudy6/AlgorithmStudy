import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import java.util.Scanner;

 

public class bj1026 {

	static Scanner scan=new Scanner(System.in);

 

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		int N=scan.nextInt();

		int[] A=new int[N];

		Integer[] B=new Integer[N];

		int[] C=new int[N];

		HashMap<Integer,ArrayList<Integer>> h=new HashMap<>();

		for(int i=0;i<N;i++) {

			A[i]=scan.nextInt();

		}

		for(int i=0;i<N;i++) {

			B[i]=scan.nextInt();

//			if(!h.containsKey(B[i])) {
//
//				ArrayList<Integer>list=new ArrayList<>();
//
//				list.add(i);
//
//				h.put(B[i], list);
//
//			}
//
//			else{//이미 키값이 있을 때
//
//				ArrayList<Integer> list=h.get(B[i]);
//
//				list.add(i); 
//
//				h.put(B[i], list);
//
//			}

			

		}
		
		Arrays.sort(A);
		Arrays.sort(B,Collections.reverseOrder());
		int sum=0;
		for(int i=0;i<N;i++) {
			sum+=A[i]*B[i];
		}
		System.out.println(sum);

		

	}

 

}
