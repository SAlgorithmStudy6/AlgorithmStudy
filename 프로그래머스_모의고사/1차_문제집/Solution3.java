import java.util.LinkedList;
import java.util.Stack;

class Solution3 {
	public static void main(String[] args) {
		Solution3 s = new Solution3();

		int order[] = { 4, 3, 1, 2, 5 };
		System.out.println(s.solution(order));
	} // End of main

	public int solution(int[] order) {
		// 보조컨테이너 벨트 -> 스택구조

		// 1, 2, 3, 4, 5로 된 택배를
		// 4, 3, 1, 2, 5로 만들어라 가능한 갯수
		// 불가능할 경우, -1을 출력
		int len = order.length;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < len; i++) {
			list.add(i + 1);
		}

		// target값과 list값을 확인
		// 같을 경우, result++
		// 다를 경우, stack확인

		// 만약 stack이 비어있다. 바로 중지
		
		// 컨베이어 벨트에서 나올 수 있는 값과 
		// 다음 택배 번호가 다르다

		int result = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < len; i++) {
			int target = order[i];
			
			for(int j=0; j<len; j++) {
				if(!list.isEmpty()) {
					if (target == list.poll()) {
						result++;
					} else {
						if (stack.isEmpty()) {
							stack.push(target);
							list.poll();
							continue;
						}

						if (stack.peek() != target) {
							stack.push(target);
							list.poll();
							if(list.isEmpty()) {
								break;
							}
							
							continue;
						}

						if (stack.peek() == target) {
							stack.pop();
							result++;
						}

					}
				}
				
			}
		}

		return result;
	} // End of solution
} // End of Solution3