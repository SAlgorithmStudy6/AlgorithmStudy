import java.util.*;

public class Solution2 {
    static Stack<Integer> ingredientStack = new Stack<>();
    static int totalBurger;

    public static void main(String[] args) {
        Solution2 s = new Solution2();

        int[] ingredient = {1, 3, 2, 1, 2, 1, 3, 1, 2};
        System.out.println(s.solution(ingredient));
    } // End of main

    // 포장하는 햄버거의 개수를 return
    public int solution(int[] ingredient) {
        totalBurger = 0;
        int len = ingredient.length;
        for (int i = 0; i < len; i++) {
            ingredientStack.add(ingredient[i]);

            if (ingredientStack.size() >= 4) {
                checking();
            }
        }

        return totalBurger;
    } // End of solution

    private static void checking() {
        Stack<Integer> tempStack = new Stack<>(); // 햄버거 순서가 맞지 않을 가능성을 대비해서 미리 배열을 만들어 둠
        boolean isPossible = true;

        for (int j = 0; j < 4; j++) {
            int num = ingredientStack.peek();

            if (j == 0) {
                if (num == 1) {
                    tempStack.add(ingredientStack.pop()); // 맞으면 값을 빼서 temp에 저장
                } else {
                    isPossible = false;
                    break;
                }
            } else if (j == 1) {
                if (num == 3) {
                    tempStack.add(ingredientStack.pop()); // 맞으면 값을 빼서 temp에 저장
                } else {
                    isPossible = false;
                    break;
                }
            } else if (j == 2) {
                if (num == 2) {
                    tempStack.add(ingredientStack.pop()); // 맞으면 값을 빼서 temp에 저장
                } else {
                    isPossible = false;
                    break;
                }
            } else {
                if (num == 1) {
                    tempStack.add(ingredientStack.pop()); // 맞으면 값을 빼서 temp에 저장
                    totalBurger++;
                    return;
                } else {
                    isPossible = false;
                }
            }
        }

        // 만들 수 없는 경우,
        if (!isPossible) {
            // 덱을 다시 원래대로
            while (!tempStack.isEmpty()) {
                ingredientStack.add(tempStack.pop());
            }
        }

    } // End of checking
} // End of Solution class