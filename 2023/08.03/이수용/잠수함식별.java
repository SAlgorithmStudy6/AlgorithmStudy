import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

// https://www.acmicpc.net/problem/2671

public class 잠수함식별 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sound = br.readLine();
        String ans = "NOISE";
        if (Pattern.matches("(100+1+|01)+", sound))
            ans = "SUBMARINE";
        System.out.println(ans);
    }
}