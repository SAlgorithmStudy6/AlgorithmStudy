import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Solution2 {
	public static void main(String[] args) {
		Solution2 t = new Solution2();
		String want[] = {"banana", "apple", "rice", "pork", "pot"};
		int number[] = {3, 2, 2, 2, 1};
		String discount[] = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
		
		System.out.println(t.solution(want, number, discount));
	}// End of main
	
    public int solution(String[] want, int[] number, String[] discount) {
        HashMap<String, Integer> wantMap = new HashMap<>();
        int total = 0;
        for(int i=0; i<want.length; i++) {
        	wantMap.put(want[i], number[i]);
        	total += number[i];
        }
        int result = 0;
        
        // 제품을 모두 할인 받을 수 있는 날을 계산
        int len = discount.length - 10; //length는 길이 기준으로 1부터 시작하고, index는 0부터 시작하므로, 14 - 9가 맞는 계산임 하지만 10으로 하고, 조건을 이상으로 주면됨.
        for(int i=0; i<=len; i++) {
        	HashMap<String, Integer> discountMap = new HashMap<>();
        	for(int j=i; j<i+10; j++) {
        		discountMap.put(discount[j], discountMap.getOrDefault(discount[j], 0)+1);
        	}
        	
    		Iterator<Entry<String, Integer>> it = wantMap.entrySet().iterator();
    		int sum = 0;
    		while(it.hasNext()) {
    			Entry<String, Integer> entrySet = (Entry<String, Integer>)it.next();
    			String key = entrySet.getKey();
    			int value = entrySet.getValue();
    			
    			//key에 해당하는 값과 한번이라도 다를 경우,
    			if(discountMap.containsKey(key)) {
    				if(discountMap.get(key) != value ) {
        				break;
        			}
    				else sum += discountMap.get(key);
    			}
    		}
    			
    		
    		if(sum == total) {
    			result++;
    		}
        }
        
        return result;
    } // End of solution
} // End of Test2 class