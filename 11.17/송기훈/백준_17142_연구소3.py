import sys
from collections import deque

sys.stdin = open("input.txt", "r", encoding="UTF-8")

# 우 하 좌 상
dy = [0, 1, 0, -1]
dx = [1, 0, -1, 0]

n, m = map(int, input().split())

matrix = []
virus = []
visited = [[-1 for _ in range(n)] for _ in range(n)]
result = 10 ** 9

# 벽은 -1, 바이러스는 따로 위치 저장
wall = 0
for y in range(n):
    temp = list(map(int, input().split()))
    for x in range(n):
        if (temp[x] == 1):
            temp[x] = -1
            wall += 1
        elif (temp[x] == 2):
            virus.append((y, x))
    matrix.append(temp)

virusCount = len(virus)


def pickM(index, now):
    global result
    if (len(now) == m):
        result = min(result, bfs(now))
        return

    for i in range(index, virusCount):
        pickM(i + 1, now + [virus[i]])


def bfs(selected):
    result = 0
    tempVisited = [item[:] for item in visited]

    q = deque(selected)
    for s in selected:
        tempVisited[s[0]][s[1]] = 0

    while q:
        y, x = q.popleft()
        for d in range(4):
            nY = y + dy[d]
            nX = x + dx[d]
            if (0 <= nY < n and 0 <= nX < n and tempVisited[nY][nX] == -1):
                # 빈 칸일 때
                if matrix[nY][nX] == 0:
                    tempVisited[nY][nX] = tempVisited[y][x] + 1
                    result = max(result, tempVisited[nY][nX])
                    q.append([nY, nX])
                # 바이러스 일 때
                elif matrix[nY][nX] == 2:
                    tempVisited[nY][nX] = tempVisited[y][x] + 1
                    q.append([nY, nX])

    flatten = []
    for temp in tempVisited:
        flatten += temp
    if flatten.count(-1) != wall:
        return 10**9
    return result


pickM(0, [])

if result == 10 ** 9:
    print(-1)
else:
    print(result)
