import java.util.*;

public class Problem {
    public static void main(String[] args) {
        new SolutionJava().solution("hit","cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
    }
}

class WordInfo{
    String word;
    ArrayList indexList = new ArrayList<Integer>();

    public WordInfo(String word, ArrayList indexList) {
        this.word = word;
        this.indexList = indexList;
    }
}
class SolutionJava {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        Queue<WordInfo> queue = new LinkedList<>();

        boolean isExist = false;

        for (int i = 0; i < words.length; i++) { // 단어가 존재하는지 체크
            if (words[i].equals(target)) {
                isExist = true;
                break;
            }
        }

        if (isExist){
            for (int i = 0; i < words.length; i++) { // 글자 수 하나 다른 단어 선택하기
                int count = 0;
                for (int j = 0; j < begin.length(); j++) {
                    if (begin.charAt(j) != words[i].charAt(j)) count++;
                    if (count > 1) break;
                }

                if (count == 1){
                    ArrayList<Integer> indexList = new ArrayList<>();
                    indexList.add(i);
                    queue.add(new WordInfo(words[i],indexList));
                }

            }
            answer = bfs(queue,target,words);
        }

        return answer;
    }

    public int bfs(Queue<WordInfo> queue, String target, String[] words){
        int count = 1;

        while (!queue.isEmpty()){
            for (int i = 0; i < queue.size(); i++) {
                WordInfo info = queue.poll();
                String changeWord = info.word; // 바뀐 단어
                ArrayList<Integer> indexList = info.indexList; // 방문한 인덱스 리스트

                if (changeWord.equals(target)) return count; // poll 했을 때 target이면 count 반환

                for (int j = 0; j < words.length; j++) {
                    if (!indexList.contains(j)){ // 중복 제거
                        int diffCnt = 0;
                        for (int k = 0; k < changeWord.length(); k++) {
                            if (changeWord.charAt(k) != words[j].charAt(k)) diffCnt++;
                            if (diffCnt > 1) break;
                        }

                        if (diffCnt == 1){ // 문자가 하나만 다르면 queue에 add
                            indexList.add(j);
                            queue.add(new WordInfo(words[j],indexList));
                        }
                    }
                }
            }
            count++;
        }
        return count;
    }
}


