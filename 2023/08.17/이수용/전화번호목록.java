import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/5052

public class 전화번호목록 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] contactList = new String[N];
            for (int i = 0; i < N; i++) {
                contactList[i] = br.readLine();
            }
            Arrays.sort(contactList);
            boolean flag = true;
            for(int i = 0; i < contactList.length - 1; i++){
                if(contactList[i + 1].startsWith(contactList[i])) {
                    System.out.println("NO");
                    flag = false;
                    break;
                }
            }
            if(flag) System.out.println("YES");
            tc--;
        }
    }
}
