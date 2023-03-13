import java.util.ArrayList;
import java.util.List;

public class PRG_시험장_나누기 {
    private static final int INF = Integer.MAX_VALUE / 4;
    private static final int MAX_SECTION = 10001;
    static List<Node>[] list;
    static int[][] cost;

    static class Node {
        int data;
        int left;
        int right;

        public Node(int data, int left, int right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    } // End of Node class

    public static void main(String[] args) {
        PRG_시험장_나누기 s = new PRG_시험장_나누기();

        int k = 1;
        int[] num = {6, 9, 7, 5};
        int[][] links = {{-1, -1}, {-1, -1}, {-1, 0}, {2, 1}};
        System.out.println(s.solution(k, num, links));
    } // End of main

    public int solution(int k, int[] num, int[][] links) {
        int size = num.length;
        list = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            list[i] = new ArrayList<>();
        }

        int sum = 0;
        boolean[] check = new boolean[size];
        for (int i = 0; i < size; i++) {
            int left = links[i][0];
            int right = links[i][1];

            if (left != -1) {
                check[left] = true;
            }

            if (right != -1) {
                check[right] = true;
            }
            list[i].add(new Node(num[i], left, right));
            sum += num[i];
        }

        int root = -1;
        for (int i = 0; i < size; i++) {
            if (!check[i]) {
                root = i;
            }
        }

        int left = sum / k;
        int right = sum;
        if (left == right) {
            return right;
        }

        while (left < right) {
            int mid = (left + right) / 2;
            cost = new int[size][2];
            traversal(root, mid);
            if (cost[root][0] <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    } // End of solution

    private static void traversal(int pos, int w) {
        Node curNode = list[pos].get(0);
        int data = curNode.data;
        int left = curNode.left;
        int right = curNode.right;

        if (left != -1) {
            traversal(left, w);
        }

        if (right != -1) {
            traversal(right, w);
        }

        // 리프노드 일 때
        if (left == -1 && right == -1) {
            if (data <= w) {
                cost[pos][0] = 1;
                cost[pos][1] = data;
            } else {
                cost[pos][0] = MAX_SECTION;
                cost[pos][1] = INF;
            }
        } else if (left != -1 && right != -1) {
            // full 노드 일 때
            if (data + cost[left][1] + cost[right][1] <= w) {
                cost[pos][0] = cost[left][0] + cost[right][0] - 1;
                cost[pos][1] = data + cost[left][1] + cost[right][1];
            } else if (data + Math.min(cost[left][1], cost[right][1]) <= w) {
                cost[pos][0] = cost[left][0] + cost[right][0];
                cost[pos][1] = data + Math.min(cost[left][1], cost[right][1]);
            } else if (data <= w) {
                cost[pos][0] = cost[left][0] + cost[right][0] + 1;
                cost[pos][1] = data;
            } else {
                cost[pos][0] = MAX_SECTION;
                cost[pos][1] = INF;
            }
        } else {
            // 왼쪽 자식만 있는 경우
            if (right == -1) {
                if (data + cost[left][1] <= w) {
                    cost[pos][0] = cost[left][0];
                    cost[pos][1] = data + cost[left][1];
                } else if (data <= w) {
                    cost[pos][0] = cost[left][0] + 1;
                    cost[pos][1] = data;
                } else {
                    cost[pos][0] = MAX_SECTION;
                    cost[pos][1] = INF;
                }
            }

            // 오른쪽 자식만 있는 경우
            if (left == -1) {
                if (data + cost[right][1] <= w) {
                    cost[pos][0] = cost[right][0];
                    cost[pos][1] = data + cost[right][1];
                } else if (data <= w) {
                    cost[pos][0] = cost[right][0] + 1;
                    cost[pos][1] = data;
                } else {
                    cost[pos][0] = MAX_SECTION;
                    cost[pos][1] = INF;
                }
            }
        }


    } // End of traversal
} // End of Solution class
