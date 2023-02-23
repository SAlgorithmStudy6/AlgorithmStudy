import java.util.ArrayList;
import java.util.Collections;

public class Problem {
    public static void main(String[] args) {
        int[] cards = {8,6,3,7,2,5,1,4};
        new SolutionJava().solution(cards);
    }
}

class SolutionJava {
    public int solution(int[] cards) {
        boolean[] checked = new boolean[cards.length]; //카드 뽑기 중복 체크 배열
        ArrayList<ArrayList<Integer>> boxes = new ArrayList<>(); //카드 상자 그룹
        int bIndex = 0; //박스 그룹 인덱스

        for (int i = 0; i < cards.length; i++){
            boxes.add(new ArrayList<>());
        }

        for (int i = 0; i < cards.length; i++) {
            if (!checked[i]){
                int cIndex = i; //뽑는 카드의 인덱스
                while (true){
                    if (checked[cIndex]) break; //이미 열려있는 상자인 경우 다음 그룹으로 넘어가기
                    checked[cIndex] = true;
                    boxes.get(bIndex).add(cards[cIndex]);
                    cIndex = cards[cIndex]-1;
                }
                bIndex++;
            }
        }

        Collections.sort(boxes,((o1, o2) -> o2.size() - o1.size())); //길이 기준으로 내림차순 정렬

        return boxes.get(0).size() * boxes.get(1).size();
    }
}


