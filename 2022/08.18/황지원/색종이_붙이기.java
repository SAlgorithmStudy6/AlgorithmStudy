import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
    // 각 크기별 색종이 개수
    static int map[] = {0, 5, 5, 5, 5, 5};
    static int arr[][];
    static int result=0, minValue = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[10][10];
        StringTokenizer st;
        for(int i=0; i<10; i++) {
             st = new StringTokenizer(br.readLine());
             for(int j=0; j<10; j++)
                 arr[i][j] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0);
        // 1로 모두 덮는 것이 불가능할 때
        if(minValue == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minValue);
    }
    
    public static void dfs(int currentX, int currentY) {
    	
        // 세로 한칸에 해당하는 가로 탐색이 끝났을때 y 인덱스 + 1
        if(currentX == 10) {
            dfs(0, currentY+1);
            return;
        }
        // y 인덱스가 10 이면 탐색이 끝난 것 => currentX=9, currentY=9에서 currentX=10, currentY=9 -> currnetX=0 , currentY=10 호출
        if(currentY == 10) {
            minValue = Math.min(minValue, result);
            return;
        }
        // 0이면 해당 부분은 그냥 넘어간다.
        if(arr[currentY][currentX] == 0) {
            dfs(currentX+1, currentY);
            return;
        }
        System.out.println("dfs (" + currentX + ", " + currentY + ")");
        // arr[currentY][currentX] == 1 이면 해당하는 부분부터 사이즈에 맞는 색종이를 붙여본다.
        for(int size=5; size>0; size--) {
            boolean isPossible = true;
            
            if(map[size] == 0 || currentY + size > 10 || currentX + size > 10) // 크기를 초과하면 continue로 가서 Size-1
                continue;
            
            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    if(arr[currentY + i][currentX + j] == 0) { // 사이즈만큼 개수가 되는지 확인
                        isPossible = false;
                        break;
                    }
                }
                if(!isPossible)
                    break;
            }
            // 해당 사이즈가 불가능하면 Continue
            if(!isPossible)
                continue;
            
            // 해당 사이즈가 가능하면 0 으로 채워준다.
            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    arr[currentY + i][currentX + j] = 0; 
                }
            }
            // 해당 사이즈의 색종이 개수 -1, 사용한 개수 +1
            map[size]--;
            result++;
            
            dfs(currentX + size, currentY);
            
            // 백트래킹을 위해 복구
            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    arr[currentY + i][currentX + j] = 1; 
                }
            }
            
            map[size]++;
            result--;
        }
            
    }
}
