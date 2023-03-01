public class PRG_N_Queen {
    static int[] arr;
    static int N;
    static int result;

    public static void main(String[] args) {
        PRG_N_Queen s = new PRG_N_Queen();

        System.out.println(s.solution(
                4
        ));
    } // End of main

    public int solution(int n) {
        N = n;
        arr = new int[N];
        result = 0;

        DFS(0);
        return result;
    } // End of solution

    private static void DFS(int depth) {
        if (depth == N) {
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[depth] = i;

            if(isPossibleCheck(depth)) {
                DFS(depth + 1);
            }
        }
    } // End of DFS

    // 해당 컬럼 위치에 가능한지 유망성을 체크
    private static Boolean isPossibleCheck(int colNum) {
        for (int i = 0; i < colNum; i++) {
            if(arr[colNum] == arr[i]) {
                return false;
            }
            
            // 대각선 체크
            // 대각선 -> 열의 값 차이와 행의 값 차이가 같을 경우 같은 대각선 상에 위치함
            if(Math.abs(arr[colNum] - arr[i]) == Math.abs(colNum - i)) {
                return false;
            }
        }

        return true;
    } // End of isPossibleCheck
} // End of Solution class
