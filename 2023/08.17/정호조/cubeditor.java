import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cubeditor_1701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int ans = 0;
        for(int i=0; i<str.length(); i++){
            ans = Math.max(ans, KMP(str.substring(i)));
        }

        System.out.println(ans);
    }

    static int KMP(String pattern) {
        int[] table  = new int[pattern.length()];
        int idx =0, max = 0;

        for(int i=1; i<pattern.length(); i++){
            while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)){
                idx = table[idx - 1];
            }
            if(pattern.charAt(i) == pattern.charAt(idx)){
                idx++;
                table[i] = idx;
                max = Math.max(max, table[i]);
            }
        }

        return max;
    }
}
