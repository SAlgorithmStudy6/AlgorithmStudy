import sys
from collections import deque

sys.stdin = open("01.30/input.txt", "r", encoding="UTF-8")

n = int(input())
graph = [[] for _ in range(n+1)]
visited = [False for _ in range(n+1)]
answer = -1

for _ in range(n-1):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)

q = deque(map(int, input().split()))

def dfs(queue):
    global answer
    nowNum = queue.popleft()
    if not queue:
        answer = 1
        print("out")
        return
    visited[nowNum] = True
    for i in range(len(graph[nowNum])):
        # queue가 없을 때 값을 꺼내다가 index 에러가 생김
        if queue:
            nextNum = queue[0]
        else:
            continue
        # 연결된 노드이고 방문하지 않았는가?
        if nextNum in graph[nowNum] and not visited[nextNum]:
            print("go")
            dfs(queue)
    if answer != 1:
        answer = 0
    return

# 시작이 1이 아니면 오답
if q[0] != 1:
    print(0)
else:
    dfs(q)
    print(answer)
