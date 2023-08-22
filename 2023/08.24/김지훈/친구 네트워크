

import java.io.*;
import java.util.*;


class Main{

    static HashMap<String,Integer> relations;
    static int F;
    static int[] counts;
    static int[] parents;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        while(TC-->0){
            F = Integer.parseInt(br.readLine());
            counts = new int[F*2+1];
            parents = new int[F*2+1];
            relations = new HashMap<>(F*2+1);

            for(int i=0;i<counts.length;i++) {
                counts[i] = 1;
                parents[i] = i;
            }

            int number = 0;
            for(int i=0;i<F;i++){
                st = new StringTokenizer(br.readLine());
                String s1 = st.nextToken();
                String s2 = st.nextToken();
                //처음 나온 경우
                if(!relations.containsKey(s1))
                    relations.put(s1,number++);
                if(!relations.containsKey(s2))
                    relations.put(s2,number++);
                int parent = Math.min(relations.get(s1),relations.get(s2));
                int child = Math.max(relations.get(s1),relations.get(s2));

                System.out.println(union(parent,child));
            }
        }
    }
    static int union(int x,int y){
        x = find(x);
        y = find(y);

        if(x!=y){
            parents[y] = x;
            counts[x]+=counts[y];
        }
        return counts[x];
    }
    static int find(int x){
        if(parents[x]==x) return x;
        return parents[x]=find(parents[x]);
    }
}
