import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_트리_1068 {
    static int N, D, ans = 0;
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/1068.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        int root = 0;
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p == -1) {
                root = i;
                continue;
            }
            nodes[p].child.add(nodes[i]);
        }
        D = Integer.parseInt(br.readLine());
        if(D != root){
            deleteNode(root);
            dfs(root);
        }
        System.out.println(ans);
    }
    static void dfs(int index){
        if(nodes[index].child.size() == 0){
            ans++;
            return;
        }
        for(Node c : nodes[index].child){
            dfs(c.index);
        }
    }

    static void deleteNode(int index){
        for(Node c : nodes[index].child){
            if(c.index == D){
                nodes[index].child.remove(c);
                return;
            }
            deleteNode(c.index);
        }
    }

    static class Node {
        int index;
        ArrayList<Node> child = new ArrayList<>();

        Node(int index) {
            this.index = index;
        }
    }
}
