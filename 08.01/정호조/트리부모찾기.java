package pro11725;
import java.util.*;

public class 트리부모찾기 {
	
    static ArrayList<Integer>[] list;
    static int[] parents;
    static boolean[] check;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        list = new ArrayList[n+1];
        parents = new int[n+1];
        check = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<Integer>();
        }
        for (int j = 1; j <n ; j++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            list[a].add(b);
            list[b].add(a);
        }

        for (int k = 1; k <=n ; k++) {
            if(!check[k]){
                dfs(k);
            }
        }
        for (int i = 2; i <=n ; i++) {
            System.out.println(parents[i]);
        }

    }

    private static void dfs(int v){
        if(check[v]){
            return;
        }
        check[v] =true;
        for (int vv: list[v]) {
            if(!check[vv]){
                parents[vv] = v;
                dfs(vv);
            }

        }

    }

}