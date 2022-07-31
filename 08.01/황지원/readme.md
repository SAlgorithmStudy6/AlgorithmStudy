## 바닥장식

0. static으로 int n, int m, boolean visited, char plank[50][50], int result 선언해주고  
1. [main] n, m 입력받기  
2. [main] plank에 입력하기  
3. [main] 2중 for문에서 자기자신 방문 true해주고 bfs함수 돌리기
4. [bfs] '-' 모양이면 좌우만 비교해서 방문 false면 true로 바꿔주기
5. [bfs] '|' 모양이면 상하만 비교해서 방문 false면 true로 바꿔주기
6. [bfs] 다시 bfs함수에 다음 x, y값으로 nx, ny를 넣어서 재귀함수 실행 
