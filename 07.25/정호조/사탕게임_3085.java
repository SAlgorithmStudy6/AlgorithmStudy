package problum3085;

import java.util.*;

public class 사탕게임_3085 {
	public static char[][] arr;
    public static int n;
    public static int MAX_VALUE = 0;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new char[n][n];

        String line = "";
        for(int i = 0; i < n; i++) {
            line = sc.next();
            arr[i] = line.toCharArray();
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n-1; j++) {
                char swap = arr[i][j];
                arr[i][j] = arr[i][j+1];
                arr[i][j+1] = swap;
                search();
                swap = arr[i][j];
                arr[i][j] = arr[i][j+1];
                arr[i][j+1] = swap;
            }
        }

        // 열
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n-1; j++) {
                char swap = arr[j][i];
                arr[j][i] = arr[j+1][i];
                arr[j+1][i] = swap;
                MAX_VALUE = Math.max(search(), MAX_VALUE);
                swap = arr[j][i];
                arr[j][i] = arr[j+1][i];
                arr[j+1][i] = swap;
            }
        }


        System.out.println(MAX_VALUE);

    }

    private static int search() {
    	int cnt = 1;
        for(int i = 0; i < n; i++) {
        	cnt = 1;
            for(int j = 0; j < n-1; j++) {
                if(arr[i][j] == arr[i][j+1]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                MAX_VALUE = Math.max(MAX_VALUE, cnt);
            }
        }

        for(int i = 0; i < n; i++) {
            cnt = 1;
            for(int j = 0; j < n-1; j++) {
                if(arr[j][i] == arr[j+1][i]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                MAX_VALUE = Math.max(MAX_VALUE, cnt);
            }
        }
        return MAX_VALUE;
    }
}
