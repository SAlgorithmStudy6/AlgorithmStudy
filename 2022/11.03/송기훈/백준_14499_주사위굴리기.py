import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

maxY, maxX, startY, startX, n = map(int, input().split())

matrix = []

for _ in range(maxY):
    matrix.append(list(map(int, input().split())))

moveList = list(map(int, input().split()))

# 동 서 북 남
dy = [0, 0, -1, 1]
dx = [1, -1, 0, 0]
dice = [0, 0, 0, 0, 0, 0]
y, x = startY, startX
for i in range(n):
    d = moveList[i] - 1
    nY = y + dy[d]
    nX = x + dx[d]

    if 0 <= nY < maxY and 0 <= nX < maxX:
        if d == 0:
            dice[0], dice[2], dice[3], dice[5] = dice[3], dice[0], dice[5], dice[2]
        elif d == 1:
            dice[0], dice[2], dice[3], dice[5] = dice[2], dice[5], dice[0], dice[3]
        elif d == 2:
            dice[0], dice[1], dice[4], dice[5] = dice[4], dice[0], dice[5], dice[1]
        elif d == 3:
            dice[0], dice[1], dice[4], dice[5] = dice[1], dice[5], dice[0], dice[4]

        # 이동한 칸에 0이 쓰여져 있으면 복사
        if matrix[nY][nX] == 0:
            matrix[nY][nX] = dice[5]
        # 아니면 바닥면에 복사하고 칸은 0으로
        else:
            dice[5] = matrix[nY][nX]
            matrix[nY][nX] = 0

        y, x = nY, nX
        print(dice[0])
