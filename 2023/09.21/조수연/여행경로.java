import java.util.*;

class Test {
    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"},
                {"ATL", "SFO"}};
        System.out.println(Arrays.toString(new Solution().solution(tickets)));
    }
}

class Solution {
    static final int MAX_SIZE = 10000;
    static Map<String, Integer> map;
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<String> paths;
    static String[] cities;
    static int[][] visited; // 주어진 티켓이 중복일 경우를 고려한 체크 배열

    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        int value = 0;
        int start;
        int end;
        map = new HashMap<>();
        graph = new ArrayList<>();
        paths = new ArrayList<>();
        cities = new String[MAX_SIZE];
        visited = new int[MAX_SIZE][MAX_SIZE];

        // 인천공항에서 모든 경로가 시작되니 0으로 초기화
        map.put("ICN", 0);
        cities[0] = "ICN";

        for (int i = 0; i < MAX_SIZE; i++) {
            graph.add(new ArrayList<>());
        }

        for (String[] ticket : tickets) { // 경로 갱신
            if (!map.containsKey(ticket[0])) {
                value++;
                start = value;
                map.put(ticket[0], start);
                cities[start] = ticket[0];
            } else start = map.get(ticket[0]);

            if (!map.containsKey(ticket[1])) {
                value++;
                end = value;
                map.put(ticket[1], end);
                cities[end] = ticket[1];
            } else end = map.get(ticket[1]);

            visited[start][end]++;
            graph.get(start).add(end);
        }

        int[] arr = new int[tickets.length + 1];

        dfs(0, 1, tickets.length + 1, arr);

        Collections.sort(paths); // 사전순으로 정렬

        String path = paths.get(0);
        StringBuilder sb = new StringBuilder();
        int idx = 0;

        for (int i = 0; i < path.length(); i++) {
            sb.append(path.charAt(i));

            if (i % 3 == 2) {
                answer[idx++] = sb.toString();
                sb.setLength(0);
            }
        }

        return answer;
    }

    public void dfs(int city, int idx, int size, int[] arr) {
        if (idx == size) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < arr.length; i++) {
                sb.append(cities[arr[i]]);
            }

            paths.add(sb.toString());

            return;
        }

        for (int next : graph.get(city)) {
            if (visited[city][next] > 0) {
                visited[city][next]--;
                arr[idx] = next;
                dfs(next, idx + 1, size, arr);
                visited[city][next]++;
            }
        }
    }
}