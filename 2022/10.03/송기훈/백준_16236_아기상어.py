from collections import deque
import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

# 상 하 좌 우
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

n = int(input())

sharkLoc = [0, 0]
sharkState = [2, 0] # nowSize, eatCount
matrix = []


def findFishDistance(y, x, sharkSize):
    distance = [[0] * n for _ in range(n)]
    visited = [[False] * n for _ in range(n)]
    q = deque()
    q.append([y, x])
    visited[y][x] = True
    candidate = []
    while q:
        nowY, nowX = q.popleft()
        for i in range(4):
            nY = nowY + dy[i]
            nX = nowX + dx[i]
            if 0 <= nY < n and 0 <= nX < n and visited[nY][nX] == False:
                # 이동 가능한 위치
                if matrix[nY][nX] <= sharkSize:
                    q.append([nY, nX])
                    visited[nY][nX] = True
                    distance[nY][nX] = distance[nowY][nowX] + 1
                    # 먹을 수 있는 물고기는 배열에 추가
                    if 0 < matrix[nY][nX] < sharkSize:
                        candidate.append([nY, nX, distance[nY][nX]])
    return sorted(candidate, key=lambda x: (x[2], x[0], x[1]))


for y in range(n):
    temp = list(map(int, input().split()))
    for x in range(n):
        if temp[x] == 9:
            sharkLoc = [y, x]
            temp[x] = 0
    matrix.append(temp)


time = 0
while True:
    fishList = findFishDistance(sharkLoc[0], sharkLoc[1], sharkState[0])
    # 찾아온 물고기가 없다
    if len(fishList) == 0:
        break

    nY, nX, distance = fishList.pop(0)

    time += distance

    # 원래 있던 곳, 앞으로 갈 곳을 0으로 초기화
    matrix[sharkLoc[0]][sharkLoc[1]] = 0
    matrix[nY][nX] = 0

    # 아기 상어 위치 옮기기
    sharkLoc = [nY, nX]

    # eatCount 하나 올려주기
    sharkState[1] += 1
    # 현재 크기와 eatCount가 같을 때
    if sharkState[0] == sharkState[1]:
        sharkState[0] += 1
        sharkState[1] = 0

print(time)
