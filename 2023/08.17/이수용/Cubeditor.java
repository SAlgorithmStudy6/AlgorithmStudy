import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cubeditor {
    static int max =0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(makeTable(br.readLine()));
    }

    static int makeTable(String parent) {
        int parentLength = parent.length();
        for(int pos=0; pos < parentLength; pos++) {
            String pattern = parent.substring(pos);
            int patternLength = pattern.length();
            // table[idx] : 패턴의 idx번째 문자까지 중복출현하는 최장 문자열 길이
            int[] table = new int[patternLength];
            // 접두사 길이
            int idx = 0;
            for(int i=1; i < patternLength; i++) {
                while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) {
                    // 여기가 이해 안됨
                    idx = table[idx-1];
                }
                // 일치하면 접두사 인덱스 한칸 이동. 접두사의 길이 최댓값 비교 후 갱신
                if(pattern.charAt(i) == pattern.charAt(idx)) {
                    table[i] = ++idx;
                    max = Math.max(max, idx);
                }
            }
        }
        return max;
    }
}
