import sys
from collections import deque

sys.stdin = open("09.26/벽돌깨기.txt", "r", encoding="UTF-8")

# 상 하 좌 우
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

T = int(input())


def bfs(y, x, arr):
    q = deque()
    q.append([y, x, arr[y][x]])
    arr[y][x] = 0
    while q:
        nowY, nowX, popCount = q.popleft()
        for d in range(4):
            for delta in range(1, popCount):
                nY = nowY + delta * dy[d]
                nX = nowX + delta * dx[d]
                if 0 <= nY < h and 0 <= nX < w and arr[nY][nX] != 0:
                    if arr[nY][nX] != 1:
                        q.append([nY, nX, arr[nY][nX]])
                    arr[nY][nX] = 0

    # 0 정리하기
    for a in range(w):
        sortZero = []
        for b in range(h-1, -1, -1):
            if arr[b][a] > 0:
                sortZero.append(arr[b][a])
        for b in range(h-1, -1, -1):
            if sortZero:
                value = sortZero.pop(0)
                arr[b][a] = value
            else:
                arr[b][a] = 0


def dfs(depth, arr):
    global minBlock
    if depth == n:
        count = 0
        for y in range(h):
            for x in range(w):
                if arr[y][x] > 0:
                    count += 1
        minBlock = min(minBlock, count)
        return

    for x in range(w):
        # 새로 복사
        tempArr = [arr[i][:] for i in range(h)]
        tempY = 0

        # 0이 아닌 y를 찾는다
        while tempY < h and tempArr[tempY][x] == 0:
            tempY += 1

        # 찾았을 때
        if tempY < h:
            bfs(tempY, x, tempArr)

        dfs(depth+1, tempArr)


for test_case in range(1, T+1):
    n, w, h = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(h)]
    minBlock = 10**9
    dfs(0, matrix)

    print("#{} {}".format(test_case, minBlock))
