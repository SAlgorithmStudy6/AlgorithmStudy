import java.util.*;
import java.io.*;

public class 크레인인형뽑기게임 {

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
		int answer = 0;

		Stack<Integer> stack = new Stack<>();
		stack.add(0);
		for(int j=0; j<moves.length; j++) {
			for (int i = 0; i < board.length; i++) {
				int doll = board[i][moves[j]-1];
				if (doll != 0) { // 인형이면 뽑아줌
					board[i][moves[j]-1] = 0;
					int temp = stack.peek();
					stack.add(doll);
					if(temp == doll) {	//peek이랑 같으면 poll
						stack.pop();
						stack.pop();
						answer += 2;
					}
					break;
				}
			}
		}
		
		System.out.println(answer);
	}
}
