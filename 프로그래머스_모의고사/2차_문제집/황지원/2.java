import java.util.*;

class Solution {
    
    static int answer = 0;
    static int me[] = new int [5];
    static int bro[] = new int[5];
    static int me_int = 0;
    static int bro_int = 0;
    static int topping[];
    static boolean visited[];

    public int solution(int[] top) {
        topping = top;
        visited = new boolean [top.length+1];
        binary_search(topping.length/2, topping.length);
        
        return answer;
    }

    void binary_search(int start, int end){ // i의 시작과 끝을 받는다. 내 토핑
        // 종료 조건
        if (1 > start || start >= end){
           return;
        }

        // 값 초기화
        Arrays.fill(me, 0);
        Arrays.fill(bro, 0);
        me_int = 0; 
        bro_int = 0;


        // 토핑 담기
        for(int j=0; j<start; j++) // i 미만 내꺼
            me[topping[j]] += 1;
        for(int k=start; k<topping.length; k++) // i 초과 동생꺼
            bro[topping[k]] += 1;

        
        // 토핑 종류 세기
        me_int = check_arr(me);
        bro_int = check_arr(bro);


        if (me_int == bro_int){
            if(visited[start] == false){
                 answer++;
                 visited[start] = true;
            }  
        }
        binary_search(start-1, start);
        binary_search(start+1, end);
    }


    static int check_arr(int arr[]){
        int count = 0;
        for(int i=1; i<5; i++){
            if(arr[i] != 0)
                count++;
        }
        return count;
    }


}
