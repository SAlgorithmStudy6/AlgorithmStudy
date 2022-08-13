class Solution {
    static int cnt =0;
    static int answer=0;
    static  int leng;
    public int solution(int[] number) {
        
        leng=number.length;
        sol(0,number,0,0);
        return answer;
    }
    public static void sol(int sum, int[] num,int start,int cnt) {
        
        if(cnt==3&&sum==0){
            answer++;
             System.out.println("answer");
            return;
        }
        else if(cnt==3){
              System.out.println("else if");

            return;
        }
        
            for(int i=start;i<leng;i++){
                System.out.print("i: "+i+" num: "+num[i]+" start: "+start+"    ");
                sum+=num[i]; 
                sol(sum,num,i+1,cnt+1);
                sum-=num[i];
            }
        
        return;
    }
}
