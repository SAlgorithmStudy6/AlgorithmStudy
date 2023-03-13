import java.util.*;

public class 다리를지나는트럭 {

	public static void main(String[] args) {

		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
		Solution s = new Solution();
		s.solution(bridge_length, weight, truck_weights);

	}

	public static class Solution {

		public int solution(int bridge_length, int weight, int[] truck_weights) {
			
			int answer = Integer.MAX_VALUE;
			ArrayList<Integer> truck = new ArrayList<>();
			ArrayList<Car> curTruck = new ArrayList<>();
			
			for(int i=0; i<truck_weights.length; i++) {
				truck.add(truck_weights[i]);
			}
			
			int time = 0;
			int curWeight = 0;

			while (true) {
				if (truck.size() == 0 && curTruck.size() == 0) {
					break;
				}
				
				time++;
				
				if (curTruck.size() != 0 && time - curTruck.get(0).enterTime == bridge_length) {
					curWeight -= curTruck.get(0).weight;
					curTruck.remove(0);
				}
				
				if (truck.size() != 0 && curTruck.size() < bridge_length && curWeight < weight) {
					if (curWeight + truck.get(0) <= weight) {
						curTruck.add(new Car(truck.get(0), time));
						curWeight += truck.get(0);
						truck.remove(0);
					}
				}
			}

			answer = Math.min(answer, time);

			System.out.println(answer);
			return answer;
		}

		public static class Car {
			int weight, enterTime;

			public Car(int weight, int enterTime) {
				this.weight = weight;
				this.enterTime = enterTime;
			}
		}
	}	
}
