import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;


// TreeSet을 통해서 2명의 토핑이 같은지 개수를 파악
// 홀수인 토핑이 홀수개, 짝수인 토핑이 짝수개
// 토핑의 개수가 중복되지 않고 같아야 함 (토핑의 종류는 달라도 상관이 없음)

public class Solution2 {	
	static HashMap<Integer, Integer> checkMap = new HashMap<>();
	public static void main(String[] args) {
		Solution2 s = new Solution2();
		
		int topping[] = { 1, 2, 1, 3, 1, 4, 1, 2 };
		System.out.println(s.solution(topping));
	} // End of main
		
    public int solution(int[] topping) {
        int len = topping.length;
        for(int i=0; i<len; i++) {
        	checkMap.put(topping[i], checkMap.getOrDefault(topping[i], 0)+1);
        }
        if(!checking()) {
        	return 0;
        }
        
        int result = 0;
        for(int cutting=0; cutting<len-1; cutting++) {
        	TreeSet<Integer> cheolsuSet = new TreeSet<>();
        	TreeSet<Integer> brotherSet = new TreeSet<>();
        	
        	for(int i=0; i<=cutting; i++) {
        		cheolsuSet.add(topping[i]);
        	}
        	
        	for(int i=cutting+1; i<len; i++) {
        		brotherSet.add(topping[i]);
        	}	
        	

        	if(cheolsuSet.size() == brotherSet.size()) {
        		result++;
        	}
        }

        return result;
    } // End of solution
    
    private static boolean checking() {
    	// 각 토핑별로 계산해서 홀수개인 토핑이 홀수개일 경우, 불가능하다고 판단.
    	int oddNum = 0;

    	Iterator<Entry<Integer, Integer>> it = checkMap.entrySet().iterator();
    	while(it.hasNext()) {
    		Entry<Integer, Integer> entrySet = (Entry<Integer, Integer>)it.next();
    		int value = entrySet.getValue();
    		
    		if(value % 2 == 1) {
    			oddNum++;
    		}
    	}
    	
    	if(oddNum % 2 == 1) {
    		return false;
    	}    
    	
    	return true;
    } // End of checking
} // End of Main class