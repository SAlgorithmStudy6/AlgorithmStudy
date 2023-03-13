import java.util.*;

class Solution {
  	static ArrayList<Integer> list = new ArrayList<>();
	static int size;
	static int arr[];
	static HashSet<Integer> set;

    public int solution(int[] elements) {
        size = elements.length;
		arr = new int[size * 2];
		set = new HashSet<>();
        for (int i = 0; i < size * 2; i++) {
			arr[i] = elements[i % size];
		}
		for (int start = 0; start < size; start++) { // 시작점 잡고
            int sum = 0;
            int i = 0;
            while(i < size && i + start < size * 2) { // 1개부터 size개까지
                sum += arr[i + start];
                set.add(sum);
                i++;
            }
        }	
		return set.size();
	}

}
