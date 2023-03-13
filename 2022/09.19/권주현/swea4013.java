import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class swea4013 {
    static int  K,ans;
    static int [][]cycle;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int o = 1; o <= T; o++) {
            ans=0;
            K=scan.nextInt();      
            cycle=new int[K][2];
            ArrayList<Deque<Integer>> arr=new ArrayList<>();
            for(int i=0;i<4;i++) {
                arr.add(new ArrayDeque<>());
                for(int j=0;j<8;j++) {
                    int tmp=scan.nextInt();
                    arr.get(i).addLast(tmp);
                }
            }
//            for(int i=0;i<4;i++) {
//                System.out.println(arr.get(i));
//            }
//            
            for(int i=0;i<K;i++) {
                cycle[i][0]=scan.nextInt();   //자석번호
                cycle[i][1]=scan.nextInt();   //회전방향 1(시계) -1(반시계)
                sol(arr,cycle[i][0]-1,cycle[i][1],true,true);
                }
//            System.out.println(arr.get(0).toString().charAt(0));
            
            for(int i=0;i<4;i++) {
                int a=arr.get(i).pollFirst();
//                System.out.println(arr.get(i));
//                System.out.println(a);
                if(a==1) {
                    ans+=Math.pow(2, i);
                }
            }
            
            System.out.println("#" + o + " " + ans);
        }
        

        
        
    }
    public static void sol(ArrayList<Deque<Integer>> arr,int num,int cycle,boolean left_go,boolean right_go) {
        //왼쪽 자석의 3번째 날, 오른쪽 자석의 7번째 날
        //왼쪽이 움직여야하는지 체크
        int left=num-1;
        boolean isleft=false;
        if(left>=0) {   //왼쪽 자석이 존재한다면
            if(arr.get(num).toString().charAt(19)!=arr.get(left).toString().charAt(7)) { //회전 해야한다면
                isleft=true;
            }        
        }
        
        //오른쪽이 움직여야하는지 체크
        int right=num+1;
        boolean isright=false;
        if(right<4) {
//            System.out.println("19: "+arr.get(num).toString().charAt(19)+" 7 : " +arr.get(num).toString().charAt(7));

            if((arr.get(num).toString().charAt(7)-'0')!=(arr.get(right).toString().charAt(19)-'0')) {
                isright=true;
            }
        }
        // 내바퀴 움직이기
        if(cycle==1) {  //시계 방향
            int tmp=arr.get(num).pollLast();
            arr.get(num).addFirst(tmp);
        }else {
            int tmp=arr.get(num).pollFirst();
            arr.get(num).addLast(tmp);
        }
        
        if(isleft&&left_go) {
            sol(arr,left,cycle*(-1),true,false);
        }
        if(isright&&right_go) {
            sol(arr,right,cycle*(-1),false,true);
        }
        
    }
        
}
