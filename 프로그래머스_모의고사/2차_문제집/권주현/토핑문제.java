import java.util.*;
class Solution {
    static  int leng;
    static  int answer =0;
    public int solution(int[] topping) {
        leng=topping.length;
        if(leng==1){
            return 0;
        }
        int[] arr=new int[topping.length];
        System.arraycopy(topping,0,arr,0,topping.length );
        int top_num=count(arr); //토핑 개수

        for(int i=0;i<topping.length;i++){
            System.out.print(topping[i]+" ");
        }

        if(top_num%2==1){//토핑 개수가 홀수이면
            System.out.println("홀수 "+top_num);
            return 0;
        }
        else {
            
            for(int i=1;i<leng;i++){
                if(i>=top_num/2&&leng-i>=top_num/2){
                    System.out.println("if문 안 "+i);
                    sol(i,topping,top_num); //cut 하는 위치 매개변수로 
                }   
            }
        }
        return answer;
    }
    public static void sol(int cut,int[] topping,int top_num){
        HashMap<Integer,Integer> hash=new HashMap<>();
        HashMap<Integer,Integer> hash2=new HashMap<>();
        int cnt=0;
        int cnt2=0;

        for(int i=0;i<cut;i++){
            if(!hash.containsKey(topping[i])){ //새로운 토핑이라면
                System.out.println("cnt++ "+i+" "+ topping[i]);
                hash.put(topping[i],1);
                cnt++;
            }   
        }
        if(cnt==top_num/2){
            System.out.println("------");
             for(int i=cut;i<leng;i++){
                if(!hash2.containsKey(topping[i])){ //새로운 토핑이라면
                    System.out.println("cnt++ "+i+" "+ topping[i]);
                    hash2.put(topping[i],1);
                    cnt2++;
                }   
            }
            if(cnt2==top_num/2){
                System.out.println("answer");
                answer++;
            }
            
            
        }
    }
    public static int count(int [] topping2) {
        int cnt=0;
        Arrays.sort(topping2);

        for(int i=0;i<topping2.length-1;i++){
            if(topping2[i]!=topping2[i+1]){
                cnt++;
            }
        }
        return cnt+1;
    }
}
