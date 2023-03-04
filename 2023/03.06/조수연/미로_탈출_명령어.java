import java.awt.*;
import static java.lang.Math.abs;

public class Problem {
    public static void main(String[] args) {
        new SolutionJava().solution(3, 4, 2, 3, 3, 1, 5);
    }
}

class SolutionJava {
    //사전 순이므로 방향 벡터를 하 좌 우 상 순으로 세팅
    int[] dx = {0, -1, 1, 0};
    int[] dy = {1, 0, 0, -1};
    char[] move = {'d', 'l', 'r', 'u'};
    StringBuilder sb = new StringBuilder();
    String answer = "impossible";

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int distance = abs(r - x) + abs(c - y);
        // E까지 거리가 k보다 작은 경우이거나 2로 나눈 나머지가 홀수인 경우에는 어차피 도달 못하니 impossible
        if (k >= distance && (k - distance) % 2 == 0) dfs(new Point(y - 1, x - 1), new Point(c - 1, r - 1), k, 0, n, m);
        return answer;
    }

    public void dfs(Point startPoint, Point endPoint, int k, int count, int n, int m) {
        if (count > k || !answer.equals("impossible")) return; //횟수를 초과하거나 단어를 찾으면 return
        for (int i = 0; i < dx.length; i++) { //방향이동
            if (!answer.equals("impossible")) break;
            int x = startPoint.x + dx[i];
            int y = startPoint.y + dy[i];
            int d = abs(endPoint.x - x) + abs(endPoint.y - y);
            // 영역 안이거나
            // 현재 위치에서 E까지 거리가 k - 이동한 횟수보다 작거나
            // k - 이동한 횟수 - 현재위치부터 E까지 거리를 2로 나눴을 때 나머지가 홀수인 경우 이동이 가능
            if (x >= 0 && x < m && y >= 0 && y < n &&d <= k - count && (k - count - d) % 2 != 0){
                if (count + 1 == k && x == endPoint.x && y == endPoint.y) {
                    answer = sb.append(move[i]).toString();
                    return;
                } else {
                    sb.append(move[i]);
                    dfs(new Point(x, y), endPoint, k, count + 1, n, m);
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
    }
}


