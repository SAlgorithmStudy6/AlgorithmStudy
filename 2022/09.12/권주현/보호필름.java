import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    static int [][] arr;
    static int D,W,K;
    static boolean visited[];
    static int min;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
        int T=scan.nextInt();
        for(int o=1;o<=T;o++) {
            D=scan.nextInt();
            W=scan.nextInt();
            K=scan.nextInt();   //합격기준 두께
            arr=new int[D][W];  
            min=Integer.MAX_VALUE;
            visited=new boolean[D];
            for(int i=0;i<D;i++) {
                for(int j=0;j<W;j++) {
                    arr[i][j]=scan.nextInt();
                }
            }
                    for(int i=0;i<D;i++) {
                      //DCi 실행->약품주입 a or b->search
                        medi(0,0,i,0);
                        if(min<Integer.MAX_VALUE) break;

                    }
                    System.out.println("#"+o+" "+ min);     
        }  
    }
    public static boolean search() {
        for(int i=0;i<W;i++) {
            int tmp_cnt=1;
            int pre=arr[0][i];
            int[] ab= new int[2];
            ab[pre]++;
            for(int j=1;j<D;j++) {
                if(pre==arr[j][i]) {    //이전 값과 같으면
                       tmp_cnt++;
                       pre=arr[j][i];
                }
                else {  //같지않으면
                    if(tmp_cnt>ab[pre]) {
                        ab[pre]=tmp_cnt;    //저장된 값보다 크면 저장해주기
                    }
                    pre=arr[j][i];
                    tmp_cnt=1;
                }
                
                if(j==D-1) {        //마지막이면
                    if(tmp_cnt>ab[pre]) {
                        ab[pre]=tmp_cnt;    //저장된 값보다 크면 저장해주기
                    }
                    pre=arr[j][i];
                    tmp_cnt=1;
                }
            }
            if(ab[0]<K&&ab[1]<K) {
                return false;
            }
        }
        return true;
    }
    public static void medi(int start,int pos,int end_depth,int depth) {
        
        if(end_depth==depth) {
            if(search()) {
//                System.out.println(start);
                   min=Math.min(depth, min);
             }
            return;
        }
            

        for(int i=start;i<D;i++) {
            ArrayList<Integer> list=new ArrayList<Integer>();
            //약품주입 a
            for(int j=0;j<W;j++) {
                if(arr[i][j]==1) {
                    arr[i][j]=0;
                    list.add(j);
                }
            }
            medi(i+1,pos+1,end_depth,depth+1);
            
            //되돌리기
            for(int m=0;m<list.size();m++) {
                arr[i][list.get(m)]=1;
            }
            
            //약품주입 b
            ArrayList<Integer> list2=new ArrayList<Integer>();
            for(int j=0;j<W;j++) {
                if(arr[i][j]==0) {
                    arr[i][j]=1;
                    list2.add(j);
                }
            }
            medi(i+1,pos+1,end_depth,depth+1);
            //되돌리기
            for(int m=0;m<list2.size();m++) {
                arr[i][list2.get(m)]=0;
            }
        }
        
    }
}
