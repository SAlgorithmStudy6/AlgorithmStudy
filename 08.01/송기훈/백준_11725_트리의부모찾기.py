import sys
sys.setrecursionlimit(10**9)

N = int(sys.stdin.readline())

tree = [[] for _ in range(N+1)]
visited = [0] * (N+1)

for _ in range(N-1):
    a, b = map(int, sys.stdin.readline().split())
    tree[a].append(b)
    tree[b].append(a)


def dfs(x):
    for i in tree[x]:
        if visited[i] == 0:
            visited[i] = x
            dfs(i)


dfs(1)

for i in range(2, N+1):
    print(visited[i])
