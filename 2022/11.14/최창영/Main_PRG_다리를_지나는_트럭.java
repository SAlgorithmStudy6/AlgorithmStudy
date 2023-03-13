import java.util.*;

public class Main_PRG_다리를_지나는_트럭 {
    static int weightLimit, bridge_length;

    static class Car {
        int weight;
        int time;

        public Car(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    } // End of Car class

    public static void main(String[] args) {
        Main_PRG_다리를_지나는_트럭 m = new Main_PRG_다리를_지나는_트럭();

        int bridge_length = 100;
        int weight = 100;
        int[] truck_weights = {10,10,10,10,10,10,10,10,10,10};

        System.out.println(m.solution(bridge_length, weight, truck_weights));
    } // End of main

    private int solution(int bridge_length, int weight, int[] truck_weights) {
        weightLimit = weight;
        this.bridge_length = bridge_length;
        Queue<Car> bridgeQue = new LinkedList<>();
        int index = 0;
        int time = 1;
        for (; ; ) {

            if (index < truck_weights.length && nowBridgeWeightCheck(bridgeQue, truck_weights[index])) {
                // 들어갈 수 있는 자리가 있으면 한번에 모두 들어감.
                bridgeQue.offer(new Car(truck_weights[index], 0));
                index++;
            }


            //for (Car c : bridgeQue) System.out.println(c.weight + ", " + c.time);
            //System.out.println("time : " + time);

            // 다리에 있는 차량들 전체 적으로 시간 증가.
            increaseTime(bridgeQue);
            if (index >= truck_weights.length && bridgeQue.isEmpty()) break;
            time++;
        }

        return time + 1;
    } // End of solution

    private static boolean nowBridgeWeightCheck(Queue<Car> bridgeQue, int nextCarWeight) { // 현재 다리의 무게 체크
        int sum = 0;
        for (Car car : bridgeQue) {
            sum += car.weight;
            if (sum >= weightLimit) return false;
        }

        sum += nextCarWeight;
        if (sum > weightLimit) return false;
        return true;
    } // End of weightCheck

    private static void increaseTime(Queue<Car> bridgeQue) {
        int size = bridgeQue.size();
        for (int i = 0; i < size; i++) {
            Car car = bridgeQue.poll();

            int nextTime = car.time + 1;
            if (nextTime == bridge_length) continue;

            bridgeQue.offer(new Car(car.weight, nextTime));
        }

    } // End of increaseTime
} // End of Main class
