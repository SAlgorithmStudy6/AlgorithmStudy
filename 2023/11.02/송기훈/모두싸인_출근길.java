package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 모두싸인_출근길 {

    static class Node implements Comparable<Node> {

        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            if (this.start != o.start) {
                return this.start - o.start;
            } else {
                return this.end - o.end;
            }
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", this.start, this.end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodeList.add(new Node(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        Collections.sort(nodeList);

        // 노드를 정리
        ArrayList<Node> jump = new ArrayList<>();
        int start = nodeList.get(0).start;
        int end = nodeList.get(0).end;
        for (int i = 1; i < N; i++) {
            if (end >= nodeList.get(i).start) {
                end = Math.max(end, nodeList.get(i).end);
            } else {
                jump.add(new Node(start, end));
                start = nodeList.get(i).start;
                end = nodeList.get(i).end;
            }
        }
        jump.add(new Node(start, end));

        int range = 0;
        int index = 0;
        for (int i = 0; i < jump.size(); i++) {
            if (range >= jump.get(i).start) {
                index = i;
                range = Math.max(range, jump.get(i).end + jump.get(i).end - jump.get(i).start);
            }
        }

        System.out.println(jump.get(index).end);

    }

}
