package problum1543;

import java.util.Scanner;

public class 문서검색_1543 {
	 public static void main(String[] args){
		 Scanner sc = new Scanner(System.in);
         String str1 = sc.next();
         String str2 = sc.next();
         int cnt = 0;
         
         for(int i=0; i<str1.length() - str2.length() +1; i++) {
        	 String temp = str1.substring(i, i+str2.length());
        	 if(temp.equals(str2)) {
        		 cnt ++;
        		 i += str2.length()-1;
        	 }
         }
         System.out.println(cnt);
         
	    }
}
