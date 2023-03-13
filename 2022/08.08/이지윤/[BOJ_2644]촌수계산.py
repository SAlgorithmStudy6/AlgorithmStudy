import sys
sys.setrecursionlimit(300000)

n = int(input())
t1, t2 = map(int, input().split())

graph = [[] for _ in range(n+1)]    # index 0 제거
visited = [False]*(n+1)
result = []

for _ in range(int(input())):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)

def dfs(node, count) :
    if node == t2 :
        print(count)
        sys.exit()
    visited[node] = True
    for v in graph[node]:
        if not visited[v]:
            dfs(v, count+1)

dfs(t1, 0)
print(-1)
