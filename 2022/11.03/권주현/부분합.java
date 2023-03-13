import java.util.*;
class Solution {
    Set<Integer> set=new HashSet<Integer>();
    public int solution(int[] elements) {
        int answer = 0;
        int leng=elements.length;
        int[] arr=new int[leng*2+1];
        System.arraycopy(elements,0,arr,0,leng);
        System.arraycopy(elements,0,arr,leng,leng);
        
        // for(int i=0;i<leng*2;i++){
        //     System.out.println(arr[i]);
        // }
        for(int i=1;i<=leng;i++){    //수열의 길이
            for(int j=0;j<leng;j++){//시작점
                int end=j+i;
                int sum=0;
                for(int a=j;a<end;a++){
                    sum+=arr[a];
                }
                set.add(sum);
                // System.out.println(sum);
            }
            
        }
        int ans=set.size();
        return ans;
    }
    
}
