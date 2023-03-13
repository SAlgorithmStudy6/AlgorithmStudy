# DFS 풀이

import sys

input = sys.stdin.readline
sys.setrecursionlimit(1000000)

N = int(input())
visited = [False] * (N + 1)
answer = [0] * (N + 1)
E = [[] for _ in range(N + 1)]
for i in range(N - 1):
    S, D = map(int, input().split())
    E[S].append(D)
    E[D].append(S)


def dfs(E, v, visited):
    visited[v] = True
    for i in E[v]:
        if not visited[i]:
            answer[i] = v
            dfs(E, i, visited)


dfs(E, 1, visited)

for i in range(2, N + 1):
    print(answer[i])