import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 수족관1_8982 {
    static int N, K, ans;
    static int[] box, hole, water;
    static ArrayList<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        int len = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            len = Math.max(len, c);

            list.add(new Node(r, c));
        }

        box = new int[len];
        water = new int[len];

        int cnt = 0;

        for (int i = 1; i < list.size() - 1; i += 2) {
            int c1 = list.get(i).c;
            int c2 = list.get(i + 1).c;
            for (int j = c1; j < c2; j++) {
                box[cnt] = list.get(i).r;
                cnt++;
            }
        }

        K = Integer.parseInt(br.readLine());
        hole = new int[K];

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            hole[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getResult());
    }

    static int getResult(){
        for(int i=0; i<K; i++){
            int h = hole[i];
            int curH = box[h];
            water[h] = curH;

            for(int j=h; j<box.length; j++){
                if(curH < box[j]){
                    water[j] =  Math.max(water[j], curH);
                } else {
                    curH = box[j];
                    water[j] = Math.max(water[j], curH);
                }
            }
            curH = box[h];

            for(int j=h; j>=0; j--){
                if(curH < box[j]){
                    water[j] =  Math.max(water[j], curH);
                } else {
                    curH = box[j];
                    water[j] = Math.max(water[j], curH);
                }
            }
        }

        for(int i=0; i<box.length; i++){
            ans += box[i] - water[i];
        }

        return ans;
    }

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
