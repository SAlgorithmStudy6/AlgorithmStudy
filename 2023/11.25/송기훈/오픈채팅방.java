package programmers.lv2;

import java.util.ArrayList;
import java.util.HashMap;

//class Test {
//
//    public static void main(String[] args) {
//        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
//        new 오픈채팅방().solution(record);
//    }
//
//}

public class 오픈채팅방 {

    public String[] solution(String[] record) {
        ArrayList<String> answerList = new ArrayList();
        HashMap<String, String> idMap = new HashMap<>();

        for (int i = 0; i < record.length; i++) {
            String[] sRecord = record[i].split(" ");

            if (sRecord[0].equals("Enter")) {
                if (!idMap.containsKey(sRecord[1])) {
                    idMap.put(sRecord[1], sRecord[2]);
                } else {
                    idMap.replace(sRecord[1], sRecord[2]);
                }
            } else if (sRecord[0].equals("Change")) {
                if (idMap.containsKey(sRecord[1])) {
                    idMap.replace(sRecord[1], sRecord[2]);
                }
            }
        }

        for (int i = 0; i < record.length; i++) {
            String[] sRecord = record[i].split(" ");
            StringBuilder sb = new StringBuilder();

            if (sRecord[0].equals("Change")) continue;

            sb.append(idMap.get(sRecord[1]));
            sb.append("님이 ");
            if(sRecord[0].equals("Enter")) {
               sb.append("들어왔습니다.");
            } else if (sRecord[0].equals("Leave")) {
                sb.append("나갔습니다.");
            }

            answerList.add(sb.toString());
        }

        String[] answer = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

}
