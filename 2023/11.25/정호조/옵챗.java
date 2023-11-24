import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 오픈채팅방 {
    public static void main(String[] args) throws IOException {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};

        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(record)));
    }

    static class Solution {
        public String[] solution(String[] record) {
            Map<String, String> userNameInfo = new HashMap<>(); //id와 nickname을 담는 map
            int answerSize = 0; //Change를 제외한 횟수
            StringTokenizer st;

            //id와 nickname 정보 추출
            for (String curRecord : record) {
                st = new StringTokenizer(curRecord);

                String info = st.nextToken();
                String id = st.nextToken();
                String nickname = st.hasMoreTokens() ? st.nextToken() : "";

                switch (info) {
                    case "Enter": {
                        if (!userNameInfo.containsKey(id)) userNameInfo.put(id, nickname);
                        else if (!userNameInfo.get(id).equals(nickname)) userNameInfo.replace(id, nickname);
                        answerSize++;
                        break;
                    }
                    case "Change": {
                        userNameInfo.replace(id, nickname);
                        break;
                    }
                    case "Leave": {
                        answerSize++;
                        break;
                    }
                }
            }

            String[] answer = new String[answerSize];
            int idx = 0;

            //정답 배열 입력
            for (String curRecord : record) {
                st = new StringTokenizer(curRecord);

                String info = st.nextToken();
                String id = st.nextToken();
                String nickname = userNameInfo.get(id);

                String msgEnter = "님이 들어왔습니다.";
                String msgLeave = "님이 나갔습니다.";

                if (info.equals("Enter")) {
                    answer[idx++] = nickname + msgEnter;
                } else if (info.equals("Leave")) {
                    answer[idx++] = nickname + msgLeave;
                }
            }

            return answer;
        }
    }
}

//좋은 풀이가 있길래 가져와봤습니다. [출처 : https://school.programmers.co.kr/learn/courses/30/lessons/42888/solution_groups?language=java (이규철님)]
//class Solution {
//    private static final String ENTER_FORMAT = "%s님이 들어왔습니다.";
//    private static final String LEAVE_FORMAT = "%s님이 나갔습니다.";
//
//    private HashMap<String, UserInfo> userMap = new HashMap<>();
//
//    private class UserInfo {
//        public String userId;
//        public String nickName;
//
//        public UserInfo(String userId, String nickName) {
//            this.userId = userId;
//            this.nickName = nickName;
//        }
//
//    }
//
//    private class Command {
//        public char command;
//        public String userId;
//
//        public Command(char command, String userName) {
//            this.command = command;
//            this.userId = userName;
//        }
//    }
//
//
//    public String[] solution(String[] records) {
//        ArrayList<Command> commandList = new ArrayList<>();
//
//        for (String record : records) {
//            String[] split = record.split(" ");
//            String command = split[0];
//            String userId = split[1];
//            String nickName = null;
//
//            switch(command.charAt(0)) {
//                case 'E': // Enter
//                    nickName = split[2];
//                    if(userMap.containsKey(userId) == false) {
//                        userMap.put(userId, new UserInfo(userId, nickName));
//                    } else {
//                        userMap.get(userId).nickName = nickName;
//                    }
//
//                    commandList.add(new Command(command.charAt(0), userId));
//                    break;
//                case 'L': // Leave
//                    commandList.add(new Command(command.charAt(0), userId));
//                    break;
//                case 'C': // Change
//                    nickName = split[2];
//                    userMap.get(userId).nickName = nickName;
//                    break;
//            }
//        }
//
//        return commandList.stream()
//                .map(cmd -> String.format( cmd.command == 'E' ? ENTER_FORMAT : LEAVE_FORMAT , userMap.get(cmd.userId).nickName))
//                .toArray(ary -> new String[commandList.size()]);
//    }
//}

