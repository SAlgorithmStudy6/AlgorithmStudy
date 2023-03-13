import sys
from collections import deque

input = sys.stdin.readline

N, K = map(int, input().split())

visited = [0] * 100001


def bfs(n):
    q = deque([n])
    while q:
        n = q.popleft()
        if n == K:
            return visited[n]
        for moveN in (n - 1, n + 1, n * 2):
            if (0 <= moveN <= 100000) and (visited[moveN] == 0):
                visited[moveN] = visited[n] + 1
                q.append(moveN)


time = bfs(N)

print(time)

"""
dfs는 모든 경로를 탐색하고 결과를 내놓으니까 bfs 사용
visited를 만들어서 해당 칸에 터치되는 횟수 = 시간을 체크...
visited는 100001에서 -1 되는 경우도 있으므로 100001개
K에 도달하면 visited의 값을 리턴
a -> a1 a2 a3 -> a11 a12 a13 / a21 a22 a23 / a31 a32 a33
"""
