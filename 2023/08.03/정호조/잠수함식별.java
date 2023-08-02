import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 잠수함식별_2671 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String ans = "SUBMARINE";

        int cnt = 0;

        if(str.length() == 3 || str.charAt(str.length() - 1) == '0'){   //조합해봤을 때 문자열의 길이가 3이거나 마지막 문자열이 0인 경우의 수는 없음
            ans = "NOISE";
            System.out.println(ans);
        } else {
            while (cnt < str.length()) {
                if (str.substring(cnt, Math.min(cnt + 2, str.length())).equals("01")) { //01은 연속할 수 있는 경우가 없으므로 바로 자름
                    cnt += 2;
                } else if (str.substring(cnt, Math.min(cnt + 3, str.length())).equals("100")) { //100은 뒤에 0이 연속할 수도 있고 1이 연속할 수 도 있으므로 체크
                    cnt +=3;
                    while (cnt < str.length() && str.charAt(cnt) == '0'){
                        cnt++;
                    }
                    while (cnt < str.length() && str.charAt(cnt) == '1'){
                        cnt++;
                    }
                } else if(cnt > 2 && str.substring(cnt - 1, Math.min(cnt + 2, str.length())).equals("100") && str.charAt(cnt - 2) == '1'){ //100110011001
                    cnt += 2;
                    while (cnt < str.length() && str.charAt(cnt) == '0'){
                        cnt++;
                    }
                    while (cnt < str.length() && str.charAt(cnt) == '1'){
                        cnt++;
                    }
                }else {
                    ans = "NOISE";
                    break;
                }
            }

            System.out.println(ans);
        }

    }
}
